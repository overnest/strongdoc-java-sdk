package com.strongsalt.strongdoc.sdk.api;

import com.strongsalt.strongdoc.sdk.api.responses.RemoveOrganizationResponse;
import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient;
import com.strongsalt.strongdoc.sdk.proto.Account;
import static com.strongsalt.strongdoc.sdk.api.StrongDocTestConstants.*;

public class StrongDocTestSetup {
    public static StrongDocServiceClient init() throws Exception {
        return StrongDocServiceClient.createStrongDocServiceClient(location);
    }

    public static String registerOrganization(StrongDocServiceClient client,
                                     final String orgName,
                                     final String orgEmail,
                                     final String orgAddress,
                                     final String adminName,
                                     final String adminPassword,
                                     final String adminEmail,
                                     final String[] sharableOrgs,
                                     final Boolean multiLevelShare,
                                     final String source,
                                     final String sourceData) throws Exception {
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

        final Account.RegisterOrganizationResp res = client.getBlockingStubNoAuth().registerOrganization(req);
        System.out.printf("Registered Organziation.  OrgID: %s, UserID: %s\n\n", res.getOrgID(), res.getUserID());

        return res.getUserID();
    }

    public static void removeOrganization(final StrongDocServiceClient client) throws Exception {
        final StrongDocAccount account = new StrongDocAccount();

        final boolean isForce = true;
        RemoveOrganizationResponse removeOrgResponse = account.removeOrganization(
                client, isForce);

        final boolean isSuccess = removeOrgResponse.isSuccess();
        final boolean isPostponed = removeOrgResponse.isPostponed();
        System.out.printf("Organziation removed? %b, postponed? %b\n\n", isSuccess, isPostponed);
    }
}

