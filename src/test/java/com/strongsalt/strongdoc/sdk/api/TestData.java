package com.strongsalt.strongdoc.sdk.api;

import com.strongsalt.strongdoc.sdk.api.responses.RegisterOrganizationResponse;
import com.strongsalt.strongdoc.sdk.api.responses.RegisterUserResponse;
import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient;

import static com.strongsalt.strongdoc.sdk.api.StrongDocTestConstants.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestData {
    public TestOrg[] testOrgs;
    public TestUser[][] testUsers;

    public static class Builder {
        private TestOrg[] testOrgs;
        private TestUser[][] testUsers;

        public Builder() {
        }

        public Builder setTestOrgs(TestOrg[] testOrgs) {
            this.testOrgs = testOrgs;
            return this;
        }

        public Builder setTestUsers(TestUser[][] testUsers) {
            this.testUsers = testUsers;
            return this;
        }

        public TestData build() {
            return new TestData(this);
        }
    }

    private TestData(TestData.Builder builder) {
        this.testOrgs = builder.testOrgs;
        this.testUsers = builder.testUsers;
    }

    public void doRegistration() throws Exception {
        StrongDocServiceClient client = StrongDocTestSetup.initClient();
        final StrongDocAccount account = new StrongDocAccount();
        TestOrg testOrgs[] = this.testOrgs;
        TestUser testUsers[][] = this.testUsers;
        int numOfOrgs = testOrgs.length;
        int numOfUsers = testUsers[0].length;
        for (int i = 0; i < numOfOrgs; i++) {
            // register org and admin
            RegisterOrganizationResponse registerOrgResp = account.registerOrganization(
                    client,
                    testOrgs[i].orgName, testOrgs[i].orgEmail, testOrgs[i].orgAddr,
                    testUsers[i][0].userName, testUsers[i][0].password, testUsers[i][0].userEmail,
                    new String[]{}, false, SOURCE, SOURCE_DATA);
            testOrgs[i].orgID = registerOrgResp.getOrgID();
            testUsers[i][0].userID = registerOrgResp.getUserID();
        }
        if (numOfUsers == 1) return;
        for (int i = 0; i < numOfOrgs; i++) {
            account.login(client, testOrgs[i].orgName, testUsers[i][0].userEmail, testUsers[i][0].password);
            for (int j = 1; j < numOfUsers; j++) {
                boolean inviteUserRes = account.InviteUser(client, testUsers[i][j].userEmail, 120);
                assertTrue(inviteUserRes);
                RegisterUserResponse registerUserRes = account.registerUser(
                        client,
                        testOrgs[i].orgName,
                        testUsers[i][j].userName, testUsers[i][j].userEmail, testUsers[i][j].password, INVITATION_CODE);
                testUsers[i][j].userID = registerUserRes.getUserID();
                assertTrue(registerUserRes.getSuccess());
            }
            String status = account.logout(client);
            assertTrue(status.contains("successfully"));

        }
    }
}
