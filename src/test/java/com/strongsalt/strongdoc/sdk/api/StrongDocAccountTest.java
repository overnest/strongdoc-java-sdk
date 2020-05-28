package com.strongsalt.strongdoc.sdk.api;

import com.strongsalt.strongdoc.sdk.api.responses.AccountInfoResponse;
import com.strongsalt.strongdoc.sdk.api.responses.OrgUserInfo;
import com.strongsalt.strongdoc.sdk.api.responses.Subscription;
import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static com.strongsalt.strongdoc.sdk.api.StrongDocTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StrongDocAccountTest {

    private final StrongDocTestSetup testSetup = new StrongDocTestSetup();
    private final StrongDocAccount account = new StrongDocAccount();
    //private com.strongsalt.strongdoc.sdk.api.StrongDocTestConstants sdConst;
    private StrongDocServiceClient client;
    private String org1AdminToken;
    private String org2AdminToken;
    
    private String org1AdminUserID;
    private String org1UserID;

    @BeforeAll
    @DisplayName("Register organizations and obtain token")
    void setUp() throws Exception {
        client = testSetup.init();
        registerOrganizations();
        getTokens();
    }

    @AfterAll
    @DisplayName("Remove organizations")
    void tearDown() throws Exception, InterruptedException {
        removeOrganizations();
        client.shutdown();
    }

    @Test
    @Order(1)
    @DisplayName("Register User")
    void registerUser() throws Exception {
        final String userID = account.registerUser(client,
                org1AdminToken,
                ORG1_USER_NAME,
                ORG1_USER_PASSWORD,
                ORG1_USER_EMAIL,
                false);
        System.out.printf("A new user %s has been added to organization 1\n\n", userID);

        org1UserID = userID;
    }

    @Test
    @Order(2)
    @DisplayName("List Users")
    void listUsers() throws Exception {
        final ArrayList<OrgUserInfo> usersList = account.listUsers(client, org1AdminToken);
        System.out.printf("Organization 1 has %d users.\n\n", usersList.size());

        assertEquals(2, usersList.size());
    }

    @Test
    @Order(3)
    @DisplayName("Promote User")
    void promoteUser() throws Exception {
        final boolean success = account.promoteUser(client, org1AdminToken, ORG1_USER_EMAIL);
        System.out.printf("User promoted successfully? %b\n\n", success);

        assertTrue(success);
    }

    @Test
    @Order(4)
    @DisplayName("Demote User")
    void demoteUser() throws Exception {
        final boolean success = account.demoteUser(client, org1AdminToken, ORG1_USER_EMAIL);
        System.out.printf("User demoted successfully? %b\n\n", success);

        assertTrue(success);
    }

    @Test
    @Order(5)
    @DisplayName("Logout User")
    void logout() throws Exception {
        final String org1UserToken = account.login(
                client, ORG1_NAME, ORG1_USER_EMAIL, ORG1_USER_PASSWORD);

        assertNotNull(org1UserToken);

        final String status = account.logout(client, org1UserToken);
        System.out.printf("User logged out status: %s\n\n", status);

        assertTrue(status.contains("successfully"));
    }

    @Test
    @Order(6)
    @DisplayName("Remove User")
    void removeUser() throws Exception {
        final long removeCount = account.removeUser(client, org1AdminToken, org1UserID);
        System.out.printf("%d user has been removed\n\n", removeCount);

        assertTrue(removeCount == 1);
    }

    @Test
    @Order(7)
    @DisplayName("Set Multi-Level Sharing")
    void setMultiLevelSharing() throws Exception {
        final boolean isEnable = true;
        final boolean success = account.setMultiLevelSharing(client, org1AdminToken, isEnable);
        System.out.printf("Enabled multi-level sharing successfully? %b\n\n", success);

        assertTrue(success);
    }

    @Test
    @Order(8)
    @DisplayName("Add Sharable Org")
    void addSharableOrg() throws Exception {
        final boolean success = account.addSharableOrg(client, org1AdminToken, ORG2_NAME);
        System.out.printf("Added sharable org successfully? %b\n\n", success);

        assertTrue(success);
    }

    @Test
    @Order(9)
    @DisplayName("Get Account Info")
    void getAccountInfo() throws Exception {
        final AccountInfoResponse accountInfoResponse = account.getAccountInfo(client, org1AdminToken);
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
        assertEquals(ORG2_NAME, accountInfoResponse.getSharableOrgs().get(0));
        assertTrue(accountInfoResponse.getMultiLevelSharing());
        assertEquals(ORG1_ADDRESS, accountInfoResponse.getOrgAddress());
        assertEquals(ORG1_NAME, accountInfoResponse.getOrgID());
        assertEquals(ORG1_ADMIN_EMAIL, accountInfoResponse.getOrgEmail());
    }

    @Test
    @Order(10)
    @DisplayName("Remove Sharable Org")
    void removeSharableOrg() throws Exception {
        final boolean success = account.removeSharableOrg(client, org1AdminToken, ORG2_NAME);
        System.out.printf("Removed sharable org successfully? %b\n\n", success);

        assertTrue(success);
    }

    @Test
    @Order(11)
    @DisplayName("Get User Info")
    void getUserInfo() throws Exception {
        final OrgUserInfo userInfoResponse = account.getUserInfo(client, org1AdminToken);
        System.out.println("User info:");
        System.out.printf("  UserID: %s\n", userInfoResponse.getUserID());
        System.out.printf("  Name: %s\n", userInfoResponse.getName());
        System.out.printf("  OrgID: %s\n\n", userInfoResponse.getOrgID());
        System.out.printf("  Email: %s\n\n", userInfoResponse.getEmail());

        assertEquals(org1AdminUserID, userInfoResponse.getUserID());
        assertEquals(ORG1_ADMIN_NAME, userInfoResponse.getName());
        assertEquals(ORG1_NAME, userInfoResponse.getOrgID());
        assertEquals(ORG1_ADMIN_EMAIL, userInfoResponse.getEmail());
        assertTrue(userInfoResponse.isAdmin());
    }

    @Test
    @Order(12)
    @DisplayName("Set Account Info")
    void setAccountInfo() throws Exception {
        final String newEmail = "neworgemail@website.com";
        final String newAddress = "123 Test New St.";

        final boolean setAccountInfoResponse = account.setAccountInfo(client, org1AdminToken, 
            newEmail, newAddress);

        assertTrue(setAccountInfoResponse);

        final AccountInfoResponse accountInfoResponse = account.getAccountInfo(client, org1AdminToken);

        assertEquals(newAddress, accountInfoResponse.getOrgAddress());
        assertEquals(newEmail, accountInfoResponse.getOrgEmail());

        assertTrue(account.setAccountInfo(client, org1AdminToken, ORG1_ADMIN_EMAIL, ORG1_ADDRESS));
    }

    @Test
    @Order(13)
    @DisplayName("Set User Info")
    void setUserInfo() throws Exception {
        final String newName = "NewUserName";
        final String newEmail = "newuseremail@website.com";

        final boolean setUserInfoResponse = account.setUserInfo(client, org1AdminToken, 
            newName, newEmail);

        assertTrue(setUserInfoResponse);

        final OrgUserInfo userInfoResponse = account.getUserInfo(client, org1AdminToken);

        assertEquals(newName, userInfoResponse.getName());
        assertEquals(newEmail, userInfoResponse.getEmail());

        assertTrue(account.setUserInfo(client, org1AdminToken, ORG1_ADMIN_NAME, ORG1_ADMIN_EMAIL));
    }

    @Test
    @Order(14)
    @DisplayName("Change User Password")
    void changeUserPassword() throws Exception {
        final String newPassword = "NewPassword";

        final boolean changeUserPasswordResponse = account.changeUserPassword(client, org1AdminToken, 
            ORG1_ADMIN_PASSWORD, newPassword);

        assertTrue(changeUserPasswordResponse);

        assertTrue(account.changeUserPassword(client, org1AdminToken, newPassword, ORG1_ADMIN_PASSWORD));
    }

    private void getTokens() throws Exception {
        org1AdminToken = testSetup.getToken(
                client, ORG1_NAME, ORG1_ADMIN_EMAIL, ORG1_ADMIN_PASSWORD);
        org2AdminToken = testSetup.getToken(
                client, ORG2_NAME, ORG2_ADMIN_EMAIL, ORG2_ADMIN_PASSWORD);
    }

    private void registerOrganizations() throws Exception {
        testSetup.registerOrganization(
                client, ORG2_NAME, ORG2_ADMIN_EMAIL, ORG2_ADDRESS, ORG2_ADMIN_NAME,
                ORG2_ADMIN_PASSWORD, ORG2_ADMIN_EMAIL, new String[]{},
                false, SOURCE, SOURCE_DATA);
        org1AdminUserID = testSetup.registerOrganization(
                client, ORG1_NAME, ORG1_ADMIN_EMAIL, ORG1_ADDRESS, ORG1_ADMIN_NAME,
                ORG1_ADMIN_PASSWORD, ORG1_ADMIN_EMAIL, new String[]{ORG2_NAME},
                false, SOURCE, SOURCE_DATA);
    }

    private void removeOrganizations() throws Exception {
        testSetup.removeOrganization(client, org1AdminToken);
        testSetup.removeOrganization(client, org2AdminToken);
    }
}

