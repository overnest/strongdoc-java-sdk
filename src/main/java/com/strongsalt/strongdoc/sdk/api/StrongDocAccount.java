/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api;

import com.strongsalt.crypto.exception.StrongSaltKdfException;
import com.strongsalt.crypto.exception.StrongSaltKeyException;
import com.strongsalt.crypto.kdf.StrongSaltKDF;
import com.strongsalt.crypto.key.StrongSaltKey;
import com.strongsalt.strongdoc.sdk.api.responses.AccountInfoResponse;
import com.strongsalt.strongdoc.sdk.api.responses.OrgUserInfo;
import com.strongsalt.strongdoc.sdk.api.responses.RegisterOrganizationResponse;
import com.strongsalt.strongdoc.sdk.api.responses.RemoveOrganizationResponse;
import com.strongsalt.strongdoc.sdk.client.JwtCallCredential;
import com.strongsalt.strongdoc.sdk.client.StrongDocManager;
import com.strongsalt.strongdoc.sdk.exceptions.StrongDocAccountException;
import com.strongsalt.strongdoc.sdk.exceptions.StrongDocServiceException;
import com.strongsalt.strongdoc.sdk.proto.Account;
import io.grpc.StatusRuntimeException;

import com.google.protobuf.util.Timestamps;

import java.util.ArrayList;
import java.util.Date;

/**
 * This class can be used to perform actions that are related to an account like organizations and users.
 */
public class StrongDocAccount {
    // ---------------------------------- RegisterOrganizationReq ----------------------------------

    /**
     * Registers a new organization. A new administrator user will also be created.
     * New users can be added using this administrator account.
     *
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
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public RegisterOrganizationResponse registerOrganization(final String orgName,
                                                              final String orgEmail,
                                                              final String orgAddress,
                                                              final String adminName,
                                                              final String adminPassword,
                                                              final String adminEmail,
                                                              final String[] sharableOrgs,
                                                              final Boolean multiLevelShare,
                                                              final String source,
                                                              final String sourceData)
            throws StatusRuntimeException, StrongDocServiceException {

        final Account.RegisterOrganizationReq.Builder regOrg = Account.RegisterOrganizationReq.newBuilder();
        regOrg.setOrgName(orgName);
        regOrg.setOrgEmail(orgEmail);
        regOrg.setOrgAddr(orgAddress);
        regOrg.setUserName(adminName);
        regOrg.setPassword(adminPassword);
        regOrg.setAdminEmail(adminEmail);
        regOrg.setMultiLevelShare(multiLevelShare);
        regOrg.setSource(source);
        regOrg.setSourceData(sourceData);
        for (String sharableOrg : sharableOrgs) {
            regOrg.addSharableOrgs(sharableOrg);
        }
        final Account.RegisterOrganizationReq req = regOrg.build();

        final Account.RegisterOrganizationResp res = StrongDocManager.getInstance().getStrongDocClient().getBlockingStub().registerOrganization(req);
        return new RegisterOrganizationResponse(res.getOrgID(), res.getUserID());
    }

    // ---------------------------------- RegisterUserReq ----------------------------------

    /**
     * Creates new user if it doesn't already exist.
     * Trying to create a user with an existing username throws an error.
     * This requires an administrator privilege.
     *
     * @param token    The user JWT token.
     * @param username The username of the new user.
     * @param password The password of the new user.
     * @param email    The email of the new user.
     * @param isAdmin  Whether the new user should be an organization administrator.
     * @return ID of the user.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public String registerUser(final String token,
                               final String username,
                               final String password,
                               final String email,
                               final Boolean isAdmin)
            throws StatusRuntimeException, StrongDocServiceException {

        final Account.RegisterUserReq req = Account.RegisterUserReq.newBuilder()
                .setUserName(username)
                .setPassword(password)
                .setEmail(email)
                .setAdmin(isAdmin)
                .build();

        final Account.RegisterUserResp res = StrongDocManager.getInstance().getStrongDocClient().getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).registerUser(req);
        return res.getUserID();
    }

    // ---------------------------------- RemoveUserReq ----------------------------------

    /**
     * Removes user from the organization.
     * This requires an administrator privilege.
     *
     * @param token  The user JWT token.
     * @param userID ID of the user.
     * @return The number of users that were removed.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public long removeUser(final String token,
                           final String userID)
            throws StatusRuntimeException, StrongDocServiceException {

        final Account.RemoveUserReq req = Account.RemoveUserReq.newBuilder()
                .setUserID(userID)
                .build();

        final Account.RemoveUserResp res = StrongDocManager.getInstance().getStrongDocClient().getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).removeUser(req);
        return res.getCount();
    }

    // ---------------------------------- PromoteUserReq ----------------------------------

    /**
     * Promotes a regular user to administrator.
     * This requires an administrator privilege.
     *
     * @param token  The user JWT token.
     * @param userID ID of the user.
     * @return Whether the operation was a success.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public Boolean promoteUser(final String token,
                               final String userID)
            throws StatusRuntimeException, StrongDocServiceException {

        final Account.PromoteUserReq req = Account.PromoteUserReq.newBuilder()
                .setUserID(userID)
                .build();

        final Account.PromoteUserResp res = StrongDocManager.getInstance().getStrongDocClient().getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).promoteUser(req);
        return res.getSuccess();
    }

    // ---------------------------------- DemoteUserReq ----------------------------------

    /**
     * Demotes an administrator to regular user level.
     * This requires an administrator privilege.
     *
     * @param token  The user JWT token.
     * @param userID ID of the user.
     * @return Whether the operation was a success.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public Boolean demoteUser(final String token,
                              final String userID)
            throws StatusRuntimeException, StrongDocServiceException {

        final Account.DemoteUserReq req = Account.DemoteUserReq.newBuilder()
                .setUserID(userID)
                .build();

        final Account.DemoteUserResp res = StrongDocManager.getInstance().getStrongDocClient().getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).demoteUser(req);
        return res.getSuccess();
    }

    // ---------------------------------- LoginReq ----------------------------------

    /**
     * Verifies the user and organization identity, and returns a JWT token for future API use.
     *
     * @param userID       The login user ID
     * @param userPassword The login user password
     * @param orgID        The login organization ID
     * @return The JWT token used to authenticate user/org when using StrongDoc APIs.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public String login(final String orgID,
                        final String userID, final String userPassword)
            throws StatusRuntimeException, StrongDocServiceException {
        final Account.LoginResp res = getLoginResp(orgID, userID, userPassword);
        return res.getToken();
    }

    // end to end login
    public String login(String userID, String userPassword, String orgID, String keyPassword)
            throws StrongDocAccountException, StrongDocServiceException {
        final Account.LoginResp res = getLoginResp(orgID, userID, userPassword);
        String token = res.getToken();
        System.out.println("token = "+token);
        String passwordKeyID = res.getKeyID();
        System.out.println("passwordKeyID = "+passwordKeyID);
        String kdfMeta = res.getKdfMeta();
        System.out.println("kdfMeta = "+kdfMeta);
        StrongDocManager manger = StrongDocManager.getInstance();
        manger.setPasswordKey(passwordKeyID);
        try{
            StrongSaltKDF kdf = StrongSaltKDF.Deserialize(kdfMeta.getBytes());
            StrongSaltKey passwordKey = kdf.GenerateKey(keyPassword.getBytes());
            manger.setPasswordKey(passwordKey);
        }catch(StrongSaltKdfException | StrongSaltKeyException e){
            throw new StrongDocAccountException("Failed to login, cannot generate passwordKey");
        }
        return token;
    }


    private Account.LoginResp getLoginResp(final String orgID, final String userID, final String userPassword) throws StatusRuntimeException, StrongDocServiceException {
            final Account.LoginReq req = Account.LoginReq.newBuilder()
                    .setUserID(userID)
                    .setPassword(userPassword)
                    .setOrgID(orgID)
                    .build();
            return StrongDocManager.getInstance().getStrongDocClient().getBlockingStub().login(req);
        }

    // ---------------------------------- LogoutReq ----------------------------------

    /**
     * Invalidates the JWT token and ending the current user session.
     *
     * @param token  The user JWT token.
     * @return Status of the logout.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public String logout(final String token)
            throws StatusRuntimeException, StrongDocServiceException {

        final Account.LogoutReq req = Account.LogoutReq.newBuilder().build();

        final Account.LogoutResp res = StrongDocManager.getInstance().getStrongDocClient().getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).logout(req);
        return res.getStatus();
    }

    // ---------------------------------- ListUsersReq ----------------------------------

    /**
     * Lists users in the organization.
     *
     * @param token  The user JWT token.
     * @return Array of objects containing data for each user in the organization.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public ArrayList<OrgUserInfo> listUsers(final String token)
            throws StatusRuntimeException, StrongDocServiceException {

        final Account.ListUsersReq req = Account.ListUsersReq.newBuilder().build();

        final Account.ListUsersResp res = StrongDocManager.getInstance().getStrongDocClient().getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).listUsers(req);
        final ArrayList<OrgUserInfo> orgUserList = new ArrayList<>();
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
     * @param token  The user JWT token.
     * @param force  If this is false, removal will fail if there
     *               are still data stored with the organization.
     *               This prevents accidental deletion.
     * @return The remove organization response.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public RemoveOrganizationResponse removeOrganization(final String token,
                                                         final Boolean force)
            throws StatusRuntimeException, StrongDocServiceException {

        final Account.RemoveOrganizationReq req = Account.RemoveOrganizationReq.newBuilder()
                .setForce(force)
                .build();

        final Account.RemoveOrganizationResp res = StrongDocManager.getInstance().getStrongDocClient().getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).removeOrganization(req);

        return new RemoveOrganizationResponse(res.getSuccess(), res.getPostponed());
    }

    // ---------------------------------- AddSharableOrgReq ----------------------------------

    /**
     * Adds a sharable Organization.
     * This requires an administrator privilege.
     *
     * @param token  The user JWT token.
     * @param orgID  The organization ID
     * @return Whether the operation was a success.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public Boolean addSharableOrg(final String token,
                                  final String orgID)
            throws StatusRuntimeException, StrongDocServiceException {

        final Account.AddSharableOrgReq req = Account.AddSharableOrgReq.newBuilder()
                .setNewOrgID(orgID)
                .build();

        final Account.AddSharableOrgResp res = StrongDocManager.getInstance().getStrongDocClient().getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).addSharableOrg(req);
        return res.getSuccess();
    }

    // ---------------------------------- RemoveSharableOrgReq ----------------------------------

    /**
     * Removes a sharable Organization.
     * This requires an administrator privilege.
     *
     * @param token  The user JWT token.
     * @param orgID  The organization ID
     * @return Whether the operation was a success.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public Boolean removeSharableOrg(final String token,
                                     final String orgID)
            throws StatusRuntimeException, StrongDocServiceException {

        final Account.RemoveSharableOrgReq req = Account.RemoveSharableOrgReq.newBuilder()
                .setRemoveOrgID(orgID)
                .build();

        final Account.RemoveSharableOrgResp res = StrongDocManager.getInstance().getStrongDocClient().getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).removeSharableOrg(req);
        return res.getSuccess();
    }

    // ---------------------------------- SetMultiLevelSharingReq ----------------------------------

    /**
     * Sets Multi-level Sharing.
     * This requires an administrator privilege.
     *
     * @param token    The user JWT token.
     * @param isEnable Indicates whether to enable or disable Multi-level Sharing
     * @return Whether the operation was a success.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public Boolean setMultiLevelSharing(final String token,
                                        final Boolean isEnable)
            throws StatusRuntimeException, StrongDocServiceException {

        final Account.SetMultiLevelSharingReq req = Account.SetMultiLevelSharingReq.newBuilder()
                .setEnable(isEnable)
                .build();

        final Account.SetMultiLevelSharingResp res = StrongDocManager.getInstance().getStrongDocClient().getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).setMultiLevelSharing(req);
        return res.getSuccess();
    }

    // ---------------------------------- GetAccountInfoReq ----------------------------------

    /**
     * Obtains information about the account
     *
     * @param token  The user JWT token.
     * @return The account info response.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public AccountInfoResponse getAccountInfo(final String token)
            throws StatusRuntimeException, StrongDocServiceException {

        final Account.GetAccountInfoReq req = Account.GetAccountInfoReq.newBuilder().build();

        final Account.GetAccountInfoResp res = StrongDocManager.getInstance().getStrongDocClient().getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).getAccountInfo(req);

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
     * @param token  The user JWT token.
     * @param orgEmail The organization's new email address. Must be a valid email address.
     * @param orgAddress The organization address
     * @return Whether or not the change was a success
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public boolean setAccountInfo(final String token,
                                  final String orgEmail,
                                  final String orgAddress)
            throws StatusRuntimeException, StrongDocServiceException {

        final Account.SetAccountInfoReq req = Account.SetAccountInfoReq.newBuilder()
                .setOrgEmail(orgEmail)
                .setOrgAddress(orgAddress)
                .build();

        final Account.SetAccountInfoResp res = StrongDocManager.getInstance().getStrongDocClient().getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).setAccountInfo(req);

        return res.getSuccess();
    }

    // ---------------------------------- GetUserInfoReq ----------------------------------

    /**
     * Obtains information about the user
     *
     * @param token  The user JWT token.
     * @return The user info response.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public OrgUserInfo getUserInfo(final String token)
            throws StatusRuntimeException, StrongDocServiceException {

        final Account.GetUserInfoReq req = Account.GetUserInfoReq.newBuilder().build();

        final Account.GetUserInfoResp res = StrongDocManager.getInstance().getStrongDocClient().getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).getUserInfo(req);

        return new OrgUserInfo(
                res.getUserID(), res.getUserName(), res.getOrgID(), res.getEmail(), res.getIsAdmin());
    }

    // ---------------------------------- SetUserInfoReq ----------------------------------

    /**
     * Sets information about the user
     *
     * @param token  The user JWT token.
     * @param name The user's new name. Cannot be an empty string.
     * @param email The user's new email address. Must be a valid email address.
     * @return Whether or not the change was a success.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public boolean setUserInfo(final String token,
                                final String name,
                                final String email)
            throws StatusRuntimeException, StrongDocServiceException {

        final Account.SetUserInfoReq req = Account.SetUserInfoReq.newBuilder()
                .setName(name)
                .setEmail(email)
                .build();

        final Account.SetUserInfoResp res = StrongDocManager.getInstance().getStrongDocClient().getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).setUserInfo(req);

        return res.getSuccess();
    }

    // ---------------------------------- ChangeUserPasswordReq ----------------------------------

    /**
     * Changes the user password
     *
     * @param token  The user JWT token.
     * @param currentPassword The user's current password.
     * @param newPassword The user's new password.
     * @return Whether or not the change was a success.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public boolean changeUserPassword(final String token,
                                      final String currentPassword,
                                      final String newPassword)
            throws StatusRuntimeException, StrongDocServiceException {

        final Account.ChangeUserPasswordReq req = Account.ChangeUserPasswordReq.newBuilder()
                .setOldPassword(currentPassword)
                .setNewPassword(newPassword)
                .build();

        final Account.ChangeUserPasswordResp res = StrongDocManager.getInstance().getStrongDocClient().getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).changeUserPassword(req);

        return res.getSuccess();
    }
}
