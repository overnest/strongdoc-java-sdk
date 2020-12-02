package com.strongsalt.strongdoc.sdk.api;

import com.strongsalt.strongdoc.sdk.api.responses.RegisterOrganizationResponse;
import com.strongsalt.strongdoc.sdk.api.responses.RegisterUserResponse;
import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient;

import static com.strongsalt.strongdoc.sdk.api.StrongDocTestConstants.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class provides some set up functions before testing
 * - register organization
 * - hard remove organization
 */

public class StrongDocTestSetupAndTearDown {
    // init StrongDocServiceClient
    public static StrongDocServiceClient initClient() throws Exception {
        return StrongDocServiceClient.createStrongDocServiceClient(location);
    }

    // initialize test data
    public static TestData initData(int numOfOrgs, int numOfUsers) {
        TestOrg[] testOrgs = new TestOrg[numOfOrgs];
        TestUser[][] testUsers = new TestUser[numOfOrgs][numOfUsers];

        for (int i = 0; i < numOfOrgs; i++) {
            testOrgs[i] = new TestOrg.Builder()
                    .setOrgName(String.format("testOrg_%d", i))
                    .setOrgAddr(String.format("testOrg_%d_address", i))
                    .setOrgEmail(String.format("testOrg_%d@email.com", i))
                    .build();

            for (int j = 0; j < numOfUsers; j++) {
                testUsers[i][j] = new TestUser.Builder()
                        .setUserName(String.format("alice_%d_%d", i, j))
                        .setUserEmail(String.format("alice_%d_%d@email.com", i, j))
                        .setPassword("password")
                        .build();
            }
        }

        return new TestData.Builder()
                .setTestOrgs(testOrgs)
                .setTestUsers(testUsers)
                .build();
    }

    // register org and user(s)
    public static TestData registerOrgAndUser(StrongDocServiceClient client, int numOfOrgs, int numOfUsers) throws Exception {
        final StrongDocAccount account = new StrongDocAccount();
        if (numOfOrgs <= 0 || numOfUsers <= 0) {
            throw new Exception("numOfOrgs or numOfUsers invalid");
        }
        TestData data = initData(numOfOrgs, numOfUsers);
        TestOrg testOrgs[] = data.testOrgs;
        TestUser testUsers[][] = data.testUsers;
        for (int i = 0; i < numOfOrgs; i++) {
            // register org and admin
            RegisterOrganizationResponse registerOrgResp = account.registerOrganization(
                    client,
                    testOrgs[i].orgName, testOrgs[i].orgEmail, testOrgs[i].orgAddr,
                    testUsers[i][0].userName, testUsers[i][0].password, testUsers[i][0].userEmail,
                    new String[]{}, false, SOURCE, SOURCE_DATA);
            testUsers[i][0].userID = registerOrgResp.getUserID();
        }
        if (numOfUsers == 1) return data;
        for (int i = 0; i < numOfOrgs; i++) {
            boolean loginRes = account.login(client, testOrgs[i].orgName, testUsers[i][0].userEmail, testUsers[i][0].password);
            assertTrue(loginRes);
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
        return data;
    }

    // hard remove organizations
    public static void hardRemoveOrgs(TestOrg[] testOrgs) throws Exception {
        InternalServiceManager manager = InternalServiceManager.getInstance();
        String token = manager.superUserLogin();
        for (TestOrg testOrg : testOrgs) {
            manager.hardRemoveOrganization(token, testOrg.orgName);
        }
        manager.superUserLogout(token);
        InternalServiceManager.shutDownService();
    }

    // hard remove one organization
    public static void hardRemoveOrg(TestOrg testOrg) throws Exception {
        hardRemoveOrgs(new TestOrg[]{testOrg});
    }
}
