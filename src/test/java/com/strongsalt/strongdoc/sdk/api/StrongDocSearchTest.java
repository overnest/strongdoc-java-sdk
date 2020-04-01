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

    private final StrongDocTestSetup testSetup = new StrongDocTestSetup();
    private final StrongDocSearch search = new StrongDocSearch();
    private com.strongsalt.strongdoc.sdk.api.StrongDocTestConstants sdConst;
    private StrongDocServiceClient client;
    private String adminToken;
    private String uploadedDocID;

    @BeforeAll
    @DisplayName("Register organization and obtain token")
    void setUp() throws Exception {
        client = testSetup.init();

        testSetup.registerOrganization(
                client, sdConst.ORG5_NAME, sdConst.ORG5_ADDRESS, sdConst.ORG5_ADMIN_NAME,
                sdConst.ORG5_ADMIN_PASSWORD, sdConst.ORG5_ADMIN_EMAIL, new String[]{},
                false, sdConst.SOURCE, sdConst.SOURCE_DATA);

        adminToken = testSetup.getToken(
                client, sdConst.ORG5_NAME, sdConst.ORG5_ADMIN_EMAIL, sdConst.ORG5_ADMIN_PASSWORD);

        uploadFile();
    }

    @AfterAll
    @DisplayName("Remove organization")
    void tearDown() throws Exception, InterruptedException {
        testSetup.removeOrganization(client, adminToken);
        client.shutdown();
    }

    @Test
    @DisplayName("Perform search")
    void runSearch() {
        final StrongDocSearch search = new StrongDocSearch();

        SearchDocumentResponse searchResponse = search.runSearch(client, adminToken, "bed mounts");
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
        uploadedDocID = document.uploadDocument(client, adminToken, filename, data);
        System.out.printf("The document %s (%d bytes) has been uploaded.\n", filename, data.length);
        System.out.printf("  Uploaded document ID is %s\n\n", uploadedDocID);
    }
}