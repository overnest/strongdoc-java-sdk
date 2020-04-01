package com.strongsalt.strongdoc.sdk.api;

import com.strongsalt.strongdoc.sdk.api.responses.UploadDocumentResponse;
import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StrongDocDocumentTest {

    private final StrongDocTestSetup testSetup = new StrongDocTestSetup();
    private final StrongDocDocument document = new StrongDocDocument();
    private com.strongsalt.strongdoc.sdk.api.StrongDocTestConstants sdConst;
    private StrongDocServiceClient client;
    private String adminToken;
    private String docPath;

    private String uploadDocID;
    private String uploadStreamDocID;
    private long uploadStreamTotalBytes;
    private String encryptDocID;
    private String encryptStreamDocID;

    @BeforeAll
    @DisplayName("Register organization and obtain token")
    void setUp() throws Exception {
        client = testSetup.init();

        testSetup.registerOrganization(
                client, sdConst.ORG4_NAME, sdConst.ORG4_ADDRESS, sdConst.ORG4_ADMIN_NAME,
                sdConst.ORG4_ADMIN_PASSWORD, sdConst.ORG4_ADMIN_EMAIL, new String[]{},
                false, sdConst.SOURCE, sdConst.SOURCE_DATA);

        adminToken = testSetup.getToken(
                client, sdConst.ORG4_NAME, sdConst.ORG4_ADMIN_EMAIL, sdConst.ORG4_ADMIN_PASSWORD);

        final Path resourceDirectory = Paths.get("src", "test", "resources", "testDocuments");
        docPath = resourceDirectory.toFile().getAbsolutePath() + "/";
    }

    @AfterAll
    @DisplayName("Remove organization")
    void tearDown() throws Exception {
        testSetup.removeOrganization(client, adminToken);
        client.shutdown();
    }

    @Test
    @Order(1)
    @DisplayName("Upload Document")
    void uploadDocument() throws Exception {
        final String filename = "textSample.txt";
        final File file = new File(docPath + filename);
        System.out.printf("Uploading %s ...\n", filename);

        byte[] data = Files.readAllBytes(file.toPath());
        uploadDocID = document.uploadDocument(client, adminToken, filename, data);
        System.out.printf("The document %s (%d bytes) has been uploaded.\n", filename, data.length);
        System.out.printf("  Uploaded document ID is %s\n", uploadDocID);

        assertNotNull(uploadDocID);
    }

    @Test
    @Order(2)
    @DisplayName("Download Document")
    void downloadDocument() throws Exception {
        System.out.printf("Downloading document %s ...\n", uploadDocID);
        final byte[] plaintext = document.downloadDocument(client, adminToken, uploadDocID);
        System.out.println("The document has been downloaded.\n");

        assertTrue(plaintext.length > 0);
    }

    @Test
    @Order(3)
    @DisplayName("Upload Document using Stream")
    void uploadDocumentStream() throws Exception {
        final String filename = "textSample.txt";
        final File file = new File(docPath + filename);
        System.out.printf("Uploading %s ...\n", filename);
        UploadDocumentResponse uploadDocumentResponse = document.uploadDocumentStream(
                client, adminToken, filename, new FileInputStream(file));
        System.out.printf("The document %s has been uploaded using stream.\n", filename);
        System.out.printf("  Uploaded document ID is %s\n", uploadDocumentResponse.getDocID());
        System.out.printf("  Uploaded total of bytes is %d\n", uploadDocumentResponse.getNumBytes());

        assertNotNull(uploadDocumentResponse.getDocID());
        assertTrue(uploadDocumentResponse.getNumBytes() > 0);

        uploadStreamDocID = uploadDocumentResponse.getDocID();
        uploadStreamTotalBytes = uploadDocumentResponse.getNumBytes();
    }

    @Test
    @Order(4)
    @DisplayName("Download Document using Stream")
    void downloadDocumentStream() throws Exception {
        System.out.printf("Downloading document %s ...\n", uploadStreamDocID);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        document.downloadDocumentStream(client, adminToken, uploadStreamDocID, output);
        System.out.println("The document has been downloaded using stream.");
        System.out.printf("  Downloaded %d bytes using stream\n\n", output.size());

        assertEquals(uploadStreamTotalBytes, output.size());
    }

    @Test
    @Order(5)
    @DisplayName("Share Document")
    void shareDocument() throws Exception {
    }

    @Test
    @Order(6)
    @DisplayName("Unshare Document")
    void unshareDocument() throws Exception {
    }

    @Test
    @Order(7)
    @DisplayName("List Documents")
    void listDocuments() throws Exception {
    }

    @Test
    @Order(8)
    @DisplayName("Remove Document")
    void removeDocument() throws Exception {
    }

    @Test
    @Order(9)
    @DisplayName("Encrypt Document using Stream")
    void encryptDocumentStream() throws Exception {
    }

    @Test
    @Order(10)
    @DisplayName("Decrypt Document using Stream")
    void decryptDocumentStream() throws Exception {
    }

    @Test
    @Order(11)
    @DisplayName("Encrypt Document")
    void encryptDocument() throws Exception {
    }

    @Test
    @Order(12)
    @DisplayName("Decrypt Document")
    void decryptDocument() throws Exception {
    }
}