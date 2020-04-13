/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api;

import com.strongsalt.strongdoc.sdk.api.responses.AccountInfoResponse;
import com.strongsalt.strongdoc.sdk.api.responses.OrgUserInfo;
import com.strongsalt.strongdoc.sdk.api.responses.RegisterOrganizationResponse;
import com.strongsalt.strongdoc.sdk.api.responses.RemoveOrganizationResponse;
import com.strongsalt.strongdoc.sdk.client.JwtCallCredential;
import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient;
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
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    private RegisterOrganizationResponse registerOrganization(final StrongDocServiceClient client,
                                                              final String orgName,
                                                              final String orgAddress,
                                                              final String adminName,
                                                              final String adminPassword,
                                                              final String adminEmail,
                                                              final String[] sharableOrgs,
                                                              final Boolean multiLevelShare,
                                                              final String source,
                                                              final String sourceData)
            throws StatusRuntimeException {

        final Account.RegisterOrganizationReq.Builder regOrg = Account.RegisterOrganizationReq.newBuilder();
        regOrg.setOrgName(orgName);
        regOrg.setOrgAddr(orgAddress);
        regOrg.setUserName(adminName);
        regOrg.setPassword(adminPassword);
        regOrg.setEmail(adminEmail);
        regOrg.setMultiLevelShare(multiLevelShare);
        regOrg.setSource(source);
        regOrg.setSourceData(sourceData);
        for (String sharableOrg : sharableOrgs) {
            regOrg.addSharableOrgs(sharableOrg);
        }
        final Account.RegisterOrganizationReq req = regOrg.build();

        final Account.RegisterOrganizationResp res = client.getBlockingStub().registerOrganization(req);
        return new RegisterOrganizationResponse(res.getOrgID(), res.getUserID());
    }

    // ---------------------------------- RegisterUserReq ----------------------------------

    /**
     * Creates new user if it doesn't already exist.
     * Trying to create a user with an existing username throws an error.
     * This requires an administrator privilege.
     *
     * @param client   The StrongDoc client used to call this API.
     * @param token    The user JWT token.
     * @param username The username of the new user.
     * @param password The password of the new user.
     * @param email    The email of the new user.
     * @param isAdmin  Whether the new user should be an organization administrator.
     * @return ID of the user.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public String registerUser(final StrongDocServiceClient client,
                               final String token,
                               final String username,
                               final String password,
                               final String email,
                               final Boolean isAdmin)
            throws StatusRuntimeException {

        final Account.RegisterUserReq req = Account.RegisterUserReq.newBuilder()
                .setUserName(username)
                .setPassword(password)
                .setEmail(email)
                .setAdmin(isAdmin)
                .build();

        final Account.RegisterUserResp res = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).registerUser(req);
        return res.getUserID();
    }

    // ---------------------------------- RemoveUserReq ----------------------------------

    /**
     * Removes user from the organization.
     * This requires an administrator privilege.
     *
     * @param client The StrongDoc client used to call this API.
     * @param token  The user JWT token.
     * @param userID ID of the user.
     * @return The number of users that were removed.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public long removeUser(final StrongDocServiceClient client,
                           final String token,
                           final String userID)
            throws StatusRuntimeException {

        final Account.RemoveUserReq req = Account.RemoveUserReq.newBuilder()
                .setUserID(userID)
                .build();

        final Account.RemoveUserResp res = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).removeUser(req);
        return res.getCount();
    }

    // ---------------------------------- PromoteUserReq ----------------------------------

    /**
     * Promotes a regular user to administrator.
     * This requires an administrator privilege.
     *
     * @param client The StrongDoc client used to call this API.
     * @param token  The user JWT token.
     * @param userID ID of the user.
     * @return Whether the operation was a success.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public Boolean promoteUser(final StrongDocServiceClient client,
                               final String token,
                               final String userID)
            throws StatusRuntimeException {

        final Account.PromoteUserReq req = Account.PromoteUserReq.newBuilder()
                .setUserID(userID)
                .build();

        final Account.PromoteUserResp res = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).promoteUser(req);
        return res.getSuccess();
    }

    // ---------------------------------- DemoteUserReq ----------------------------------

    /**
     * Demotes an administrator to regular user level.
     * This requires an administrator privilege.
     *
     * @param client The StrongDoc client used to call this API.
     * @param token  The user JWT token.
     * @param userID ID of the user.
     * @return Whether the operation was a success.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public Boolean demoteUser(final StrongDocServiceClient client,
                              final String token,
                              final String userID)
            throws StatusRuntimeException {

        final Account.DemoteUserReq req = Account.DemoteUserReq.newBuilder()
                .setUserID(userID)
                .build();

        final Account.DemoteUserResp res = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).demoteUser(req);
        return res.getSuccess();
    }

    // ---------------------------------- LoginReq ----------------------------------

    /**
     * Verifies the user and organization identity, and returns a JWT token for future API use.
     *
     * @param client       The StrongDoc client used to call this API.
     * @param userID       The login user ID
     * @param userPassword The login user password
     * @param orgID        The login organization ID
     * @return The JWT token used to authenticate user/org when using StrongDoc APIs.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public String login(final StrongDocServiceClient client, final String orgID,
                        final String userID, final String userPassword)
            throws StatusRuntimeException {

        final Account.LoginReq req = Account.LoginReq.newBuilder()
                .setUserID(userID)
                .setPassword(userPassword)
                .setOrgID(orgID)
                .build();

        final Account.LoginResp res = client.getBlockingStub().login(req);
        return res.getToken();
    }

    // ---------------------------------- LogoutReq ----------------------------------

    /**
     * Invalidates the JWT token and ending the current user session.
     *
     * @param client The StrongDoc client used to call this API.
     * @param token  The user JWT token.
     * @return Status of the logout.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public String logout(final StrongDocServiceClient client,
                         final String token)
            throws StatusRuntimeException {

        final Account.LogoutReq req = Account.LogoutReq.newBuilder().build();

        final Account.LogoutResp res = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).logout(req);
        return res.getStatus();
    }

    // ---------------------------------- ListUsersReq ----------------------------------

    /**
     * Lists users in the organization.
     *
     * @param client The StrongDoc client used to call this API.
     * @param token  The user JWT token.
     * @return Array of objects containing data for each user in the organization.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public ArrayList<OrgUserInfo> listUsers(final StrongDocServiceClient client,
                                            final String token)
            throws StatusRuntimeException {

        final Account.ListUsersReq req = Account.ListUsersReq.newBuilder().build();

        final Account.ListUsersResp res = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).listUsers(req);
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
     * @param token  The user JWT token.
     * @param force  If this is false, removal will fail if there
     *               are still data stored with the organization.
     *               This prevents accidental deletion.
     * @return The remove organization response.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public RemoveOrganizationResponse removeOrganization(final StrongDocServiceClient client, final String token,
                                      final Boolean force)
            throws StatusRuntimeException {

        final Account.RemoveOrganizationReq req = Account.RemoveOrganizationReq.newBuilder()
                .setForce(force)
                .build();

        final Account.RemoveOrganizationResp res = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).removeOrganization(req);

        final RemoveOrganizationResponse response = new RemoveOrganizationResponse(res.getSuccess(), res.getPostponed());
        return response;
    }

    // ---------------------------------- AddSharableOrgReq ----------------------------------

    /**
     * Adds a sharable Organization.
     * This requires an administrator privilege.
     *
     * @param client The StrongDoc client used to call this API.
     * @param token  The user JWT token.
     * @param orgID  The organization ID
     * @return Whether the operation was a success.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public Boolean addSharableOrg(final StrongDocServiceClient client,
                                  final String token,
                                  final String orgID)
            throws StatusRuntimeException {

        final Account.AddSharableOrgReq req = Account.AddSharableOrgReq.newBuilder()
                .setNewOrgID(orgID)
                .build();

        final Account.AddSharableOrgResp res = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).addSharableOrg(req);
        return res.getSuccess();
    }

    // ---------------------------------- RemoveSharableOrgReq ----------------------------------

    /**
     * Removes a sharable Organization.
     * This requires an administrator privilege.
     *
     * @param client The StrongDoc client used to call this API.
     * @param token  The user JWT token.
     * @param orgID  The organization ID
     * @return Whether the operation was a success.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public Boolean removeSharableOrg(final StrongDocServiceClient client,
                                     final String token,
                                     final String orgID)
            throws StatusRuntimeException {

        final Account.RemoveSharableOrgReq req = Account.RemoveSharableOrgReq.newBuilder()
                .setRemoveOrgID(orgID)
                .build();

        final Account.RemoveSharableOrgResp res = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).removeSharableOrg(req);
        return res.getSuccess();
    }

    // ---------------------------------- SetMultiLevelSharingReq ----------------------------------

    /**
     * Sets Multi-level Sharing.
     * This requires an administrator privilege.
     *
     * @param client   The StrongDoc client used to call this API.
     * @param token    The user JWT token.
     * @param isEnable Indicates whether to enable or disable Multi-level Sharing
     * @return Whether the operation was a success.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public Boolean setMultiLevelSharing(final StrongDocServiceClient client,
                                        final String token,
                                        final Boolean isEnable)
            throws StatusRuntimeException {

        final Account.SetMultiLevelSharingReq req = Account.SetMultiLevelSharingReq.newBuilder()
                .setEnable(isEnable)
                .build();

        final Account.SetMultiLevelSharingResp res = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).setMultiLevelSharing(req);
        return res.getSuccess();
    }

    // ---------------------------------- GetAccountInfoReq ----------------------------------

    /**
     * Obtains information about the account
     *
     * @param client The StrongDoc client used to call this API.
     * @param token  The user JWT token.
     * @return The account info response.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public AccountInfoResponse getAccountInfo(final StrongDocServiceClient client,
                                              final String token)
            throws StatusRuntimeException {

        final Account.GetAccountInfoReq req = Account.GetAccountInfoReq.newBuilder().build();

        final Account.GetAccountInfoResp res = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).getAccountInfo(req);

        final AccountInfoResponse response = new AccountInfoResponse(
                res.getOrgID(), res.getOrgAddress(), res.getMultiLevelShare());

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

    // ---------------------------------- GetUserInfoReq ----------------------------------

    /**
     * Obtains information about the user
     *
     * @param client The StrongDoc client used to call this API.
     * @param token  The user JWT token.
     * @return The user info response.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public OrgUserInfo getUserInfo(final StrongDocServiceClient client,
                                              final String token)
            throws StatusRuntimeException {

        final Account.GetUserInfoReq req = Account.GetUserInfoReq.newBuilder().build();

        final Account.GetUserInfoResp res = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).getUserInfo(req);

        final OrgUserInfo response = new OrgUserInfo(
                res.getUserID(), res.getUserName(), res.getOrgID(), res.getEmail(), res.getIsAdmin());

        return response;
    }
}

