/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api;

import com.google.protobuf.util.Timestamps;
import com.strongsalt.crypto.exception.StrongSaltKdfException;
import com.strongsalt.crypto.exception.StrongSaltKeyException;
import com.strongsalt.crypto.exception.StrongSaltSRPException;
import com.strongsalt.crypto.kdf.StrongSaltKDF;
import com.strongsalt.crypto.key.StrongSaltKey;
import com.strongsalt.crypto.pake.srp.SRP;
import com.strongsalt.crypto.pake.srp.SRPSession;
import com.strongsalt.crypto.pake.srp.Verifier;
import com.strongsalt.strongdoc.sdk.api.responses.*;
import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient;
import com.strongsalt.strongdoc.sdk.exceptions.StrongDocServiceException;
import com.strongsalt.strongdoc.sdk.proto.Account;
import io.grpc.StatusRuntimeException;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * This class can be used to perform actions that are related to an account like organizations and users.
 */
public class StrongDocAccount {
    // ---------------------------------- RegisterOrganizationReq ----------------------------------

    /**
     * Registers a new organization. A new administrator user will also be created.
     * New users can be added using this administrator account.
     *
     * @param client          The StrongDoc client used to call this API.
     * @param orgName         The organization name to create.
     * @param orgAddress      The organization address.
     * @param adminName       The organization administrator name.
     * @param adminPassword   The organization administrator password.
     * @param adminEmail      The organization administrator email.
     * @param sharableOrgs    The list of sharable organization IDs.
     * @param multiLevelShare The ability to "reshare" a document that has been shared with to another user.
     * @param source          How was the organization registered.
     * @param sourceData      Any data related to registration from the source (in JSON).
     * @return The register organization response.
     * @throws StrongDocServiceException on StrongDocServiceException errors
     */
    public RegisterOrganizationResponse registerOrganization(final StrongDocServiceClient client,
                                                             final String orgName,
                                                             final String orgEmail,
                                                             final String orgAddress,
                                                             final String adminName,
                                                             final String adminPassword,
                                                             final String adminEmail,
                                                             final String[] sharableOrgs,
                                                             final Boolean multiLevelShare,
                                                             final String source,
                                                             final String sourceData)
            throws StrongDocServiceException {
        Account.RegisterOrganizationReq.Builder regOrg = Account.RegisterOrganizationReq.newBuilder();
        regOrg.setOrgName(orgName);
        regOrg.setOrgEmail(orgEmail);
        regOrg.setOrgAddr(orgAddress);
        regOrg.setUserName(adminName);
        regOrg.setAdminEmail(adminEmail);
        regOrg.setMultiLevelShare(multiLevelShare);
        regOrg.setSource(source);
        regOrg.setSourceData(sourceData);
        for (String sharableOrg : sharableOrgs) {
            regOrg.addSharableOrgs(sharableOrg);
        }

        // generate loginData
        Account.RegisterAuthData.Builder authData = Account.RegisterAuthData.newBuilder();
        authData.setAuthType(Account.AuthType.AUTH_SRP);
        authData.setAuthVersion(1);
        try {
            SRPSession srpSession = SRP.ONE.getSession();
            Verifier verifier = srpSession.verifier("whatever".getBytes(), adminPassword.getBytes());
            String[] encodedVerifier = verifier.encode();
            authData.setSrpVerifier(encodedVerifier[1]);
        } catch (StrongSaltSRPException e) {
            throw new StrongDocServiceException("Fail to init SRP", e);
        }
        regOrg.setAdminAuthData(authData.build());

        // generate client keys
        try {
            StrongSaltKDF userKdf = StrongSaltKDF.GenerateKDF(StrongSaltKDF.KDFType.PBKDF2, StrongSaltKey.KeyType.SECRETBOX);
            byte[] kdfMetaBytes = userKdf.serialize();
            StrongSaltKey userPasswordKey = userKdf.GenerateKey(adminPassword.getBytes());
            StrongSaltKey userKey = StrongSaltKey.GenerateKey(StrongSaltKey.KeyType.X25519);
            byte[] userPublicKeyBytes = userKey.serializePublic();
            byte[] userFullKeyBytes = userKey.serialize();
            byte[] encUserPriKeyBytes = userPasswordKey.encrypt(userFullKeyBytes);

            StrongSaltKey orgKey = StrongSaltKey.GenerateKey(StrongSaltKey.KeyType.X25519);
            byte[] orgPublicKeyBytes = orgKey.serializePublic();
            byte[] orgFullKeyBytes = orgKey.serialize();
            byte[] encOrgPriKeyBytes = userKey.encrypt(orgFullKeyBytes);

            regOrg.setKdfMetadata(Base64.getUrlEncoder().encodeToString(kdfMetaBytes));
            regOrg.setEncOrgPriKey(Base64.getUrlEncoder().encodeToString(encOrgPriKeyBytes));
            regOrg.setOrgPubKey(Base64.getUrlEncoder().encodeToString(orgPublicKeyBytes));
            regOrg.setEncUserPriKey(Base64.getUrlEncoder().encodeToString(encUserPriKeyBytes));
            regOrg.setUserPubKey(Base64.getUrlEncoder().encodeToString(userPublicKeyBytes));
        } catch (StrongSaltKdfException | StrongSaltKeyException e) {
            throw new StrongDocServiceException("Fail to generate client keys", e);
        }

        final Account.RegisterOrganizationReq req = regOrg.build();
        final Account.RegisterOrganizationResp res = client.getBlockingStubNoAuth().registerOrganization(req);
        return new RegisterOrganizationResponse(res.getOrgID(), res.getUserID());
    }

    // ---------------------------------- RegisterUserReq ----------------------------------

    /**
     * Creates new user if it doesn't already exist.
     * Trying to create a user with an existing username throws an error.
     * This requires an administrator privilege.
     *
     * @param client   The StrongDoc client used to call this API.
     * @param username The username of the new user.
     * @param email    The email of the new user.
     * @param password The password of the new user.
     * @return RegisterUserResponse
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public RegisterUserResponse registerUser(final StrongDocServiceClient client,
                                             final String orgID,
                                             final String username,
                                             final String email,
                                             final String password,
                                             final String invitationCode)
            throws StrongDocServiceException {

        final Account.RegisterUserReq.Builder registerUserReqBuilder = Account.RegisterUserReq.newBuilder();
        registerUserReqBuilder.setEmail(email);
        registerUserReqBuilder.setInvitationCode(invitationCode);
        registerUserReqBuilder.setOrgID(orgID);
        registerUserReqBuilder.setUserName(username);

        // generate client keys
        try {
            StrongSaltKDF userKdf = StrongSaltKDF.GenerateKDF(StrongSaltKDF.KDFType.PBKDF2, StrongSaltKey.KeyType.SECRETBOX);
            byte[] kdfMetaBytes = userKdf.serialize();
            StrongSaltKey userPasswordKey = userKdf.GenerateKey(password.getBytes());
            StrongSaltKey userKey = StrongSaltKey.GenerateKey(StrongSaltKey.KeyType.X25519);
            byte[] userPublicKeyBytes = userKey.serializePublic();
            byte[] userFullKeyBytes = userKey.serialize();
            byte[] encUserPriKeyBytes = userPasswordKey.encrypt(userFullKeyBytes);

            registerUserReqBuilder.setKdfMetadata(Base64.getUrlEncoder().encodeToString(kdfMetaBytes));
            registerUserReqBuilder.setEncUserPriKey(Base64.getUrlEncoder().encodeToString(encUserPriKeyBytes));
            registerUserReqBuilder.setUserPubKey(Base64.getUrlEncoder().encodeToString(userPublicKeyBytes));
        } catch (StrongSaltKdfException | StrongSaltKeyException e) {
            throw new StrongDocServiceException("Fail to generate client keys", e);
        }

        // generate loginData
        Account.RegisterAuthData.Builder authData = Account.RegisterAuthData.newBuilder();
        authData.setAuthType(Account.AuthType.AUTH_SRP);
        authData.setAuthVersion(1);
        try {
            SRPSession srpSession = SRP.ONE.getSession();
            Verifier verifier = srpSession.verifier("whatever".getBytes(), password.getBytes());
            String[] encodedVerifier = verifier.encode();
            authData.setSrpVerifier(encodedVerifier[1]);
        } catch (StrongSaltSRPException e) {
            throw new StrongDocServiceException("Fail to init SRP", e);
        }
        registerUserReqBuilder.setAuthData(authData.build());


        final Account.RegisterUserResp res = client.getBlockingStub().registerUser(registerUserReqBuilder.build());
        return new RegisterUserResponse(res.getSuccess(), res.getOrgID(), res.getUserID());
    }

    // ---------------------------------- RemoveUserReq ----------------------------------

    /**
     * Removes user from the organization.
     * This requires an administrator privilege.
     *
     * @param client The StrongDoc client used to call this API.
     * @param userID ID of the user.
     * @return The number of users that were removed.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public long removeUser(final StrongDocServiceClient client,
                           final String userID)
            throws StatusRuntimeException {

        final Account.RemoveUserReq req = Account.RemoveUserReq.newBuilder()
                .setUserID(userID)
                .build();
        final Account.RemoveUserResp res = client.getBlockingStub().removeUser(req);
        return res.getCount();
    }

    // ---------------------------------- PromoteUserReq ----------------------------------

    /**
     * Promotes a regular user to administrator.
     * This requires an administrator privilege.
     *
     * @param client        The StrongDoc client used to call this API.
     * @param userIDOrEmail ID or email of the user.
     * @return Whether the operation was a success.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public Boolean promoteUser(final StrongDocServiceClient client,
                               final String userIDOrEmail)
            throws StatusRuntimeException {
        return true;

//        final Account.PreparePromoteUserReq preparePromoteUserReq = Account.PreparePromoteUserReq.newBuilder()
//                .setUserID(userIDOrEmail)
//                .build();
//        final Account.PreparePromoteUserResp preparePromoteRes = client.getBlockingStub().preparePromoteUser(preparePromoteUserReq);
//
//        Encryption.EncryptedKey encryptedOrgKey = preparePromoteRes.getEncOrgKey();
//        byte[] encOrgPriKeyBytes = Base64.getUrlDecoder().decode(encryptedOrgKey.getEncKey());
//        byte[] encUserPriKeyBytes = Base64.getUrlDecoder().decode(preparePromoteRes.getEncUserPriKey());
//        byte[] newUserPubKeyBytes = Base64.getUrlDecoder().decode(preparePromoteRes.getNewUserPubKey());
//
//
//        Encryption.EncryptedKey.Builder encryptedKeyBuilder = Encryption.EncryptedKey.newBuilder();
//        try {
//            byte[] decryptedUserKeyBytes = client.userDecrypt(encUserPriKeyBytes);
//
//            StrongSaltKey adminKey = StrongSaltKey.Deserialize(decryptedUserKeyBytes);
//            byte[] orgPriKeyBytes = adminKey.decrypt(encOrgPriKeyBytes);
//
//            StrongSaltKey userKey = StrongSaltKey.Deserialize(newUserPubKeyBytes);
//            byte[] reEncryptedOrgKey = userKey.encrypt(orgPriKeyBytes);
//            encryptedKeyBuilder.setEncKey(Base64.getUrlEncoder().encodeToString(reEncryptedOrgKey))
//                    .setEncryptorID(encryptedOrgKey.getEncryptorID())
//                    .setOwnerID(encryptedOrgKey.getOwnerID())
//                    .setKeyID(encryptedOrgKey.getKeyID());
//
//
//        } catch (StrongSaltKeyException e) {
//            e.printStackTrace();
//        }
//
//
//        final Account.PromoteUserReq promoteUserReq = Account.PromoteUserReq.newBuilder()
//                .setEncryptedKey(encryptedKeyBuilder.build())
//                .build();
//        final Account.PromoteUserResp res = client.getBlockingStub().promoteUser(promoteUserReq);
//        return res.getSuccess();
    }

    // ---------------------------------- DemoteUserReq ----------------------------------

    /**
     * Demotes an administrator to regular user level.
     * This requires an administrator privilege.
     *
     * @param client The StrongDoc client used to call this API.
     * @param userID ID of the user.
     * @return Whether the operation was a success.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public Boolean demoteUser(final StrongDocServiceClient client,
                              final String userID)
            throws StatusRuntimeException {

        final Account.DemoteUserReq req = Account.DemoteUserReq.newBuilder()
                .setUserID(userID)
                .build();
        final Account.DemoteUserResp res = client.getBlockingStub().demoteUser(req);
        return res.getSuccess();
    }

    // ---------------------------------- LoginReq ----------------------------------

    /**
     * Verifies the user and organization identity, and returns a JWT token for future API use.
     *
     * @param client        The StrongDoc client used to call this API.
     * @param userIDorEmail The login user ID or Email
     * @param userPassword  The login user password
     * @param orgID         The login organization ID
     * @return void
     * @throws StrongDocServiceException on StrongDocServiceException errors
     */
    public void login(final StrongDocServiceClient client, final String orgID,
                      final String userIDorEmail, final String userPassword)
            throws StrongDocServiceException {
        client.login(orgID, userIDorEmail, userPassword);
    }

    // ---------------------------------- LogoutReq ----------------------------------

    /**
     * Invalidates the JWT token and ending the current user session.
     *
     * @param client The StrongDoc client used to call this API.
     * @return Status of the logout.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public String logout(final StrongDocServiceClient client)
            throws StatusRuntimeException {
        return client.logout();
    }

    // ---------------------------------- InviteUserReq ----------------------------------
    public boolean InviteUser(final StrongDocServiceClient client, String userEmail, int expireTime) throws StrongDocServiceException {
        final Account.InviteUserReq inviteUserReq = Account.InviteUserReq.newBuilder()
                .setEmail(userEmail)
                .setExpireTime(expireTime)
                .build();
        final Account.InviteUserResp inviteUserResp = client.getBlockingStub().inviteUser(inviteUserReq);
        return inviteUserResp.getSuccess();
    }

    // ---------------------------------- ListInvitationsReq ----------------------------------
    public List<InvitationInfo> ListInvitations(final StrongDocServiceClient client) throws StrongDocServiceException {
        final Account.ListInvitationsReq listInvitationsReq = Account.ListInvitationsReq.newBuilder().build();
        final Account.ListInvitationsResp inviteUserResp = client.getBlockingStub().listInvitations(listInvitationsReq);


        final List<InvitationInfo> res = new ArrayList<>();

        for (Account.Invitation invitation : inviteUserResp.getInvitationsList()) {
            res.add(new InvitationInfo(invitation.getEmail(), invitation.getCreateTime(), invitation.getExpireTime()));
        }
        return res;
    }

    // ---------------------------------- RevokeInvitationReq ----------------------------------
    public boolean revokeInvitation(final StrongDocServiceClient client, String userEmail) throws StrongDocServiceException {
        final Account.RevokeInvitationReq revokeInvitationReq = Account.RevokeInvitationReq.newBuilder()
                .setEmail(userEmail)
                .build();
        final Account.RevokeInvitationResp revokeInvitationResp = client.getBlockingStub().revokeInvitation(revokeInvitationReq);
        return revokeInvitationResp.getSuccess();
    }

    // ---------------------------------- ListUsersReq ----------------------------------

    /**
     * Lists users in the organization.
     *
     * @param client The StrongDoc client used to call this API.
     * @return Array of objects containing data for each user in the organization.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public ArrayList<OrgUserInfo> listUsers(final StrongDocServiceClient client)
            throws StatusRuntimeException {

        final Account.ListUsersReq req = Account.ListUsersReq.newBuilder().build();

        final Account.ListUsersResp res = client.getBlockingStub().listUsers(req);
        final ArrayList<OrgUserInfo> orgUserList = new ArrayList<OrgUserInfo>();
        for (Account.ListUsersResp.OrgUser orgUser : res.getOrgUsersList()) {
            orgUserList.add(new OrgUserInfo(orgUser.getUserID(), orgUser.getUserName(), "", "", orgUser.getIsAdmin()));
        }
        return orgUserList;
    }

    // ---------------------------------- RemoveOrganizationReq ----------------------------------

    /**
     * Removes an organization, deleting all data stored with the organization.
     * This requires an administrator privilege.
     *
     * @param client The StrongDoc client used to call this API.
     * @param force  If this is false, removal will fail if there
     *               are still data stored with the organization.
     *               This prevents accidental deletion.
     * @return The remove organization response.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public RemoveOrganizationResponse removeOrganization(final StrongDocServiceClient client,
                                      final Boolean force)
            throws StatusRuntimeException {

        final Account.RemoveOrganizationReq req = Account.RemoveOrganizationReq.newBuilder()
                .setForce(force)
                .build();
        final Account.RemoveOrganizationResp res = client.getBlockingStub().removeOrganization(req);

        final RemoveOrganizationResponse response = new RemoveOrganizationResponse(res.getSuccess(), res.getPostponed());
        return response;
    }

    // ---------------------------------- AddSharableOrgReq ----------------------------------

    /**
     * Adds a sharable Organization.
     * This requires an administrator privilege.
     *
     * @param client The StrongDoc client used to call this API.
     * @param orgID  The organization ID
     * @return Whether the operation was a success.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public Boolean addSharableOrg(final StrongDocServiceClient client,
                                  final String orgID)
            throws StatusRuntimeException {

        final Account.AddSharableOrgReq req = Account.AddSharableOrgReq.newBuilder()
                .setNewOrgID(orgID)
                .build();

        final Account.AddSharableOrgResp res = client.getBlockingStub().addSharableOrg(req);
        return res.getSuccess();
    }

    // ---------------------------------- RemoveSharableOrgReq ----------------------------------

    /**
     * Removes a sharable Organization.
     * This requires an administrator privilege.
     *
     * @param client The StrongDoc client used to call this API.
     * @param orgID  The organization ID
     * @return Whether the operation was a success.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public Boolean removeSharableOrg(final StrongDocServiceClient client,
                                     final String orgID)
            throws StatusRuntimeException {

        final Account.RemoveSharableOrgReq req = Account.RemoveSharableOrgReq.newBuilder()
                .setRemoveOrgID(orgID)
                .build();
        final Account.RemoveSharableOrgResp res = client.getBlockingStub().removeSharableOrg(req);
        return res.getSuccess();
    }

    // ---------------------------------- SetMultiLevelSharingReq ----------------------------------

    /**
     * Sets Multi-level Sharing.
     * This requires an administrator privilege.
     *
     * @param client   The StrongDoc client used to call this API.
     * @param isEnable Indicates whether to enable or disable Multi-level Sharing
     * @return Whether the operation was a success.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public Boolean setMultiLevelSharing(final StrongDocServiceClient client,
                                        final Boolean isEnable)
            throws StatusRuntimeException {

        final Account.SetMultiLevelSharingReq req = Account.SetMultiLevelSharingReq.newBuilder()
                .setEnable(isEnable)
                .build();

        final Account.SetMultiLevelSharingResp res = client.getBlockingStub()
                .setMultiLevelSharing(req);
        return res.getSuccess();
    }

    // ---------------------------------- GetAccountInfoReq ----------------------------------

    /**
     * Obtains information about the account
     *
     * @param client The StrongDoc client used to call this API.
     * @return The account info response.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public AccountInfoResponse getAccountInfo(final StrongDocServiceClient client)
            throws StatusRuntimeException {

        final Account.GetAccountInfoReq req = Account.GetAccountInfoReq.newBuilder().build();

        final Account.GetAccountInfoResp res = client.getBlockingStub().getAccountInfo(req);

        final AccountInfoResponse response = new AccountInfoResponse(
                res.getOrgID(), res.getOrgEmail(), res.getOrgAddress(), res.getMultiLevelShare());

        response.setSubscription(res.getSubscription().getType(), res.getSubscription().getStatus());

        for (int index = 0; index < res.getPaymentsCount(); index++) {
            Account.Payment payment = res.getPayments(index);

            Date billedAt = new Date(Timestamps.toMillis(payment.getBilledAt()));
            Date periodStart = new Date(Timestamps.toMillis(payment.getPeriodStart()));
            Date periodEnd = new Date(Timestamps.toMillis(payment.getPeriodEnd()));

            response.addPayment(billedAt, periodStart, periodEnd,
                    payment.getAmount(), payment.getStatus());
        }

        for (int i = 0; i < res.getSharableOrgsCount(); i++) {
            response.addSharableOrg(res.getSharableOrgs(i));
        }

        return response;
    }

        // ---------------------------------- SetAccountInfoReq ----------------------------------

    /**
     * Sets information about the account's organization
     *
     * @param client The StrongDoc client used to call this API.
     * @param orgEmail The organization's new email address. Must be a valid email address.
     * @param orgAddress The organization address
     * @return Whether or not the change was a success
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public boolean setAccountInfo(final StrongDocServiceClient client,
                                              final String orgEmail,
                                              final String orgAddress)
            throws StatusRuntimeException {

        final Account.SetAccountInfoReq req = Account.SetAccountInfoReq.newBuilder()
                .setOrgEmail(orgEmail)
                .setOrgAddress(orgAddress)
                .build();

        final Account.SetAccountInfoResp res = client.getBlockingStub().setAccountInfo(req);

        return res.getSuccess();
    }

    // ---------------------------------- GetUserInfoReq ----------------------------------

    /**
     * Obtains information about the user
     *
     * @param client The StrongDoc client used to call this API.
     * @return The user info response.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public OrgUserInfo getUserInfo(final StrongDocServiceClient client)
            throws StatusRuntimeException {

        final Account.GetUserInfoReq req = Account.GetUserInfoReq.newBuilder().build();

        final Account.GetUserInfoResp res = client.getBlockingStub().getUserInfo(req);

        final OrgUserInfo response = new OrgUserInfo(
                res.getUserID(), res.getUserName(), res.getOrgID(), res.getEmail(), res.getIsAdmin());

        return response;
    }

    // ---------------------------------- SetUserInfoReq ----------------------------------

    /**
     * Sets information about the user
     *
     * @param client The StrongDoc client used to call this API.
     * @param name The user's new name. Cannot be an empty string.
     * @param email The user's new email address. Must be a valid email address.
     * @return Whether or not the change was a success.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public boolean setUserInfo(final StrongDocServiceClient client,
                                                final String name,
                                                final String email)
            throws StatusRuntimeException {

        final Account.SetUserInfoReq req = Account.SetUserInfoReq.newBuilder()
                .setName(name)
                .setEmail(email)
                .build();

        final Account.SetUserInfoResp res = client.getBlockingStub().setUserInfo(req);

        return res.getSuccess();
    }

    // ---------------------------------- ChangeUserPasswordReq ----------------------------------

    /**
     * Changes the user password
     *
     * @param client          The StrongDoc client used to call this API.
     * @param currentPassword The user's current password.
     * @param newPassword     The user's new password.
     * @return void
     * @throws StatusRuntimeException on gRPC errors, StrongDocServiceException on client service errors
     * @see StatusRuntimeException io.grpc
     */
    public void changeUserPassword(final StrongDocServiceClient client,
                                   final String currentPassword,
                                   final String newPassword)
            throws StatusRuntimeException, StrongDocServiceException {
        client.changePassword(currentPassword, newPassword);
    }
}
