/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api;


import com.strongsalt.strongdoc.sdk.api.responses.RegisterOrganizationResponse;
import com.strongsalt.strongdoc.sdk.client.JwtCallCredential;
import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient;
import com.strongsalt.strongdoc.sdk.proto.Account;
import io.grpc.StatusRuntimeException;


/**
 * This class can be used to perform actions that are related to an account like organizations and users.
 */
public class StrongDocAccount {
    /**
     * Registers a new organization. A new administrator user will also be created.
     * New users can be added using this administrator account.
     * @param client        The StrongDoc client used to call this API.
     * @param orgName       The organization name to create
     * @param orgAddress    The organization address
     * @param adminName     The organization administrator name
     * @param adminPassword The organization administrator password
     * @param adminEmail    The organization administrator email
     * @return The register organization response.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public RegisterOrganizationResponse registerOrganization(final StrongDocServiceClient client,
                                                             final String orgName,
                                                             final String orgAddress,
                                                             final String adminName,
                                                             final String adminPassword,
                                                             final String adminEmail)
            throws StatusRuntimeException {

        final Account.RegisterOrganizationRequest req = Account.RegisterOrganizationRequest.newBuilder()
                .setOrgName(orgName)
                .setOrgAddr(orgAddress)
                .setUserName(adminName)
                .setPassword(adminPassword)
                .setEmail(adminEmail)
                .setMultiLevelShare(false)
                .build();

        final Account.RegisterOrganizationResponse res = client.getBlockingStub().registerOrganization(req);
        return new RegisterOrganizationResponse(res.getOrgID(), res.getUserID());
    }

    /**
     * Verifies the user and organization identity, and returns a JWT token for future API use.
     * @param client       The StrongDoc client used to call this API.
     * @param userID       The login user ID
     * @param userPassword The login user password
     * @param orgID        The login organization ID
     * @return The JWT token used to authenticate user/org when using StrongDoc APIs
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public String login(final StrongDocServiceClient client, final String orgID,
                        final String userID, final String userPassword)
            throws StatusRuntimeException {

        final Account.LoginRequest req = Account.LoginRequest.newBuilder()
                .setUserID(userID)
                .setPassword(userPassword)
                .setOrgID(orgID)
                .build();

        final Account.LoginResponse res = client.getBlockingStub().login(req);
        return res.getToken();
    }

    /**
     * Removes an organization, deleting all data stored with the organization.
     * This requires an administrator priviledge.
     * @param client The StrongDoc client used to call this API.
     * @param token  The user JWT token
     * @param force  If this is false, removal will fail if there
     *               are still data stored with the organization.
     *               This prevents accidental deletion.
     * @return Whether the removal was a success
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public Boolean removeOrganization(final StrongDocServiceClient client, final String token,
                                      final Boolean force)
            throws StatusRuntimeException {

        final Account.RemoveOrganizationRequest req = Account.RemoveOrganizationRequest.newBuilder()
                .setForce(force)
                .build();

        final Account.RemoveOrganizationResponse res = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).removeOrganization(req);
        return res.getSuccess();
    }
}
