package com.strongsalt.strongdoc.sdk.api;

import com.strongsalt.strongdoc.sdk.api.responses.DocumentInfo;
import com.strongsalt.strongdoc.sdk.api.responses.EncryptDocumentResponse;
import com.strongsalt.strongdoc.sdk.api.responses.RegisterOrganizationResponse;
import com.strongsalt.strongdoc.sdk.api.responses.UploadDocumentResponse;
import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient;
import com.strongsalt.strongdoc.sdk.exceptions.StrongDocServiceException;
import org.json.JSONException;
import org.junit.jupiter.api.*;

import java.io.*;
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

    private StrongDocTestSetup testSetup;
    private StrongDocTestTeardown testTeardown;
    private StrongDocDocument document;

    private String org4ID;
    private String org6ID;

    private String org4AdminToken;
    private String org6AdminToken;

    private String docPath;
    private String docFilename;
    private byte[] docByteArray;

    private String uploadDocID;
    private String uploadStreamDocID;
    private String uploadStreamE2EEDocID;
    private long uploadStreamTotalBytes;
    private long uploadStreamE2EETotalBytes;
    private String encryptDocID;
    private byte[] encryptDocCiphertext;
    private String encryptStreamDocID;
    private byte[] encryptStreamCiphertext;

    @BeforeAll
    @DisplayName("Register organization and obtain token")
    void setUp() throws Exception {
        testSetup = new StrongDocTestSetup();
        testTeardown = new StrongDocTestTeardown();
        document = new StrongDocDocument();

        registerOrganizations();
        getTokens();

        final Path resourceDirectory = Paths.get("src", "test", "resources", "testDocuments");
        docPath = resourceDirectory.toFile().getAbsolutePath() + "/";
        docFilename = "textSample.txt";
        final File file = new File(docPath + docFilename);
        docByteArray = Files.readAllBytes(file.toPath());
    }

    @AfterAll
    @DisplayName("Remove organization")
    void tearDown() throws Exception {
        testSetup.exit();
        removeOrganizations();
        testTeardown.exit();
    }

    @Test
    @Order(1)
    @DisplayName("Upload Document")
    void uploadDocument() throws Exception {
        final File file = new File(docPath + docFilename);
        System.out.printf("Uploading %s ...\n", docFilename);

        byte[] data = Files.readAllBytes(file.toPath());
        uploadDocID = document.uploadDocument(org4AdminToken, docFilename, data);
        System.out.printf("The document %s (%d bytes) has been uploaded.\n", docFilename, data.length);
        System.out.printf("  Uploaded document ID is %s\n", uploadDocID);

        assertNotNull(uploadDocID);
    }

    @Test
    @Order(2)
    @DisplayName("Download Document")
    void downloadDocument() throws Exception {
        System.out.printf("Downloading document %s ...\n", uploadDocID);
        final byte[] plaintext = document.downloadDocument(org4AdminToken, uploadDocID);
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
        UploadDocumentResponse uploadDocumentResponse = document.uploadDocumentStream(org4AdminToken, docFilename, new FileInputStream(file));
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
        document.downloadDocumentStream(org4AdminToken, uploadStreamDocID, output);
        System.out.println("The document has been downloaded using stream.");
        System.out.printf("  Downloaded %d bytes using stream\n\n", output.size());

        assertEquals(uploadStreamTotalBytes, output.size());
        assertTrue(Arrays.equals(docByteArray, output.toByteArray()));
    }

    @Test
    @Order(5)
    @DisplayName("Share Document")
    void shareDocument() throws Exception {
        final boolean success = document.shareDocument(org4AdminToken, uploadDocID, ORG6_ADMIN_EMAIL);
        System.out.printf("Shared doc successfully? %b\n\n", success);

        assertTrue(success);
    }

    @Test
    @Order(6)
    @DisplayName("Unshare Document")
    void unshareDocument() throws Exception {
        final long count = document.unshareDocument(org4AdminToken, uploadDocID, ORG6_ADMIN_EMAIL);
        System.out.printf("Unshared doc successfully? %b\n\n", count > 0);

        assertTrue(count > 0);
    }

    @Test
    @Order(7)
    @DisplayName("List Documents")
    void listDocuments() throws Exception {
        final List<DocumentInfo> docs = document.listDocuments(org4AdminToken);
        assertTrue(docs.size() == 2);
        String docID = docs.get(0).getDocID();
        assertTrue(docID.equals(uploadDocID) || docID.equals(uploadStreamDocID));
    }

    @Test
    @Order(8)
    @DisplayName("Remove Document")
    void removeDocument() throws Exception {
        final boolean success = document.removeDocument(org4AdminToken, uploadDocID);
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
                org4AdminToken, docFilename, new FileInputStream(file));
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
        byte[] output = document.decryptDocumentStream(org4AdminToken, encryptStreamDocID, input);
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
        EncryptDocumentResponse res = document.encryptDocument(org4AdminToken, docFilename, data);
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
        final byte[] plaintext = document.decryptDocument(org4AdminToken, encryptDocID, encryptDocCiphertext);
        System.out.println("The document has been downloaded.\n");

        assertTrue(plaintext.length > 0);
        assertTrue(Arrays.equals(docByteArray, plaintext));
    }


//    @Test
//    @Order(13)
//    @DisplayName("Upload Document E2EE using Stream")
//    void uploadDocumentStreamE2EE() throws Exception {
//        final File file = new File(docPath + docFilename);
//        System.out.printf("Uploading %s ...\n", docFilename);
//        UploadDocumentResponse uploadDocumentResponse = document.UploadDocumentStreamE2EE(org4AdminToken, docFilename, new FileInputStream(file));
//        System.out.printf("The document %s has been uploaded using E2EE stream.\n", docFilename);
//        System.out.printf("  Uploaded document ID is %s\n", uploadDocumentResponse.getDocID());
//        System.out.printf("  Uploaded total of bytes is %d\n", uploadDocumentResponse.getNumBytes());
//
//        assertNotNull(uploadDocumentResponse.getDocID());
//        assertTrue(uploadDocumentResponse.getNumBytes() > 0);
//
//        uploadStreamE2EEDocID = uploadDocumentResponse.getDocID();
//        uploadStreamE2EETotalBytes = uploadDocumentResponse.getNumBytes();
//    }
//
//    @Test
//    @Order(14)
//    @DisplayName("Download Document E2EE using Stream")
//    void downloadDocumentE2EEStream() throws Exception {
//        System.out.printf("Downloading document %s ...\n", uploadStreamE2EEDocID);
//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        document.downloadDocumentStream(org4AdminToken, uploadStreamE2EEDocID, output);
//        System.out.println("The document has been downloaded using stream.");
//        System.out.printf("  Downloaded %d bytes using stream\n\n", output.size());
//
//        assertEquals(uploadStreamE2EETotalBytes, output.size());
//        assertTrue(Arrays.equals(docByteArray, output.toByteArray()));
//    }

    @Order(15)
    @DisplayName("Share Document E2EE")
    void shareDocumentE2EE() throws Exception {
        final boolean success = document.shareDocument(org4AdminToken, uploadDocID, ORG6_ADMIN_EMAIL);
        System.out.printf("Shared doc successfully? %b\n\n", success);

        assertTrue(success);
    }

    @Test
    @Order(16)
    @DisplayName("Unshare Document E2EE")
    void unshareDocumentE2EE() throws Exception {
        final long count = document.unshareDocument(org4AdminToken, uploadDocID, ORG6_ADMIN_EMAIL);
        System.out.printf("Unshared doc successfully? %b\n\n", count > 0);

        assertTrue(count > 0);
    }
    private void registerOrganizations() throws StrongDocServiceException {
        RegisterOrganizationResponse resp6 = testSetup.registerOrganization(
                ORG6_NAME, ORG6_ADMIN_EMAIL, ORG6_ADDRESS, ORG6_ADMIN_NAME,
                ORG6_ADMIN_PASSWORD, ORG6_ADMIN_EMAIL, new String[]{},
                false, SOURCE, SOURCE_DATA);
        org6ID = resp6.getOrgID();
        RegisterOrganizationResponse resp4 = testSetup.registerOrganization(
                ORG4_NAME, ORG4_ADMIN_EMAIL, ORG4_ADDRESS, ORG4_ADMIN_NAME,
                ORG4_ADMIN_PASSWORD, ORG4_ADMIN_EMAIL, new String[]{ORG6_NAME},
                false, SOURCE, SOURCE_DATA);
        org4ID = resp4.getOrgID();
    }

    private void getTokens() throws StrongDocServiceException {
        org4AdminToken = testSetup.getToken(
                ORG4_NAME, ORG4_ADMIN_EMAIL, ORG4_ADMIN_PASSWORD);
        org6AdminToken = testSetup.getToken(
                ORG6_NAME, ORG6_ADMIN_EMAIL, ORG6_ADMIN_PASSWORD);
    }

    private void removeOrganizations() throws IOException, JSONException {
        testTeardown.hardRemoveOrganization(org4ID);
        testTeardown.hardRemoveOrganization(org6ID);
    }
}