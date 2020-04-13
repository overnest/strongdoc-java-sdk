package com.strongsalt.strongdoc.sdk.api;

import com.strongsalt.strongdoc.sdk.api.responses.RemoveOrganizationResponse;
import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient;
import com.strongsalt.strongdoc.sdk.proto.Account;
import io.netty.handler.ssl.SslContext;

import java.nio.file.Path;
import java.nio.file.FileSystems;

import static com.strongsalt.strongdoc.sdk.api.StrongDocTestConstants.*;

public class StrongDocTestSetup {

    //private com.strongsalt.strongdoc.sdk.api.StrongDocTestConstants sdConst;

    public StrongDocServiceClient init() throws Exception {
        final StrongDocServiceClient client =
                new StrongDocServiceClient(HOST, PORT, CERT_PATH);

        return client;
    }

    public void registerOrganization(StrongDocServiceClient client,
                                     final String orgName,
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
        System.out.printf("Registered Organziation.  OrgID: %s, UserID: %s\n\n", res.getOrgID(), res.getUserID());
    }

    public void removeOrganization(final StrongDocServiceClient client, final String token)
            throws Exception {
        final StrongDocAccount account = new StrongDocAccount();

        final boolean isForce = true;
        RemoveOrganizationResponse removeOrgResponse = account.removeOrganization(
                client, token, isForce);

        final boolean isSuccess = removeOrgResponse.isSuccess();
        final boolean isPostponed = removeOrgResponse.isPostponed();
        System.out.printf("Organziation removed? %b, postponed? %b\n\n", isSuccess, isPostponed);
    }

    public String getToken(final StrongDocServiceClient client, final String orgName,
                           final String userEmail, final String userPassword)
            throws Exception {
        final StrongDocAccount account = new StrongDocAccount();

        final String token = account.login(client, orgName, userEmail, userPassword);
        System.out.printf("Token has been obtained for org %s user %s.\n\n", orgName, userEmail);

        return token;
    }
}
