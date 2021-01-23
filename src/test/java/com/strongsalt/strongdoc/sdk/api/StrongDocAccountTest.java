package com.strongsalt.strongdoc.sdk.api;

import com.strongsalt.strongdoc.sdk.api.responses.*;
import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static com.strongsalt.strongdoc.sdk.api.StrongDocTestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StrongDocAccountTest {

    private TestOrg testOrg1;
    private TestOrg testOrg2;

    private TestUser[] testUsersInOrg1;
    private TestUser[] testUsersInOrg2;

    private StrongDocAccount account;
    private StrongDocServiceClient client;

    @BeforeAll
    @DisplayName("Test Setup")
    void setUp() throws Exception {
        TestData testData = StrongDocTestSetup.preTest(2, 2);
        testOrg1 = testData.testOrgs[0];
        testOrg2 = testData.testOrgs[1];
        testUsersInOrg1 = testData.testUsers[0];
        testUsersInOrg2 = testData.testUsers[1];

        // initialize client and testData
        client = StrongDocTestSetup.initClient();
        // initialize StrongDocAccount
        account = new StrongDocAccount();
    }


    @AfterAll
    @DisplayName("Test tear down")
    void tearDown() throws Exception {
        client.shutdown();
    }

    @Test
    @Order(1)
    @DisplayName("Register Organizations")
    void registerOrg() throws Exception {
        RegisterOrganizationResponse registerOrg1Resp = account.registerOrganization(
                client,
                testOrg1.orgName, testOrg1.orgEmail, testOrg1.orgAddr,
                testUsersInOrg1[0].userName, testUsersInOrg1[0].password, testUsersInOrg1[0].userEmail,
                new String[]{}, false, SOURCE, SOURCE_DATA);
        testUsersInOrg1[0].userID = registerOrg1Resp.getUserID();


        RegisterOrganizationResponse registerOrg2Resp = account.registerOrganization(
                client,
                testOrg2.orgName, testOrg2.orgEmail, testOrg2.orgAddr,
                testUsersInOrg2[0].userName, testUsersInOrg2[0].password, testUsersInOrg2[0].userEmail,
                new String[]{}, false, SOURCE, SOURCE_DATA);
        testUsersInOrg2[0].userID = registerOrg2Resp.getUserID();

        System.out.printf("registered new org \n orgID: %s\n adminUserID: %s\n", registerOrg1Resp.getOrgID(), registerOrg1Resp.getUserID());
        System.out.printf("registered new org \n orgID: %s\n adminUserID: %s\n", registerOrg2Resp.getOrgID(), registerOrg2Resp.getUserID());
    }

    @Test
    @Order(2)
    @DisplayName("testOrg1 admin login")
    void adminLogin() throws Exception {
        account.login(client, testOrg1.orgName, testUsersInOrg1[0].userEmail, testUsersInOrg1[0].password);
    }

    @Test
    @Order(3)
    @DisplayName("Invite new user")
    void inviteUser() throws Exception {
        boolean inviteUserRes = account.InviteUser(client, testUsersInOrg1[1].userEmail, 120);
        assertTrue(inviteUserRes);

        List<InvitationInfo> invitations = account.ListInvitations(client);
        assertEquals(invitations.size(), 1);

        for (InvitationInfo info : invitations) {
            System.out.printf("userEmail: %s\ncreatedAt: %s\nexpiredAt: %s\n",
                    info.getEmail(),
                    info.getCreatedAtLocalTime("America/Los_Angeles"),
                    info.getExpiredAtLocalTime("America/Los_Angeles"));
        }

        boolean revokeInvitationRes = account.revokeInvitation(client, testUsersInOrg1[1].userEmail);
        assertTrue(revokeInvitationRes);

        invitations = account.ListInvitations(client);
        assertEquals(invitations.size(), 0);
    }

    @Test
    @Order(5)
    @DisplayName("Register new user")
    void registerUser() throws Exception {
        // create invitation
        boolean inviteUserRes = account.InviteUser(client, testUsersInOrg1[1].userEmail, 120);
        assertTrue(inviteUserRes);

        // user register
        RegisterUserResponse registerUserRes = account.registerUser(client,
                testOrg1.orgName, testUsersInOrg1[1].userName, testUsersInOrg1[1].userEmail, testUsersInOrg1[1].password, INVITATION_CODE);

        testUsersInOrg1[1].userID = registerUserRes.getUserID();
        assertTrue(registerUserRes.getSuccess());
    }


    @Test
    @Order(6)
    @DisplayName("List Users")
    void listUsers() throws Exception {
        final ArrayList<OrgUserInfo> usersList = account.listUsers(client);
        System.out.printf("testOrg1 has %d users.\n\n", usersList.size());
        assertEquals(2, usersList.size());
    }

    @Test
    @Order(7)
    @DisplayName("Promote User")
    void promoteUser() throws Exception {
        final boolean success = account.promoteUser(client, testUsersInOrg1[1].userEmail);
        System.out.printf("User promoted successfully? %b\n\n", success);
        assertTrue(success);
    }

    @Test
    @Order(8)
    @DisplayName("Demote User")
    void demoteUser() throws Exception {
        final boolean success = account.demoteUser(client, testUsersInOrg1[1].userEmail);
        System.out.printf("User demoted successfully? %b\n\n", success);
        assertTrue(success);
    }

    @Test
    @Order(10)
    @DisplayName("Remove User")
    void removeUser() throws Exception {
        final long removeCount = account.removeUser(client, testUsersInOrg1[1].userID);
        System.out.printf("%d user has been removed\n\n", removeCount);
        assertTrue(removeCount == 1);
    }

    @Test
    @Order(11)
    @DisplayName("Set Multi-Level Sharing")
    void setMultiLevelSharing() throws Exception {
        final boolean success = account.setMultiLevelSharing(client, true);
        System.out.printf("Enabled multi-level sharing successfully? %b\n\n", success);
        assertTrue(success);
    }

    @Test
    @Order(12)
    @DisplayName("Add Sharable Org")
    void addSharableOrg() throws Exception {
        final boolean success = account.addSharableOrg(client, testOrg2.orgName);
        System.out.printf("Added sharable org successfully? %b\n\n", success);
        assertTrue(success);
    }

    @Test
    @Order(13)
    @DisplayName("Get Account Info")
    void getAccountInfo() throws Exception {
        final AccountInfoResponse accountInfoResponse = account.getAccountInfo(client);
        final Subscription subscription = accountInfoResponse.getSubscription();
        final String accountInfoType = subscription.getType();
        final String accountInfoStatus = subscription.getStatus();
        System.out.println("User account info:");
        System.out.println("  Subscription");
        System.out.printf("    type: %s\n", accountInfoType);
        System.out.printf("    status: %s\n\n", accountInfoStatus);

        assertEquals("Test Active", accountInfoType);
        assertEquals("Subscribed", accountInfoStatus);
        assertEquals(1, accountInfoResponse.getPayments().size());
        assertEquals(testOrg2.orgName, accountInfoResponse.getSharableOrgs().get(0));
        assertTrue(accountInfoResponse.getMultiLevelSharing());
        assertEquals(testOrg1.orgAddr, accountInfoResponse.getOrgAddress());
        assertEquals(testOrg1.orgName, accountInfoResponse.getOrgID());
        assertEquals(testOrg1.orgEmail, accountInfoResponse.getOrgEmail());
    }

    @Test
    @Order(14)
    @DisplayName("Remove Sharable Org")
    void removeSharableOrg() throws Exception {
        final boolean success = account.removeSharableOrg(client, testOrg2.orgName);
        System.out.printf("Removed sharable org successfully? %b\n\n", success);

        assertTrue(success);
    }

    @Test
    @Order(15)
    @DisplayName("Get User Info")
    void getUserInfo() throws Exception {
        final OrgUserInfo userInfoResponse = account.getUserInfo(client);
        System.out.println("User info:");
        System.out.printf("  UserID: %s\n", userInfoResponse.getUserID());
        System.out.printf("  Name: %s\n", userInfoResponse.getName());
        System.out.printf("  OrgID: %s\n\n", userInfoResponse.getOrgID());
        System.out.printf("  Email: %s\n\n", userInfoResponse.getEmail());

        assertEquals(testUsersInOrg1[0].userID, userInfoResponse.getUserID());
        assertEquals(testUsersInOrg1[0].userName, userInfoResponse.getName());
        assertEquals(testOrg1.orgName, userInfoResponse.getOrgID());
        assertEquals(testUsersInOrg1[0].userEmail, userInfoResponse.getEmail());
        assertTrue(userInfoResponse.isAdmin());
    }

    @Test
    @Order(16)
    @DisplayName("Set Account Info")
    void setAccountInfo() throws Exception {
        final String newEmail = "neworgemail@website.com";
        final String newAddress = "123 Test New St.";

        final boolean setAccountInfoResponse = account.setAccountInfo(client,
                newEmail, newAddress);

        assertTrue(setAccountInfoResponse);

        final AccountInfoResponse accountInfoResponse = account.getAccountInfo(client);

        assertEquals(newAddress, accountInfoResponse.getOrgAddress());
        assertEquals(newEmail, accountInfoResponse.getOrgEmail());

        assertTrue(account.setAccountInfo(client, testOrg1.orgEmail, testOrg2.orgAddr));
    }

    @Test
    @Order(17)
    @DisplayName("Set User Info")
    void setUserInfo() throws Exception {
        final String newName = "NewUserName";
        final String newEmail = "newuseremail@website.com";

        final boolean setUserInfoResponse = account.setUserInfo(client,
                newName, newEmail);

        assertTrue(setUserInfoResponse);

        final OrgUserInfo userInfoResponse = account.getUserInfo(client);

        assertEquals(newName, userInfoResponse.getName());
        assertEquals(newEmail, userInfoResponse.getEmail());

        assertTrue(account.setUserInfo(client, testUsersInOrg1[0].userName, testUsersInOrg1[0].userEmail));
    }

    @Test
    @Order(18)
    @DisplayName("Change password")
    void changePassword() throws Exception {
        final String newPassword = "NewPassword";
        account.changeUserPassword(client, testUsersInOrg1[0].password, newPassword);
        account.login(client, testOrg1.orgName, testUsersInOrg1[0].userEmail, newPassword);
    }

    @Test
    @Order(19)
    @DisplayName("testOrg1 admin logout")
    void logout() throws Exception {
        String status = account.logout(client);
        assertTrue(status.contains("successfully"));
        client.shutdown();
    }
}
