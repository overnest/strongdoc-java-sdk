/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api;

import com.google.common.io.ByteStreams;
import com.google.protobuf.ByteString;
import com.google.protobuf.Timestamp;
import com.strongsalt.strongdoc.sdk.api.responses.DocActionHistoryInfo;
import com.strongsalt.strongdoc.sdk.api.responses.DocumentInfo;
import com.strongsalt.strongdoc.sdk.api.responses.EncryptDocumentResponse;
import com.strongsalt.strongdoc.sdk.api.responses.UploadDocumentResponse;
import com.strongsalt.strongdoc.sdk.client.JwtCallCredential;
import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient;
import com.strongsalt.strongdoc.sdk.proto.Documents;
import com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
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
     * @param client     The StrongDoc client used to call this API.
     * @param token      The user JWT token.
     * @param docName    The name of the document.
     * @param dataStream The stream where the uploaded document will be read from.
     * @return The upload response.
     * @throws InterruptedException on the thread is interrupted
     */
    public UploadDocumentResponse uploadDocumentStream(final StrongDocServiceClient client,
                                                       final String token,
                                                       final String docName,
                                                       final InputStream dataStream)
            throws InterruptedException {

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

    // ---------------------------------- DownloadDocumentStream ----------------------------------

    /**
     * Downloads any document previously stored on Strongdoc provided storage.
     *
     * @param client The StrongDoc client used to call this API.
     * @param token  The user JWT token.
     * @param docID  The ID of the document.
     * @param output The stream to where the downloaded document will be written to.
     * @throws InterruptedException on the thread is interrupted
     */
    public void downloadDocumentStream(final StrongDocServiceClient client,
                                       final String token,
                                       final String docID,
                                       final ByteArrayOutputStream output)
            throws InterruptedException {

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

    // ---------------------------------- UploadDocument ----------------------------------
    // proto.UploadDocReq

    /**
     * Uploads a document to Strongdoc provided storage.
     *
     * @param client    The StrongDoc client used to call this API.
     * @param token     The user JWT token.
     * @param docName   The name of the document to upload.
     * @param plaintext The data of the document to upload.
     * @return The ID of the document uploaded.
     * @throws InterruptedException
     */
    public String uploadDocument(final StrongDocServiceClient client,
                                 final String token,
                                 final String docName,
                                 final byte[] plaintext)
            throws InterruptedException {

        InputStream inputStream = new ByteArrayInputStream(plaintext);
        UploadDocumentResponse response = uploadDocumentStream(client, token, docName, inputStream);
        return response.getDocID();
    }

    // ---------------------------------- DownloadDocument ----------------------------------
    // proto.DownloadDocReq

    /**
     * Downloads a document stored in Strongdoc provided storage.
     *
     * @param client The StrongDoc client used to call this API.
     * @param token  The user JWT token.
     * @param docID  The ID of the document to download.
     * @return The decrypted data of the downloaded document.
     * @throws InterruptedException
     */
    public byte[] downloadDocument(final StrongDocServiceClient client,
                                   final String token,
                                   final String docID)
            throws InterruptedException {

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        downloadDocumentStream(client, token, docID, os);
        return os.toByteArray();
    }

    // ---------------------------------- ShareDocument ----------------------------------

    /**
     * Shares a document to another user.
     *
     * @param client The StrongDoc client used to call this API.
     * @param token  The user JWT token.
     * @param docID  The ID of the document to share.
     * @param userID The user ID to share it to.
     * @return Whether the operation was successful.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public Boolean shareDocument(final StrongDocServiceClient client,
                                 final String token,
                                 final String docID,
                                 final String userID)
            throws StatusRuntimeException {

        final Documents.ShareDocumentReq req = Documents.ShareDocumentReq.newBuilder()
                .setDocID(docID)
                .setUserID(userID)
                .build();

        final Documents.ShareDocumentResp res = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).shareDocument(req);
        return res.getSuccess();
    }

    // ---------------------------------- UnshareDocument ----------------------------------

    /**
     * Unshares a document that had previously been shared to a user.
     *
     * @param client The StrongDoc client used to call this API.
     * @param token  The user JWT token.
     * @param docID  The ID of the document to unshare.
     * @param userID The user ID to unshare it to.
     * @return The unshared document count.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public long unshareDocument(final StrongDocServiceClient client,
                                final String token,
                                final String docID,
                                final String userID)
            throws StatusRuntimeException {

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
     * @param client The StrongDoc client used to call this API.
     * @param token  The user JWT token.
     * @return The list of document info.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public ArrayList<DocumentInfo> listDocuments(final StrongDocServiceClient client,
                                                 final String token)
            throws StatusRuntimeException {

        final Documents.ListDocumentsReq req = Documents.ListDocumentsReq.newBuilder().build();

        final Documents.ListDocumentsResp res = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).listDocuments(req);
        final ArrayList<DocumentInfo> docInfoList = new ArrayList<DocumentInfo>();
        for (Documents.ListDocumentsResp.Document document : res.getDocumentsList()) {
            docInfoList.add(new DocumentInfo(document.getDocID(), document.getDocName(), document.getSize()));
        }

        return docInfoList;
    }

    // ---------------------------------- ListDocActionHistory ----------------------------------

    /**
     * Lists the documents the user can access.
     * An administrator can see all documents belonging to the organization.
     *
     * @param client The StrongDoc client used to call this API.
     * @param token  The user JWT token.
     * @param docID   
     * @param userID
     * @param actionStartTime 
     * @param actionEndTime
     * @param page
     * @param per_page    
     * @return The list of document action history info.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public ArrayList<DocActionHistoryInfo> ListDocActionHistory(final StrongDocServiceClient client,
                                                 final String token, final String docID, final String userID, 
                                                 final Timestamp actionStartTime, final Timestamp actionEndTime, final int page, final int per_page)
            throws StatusRuntimeException {

        final Documents.ListDocActionHistoryReq req = Documents.ListDocActionHistoryReq.newBuilder().setDocID(docID).setUserID(userID)
        .setActionStartTime(actionStartTime).setActionEndTime(actionEndTime).setPage(page).setPerPage(per_page).build();

        final Documents.ListDocActionHistoryResp res = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).listDocActionHistory(req);
        final ArrayList<DocActionHistoryInfo> docActioniHistoryInfoList = new ArrayList<DocActionHistoryInfo>();
        for (Documents.ListDocActionHistoryResp.DocActionHistory docActionHistory : res.getDocActionHistoryListList()) {
            docActioniHistoryInfoList.add(new DocActionHistoryInfo(docActionHistory.getDocID(), docActionHistory.getUserID(), docActionHistory.getDocName(),
            docActionHistory.getActionTime(), docActionHistory.getActionType(), docActionHistory.getOtherUserID()));
        }

        return docActioniHistoryInfoList;
    }

    // ---------------------------------- RemoveDocument ----------------------------------

    /**
     * Removes a document from the service.
     * An administrator can remove document for the whole organization.
     * A 'regular' user only can remove document for him/herself.
     *
     * @param client The StrongDoc client used to call this API.
     * @param token  The user JWT token.
     * @param docID  The ID of the document.
     * @return Whether the removal was a success
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public Boolean removeDocument(final StrongDocServiceClient client,
                                  final String token,
                                  final String docID)
            throws StatusRuntimeException {

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
     * @param client     The StrongDoc client used to call this API.
     * @param token      The user JWT token.
     * @param docName    The name of the document.
     * @param dataStream The stream where the text of the document will be read from.
     * @return The encrypt document response.
     * @throws InterruptedException on the thread is interrupted
     */
    public EncryptDocumentResponse encryptDocumentStream(final StrongDocServiceClient client,
                                                         final String token,
                                                         final String docName,
                                                         final InputStream dataStream)
            throws InterruptedException {

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
     * @param client     The StrongDoc client used to call this API.
     * @param token      The user JWT token.
     * @param docID      The ID of the document.
     * @param dataStream The stream where the document ciphertext to be decrypted will be read from.
     * @return The decrypted plaintext content of the document.
     * @throws InterruptedException on the thread is interrupted
     */
    public byte[] decryptDocumentStream(final StrongDocServiceClient client,
                                        final String token,
                                        final String docID,
                                        final InputStream dataStream)
            throws InterruptedException {

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
     * @param client    The StrongDoc client used to call this API.
     * @param token     The user JWT token.
     * @param docName   The name of the document to encrypt.
     * @param plaintext The data of the document to encrypt.
     * @return The encrypt document response. The ciphertext isn't being stored.
     * @throws InterruptedException
     */
    public EncryptDocumentResponse encryptDocument(final StrongDocServiceClient client,
                                                   final String token,
                                                   final String docName,
                                                   final byte[] plaintext)
            throws InterruptedException {

        InputStream in = new ByteArrayInputStream(plaintext);
        return encryptDocumentStream(client, token, docName, in);
    }

    // ---------------------------------- DecryptDocument ----------------------------------
    // proto.DecryptDocReq

    /**
     * Decrypts the passed in ciphertext.
     *
     * @param client     The StrongDoc client used to call this API.
     * @param token      The user JWT token.
     * @param docID      The ID of the document to decrypt.
     * @param ciphertext The data of the document to decrypt.
     * @return The decrypted plain text in bytes back to the user without storing it.
     * @throws InterruptedException
     */
    public byte[] decryptDocument(final StrongDocServiceClient client,
                                  final String token,
                                  final String docID,
                                  final byte[] ciphertext)
            throws InterruptedException {

        InputStream in = new ByteArrayInputStream(ciphertext);
        return decryptDocumentStream(client, token, docID, in);
    }
}
