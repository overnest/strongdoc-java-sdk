package com.strongsalt.strongdoc.sdk.api;

import com.strongsalt.strongdoc.sdk.api.responses.AccountInfoResponse;
import com.strongsalt.strongdoc.sdk.api.responses.OrgUserInfo;
import com.strongsalt.strongdoc.sdk.api.responses.Subscription;
import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StrongDocAccountTest {

    private final StrongDocTestSetup testSetup = new StrongDocTestSetup();
    private final StrongDocAccount account = new StrongDocAccount();
    private com.strongsalt.strongdoc.sdk.api.StrongDocTestConstants sdConst;
    private StrongDocServiceClient client;
    private String org1AdminToken;
    private String org2AdminToken;

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
                sdConst.ORG1_USER_NAME,
                sdConst.ORG1_USER_PASSWORD,
                sdConst.ORG1_USER_EMAIL,
                false);
        System.out.printf("A new user %s has been added to organization 1\n\n", userID);

        assertEquals(sdConst.ORG1_USER_EMAIL, userID);
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
        final boolean success = account.promoteUser(client, org1AdminToken, sdConst.ORG1_USER_EMAIL);
        System.out.printf("User promoted successfully? %b\n\n", success);

        assertTrue(success);
    }

    @Test
    @Order(4)
    @DisplayName("Demote User")
    void demoteUser() throws Exception {
        final boolean success = account.demoteUser(client, org1AdminToken, sdConst.ORG1_USER_EMAIL);
        System.out.printf("User demoted successfully? %b\n\n", success);

        assertTrue(success);
    }

    @Test
    @Order(5)
    @DisplayName("Logout User")
    void logout() throws Exception {
        final String org1UserToken = account.login(
                client, sdConst.ORG1_NAME, sdConst.ORG1_USER_EMAIL, sdConst.ORG1_USER_PASSWORD);

        assertNotNull(org1UserToken);

        final String status = account.logout(client, org1UserToken);
        System.out.printf("User logged out status: %s\n\n", status);

        assertTrue(status.contains("successfully"));
    }

    @Test
    @Order(6)
    @DisplayName("Remove User")
    void removeUser() throws Exception {
        final long removeCount = account.removeUser(client, org1AdminToken, sdConst.ORG1_USER_EMAIL);
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
        final boolean success = account.addSharableOrg(client, org1AdminToken, sdConst.ORG2_NAME);
        System.out.printf("Added sharable org successfully? %b\n\n", success);

        assertTrue(success);
    }

    @Test
    @Order(9)
    @DisplayName("Remove Sharable Org")
    void removeSharableOrg() throws Exception {
        final boolean success = account.removeSharableOrg(client, org1AdminToken, sdConst.ORG2_NAME);
        System.out.printf("Removed sharable org successfully? %b\n\n", success);

        assertTrue(success);
    }

    @Test
    @Order(10)
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
    }

    private void getTokens() throws Exception {
        org1AdminToken = testSetup.getToken(
                client, sdConst.ORG1_NAME, sdConst.ORG1_ADMIN_EMAIL, sdConst.ORG1_ADMIN_PASSWORD);
        org2AdminToken = testSetup.getToken(
                client, sdConst.ORG2_NAME, sdConst.ORG2_ADMIN_EMAIL, sdConst.ORG2_ADMIN_PASSWORD);
    }

    private void registerOrganizations() throws Exception {
        testSetup.registerOrganization(
                client, sdConst.ORG2_NAME, sdConst.ORG2_ADDRESS, sdConst.ORG2_ADMIN_NAME,
                sdConst.ORG2_ADMIN_PASSWORD, sdConst.ORG2_ADMIN_EMAIL, new String[]{},
                false, sdConst.SOURCE, sdConst.SOURCE_DATA);
        testSetup.registerOrganization(
                client, sdConst.ORG1_NAME, sdConst.ORG1_ADDRESS, sdConst.ORG1_ADMIN_NAME,
                sdConst.ORG1_ADMIN_PASSWORD, sdConst.ORG1_ADMIN_EMAIL, new String[]{sdConst.ORG2_NAME},
                false, sdConst.SOURCE, sdConst.SOURCE_DATA);
    }

    private void removeOrganizations() throws Exception {
        testSetup.removeOrganization(client, org1AdminToken);
        testSetup.removeOrganization(client, org2AdminToken);
    }
}