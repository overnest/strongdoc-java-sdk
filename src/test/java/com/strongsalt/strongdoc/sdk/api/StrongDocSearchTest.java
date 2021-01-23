package com.strongsalt.strongdoc.sdk.api;


import com.strongsalt.strongdoc.sdk.api.responses.SearchDocumentResponse;
import com.strongsalt.strongdoc.sdk.api.responses.SearchDocumentResult;
import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient;
import org.junit.jupiter.api.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StrongDocSearchTest {
    private final StrongDocSearch search = new StrongDocSearch();
    private StrongDocServiceClient client;
    private String uploadedDocID;

    private TestOrg testOrg;
    private TestUser testOrgAdmin;

    @BeforeAll
    @DisplayName("Register organization and obtain token")
    void setUp() throws Exception {
        TestData testData = StrongDocTestSetup.preTest(1, 1);
        testData.doRegistration();
        testOrg = testData.testOrgs[0];
        testOrgAdmin = testData.testUsers[0][0];

        client = StrongDocTestSetup.initClient();
        client.login(testOrg.orgName, testOrgAdmin.userEmail, testOrgAdmin.password);
        uploadFile();
    }

    @AfterAll
    @DisplayName("Test tear down")
    void tearDown() throws Exception {
        client.shutdown();
    }

    @Test
    @DisplayName("Perform search")
    void runSearch() {
        final StrongDocSearch search = new StrongDocSearch();

        SearchDocumentResponse searchResponse = search.runSearch(client, "bed mounts");
        final List<SearchDocumentResult> hitsList = searchResponse.getHitsList();
        System.out.printf("Found %d search result(s)\n\n", hitsList.size());

        assertEquals(1, hitsList.size());
        assertEquals(uploadedDocID, ((SearchDocumentResult) hitsList.toArray()[0]).getDocID());
    }

    private void uploadFile() throws Exception {
        String filename = "BedMounts.pdf";
        System.out.printf("Uploading %s ...\n", filename);

        final Path resourceDirectory = Paths.get("src", "test", "resources", "testDocuments");
        final String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        File file = new File(absolutePath + "/" + filename);
        byte[] data = Files.readAllBytes(file.toPath());

        final StrongDocDocument document = new StrongDocDocument();
        uploadedDocID = document.uploadDocument(client, filename, data);
        System.out.printf("The document %s (%d bytes) has been uploaded.\n", filename, data.length);
        System.out.printf("  Uploaded document ID is %s\n\n", uploadedDocID);
    }
}
