package com.strongsalt.strongdoc.sdk.api;

import com.strongsalt.strongdoc.sdk.api.responses.RegisterOrganizationResponse;
import com.strongsalt.strongdoc.sdk.api.responses.RemoveOrganizationResponse;
import com.strongsalt.strongdoc.sdk.client.StrongDocManager;
import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient;
import com.strongsalt.strongdoc.sdk.exceptions.StrongDocServiceException;

/**
 * This class provides some set up functions before testing
 * - register organization
 * - login and get token
 * - soft remove organization
 */

public class StrongDocTestSetup {
    private StrongDocAccount account = new StrongDocAccount();

    // todo need to remove
    public StrongDocServiceClient init() throws Exception {
        return StrongDocManager.getInstance().getStrongDocClient();
    }

    public RegisterOrganizationResponse registerOrganization(final String orgName,
                                     final String orgEmail,
                                     final String orgAddress,
                                     final String adminName,
                                     final String adminPassword,
                                     final String adminEmail,
                                     final String[] sharableOrgs,
                                     final Boolean multiLevelShare,
                                     final String source,
                                     final String sourceData) throws StrongDocServiceException {
        RegisterOrganizationResponse res = account.registerOrganization(orgName, orgEmail, orgAddress, adminName, adminPassword, adminEmail, sharableOrgs, multiLevelShare, source, sourceData);
        System.out.printf("Registered Organziation.  OrgID: %s, UserID: %s\n\n", res.getOrgID(), res.getUserID());
        return res;
    }

    public void removeOrganization(final String token)
            throws Exception {
        final boolean isForce = true;
        RemoveOrganizationResponse removeOrgResponse = account.removeOrganization(token, isForce);

        final boolean isSuccess = removeOrgResponse.isSuccess();
        final boolean isPostponed = removeOrgResponse.isPostponed();
        System.out.printf("Organziation removed? %b, postponed? %b\n\n", isSuccess, isPostponed);
    }

    public String getToken(final String orgName,
                           final String userEmail, final String userPassword) throws StrongDocServiceException {
        final String token = account.login(orgName, userEmail, userPassword);
        System.out.printf("Token has been obtained for org %s user %s.\n\n", orgName, userEmail);

        return token;
    }

    public void exit() throws StrongDocServiceException {
        StrongDocManager.getInstance().close();
    }
}
