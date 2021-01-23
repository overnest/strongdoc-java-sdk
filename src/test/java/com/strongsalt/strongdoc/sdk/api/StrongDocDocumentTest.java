package com.strongsalt.strongdoc.sdk.api;

import com.google.common.io.ByteStreams;
import com.strongsalt.strongdoc.sdk.api.responses.*;
import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StrongDocDocumentTest {

    private final StrongDocDocument document = new StrongDocDocument();
    private StrongDocServiceClient client1;
    private StrongDocServiceClient client2;

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
    private String e2eeUploadStreamDocID;


    private TestOrg testOrg1;
    private TestOrg testOrg2;
    private TestUser testOrg1Admin;
    private TestUser testOrg2Admin;

    @BeforeAll
    @DisplayName("Register organization and obtain token")
    void setUp() throws Exception {
        TestData registerOrgRes = StrongDocTestSetup.preTest(2, 1);
        registerOrgRes.doRegistration();

        client1 = StrongDocTestSetup.initClient();
        client2 = StrongDocTestSetup.initClient();

        testOrg1 = registerOrgRes.testOrgs[0];
        testOrg1Admin = registerOrgRes.testUsers[0][0];
        testOrg2 = registerOrgRes.testOrgs[1];
        testOrg2Admin = registerOrgRes.testUsers[1][0];

        client1.login(testOrg1.orgName, testOrg1Admin.userEmail, testOrg1Admin.password);
        final boolean success = new StrongDocAccount().addSharableOrg(client1, testOrg2.orgName);
        System.out.printf("Added sharable org successfully? %b\n\n", success);
        assertTrue(success);

        client2.login(testOrg2.orgName, testOrg2Admin.userEmail, testOrg2Admin.password);

        final Path resourceDirectory = Paths.get("src", "test", "resources", "testDocuments");
        docPath = resourceDirectory.toFile().getAbsolutePath() + "/";
        docFilename = "textSample.txt";
        final File file = new File(docPath + docFilename);
        docByteArray = Files.readAllBytes(file.toPath());
    }

    @AfterAll
    @DisplayName("Test tear down")
    void tearDown() throws Exception {
        client1.shutdown();
        client2.shutdown();
    }

    @Test
    @Order(1)
    @DisplayName("Upload Document")
    void uploadDocument() throws Exception {
        final File file = new File(docPath + docFilename);
        System.out.printf("Uploading %s ...\n", docFilename);

        byte[] data = Files.readAllBytes(file.toPath());
        uploadDocID = document.uploadDocument(client1, docFilename, data);
        System.out.printf("The document %s (%d bytes) has been uploaded.\n", docFilename, data.length);
        System.out.printf("  Uploaded document ID is %s\n", uploadDocID);

        assertNotNull(uploadDocID);
    }

    @Test
    @Order(2)
    @DisplayName("Download Document")
    void downloadDocument() throws Exception {
        System.out.printf("Downloading document %s ...\n", uploadDocID);
        final byte[] plaintext = document.downloadDocument(client1, uploadDocID);
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
                client1, docFilename, new FileInputStream(file));
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
        InputStream inputStream = document.downloadDocumentStream(client1, uploadStreamDocID);

        final byte[] buffer = new byte[10];
        int read = 0;

        while ((read = inputStream.read(buffer)) >= 0) {
            if (read > 0) {
                output.write(buffer, 0, read);
//                System.out.println("downloadDocumentStream bytes=" + bytes);
            }
        }

        System.out.println("The document has been downloaded using stream.");
        System.out.printf("  Downloaded %d bytes using stream\n\n", output.size());

        assertEquals(uploadStreamTotalBytes, output.size());
        assertTrue(Arrays.equals(docByteArray, output.toByteArray()));
    }

    @Test
    @Order(5)
    @DisplayName("Share Document")
    void shareDocument() throws Exception {
        ShareDocumentResponse resp = document.bulkShareDocWithUsers(client1, uploadDocID, Arrays.asList(testOrg2Admin.userEmail));
        boolean success = resp.getSharedReceivers().contains(testOrg2Admin.userEmail);
        System.out.printf("Shared doc successfully? %b\n\n", success);

        assertTrue(success);
    }

    @Test
    @Order(6)
    @DisplayName("Unshare Document")
    void unshareDocument() throws Exception {
        final UnshareDocumentResponse resp = document.unshareDocumentWithUser(client1, uploadDocID, testOrg2Admin.userEmail);
        System.out.printf("Unshared doc successfully? %b\n\n", resp.getSuccess());

        assertTrue(resp.getSuccess());
        assertTrue(resp.getAllowed());
        assertTrue(!resp.getAlreadyUnshared());

    }

    @Test
    @Order(7)
    @DisplayName("List Documents")
    void listDocuments() throws Exception {
        final List<DocumentInfo> docs = document.listDocuments(client1);
        assertTrue(docs.size() == 2);
        String docID = docs.get(0).getDocID();
        assertTrue(docID.equals(uploadDocID) || docID.equals(uploadStreamDocID));
    }

    @Test
    @Order(8)
    @DisplayName("Remove Document")
    void removeDocument() throws Exception {
        final boolean success = document.removeDocument(client1, uploadDocID);
        System.out.printf("Removed doc successfully? %b\n\n", success);

        assertTrue(success);
    }

    @Test
    @Order(9)
    @DisplayName("Encrypt Document using Stream")
    void encryptDocumentStream() throws Exception {
        final File file = new File(docPath + docFilename);
        System.out.printf("Encrypting %s ...\n", docFilename);

        EncryptDocumentStreamResponse response = document.encryptDocumentStream(
                client1, docFilename, new FileInputStream(file));
        final byte[] ciphertext = ByteStreams.toByteArray(response.getCipherStream());

        System.out.printf("The document %s has been encrypted using stream.\n", docFilename);
        System.out.printf("  Encrypted document ID is %s\n", response.getDocID());
        System.out.printf("  Encrypted total of bytes is %d\n", ciphertext.length);

        assertNotNull(response.getDocID());
        assertTrue(ciphertext.length > 0);

        encryptStreamDocID = response.getDocID();
        encryptStreamCiphertext = ciphertext;
    }

    @Test
    @Order(10)
    @DisplayName("Decrypt Document using Stream")
    void decryptDocumentStream() throws Exception {
        System.out.printf("Decrypting document %s ...\n", encryptStreamDocID);
        ByteArrayInputStream input = new ByteArrayInputStream(encryptStreamCiphertext);
        InputStream plainStream = document.decryptDocumentStream(client1, encryptStreamDocID, input);
        final byte[] plaintext = ByteStreams.toByteArray(plainStream);
        System.out.println("The document has been decrypted using stream.");
        System.out.printf("  Downloaded %d bytes using stream\n\n", plaintext.length);

        assertTrue(Arrays.equals(docByteArray, plaintext));
    }

    @Test
    @Order(11)
    @DisplayName("Encrypt Document")
    void encryptDocument() throws Exception {
        final File file = new File(docPath + docFilename);
        System.out.printf("Uploading %s ...\n", docFilename);

        byte[] data = Files.readAllBytes(file.toPath());
        EncryptDocumentResponse res = document.encryptDocument(client1, docFilename, data);
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
        final byte[] plaintext = document.decryptDocument(client1, encryptDocID, encryptDocCiphertext);
        System.out.println("The document has been downloaded.\n");

        assertTrue(plaintext.length > 0);
        assertTrue(Arrays.equals(docByteArray, plaintext));
    }

    @Test
    @Order(13)
    @DisplayName("Upload Document using Stream")
    void e2eeUploadDocumentStream() throws Exception {
        final File file = new File(docPath + docFilename);
        //final File file = new File(docPath + "smallpicture.bmp");
        docByteArray = Files.readAllBytes(file.toPath());
        System.out.printf("Client-Side Uploading %s ...\n", docFilename);
        UploadDocumentResponse uploadDocumentResponse = document.e2eeUploadDocumentStream(
                client1, docFilename, new FileInputStream(file));
        System.out.printf("The document %s has been uploaded using stream.\n", docFilename);
        System.out.printf("  Uploaded document ID is %s\n", uploadDocumentResponse.getDocID());
        System.out.printf("  Uploaded total of bytes is %d\n", uploadDocumentResponse.getNumBytes());

        assertNotNull(uploadDocumentResponse.getDocID());
        assertTrue(uploadDocumentResponse.getNumBytes() > 0);

        e2eeUploadStreamDocID = uploadDocumentResponse.getDocID();
        uploadStreamTotalBytes = uploadDocumentResponse.getNumBytes();
    }

    @Test
    @Order(14)
    @DisplayName("Download Document using Stream")
    void e2eeDownloadDocumentStream() throws Exception {
        System.out.printf("Client-Side Downloading document %s ...\n", e2eeUploadStreamDocID);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        InputStream inputStream = document.downloadDocumentStream(client1, e2eeUploadStreamDocID);

        final byte[] buffer = new byte[10];
        int read = 0;

        while ((read = inputStream.read(buffer)) >= 0) {
            if (read > 0) {
                output.write(buffer, 0, read);
                //System.out.println("downloadDocumentStream bytes=" + read);
            }
        }

        System.out.println("The document has been downloaded using stream.");
        System.out.printf("  Downloaded %d bytes using stream\n\n", output.size());

        assertEquals(uploadStreamTotalBytes, output.size());
        assertTrue(Arrays.equals(docByteArray, output.toByteArray()));
    }
}
