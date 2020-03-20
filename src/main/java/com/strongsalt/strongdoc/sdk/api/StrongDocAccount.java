/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api;

import com.strongsalt.strongdoc.sdk.api.responses.OrgUserInfo;
import com.strongsalt.strongdoc.sdk.api.responses.RegisterOrganizationResponse;
import com.strongsalt.strongdoc.sdk.client.JwtCallCredential;
import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient;
import com.strongsalt.strongdoc.sdk.proto.Account;
import io.grpc.StatusRuntimeException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class can be used to perform actions that are related to an account like organizations and users.
 */
public class StrongDocAccount {
    // ---------------------------------- RegisterOrganizationReq ----------------------------------

    /**
     * Registers a new organization. A new administrator user will also be created.
     * New users can be added using this administrator account.
     *
     * @param client        The StrongDoc client used to call this API.
     * @param orgName       The organization name to create.
     * @param orgAddress    The organization address.
     * @param adminName     The organization administrator name.
     * @param adminPassword The organization administrator password.
     * @param adminEmail    The organization administrator email.
     * @param source        How was the organization registered.
     * @param sourceData    Any data related to registration from the source (in JSON).
     * @return The register organization response.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public RegisterOrganizationResponse registerOrganization(final StrongDocServiceClient client,
                                                             final String orgName,
                                                             final String orgAddress,
                                                             final String adminName,
                                                             final String adminPassword,
                                                             final String adminEmail,
                                                             final String source,
                                                             final String sourceData)
            throws StatusRuntimeException {

        final Account.RegisterOrganizationReq req = Account.RegisterOrganizationReq.newBuilder()
                .setOrgName(orgName)
                .setOrgAddr(orgAddress)
                .setUserName(adminName)
                .setPassword(adminPassword)
                .setEmail(adminEmail)
                .setMultiLevelShare(false)
                .setSource(source)
                .setSourceData(sourceData)
                .build();

        final Account.RegisterOrganizationResp res = client.getBlockingStub().registerOrganization(req);
        return new RegisterOrganizationResponse(res.getOrgID(), res.getUserID());
    }

    // ---------------------------------- RegisterUserReq ----------------------------------

    /**
     * Creates new user if it doesn't already exist.
     * Trying to create a user with an existing username throws an error.
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
            orgUserList.add(new OrgUserInfo(orgUser.getUserName(), orgUser.getUserID(), orgUser.getIsAdmin()));
        }
        return orgUserList;
    }

    // ---------------------------------- RemoveOrganizationReq ----------------------------------

    /**
     * Removes an organization, deleting all data stored with the organization.
     * This requires an administrator priviledge.
     *
     * @param client The StrongDoc client used to call this API.
     * @param token  The user JWT token.
     * @param force  If this is false, removal will fail if there
     *               are still data stored with the organization.
     *               This prevents accidental deletion.
     * @return Whether the operation was a success.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public Boolean removeOrganization(final StrongDocServiceClient client, final String token,
                                      final Boolean force)
            throws StatusRuntimeException {

        final Account.RemoveOrganizationReq req = Account.RemoveOrganizationReq.newBuilder()
                .setForce(force)
                .build();

        final Account.RemoveOrganizationResp res = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).removeOrganization(req);
        return res.getSuccess();
    }

    // ---------------------------------- AddSharableOrgReq ----------------------------------

    /**
     * Adds a sharable Organization.
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
     * @return A map of subscription type and status.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public Map<String, String> getAccountInfo(final StrongDocServiceClient client,
                                              final String token)
            throws StatusRuntimeException {

        final Account.GetAccountInfoReq req = Account.GetAccountInfoReq.newBuilder().build();

        final Account.GetAccountInfoResp res = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).getAccountInfo(req);
        Map<String, String> infoMap = new HashMap<String, String>();
        infoMap.put("type", res.getSubscription().getType());
        infoMap.put("status", res.getSubscription().getStatus());
        return infoMap;
    }
}
