package com.strongsalt.strongdoc.sdk.api;

import com.strongsalt.strongdoc.sdk.api.responses.DocumentInfo;
import com.strongsalt.strongdoc.sdk.api.responses.EncryptDocumentResponse;
import com.strongsalt.strongdoc.sdk.api.responses.UploadDocumentResponse;
import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static com.strongsalt.strongdoc.sdk.api.StrongDocTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StrongDocDocumentTest {

    private final StrongDocTestSetup testSetup = new StrongDocTestSetup();
    private final StrongDocDocument document = new StrongDocDocument();
    //private com.strongsalt.strongdoc.sdk.api.StrongDocTestConstants sdConst;
    private StrongDocServiceClient client;
    private String adminToken;
    private String adminToken2;

    private String docPath;
    private String docFilename;
    private byte[] docByteArray;

    private String uploadDocID;
    private String uploadStreamDocID;
    private long uploadStreamTotalBytes;
    private String encryptDocID;
    private byte[] encryptDocCiphertext;
    private String encryptStreamDocID;
    private byte[] encryptStreamCiphertext;

    @BeforeAll
    @DisplayName("Register organization and obtain token")
    void setUp() throws Exception {
        client = testSetup.init();

        testSetup.registerOrganization(
                client, ORG6_NAME, ORG6_ADDRESS, ORG6_ADMIN_NAME,
                ORG6_ADMIN_PASSWORD, ORG6_ADMIN_EMAIL, new String[]{},
                false, SOURCE, SOURCE_DATA);
        testSetup.registerOrganization(
                client, ORG4_NAME, ORG4_ADDRESS, ORG4_ADMIN_NAME,
                ORG4_ADMIN_PASSWORD, ORG4_ADMIN_EMAIL, new String[]{ORG6_NAME},
                false, SOURCE, SOURCE_DATA);
        adminToken = testSetup.getToken(
                client, ORG4_NAME, ORG4_ADMIN_EMAIL, ORG4_ADMIN_PASSWORD);
        adminToken2 = testSetup.getToken(
                client, ORG6_NAME, ORG6_ADMIN_EMAIL, ORG6_ADMIN_PASSWORD);

        
        final Path resourceDirectory = Paths.get("src", "test", "resources", "testDocuments");
        docPath = resourceDirectory.toFile().getAbsolutePath() + "/";
        docFilename = "textSample.txt";
        final File file = new File(docPath + docFilename);
        docByteArray = Files.readAllBytes(file.toPath());
    }

    @AfterAll
    @DisplayName("Remove organization")
    void tearDown() throws Exception {
        testSetup.removeOrganization(client, adminToken);
        testSetup.removeOrganization(client, adminToken2);
        client.shutdown();
    }

    @Test
    @Order(1)
    @DisplayName("Upload Document")
    void uploadDocument() throws Exception {
        final File file = new File(docPath + docFilename);
        System.out.printf("Uploading %s ...\n", docFilename);

        byte[] data = Files.readAllBytes(file.toPath());
        uploadDocID = document.uploadDocument(client, adminToken, docFilename, data);
        System.out.printf("The document %s (%d bytes) has been uploaded.\n", docFilename, data.length);
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
        assertTrue(Arrays.equals(docByteArray, plaintext));
    }

    @Test
    @Order(3)
    @DisplayName("Upload Document using Stream")
    void uploadDocumentStream() throws Exception {
        final File file = new File(docPath + docFilename);
        System.out.printf("Uploading %s ...\n", docFilename);
        UploadDocumentResponse uploadDocumentResponse = document.uploadDocumentStream(
                client, adminToken, docFilename, new FileInputStream(file));
        System.out.printf("The document %s has been uploaded using stream.\n", docFilename);
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
        assertTrue(Arrays.equals(docByteArray, output.toByteArray()));
    }

    @Test
    @Order(5)
    @DisplayName("Share Document")
    void shareDocument() throws Exception {
        final boolean success = document.shareDocument(client, adminToken, uploadDocID, ORG6_ADMIN_EMAIL);
        System.out.printf("Shared doc successfully? %b\n\n", success);

        assertTrue(success);
    }

    @Test
    @Order(6)
    @DisplayName("Unshare Document")
    void unshareDocument() throws Exception {
        final long count = document.unshareDocument(client, adminToken, uploadDocID, ORG6_ADMIN_EMAIL);
        System.out.printf("Unshared doc successfully? %b\n\n", count > 0);

        assertTrue(count > 0);
    }

    @Test
    @Order(7)
    @DisplayName("List Documents")
    void listDocuments() throws Exception {
        final List<DocumentInfo> docs = document.listDocuments(client, adminToken);
        assertTrue(docs.size() == 2);
        String docID = docs.get(0).getDocID();
        assertTrue(docID.equals(uploadDocID) || docID.equals(uploadStreamDocID));
    }

    @Test
    @Order(8)
    @DisplayName("Remove Document")
    void removeDocument() throws Exception {
        final boolean success = document.removeDocument(client, adminToken, uploadDocID);
        System.out.printf("Removed doc successfully? %b\n\n", success);

        assertTrue(success);
    }

    @Test
    @Order(9)
    @DisplayName("Encrypt Document using Stream")
    void encryptDocumentStream() throws Exception {
        final File file = new File(docPath + docFilename);
        System.out.printf("Encrypting %s ...\n", docFilename);
        EncryptDocumentResponse encryptDocumentResponse = document.encryptDocumentStream(
                client, adminToken, docFilename, new FileInputStream(file));
        System.out.printf("The document %s has been encrypted using stream.\n", docFilename);
        System.out.printf("  Encrypted document ID is %s\n", encryptDocumentResponse.getDocID());
        System.out.printf("  Encrypted total of bytes is %d\n", encryptDocumentResponse.getCiphertext().length);

        assertNotNull(encryptDocumentResponse.getDocID());
        assertTrue(encryptDocumentResponse.getCiphertext().length > 0);

        encryptStreamDocID = encryptDocumentResponse.getDocID();
        encryptStreamCiphertext = encryptDocumentResponse.getCiphertext();
    }

    @Test
    @Order(10)
    @DisplayName("Decrypt Document using Stream")
    void decryptDocumentStream() throws Exception {
        System.out.printf("Decrypting document %s ...\n", encryptStreamDocID);
        ByteArrayInputStream input = new ByteArrayInputStream(encryptStreamCiphertext);
        byte[] output = document.decryptDocumentStream(client, adminToken, encryptStreamDocID, input);
        System.out.println("The document has been decrypted using stream.");
        System.out.printf("  Downloaded %d bytes using stream\n\n", output.length);

        assertTrue(Arrays.equals(docByteArray, output));
    }

    @Test
    @Order(11)
    @DisplayName("Encrypt Document")
    void encryptDocument() throws Exception {
        final File file = new File(docPath + docFilename);
        System.out.printf("Uploading %s ...\n", docFilename);

        byte[] data = Files.readAllBytes(file.toPath());
        EncryptDocumentResponse res = document.encryptDocument(client, adminToken, docFilename, data);
        encryptDocID = res.getDocID();
        encryptDocCiphertext = res.getCiphertext();
        
        System.out.printf("The document %s (%d bytes) has been encrypted.\n", docFilename, data.length);
        System.out.printf("  Uploaded document ID is %s\n", encryptDocID);

        assertNotNull(encryptDocID);
    }

    @Test
    @Order(12)
    @DisplayName("Decrypt Document")
    void decryptDocument() throws Exception {
        System.out.printf("Decrypting document %s ...\n", encryptDocID);
        final byte[] plaintext = document.decryptDocument(client, adminToken, encryptDocID, encryptDocCiphertext);
        System.out.println("The document has been downloaded.\n");

        assertTrue(plaintext.length > 0);
        assertTrue(Arrays.equals(docByteArray, plaintext));
    }
}