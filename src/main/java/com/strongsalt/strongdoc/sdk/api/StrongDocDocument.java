/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api;

import com.google.common.io.ByteStreams;
import com.google.protobuf.ByteString;
import com.strongsalt.crypto.exception.StrongSaltKeyException;
import com.strongsalt.crypto.key.StrongSaltKey;
import com.strongsalt.crypto.key.midstream.Decryptor;
import com.strongsalt.crypto.key.midstream.Encryptor;
import com.strongsalt.strongdoc.sdk.api.responses.*;
import com.strongsalt.strongdoc.sdk.client.JwtCallCredential;
import com.strongsalt.strongdoc.sdk.client.StrongDocManager;
import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient;
import com.strongsalt.strongdoc.sdk.exceptions.StrongDocDocumentException;
import com.strongsalt.strongdoc.sdk.exceptions.StrongDocServiceException;
import com.strongsalt.strongdoc.sdk.proto.*;
import com.strongsalt.strongdoc.sdk.utils.StrongDocUtils;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import javax.print.Doc;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;


/**
 * This class can be used to perform actions that are related to documents.
 */
public class StrongDocDocument {
    /**
     * The chunk size
     */
    private static final int BLOCK_SIZE = 1024 * 1024; // 1 MB
    /**
     * The document ID for the streamed document
     */
    private String streamedDocumentId;
    /**
     * The total number of bytes streamed
     */
    private int uploadedDocumentSize;
    /**
     * client
     */
    private static StrongDocServiceClient client;

    /**
     * Set the document ID for the streamed document
     *
     * @param docID The document ID of the streamed document
     */
    protected void setStreamedDocumentId(final String docID) {
        streamedDocumentId = docID;
    }

    /**
     * Get the document ID for the streamed document
     *
     * @return The document ID of the streamed document
     */
    protected String getStreamedDocumentId() {
        return streamedDocumentId;
    }

    /**
     * Set the total number of bytes streamed
     *
     * @param size The total number of bytes streamed
     */
    protected void setUploadedDocumentSize(final int size) {
        uploadedDocumentSize = size;
    }

    /**
     * Get the total number of bytes streamed
     *
     * @return The total number of bytes streamed
     */
    public int getUploadedDocumentSize() {
        return uploadedDocumentSize;
    }

    // ---------------------------------- UploadDocumentStream ----------------------------------

    /**
     * Uploads a document to Strongdoc provided storage.
     *
     * @param token      The user JWT token.
     * @param docName    The name of the document.
     * @param dataStream The stream where the uploaded document will be read from.
     * @return The upload response.
     * @throws InterruptedException on the thread is interrupted
     */
    public UploadDocumentResponse uploadDocumentStream(final String token,
                                                       final String docName,
                                                       final InputStream dataStream)
            throws InterruptedException, StrongDocServiceException {
        if (client == null) client = StrongDocManager.getInstance().getStrongDocClient();

        final CountDownLatch finishLatch = new CountDownLatch(1);
        final StreamObserver<Documents.UploadDocStreamResp> responseObserver =
                new StreamObserver<Documents.UploadDocStreamResp>() {

                    private String documentId = "";

                    @Override
                    public void onNext(final Documents.UploadDocStreamResp value) {
                        if (documentId == "") {
                            documentId = value.getDocID();
                        }
                    }

                    @Override
                    public void onError(final Throwable t) {
                        t.printStackTrace();
                        finishLatch.countDown();
                    }

                    @Override
                    public void onCompleted() {
                        setStreamedDocumentId(documentId);
                        finishLatch.countDown();
                    }
                };

        final StreamObserver<Documents.UploadDocStreamReq> requestObserver = client.getAsyncStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token))
                .uploadDocumentStream(responseObserver);

        try {
            Documents.UploadDocStreamReq req = Documents.UploadDocStreamReq.newBuilder()
                    .setDocName(docName).build();
            requestObserver.onNext(req);

            final int bufferSize = BLOCK_SIZE;
            final byte[] buffer = new byte[bufferSize];
            int read = 0;
            int size = 0;
            while ((read = dataStream.read(buffer)) > 0) {
                size += read;
//              System.out.printf("Uploaded %d bytes, total %d bytes uploaded.\n", read, size);
                final ByteString byteString = ByteString.copyFrom(buffer, 0, read);
                req = Documents.UploadDocStreamReq.newBuilder().setPlaintext(byteString).build();
                requestObserver.onNext(req);
            }
            setUploadedDocumentSize(size);
            dataStream.close();
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (final RuntimeException e) {
            requestObserver.onError(e);
            throw e;
        }

        requestObserver.onCompleted();

        // Can set a time limit if desired.
        // Use await(10, TimeUnit.MINUTES)
        finishLatch.await();

        return new UploadDocumentResponse(getStreamedDocumentId(), getUploadedDocumentSize());
    }

    // ---------------------------------- UploadDocumentStreamE2EE ----------------------------------

    /**
     * Uploads a document to Strongdoc provided storage with client-side encryption
     *
     * @param token      The user JWT token.
     * @param docName    The name of the document.
     * @param dataStream The stream where the uploaded document will be read from.
     * @return The upload response.
     */
    public UploadDocumentResponse UploadDocumentStreamE2EE(final String token,
                                                      final String docName,
                                                      final InputStream dataStream) throws StrongDocDocumentException, InterruptedException, StrongDocServiceException {
        if (client == null) client = StrongDocManager.getInstance().getStrongDocClient();

        // send request, get GetOwnKeysResponse
        Encryption.GetOwnKeysReq getOwnKeysReq = Encryption.GetOwnKeysReq.newBuilder().build();
        Encryption.GetOwnKeysResp resp = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token))
                .getOwnKeys(getOwnKeysReq);

        // begin streaming
        final CountDownLatch finishLatch = new CountDownLatch(1);
        // todo check, response once, send a lot of times
        final StreamObserver<Documents.UploadDocStreamResp> responseObserver =
                new StreamObserver<Documents.UploadDocStreamResp>() {

                    private String documentId = "";

                    @Override
                    public void onNext(final Documents.UploadDocStreamResp value) {
                        if (documentId == "") {
                            documentId = value.getDocID();
                        }
                    }

                    @Override
                    public void onError(final Throwable t) {
                        t.printStackTrace();
                        finishLatch.countDown();
                    }

                    @Override
                    public void onCompleted() {
                        setStreamedDocumentId(documentId);
                        finishLatch.countDown();
                    }
                };

        final StreamObserver<Documents.UploadDocStreamReq> requestObserver = client.getAsyncStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token))
                .uploadDocumentStream(responseObserver);

        try {
            /**
             *
             * step1: preMeta data
             * parse user public key from response
             * deserialize user public key
             * generate doc key and MAC key
             * encrypt keys with user public key
             * build UploadDocStreamRequest
             * */
            Encryption.UserPubKeys keys = resp.getUserPubKeys();
            System.out.println("deserializeUserPubKeys");
            UserPubKeysInfo keysInfo = UserPubKeysInfo.deserializeUserPubKeys(keys);
            System.out.println("deserializeUserPubKeys done");
            KeyInfo userPubKeyInfo = keysInfo.getToUserKey();
            List<KeyInfo> orgPubKeys = keysInfo.getToOrgKeys();

            StrongSaltKey docKey = StrongSaltKey.GenerateKey(StrongSaltKey.KeyType.XCHACHA20);
            byte[] serialDocKey = docKey.serialize();
            StrongSaltKey macKey = StrongSaltKey.GenerateKey(StrongSaltKey.KeyType.HMACSHA512);
            byte[] serialMacKey = macKey.serialize();

            Encryption.EncryptedKey protoUserEncryptDocKey = userPubKeyInfo.buildEncryptedKey(serialDocKey);
            Encryption.EncryptedKey protoUserEncryptMACKey = userPubKeyInfo.buildEncryptedKey(serialMacKey);

            List<Encryption.EncryptedKey> protoOrgEncryptDocKeys = new ArrayList<>();
            List<Encryption.EncryptedKey> protoOrgEncryptMACKeys = new ArrayList<>();
            for (KeyInfo orgPubKeyInfo : orgPubKeys) {
                protoOrgEncryptDocKeys.add(orgPubKeyInfo.buildEncryptedKey(serialDocKey));
                protoOrgEncryptMACKeys.add(orgPubKeyInfo.buildEncryptedKey(serialMacKey));
            }

            Documents.UploadDocPreMetadata preMetadata = Documents.UploadDocPreMetadata.newBuilder()
                    .setDocName(docName)
                    .setClientSide(true)
                    .setUserEncDocKey(protoUserEncryptDocKey)
                    .setUserEncMACKey(protoUserEncryptMACKey)
                    .addAllOrgEncDocKeys(protoOrgEncryptDocKeys)
                    .addAllOrgEncMACKeys(protoOrgEncryptMACKeys)
                    .build();

            Documents.UploadDocStreamReq req = Documents.UploadDocStreamReq.newBuilder()
                    .setPreMetadata(preMetadata)
                    .build();
            System.out.println("build preMetaData request");
            requestObserver.onNext(req);


            /**
             *
             * step2: cipher data
             * initialize stream encryptor with docKey
             * read data from inputStream, do encryption, send to server
             *
             * */
            Encryptor encryptor = docKey.encryptStream();
            final int bufferSize = BLOCK_SIZE;
            final byte[] buffer = new byte[bufferSize];
            int read;
            int size = 0; // total number of read_bytes
            while ((read = dataStream.read(buffer)) > 0) {
                size += read;
                // read plaintext from stream
                byte[] plain = StrongDocUtils.arrayCopy(buffer, 0, read);
                // do encryption
                encryptor.write(plain);
                int nRead = encryptor.read(buffer);
                byte[] cipher= StrongDocUtils.arrayCopy(buffer, 0, nRead);
                macKey.MACWrite(cipher);
                // send cipher to server
                final ByteString byteString = ByteString.copyFrom(cipher, 0, cipher.length);
                req = Documents.UploadDocStreamReq.newBuilder().setData(byteString).build();
                requestObserver.onNext(req);
            }
            // todo readLast
            byte[] lastCipher = encryptor.readLast();
            if(lastCipher != null && lastCipher.length > 0){
                final ByteString byteString = ByteString.copyFrom(lastCipher, 0, lastCipher.length);
                req = Documents.UploadDocStreamReq.newBuilder().setData(byteString).build();
                requestObserver.onNext(req);
                size += lastCipher.length;
                macKey.MACWrite(lastCipher);
            }
            System.out.println("done with upload data");
            /**
             *
             * step3: postMeta data
             * compute mac value, send to server
             * update uploaded document size
             * close inputStream
             * */
            byte[] cipherMAC = macKey.MACSum();
            Documents.UploadDocPostMetadata postMetadata = Documents.UploadDocPostMetadata.newBuilder()
                    .setMac(StrongDocUtils.encodeWithBase64(cipherMAC))
                    .build();
            req = Documents.UploadDocStreamReq.newBuilder().setPostMetadata(postMetadata).build();
            requestObserver.onNext(req);
            size += cipherMAC.length;
            setUploadedDocumentSize(size); //todo extract size from response
            dataStream.close();
            System.out.println("postMeat data");
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (final RuntimeException e) {
            requestObserver.onError(e);
            throw e;
        } catch (final StrongSaltKeyException e){
//            e.printStackTrace();
            throw new StrongDocDocumentException("cannot do streaming encryption/MAC");
        }

        requestObserver.onCompleted();
        finishLatch.await(); //wait util response received

        return new UploadDocumentResponse(getStreamedDocumentId(), getUploadedDocumentSize());

    }

    // ---------------------------------- DownloadDocumentStream ----------------------------------

    /**
     * Downloads any document previously stored on Strongdoc provided storage.
     *
     * @param token  The user JWT token.
     * @param docID  The ID of the document.
     * @param output The stream to where the downloaded document will be written to.
     * @throws InterruptedException on the thread is interrupted
     */
    public void downloadDocumentStream(final String token,
                                       final String docID,
                                       final ByteArrayOutputStream output)
            throws InterruptedException, StrongDocServiceException {
        if (client == null) client = StrongDocManager.getInstance().getStrongDocClient();

        final CountDownLatch finishLatch = new CountDownLatch(1);

        final StreamObserver<Documents.DownloadDocStreamResp> responseObserver =
                new StreamObserver<Documents.DownloadDocStreamResp>() {

                    @Override
                    public void onNext(final Documents.DownloadDocStreamResp value) {
                        final ByteString downloadedBytes = value.getPlaintext();
//                      System.out.printf("Downloaded %d bytes\n", downloadedBytes.size());
                        if (!downloadedBytes.isEmpty()) {
                            try {
                                downloadedBytes.writeTo(output);
                            } catch (final IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(final Throwable t) {
                        t.printStackTrace();
                        finishLatch.countDown();
                    }

                    @Override
                    public void onCompleted() {
                        finishLatch.countDown();
                        try {
                            // Close is not needed since it has no effect.
                            // The methods in this class can be called after the stream has been closed.
                            // But, close it anyway.
                            output.close();
                        } catch (final IOException e) {
                            e.printStackTrace();
                        }
                    }
                };

        try {
            final Documents.DownloadDocStreamReq req = Documents.DownloadDocStreamReq.newBuilder()
                    .setDocID(docID).build();
            client.getAsyncStub().withCallCredentials(JwtCallCredential.getCallCredential(token))
                    .downloadDocumentStream(req, responseObserver);
        } catch (final RuntimeException e) {
            responseObserver.onError(e);
            throw e;
        }

        // Can set a time limit if desired.
        // Use await(10, TimeUnit.MINUTES)
        finishLatch.await();

        // The downloaded document has been written to the passed-in stream already
    }

    // ---------------------------------- downloadDocumentStreamE2EE ----------------------------------
    /**
     *
     * @param token JWT token
     * @param docID the id of doc
     * @param output outputStream
     * @return true: valid output; false: mac verification failed
     * @throws StrongDocDocumentException
     */
    public boolean downloadDocumentStreamE2EE(final String token,
                                           final String docID,
                                           final ByteArrayOutputStream output)
            throws StrongDocDocumentException, StrongDocServiceException {
        if (client == null) client = StrongDocManager.getInstance().getStrongDocClient();

        /**
         *
         * prepareDownloadDoc
         * build prepareDownloadDocRequest
         * deserialize DocumentAccessMetaData, parse docKey, macKey, MAC value
         * generate decryptor with docKey
         *
         * */
        Documents.PrepareDownloadDocReq prepareDownloadDocReq = Documents.PrepareDownloadDocReq.newBuilder().setDocID(docID).build();
        Documents.PrepareDownloadDocResp resp = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token))
                .prepareDownloadDoc(prepareDownloadDocReq);
        Documents.DocumentAccessMetadata documentAccessMetadata = resp.getDocumentAccessMetadata();

        DocumentAccessMetadataInfo documentAccessMetadataInfo = DocumentAccessMetadataInfo.deserialize(documentAccessMetadata);
        StrongSaltKey docKey = documentAccessMetadataInfo.getDocKey();
        StrongSaltKey macKey = documentAccessMetadataInfo.getMACKey();
        byte[] expectedMac = StrongDocUtils.decodeWithBase64(documentAccessMetadata.getMac());

        Decryptor decryptor;
        try {
            decryptor = docKey.decryptStream(0);
        } catch (StrongSaltKeyException e){
            throw new StrongDocDocumentException("fail to initialize decryptor");
        }

        // start streaming
        byte[] buffer = new byte[BLOCK_SIZE]; //todo check buffer size
        final CountDownLatch finishLatch = new CountDownLatch(1);
        final StreamObserver<Documents.DownloadDocStreamResp> responseObserver =
                new StreamObserver<Documents.DownloadDocStreamResp>() {

                    @Override
                    public void onNext(final Documents.DownloadDocStreamResp value) {
                        final ByteString downloadedBytes = value.getData(); //todo getData?
                        System.out.printf("Downloaded %d bytes\n", downloadedBytes.size());
                        /**
                         *
                         * step1: decryption
                         * receive data
                         * do decryption
                         * perform macWrite
                         *
                         * */
                        if (!downloadedBytes.isEmpty()) {
                            byte[] ciphertext = downloadedBytes.toByteArray();
                            try {
                                decryptor.write(ciphertext);
                                int nRead = decryptor.read(buffer);
                                output.write(buffer, 0, nRead);
                                byte[] plain = StrongDocUtils.arrayCopy(buffer, 0, nRead);
                                macKey.MACWrite(plain);
                            } catch (StrongSaltKeyException e){
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(final Throwable t) {
                        t.printStackTrace();
                        finishLatch.countDown();
                    }

                    @Override
                    public void onCompleted() {
                        /**
                         *
                         * step2: handle remaining plaintext
                         * read remaining plaintext from decryptor
                         * perform macWrite
                         *
                         * */
                        try {
                            byte[] lastPlain = decryptor.readLast();
                            output.write(lastPlain);
                            macKey.MACWrite(lastPlain);
                        } catch (StrongSaltKeyException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        finishLatch.countDown();
                        try {
                            output.close();
                        } catch (final IOException e) {
                            e.printStackTrace();
                        }
                    }
                };

        try {
            final Documents.DownloadDocStreamReq req = Documents.DownloadDocStreamReq.newBuilder()
                    .setDocID(docID).build();
            client.getAsyncStub().withCallCredentials(JwtCallCredential.getCallCredential(token))
                    .downloadDocumentStream(req, responseObserver);
        } catch (final RuntimeException e) {
            responseObserver.onError(e);
            throw e;
        }

        try{
            finishLatch.await();
        }catch (InterruptedException e){
            throw new StrongDocDocumentException("failed to wait");
        }
        /**
         *
         * step3: verification
         * verify mac result
         * return verification result
         * */
        try{
            return macKey.MACVerify(expectedMac);
        }catch (StrongSaltKeyException e){
            throw new StrongDocDocumentException("failed to MACVerify");
        }
    }


    // ---------------------------------- UploadDocument ----------------------------------
    // proto.UploadDocReq

    /**
     * Uploads a document to Strongdoc provided storage.
     *
     * @param token     The user JWT token.
     * @param docName   The name of the document to upload.
     * @param plaintext The data of the document to upload.
     * @return The ID of the document uploaded.
     * @throws InterruptedException
     */
    public String uploadDocument(final String token,
                                 final String docName,
                                 final byte[] plaintext)
            throws InterruptedException, StrongDocServiceException {

        InputStream inputStream = new ByteArrayInputStream(plaintext);
        UploadDocumentResponse response = uploadDocumentStream(token, docName, inputStream);
        return response.getDocID();
    }

    // ---------------------------------- DownloadDocument ----------------------------------
    // proto.DownloadDocReq

    /**
     * Downloads a document stored in Strongdoc provided storage.
     *
     * @param token  The user JWT token.
     * @param docID  The ID of the document to download.
     * @return The decrypted data of the downloaded document.
     * @throws InterruptedException
     */
    public byte[] downloadDocument(final String token,
                                   final String docID)
            throws InterruptedException, StrongDocServiceException {

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        downloadDocumentStream(token, docID, os);
        return os.toByteArray();
    }

    // ---------------------------------- ShareDocument ----------------------------------

    /**
     * Shares a document to another user.
     *
     * @param token  The user JWT token.
     * @param docID  The ID of the document to share.
     * @param userID The user ID to share it to.
     * @return Whether the operation was successful.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public Boolean shareDocument(final String token,
                                 final String docID,
                                 final String userID)
            throws StatusRuntimeException, StrongDocServiceException {
        if (client == null) client = StrongDocManager.getInstance().getStrongDocClient();

        final Documents.ShareDocumentReq req = Documents.ShareDocumentReq.newBuilder()
                .setDocID(docID)
                .setUserID(userID)
                .build();

        final Documents.ShareDocumentResp res = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).shareDocument(req);
        return res.getSuccess();
    }

    // ---------------------------------- ShareDocument ----------------------------------

    /**
     * Shares a document to another user.
     *
     * @param token  The user JWT token.
     * @param docID  The ID of the document to share.
     * @param userID The user ID to share it to.
     * @return Whether the operation was successful.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public Boolean shareDocumentE2EE(final String token,
                                 final String docID,
                                 final String userID)
            throws StatusRuntimeException, StrongDocServiceException, StrongDocDocumentException, StrongSaltKeyException {
        if (client == null) client = StrongDocManager.getInstance().getStrongDocClient();

        Documents.PrepareShareDocumentReq prepareShareDocumentReq = Documents.PrepareShareDocumentReq.newBuilder()
                .setDocID(docID)
                .setUserID(userID)
                .build();
        Documents.PrepareShareDocumentResp prepareResp = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token))
                .prepareShareDocument(prepareShareDocumentReq);
        Documents.DocumentAccessMetadata accessMetadata = prepareResp.getDocumentAccessMetadata();
        DocumentAccessMetadataInfo accessMetadataInfo = DocumentAccessMetadataInfo.deserialize(accessMetadata);

        boolean success;
        if (accessMetadata.getIsEncryptedByClientSide()){
            byte[] serialDocKey = accessMetadataInfo.getSerializedDocKey();
            byte[] serialMACKey = accessMetadataInfo.getSerializedMACKey();
            Encryption.UserPubKeys keys = prepareResp.getToUserPubKeys();
            UserPubKeysInfo keysInfo = UserPubKeysInfo.deserializeUserPubKeys(keys);
            List<KeyInfo> orgPubKeys = keysInfo.getToOrgKeys();

            KeyInfo userPubKeyInfo = keysInfo.getToUserKey();
            Encryption.EncryptedKey protoUserEncryptDocKey = userPubKeyInfo.buildEncryptedKey(serialDocKey);
            Encryption.EncryptedKey protoUserEncryptMACKey = userPubKeyInfo.buildEncryptedKey(serialMACKey);

            List<Encryption.EncryptedKey> protoOrgEncryptDocKeys = new ArrayList<>();
            List<Encryption.EncryptedKey> protoOrgEncryptMACKeys = new ArrayList<>();
            for (KeyInfo orgPubKeyInfo : orgPubKeys) {
                protoOrgEncryptDocKeys.add(orgPubKeyInfo.buildEncryptedKey(serialDocKey));
                protoOrgEncryptMACKeys.add(orgPubKeyInfo.buildEncryptedKey(serialMACKey));
            }

            Documents.ShareDocumentReq req = Documents.ShareDocumentReq.newBuilder()
                    .setDocID(docID)
                    .setUserID(userID)
                    .setUserEncDocKey(protoUserEncryptDocKey)
                    .setUserEncMACKey(protoUserEncryptMACKey)
                    .addAllOrgEncDocKeys(protoOrgEncryptDocKeys)
                    .addAllOrgEncMACKeys(protoOrgEncryptMACKeys)
                    .build();

            Documents.ShareDocumentResp res = client.getBlockingStub()
                    .withCallCredentials(JwtCallCredential.getCallCredential(token)).shareDocument(req);
            success = res.getSuccess();
        }else{
            Documents.ShareDocumentReq req = Documents.ShareDocumentReq.newBuilder()
                    .setDocID(docID)
                    .setUserID(userID)
                    .build();

            Documents.ShareDocumentResp res = client.getBlockingStub()
                    .withCallCredentials(JwtCallCredential.getCallCredential(token)).shareDocument(req);
            success = res.getSuccess();
        }
        return success;
    }

    // ---------------------------------- UnshareDocument ----------------------------------

    /**
     * Unshares a document that had previously been shared to a user.
     *
     * @param token  The user JWT token.
     * @param docID  The ID of the document to unshare.
     * @param userID The user ID to unshare it to.
     * @return The unshared document count.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public long unshareDocument(final String token,
                                final String docID,
                                final String userID)
            throws StatusRuntimeException, StrongDocServiceException {
        if (client == null) client = StrongDocManager.getInstance().getStrongDocClient();

        final Documents.UnshareDocumentReq req = Documents.UnshareDocumentReq.newBuilder()
                .setDocID(docID)
                .setUserID(userID)
                .build();

        final Documents.UnshareDocumentResp res = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).unshareDocument(req);
        return res.getCount();
    }

    // ---------------------------------- ListDocuments ----------------------------------

    /**
     * Lists the documents the user can access.
     * An administrator can see all documents belonging to the organization.
     *
     * @param token  The user JWT token.
     * @return The list of document info.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public ArrayList<DocumentInfo> listDocuments(final String token)
            throws StatusRuntimeException, StrongDocServiceException {
        if (client == null) client = StrongDocManager.getInstance().getStrongDocClient();

        final Documents.ListDocumentsReq req = Documents.ListDocumentsReq.newBuilder().build();

        final Documents.ListDocumentsResp res = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).listDocuments(req);
        final ArrayList<DocumentInfo> docInfoList = new ArrayList<DocumentInfo>();
        for (Documents.ListDocumentsResp.Document document : res.getDocumentsList()) {
            docInfoList.add(new DocumentInfo(document.getDocID(), document.getDocName(), document.getSize()));
        }

        return docInfoList;
    }

    // ---------------------------------- RemoveDocument ----------------------------------

    /**
     * Removes a document from the service.
     * An administrator can remove document for the whole organization.
     * A 'regular' user only can remove document for him/herself.
     *
     * @param token  The user JWT token.
     * @param docID  The ID of the document.
     * @return Whether the removal was a success
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public Boolean removeDocument(final String token,
                                  final String docID)
            throws StatusRuntimeException, StrongDocServiceException {
        if (client == null) client = StrongDocManager.getInstance().getStrongDocClient();

        final Documents.RemoveDocumentReq req = Documents.RemoveDocumentReq.newBuilder()
                .setDocID(docID)
                .build();

        final Documents.RemoveDocumentResp res = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).removeDocument(req);
        return res.getStatus();
    }

    // ---------------------------------- EncryptDocumentStream ----------------------------------

    /**
     * Encrypts a document using the service, but do not store it.
     * The encrypted ciphertext will be returned.
     *
     * @param token      The user JWT token.
     * @param docName    The name of the document.
     * @param dataStream The stream where the text of the document will be read from.
     * @return The encrypt document response.
     * @throws InterruptedException on the thread is interrupted
     */
    public EncryptDocumentResponse encryptDocumentStream(final String token,
                                                         final String docName,
                                                         final InputStream dataStream)
            throws InterruptedException, StrongDocServiceException {
        if (client == null) client = StrongDocManager.getInstance().getStrongDocClient();

        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        final CountDownLatch finishLatch = new CountDownLatch(1);

        final StreamObserver<DocumentsNoStore.EncryptDocStreamResp> responseObserver =
                new StreamObserver<DocumentsNoStore.EncryptDocStreamResp>() {

                    private String documentId = "";

                    @Override
                    public void onNext(final DocumentsNoStore.EncryptDocStreamResp value) {
                        if (documentId == "") {
                            documentId = value.getDocID();
                        }
                        final ByteString bs = value.getCiphertext();
                        if (!bs.isEmpty()) {
                            try {
                                bs.writeTo(os);
                            } catch (final IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(final Throwable t) {
                        t.printStackTrace();
                        finishLatch.countDown();
                    }

                    @Override
                    public void onCompleted() {
                        setStreamedDocumentId(documentId);
                        finishLatch.countDown();
                        try {
                            os.close();
                        } catch (final IOException e) {
                            e.printStackTrace();
                        }
                    }
                };

        final StreamObserver<DocumentsNoStore.EncryptDocStreamReq> requestObserver =
                client.getAsyncStub().withCallCredentials(JwtCallCredential.getCallCredential(token))
                        .encryptDocumentStream(responseObserver);

        try {
            DocumentsNoStore.EncryptDocStreamReq req = DocumentsNoStore.EncryptDocStreamReq.newBuilder()
                    .setDocName(docName).build();
            requestObserver.onNext(req);

            byte[] plaintext = ByteStreams.toByteArray(dataStream);
            final ByteString byteString = ByteString.copyFrom(plaintext);
            req = DocumentsNoStore.EncryptDocStreamReq.newBuilder().setPlaintext(byteString).build();
            requestObserver.onNext(req);
        } catch (final RuntimeException e) {
            requestObserver.onError(e);
            throw e;
        } catch (final IOException e) {
            e.printStackTrace();
        }

        requestObserver.onCompleted();

        // Can set a time limit if desired.
        // Use await(10, TimeUnit.MINUTES)
        finishLatch.await();

        return new EncryptDocumentResponse(getStreamedDocumentId(), os.toByteArray());
    }

    // ---------------------------------- DecryptDocumentStream ----------------------------------

    /**
     * Decrypts a document using the service.
     * The user must provide the ciphertext returned during the encryptDocument API call.
     *
     * @param token      The user JWT token.
     * @param docID      The ID of the document.
     * @param dataStream The stream where the document ciphertext to be decrypted will be read from.
     * @return The decrypted plaintext content of the document.
     * @throws InterruptedException on the thread is interrupted
     */
    public byte[] decryptDocumentStream(final String token,
                                        final String docID,
                                        final InputStream dataStream)
            throws InterruptedException, StrongDocServiceException {
        if (client == null) client = StrongDocManager.getInstance().getStrongDocClient();

        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        final CountDownLatch finishLatch = new CountDownLatch(1);
        final StreamObserver<DocumentsNoStore.DecryptDocStreamResp> responseObserver =
                new StreamObserver<DocumentsNoStore.DecryptDocStreamResp>() {

                    @Override
                    public void onNext(final DocumentsNoStore.DecryptDocStreamResp value) {
                        final ByteString bs = value.getPlaintext();
                        if (!bs.isEmpty()) {
                            try {
                                bs.writeTo(os);
                            } catch (final IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(final Throwable t) {
                        t.printStackTrace();
                        finishLatch.countDown();
                    }

                    @Override
                    public void onCompleted() {
                        finishLatch.countDown();
                        try {
                            os.close();
                        } catch (final IOException e) {
                            e.printStackTrace();
                        }
                    }
                };

        final StreamObserver<DocumentsNoStore.DecryptDocStreamReq> requestObserver =
                client.getAsyncStub().withCallCredentials(JwtCallCredential.getCallCredential(token))
                        .decryptDocumentStream(responseObserver);

        try {
            DocumentsNoStore.DecryptDocStreamReq req = DocumentsNoStore.DecryptDocStreamReq.newBuilder()
                    .setDocID(docID).build();
            requestObserver.onNext(req);

            byte[] ciphertext = ByteStreams.toByteArray(dataStream);
            // Chunk up the ciphertext into smaller blocks
            int blockSize = BLOCK_SIZE;
            for (int i = 0; i < ciphertext.length; i += blockSize) {
                byte[] block;
                if (i + blockSize < ciphertext.length) {
                    block = Arrays.copyOfRange(ciphertext, i, i + blockSize);
                } else {
                    block = Arrays.copyOfRange(ciphertext, i, ciphertext.length);
                }
                ByteString byteString = ByteString.copyFrom(block);
                req = DocumentsNoStore.DecryptDocStreamReq.newBuilder().setCiphertext(byteString).build();
                requestObserver.onNext(req);
            }
        } catch (final RuntimeException e) {
            requestObserver.onError(e);
            throw e;
        } catch (final IOException e) {
            e.printStackTrace();
        }

        requestObserver.onCompleted();

        // Can set a time limit if desired.
        // Use await(10, TimeUnit.MINUTES)
        finishLatch.await();

        return os.toByteArray();
    }

    // ---------------------------------- EncryptDocument ----------------------------------
    // proto.EncryptDocReq

    /**
     * Encrypts the document.
     *
     * @param token     The user JWT token.
     * @param docName   The name of the document to encrypt.
     * @param plaintext The data of the document to encrypt.
     * @return The encrypt document response. The ciphertext isn't being stored.
     * @throws InterruptedException
     */
    public EncryptDocumentResponse encryptDocument(final String token,
                                                   final String docName,
                                                   final byte[] plaintext)
            throws InterruptedException, StrongDocServiceException {

        InputStream in = new ByteArrayInputStream(plaintext);
        return encryptDocumentStream(token, docName, in);
    }

    // ---------------------------------- DecryptDocument ----------------------------------
    // proto.DecryptDocReq

    /**
     * Decrypts the passed in ciphertext.
     *
     * @param token      The user JWT token.
     * @param docID      The ID of the document to decrypt.
     * @param ciphertext The data of the document to decrypt.
     * @return The decrypted plain text in bytes back to the user without storing it.
     * @throws InterruptedException
     */
    public byte[] decryptDocument(final String token,
                                  final String docID,
                                  final byte[] ciphertext)
            throws InterruptedException, StrongDocServiceException {

        InputStream in = new ByteArrayInputStream(ciphertext);
        return decryptDocumentStream(token, docID, in);
    }
}
