package com.strongsalt.strongdoc.sdk.api;

import com.strongsalt.strongdoc.sdk.api.responses.*;
import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient;
import org.junit.jupiter.api.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StrongDocShareTest {
    StrongDocAccount account = new StrongDocAccount();
    StrongDocDocument document = new StrongDocDocument();
    StrongDocSearch search = new StrongDocSearch();
    // document
    private String docFilename;
    private byte[] docByteArray;
    private String query = "Privacy";
    // client
    private StrongDocServiceClient client;

    @BeforeAll
    @DisplayName("Test Setup")
    void setUp() throws Exception {
        // init client
        client = StrongDocTestSetupAndTearDown.initClient();
        // test document
        final Path resourceDirectory = Paths.get("src", "test", "resources", "testDocuments");
        String docPath = resourceDirectory.toFile().getAbsolutePath() + "/";
        docFilename = "textSample.txt";
        final File file = new File(docPath + docFilename);
        docByteArray = Files.readAllBytes(file.toPath());
    }

    @AfterAll
    @DisplayName("Hard Remove organizations")
    void tearDown() throws Exception {
        client.shutdown();
    }

    @Test
    @Order(1)
    @DisplayName("share server-encrypted document, multiLevelShare disabled")
    void testShare1() throws Exception {
        // set up
        TestData testData = StrongDocTestSetupAndTearDown.initData(3, 3);
        StrongDocTestSetupAndTearDown.hardRemoveOrgs(testData.testOrgs);
        StrongDocTestSetupAndTearDown.registerOrgAndUser(client, testData);
        // test
        testShareWithoutMultiLevelShare(testData, false);
        // tear down
        StrongDocTestSetupAndTearDown.hardRemoveOrgs(testData.testOrgs);
    }

    @Test
    @Order(2)
    @DisplayName("share client-encrypted document, multiLevelShare disabled")
    void testShare2() throws Exception {
        // set up
        TestData testData = StrongDocTestSetupAndTearDown.initData(3, 3);
        StrongDocTestSetupAndTearDown.hardRemoveOrgs(testData.testOrgs);
        StrongDocTestSetupAndTearDown.registerOrgAndUser(client, testData);
        // test
        testShareWithoutMultiLevelShare(testData, true);
        // tear down
        StrongDocTestSetupAndTearDown.hardRemoveOrgs(testData.testOrgs);
    }


    @Test
    @Order(3)
    @DisplayName("share server-encrypted document, multiLevelShare enabled")
    void testShare3() throws Exception {
        // set up
        TestData testData = StrongDocTestSetupAndTearDown.initData(3, 4);
        StrongDocTestSetupAndTearDown.hardRemoveOrgs(testData.testOrgs);
        StrongDocTestSetupAndTearDown.registerOrgAndUser(client, testData);
        // test
        testShareWithMultiLevelShare(testData, false);
        // tear down
        StrongDocTestSetupAndTearDown.hardRemoveOrgs(testData.testOrgs);
    }

    @Test
    @Order(4)
    @DisplayName("share client-encrypted document, multiLevelShare enabled")
    void testShare4() throws Exception {
        // set up
        TestData testData = StrongDocTestSetupAndTearDown.initData(3, 4);
        StrongDocTestSetupAndTearDown.hardRemoveOrgs(testData.testOrgs);
        StrongDocTestSetupAndTearDown.registerOrgAndUser(client, testData);
        // test
        testShareWithMultiLevelShare(testData, true);
        // tear down
        StrongDocTestSetupAndTearDown.hardRemoveOrgs(testData.testOrgs);
    }

    @Test
    @Order(5)
    @DisplayName("check search api after sharing/unsharing")
    void testShareSearch1() throws Exception {
        // set up
        TestData testData = StrongDocTestSetupAndTearDown.initData(3, 4);
        StrongDocTestSetupAndTearDown.hardRemoveOrgs(testData.testOrgs);
        StrongDocTestSetupAndTearDown.registerOrgAndUser(client, testData);

        // test
        testShareAndSearch(testData, false);
        // tear down
        StrongDocTestSetupAndTearDown.hardRemoveOrgs(testData.testOrgs);
    }

    @Order(6)
    @DisplayName("check search api after sharing/unsharing")
    void testShareSearch2() throws Exception {
        // set up
        TestData testData = StrongDocTestSetupAndTearDown.initData(3, 4);
        StrongDocTestSetupAndTearDown.hardRemoveOrgs(testData.testOrgs);
        StrongDocTestSetupAndTearDown.registerOrgAndUser(client, testData);

        // test
        testShareAndSearch(testData, true);
        // tear down
        StrongDocTestSetupAndTearDown.hardRemoveOrgs(testData.testOrgs);
    }

    void testShareAndSearch(TestData testData, boolean e2e) throws Exception {
        TestOrg org1 = testData.testOrgs[0];
        TestOrg org2 = testData.testOrgs[1];
        TestOrg org3 = testData.testOrgs[2];
        TestUser org1Admin = testData.testUsers[0][0];
        TestUser org1User1 = testData.testUsers[0][1];
        TestUser org1User2 = testData.testUsers[0][2];
        TestUser org2Admin = testData.testUsers[1][0];
        TestUser org2User = testData.testUsers[1][1];
        TestUser org3Admin = testData.testUsers[2][0];
        TestUser org3User = testData.testUsers[2][1];

        // add sharble org
        addSharableOrg(org1, org1Admin, org2);
        addSharableOrg(org1, org1Admin, org3);

        // org1User1(uploader) upload a file
        String docID = uploadDocument(org1, org1User1, e2e);

        List<SearchDocumentResult> hits = search(org1, org1User1, query);
        Assertions.assertTrue(hits.size() == 1);

        hits = search(org1, org1Admin, query);
        Assertions.assertTrue(hits.size() == 1);

        // ----------------------------- share with user within same org -----------------------------
        // org1user1 share to org1user2
        ShareDocumentResponse shareResp = shareWithUser(docID, org1, org1User1, org1User2);
        Assertions.assertTrue(checkDocumentAccess(docID, org1, org1User2));

        hits = search(org1, org1User2, query);
        Assertions.assertTrue(hits.size() == 1);

        // unshare
        UnshareDocumentResponse unshareResp = unshareWithUser(docID, org1, org1User1, org1User2);
        hits = search(org1, org1User2, query);
        Assertions.assertTrue(hits.size() == 0);

        // ----------------------------- share with user from external org -----------------------------
        // org1user1 share to org2user
        shareWithUser(docID, org1, org1User1, org2User);
        Assertions.assertTrue(checkDocumentAccess(docID, org2, org2User));
        hits = search(org2, org2User, query);
        Assertions.assertTrue(hits.size() == 1);
        hits = search(org2, org2Admin, query);
        Assertions.assertTrue(hits.size() == 0);

        //unshare
        unshareWithUser(docID, org1, org1User1, org2User);
        hits = search(org2, org2User, query);
        Assertions.assertTrue(hits.size() == 0);

        // ----------------------------- share with another org -----------------------------
        // share with org3
        shareWithOrg(docID, org1, org1User1, org3);
        Assertions.assertTrue(checkDocumentAccess(docID, org3, org3Admin));
        hits = search(org3, org3Admin, query);
        Assertions.assertTrue(hits.size() == 1);
        Assertions.assertTrue(!checkDocumentAccess(docID, org3, org3User));
        hits = search(org3, org3User, query);
        Assertions.assertTrue(hits.size() == 0);

        // share to org3user
        shareWithUser(docID, org1, org1User1, org3User);
        hits = search(org3, org3User, query);
        Assertions.assertTrue(hits.size() == 1);

        // unshare org3
        unshareWithOrg(docID, org1, org1User1, org3);
        hits = search(org3, org3Admin, query);
        Assertions.assertTrue(hits.size() == 0);
        hits = search(org3, org3User, query);
        Assertions.assertTrue(hits.size() == 1);
    }

    // test multiLevelShare = true
    void testShareWithMultiLevelShare(TestData testData, boolean e2e) throws Exception {
        TestOrg org1 = testData.testOrgs[0];
        TestOrg org2 = testData.testOrgs[1];
        TestOrg org3 = testData.testOrgs[2];
        TestUser org1Admin = testData.testUsers[0][0];
        TestUser org1User1 = testData.testUsers[0][1];
        TestUser org1User2 = testData.testUsers[0][2];
        TestUser org1User3 = testData.testUsers[0][3];

        TestUser org2User = testData.testUsers[1][1];
        TestUser org3Admin = testData.testUsers[2][0];

        // org1 admin set multiLevelShare enabled and add sharableOrg
        enableMultiLevelSharing(org1, org1Admin);
        addSharableOrg(org1, org1Admin, org2);
        addSharableOrg(org1, org1Admin, org3);

        // org1User1(uploader) upload a file, only org1Admin and org1User1 can share the document
        String docID = uploadDocument(org1, org1User1, e2e);

        // ----------------------------- share with user within same org -----------------------------
        // org1User1(uploader) share doc with org1User2
        shareWithUser(docID, org1, org1User1, org1User2);
        Assertions.assertTrue(checkDocumentAccess(docID, org1, org1User2));

        // org1User2 share with org1User3
        shareWithUser(docID, org1, org1User2, org1User3);
        Assertions.assertTrue(checkDocumentAccess(docID, org1, org1User3));

        // fail to unshare with uploader
        UnshareDocumentResponse unshareResp = unshareWithUser(docID, org1, org1Admin, org1User1);
        Assertions.assertTrue(!unshareResp.getAllowed());

        // unshare with org1user3
        unshareWithUser(docID, org1, org1User1, org1User3);
        Assertions.assertTrue(!checkDocumentAccess(docID, org1, org1User3));

        // unshare again
        unshareResp = unshareWithUser(docID, org1, org1User1, org1User3);
        Assertions.assertTrue(unshareResp.getAlreadyUnshared());
        Assertions.assertTrue(!checkDocumentAccess(docID, org1, org1User3));

        // ----------------------------- share with user from external org -----------------------------
        // org1User2 share doc with external user
        shareWithUser(docID, org1, org1User2, org2User);
        Assertions.assertTrue(checkDocumentAccess(docID, org2, org2User));

        // unshare
        unshareWithUser(docID, org1, org1User1, org2User);
        Assertions.assertTrue(!checkDocumentAccess(docID, org2, org2User));

        // unshare again
        unshareResp = unshareWithUser(docID, org1, org1User1, org2User);
        Assertions.assertTrue(unshareResp.getAlreadyUnshared());
        Assertions.assertTrue(!checkDocumentAccess(docID, org2, org2User));

        // ----------------------------- share with another org -----------------------------
        // org1Admin share doc with another org
        shareWithOrg(docID, org1, org1Admin, org3);
        Assertions.assertTrue(checkDocumentAccess(docID, org3, org3Admin));

        // unshare
        unshareWithOrg(docID, org1, org1Admin, org3);
        Assertions.assertTrue(!checkDocumentAccess(docID, org3, org3Admin));

        // unshare again
        unshareResp = unshareWithOrg(docID, org1, org1Admin, org3);
        Assertions.assertTrue(unshareResp.getAlreadyUnshared());
        Assertions.assertTrue(!checkDocumentAccess(docID, org3, org3Admin));

    }

    // test multiLevelShare = false
    void testShareWithoutMultiLevelShare(TestData testData, boolean e2e) throws Exception {
        TestOrg org1 = testData.testOrgs[0];
        TestOrg org2 = testData.testOrgs[1];
        TestOrg org3 = testData.testOrgs[2];
        TestUser org1Admin = testData.testUsers[0][0];
        TestUser org1User1 = testData.testUsers[0][1];
        TestUser org1User2 = testData.testUsers[0][2];
        TestUser org2Admin = testData.testUsers[1][0];
        TestUser org2User = testData.testUsers[1][1];
        TestUser org3Admin = testData.testUsers[2][0];

        // org1User1(uploader) upload a file, only org1Admin and org1User1 can share the document
        String docID = uploadDocument(org1, org1User1, e2e);

        // ----------------------------- share with user within same org -----------------------------
        // org1 admin share to org1 user2
        ShareDocumentResponse shareResp = shareWithUser(docID, org1, org1Admin, org1User2);
        Assertions.assertTrue(checkDocumentAccess(docID, org1, org1User2));

        // share again
        shareResp = shareWithUser(docID, org1, org1Admin, org1User2);
        Assertions.assertTrue(checkDocumentAccess(docID, org1, org1User2));
        Assertions.assertTrue(shareResp.getAlreadyAccessibleReceivers().contains(org1User2.userEmail));

        // fail to unshare with uploader
        UnshareDocumentResponse unshareResp = unshareWithUser(docID, org1, org1Admin, org1User1);
        Assertions.assertTrue(!unshareResp.getAllowed());

        // unshare
        unshareResp = unshareWithUser(docID, org1, org1Admin, org1User2);
        Assertions.assertTrue(unshareResp.getSuccess());

        // unshare again
        unshareResp = unshareWithUser(docID, org1, org1Admin, org1User2);
        Assertions.assertTrue(unshareResp.getAlreadyUnshared());

        Assertions.assertTrue(!checkDocumentAccess(docID, org1, org1User2));

        // ----------------------------- share with user from external org -----------------------------
        // fail because org2 is unsharable
        shareResp = shareWithUser(docID, org1, org1Admin, org2User);
        Assertions.assertTrue(shareResp.getUnsharedReceivers().contains(org2User.userEmail));

        // org1 add sharableOrg org2
        addSharableOrg(org1, org1Admin, org2);

        // share
        shareWithUser(docID, org1, org1Admin, org2User);
        Assertions.assertTrue(checkDocumentAccess(docID, org2, org2User));
        Assertions.assertTrue(!checkDocumentAccess(docID, org2, org2Admin));

        // share again
        shareResp = shareWithUser(docID, org1, org1Admin, org2User);
        Assertions.assertTrue(checkDocumentAccess(docID, org2, org2User));
        Assertions.assertTrue(shareResp.getAlreadyAccessibleReceivers().contains(org2User.userEmail));

        // unshare
        unshareWithUser(docID, org1, org1Admin, org2User);
        Assertions.assertTrue(!checkDocumentAccess(docID, org2, org2User));

        // ----------------------------- share with another org -----------------------------
        // fail because org3 is unsharable
        shareResp = shareWithOrg(docID, org1, org1Admin, org3);
        Assertions.assertTrue(shareResp.getUnsharedReceivers().contains(org3.orgID));

        // org1 add sharableOrg org3
        addSharableOrg(org1, org1Admin, org3);

        // share
        shareWithOrg(docID, org1, org1Admin, org3);
        Assertions.assertTrue(checkDocumentAccess(docID, org3, org3Admin));

        // share again
        shareResp = shareWithOrg(docID, org1, org1Admin, org3);
        Assertions.assertTrue(shareResp.getAlreadyAccessibleReceivers().contains(org3.orgID));

        // unshare
        unshareWithOrg(docID, org1, org1Admin, org3);
        Assertions.assertTrue(!checkDocumentAccess(docID, org3, org3Admin));

        // unshare again
        unshareResp = unshareWithOrg(docID, org1, org1Admin, org3);
        Assertions.assertTrue(!checkDocumentAccess(docID, org3, org3Admin));
        Assertions.assertTrue(unshareResp.getAlreadyUnshared());
    }

    // set multilevel sharing
    void enableMultiLevelSharing(TestOrg userOrg, TestUser user) throws Exception {
        boolean loginRes = account.login(client, userOrg.orgID, user.userID, user.password);
        Assertions.assertTrue(loginRes);
        boolean succ = account.setMultiLevelSharing(client, true);
        Assertions.assertTrue(succ);
        account.logout(client);

    }

    //  add sharable org
    void addSharableOrg(TestOrg userOrg, TestUser user, TestOrg addOrg) throws Exception {
        boolean loginRes = account.login(client, userOrg.orgID, user.userID, user.password);
        Assertions.assertTrue(loginRes);
        boolean succ = account.addSharableOrg(client, addOrg.orgID);
        Assertions.assertTrue(succ);
        account.logout(client);

    }

    // upload document, return docID
    String uploadDocument(TestOrg testOrg, TestUser uploader, boolean e2e) throws Exception {
        boolean loginRes = account.login(client, testOrg.orgID, uploader.userID, uploader.password);
        Assertions.assertTrue(loginRes);
        String docID = "";
        if (e2e) {
            docID = document.e2eeUploadDocument(client, docFilename, docByteArray);
            System.out.println("uploaded e2e document " + docID);
        } else {
            docID = document.uploadDocument(client, docFilename, docByteArray);
            System.out.println("uploaded document " + docID);
        }
        account.logout(client);
        return docID;
    }

    ShareDocumentResponse shareWithUser(String docID, TestOrg org, TestUser from, TestUser to) throws Exception {
        boolean loginRes = account.login(client, org.orgID, from.userID, from.password);
        Assertions.assertTrue(loginRes);
        ShareDocumentResponse resp = document.bulkShareDocWithUsers(client, docID, Arrays.asList(to.userEmail));
        account.logout(client);
        return resp;
    }

    UnshareDocumentResponse unshareWithUser(String docID, TestOrg org, TestUser from, TestUser to) throws Exception {
        boolean loginRes = account.login(client, org.orgID, from.userID, from.password);
        Assertions.assertTrue(loginRes);
        UnshareDocumentResponse resp = document.unshareDocumentWithUser(client, docID, to.userEmail);
        account.logout(client);
        return resp;
    }

    UnshareDocumentResponse unshareWithOrg(String docID, TestOrg org, TestUser from, TestOrg to) throws Exception {
        boolean loginRes = account.login(client, org.orgID, from.userID, from.password);
        Assertions.assertTrue(loginRes);
        UnshareDocumentResponse resp = document.unshareDocumentWithOrg(client, docID, to.orgID);
        account.logout(client);
        return resp;
    }

    ShareDocumentResponse shareWithOrg(String docID, TestOrg org, TestUser from, TestOrg to) throws Exception {
        boolean loginRes = account.login(client, org.orgID, from.userID, from.password);
        Assertions.assertTrue(loginRes);
        ShareDocumentResponse resp = document.bulkShareDocWithOrgs(client, docID, Arrays.asList(to.orgID));
        account.logout(client);
        return resp;
    }

    boolean checkDocumentAccess(String docID, TestOrg org, TestUser user) throws Exception {
        boolean loginRes = account.login(client, org.orgID, user.userID, user.password);
        Assertions.assertTrue(loginRes);
        ArrayList<DocumentInfo> docs = document.listDocuments(client);
        boolean found = false;
        for (DocumentInfo doc : docs) {
            if (doc.getDocID().equals(docID)) {
                found = true;
                break;
            }
        }
        account.logout(client);
        return found;
    }

    List<SearchDocumentResult> search(TestOrg org, TestUser user, String query) throws Exception {
        boolean loginRes = account.login(client, org.orgID, user.userID, user.password);
        Assertions.assertTrue(loginRes);
        SearchDocumentResponse resp = search.runSearch(client, query);
        account.logout(client);
        return resp.getHitsList();
    }
}
