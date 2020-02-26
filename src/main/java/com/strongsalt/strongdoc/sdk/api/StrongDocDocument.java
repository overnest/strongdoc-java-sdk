/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api;

import com.google.protobuf.ByteString;
import com.strongsalt.strongdoc.sdk.api.responses.EncryptDocumentResponse;
import com.strongsalt.strongdoc.sdk.api.responses.UploadDocumentResponse;
import com.strongsalt.strongdoc.sdk.client.JwtCallCredential;
import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient;
import com.strongsalt.strongdoc.sdk.proto.Documents;
import com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore;

import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

/**
 * This class can be used to perform actions that are related to documents.
 */
public class StrongDocDocument {
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
     * @param docID The document ID of the streamed document
     */
    protected void setStreamedDocumentId(final String docID) {
        streamedDocumentId = docID;
    }

    /**
     * Get the document ID for the streamed document
     * @return The document ID of the streamed document
     */
    protected String getStreamedDocumentId() {
        return streamedDocumentId;
    }

    /**
     * Set the total number of bytes streamed
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

    /**
     * Removes a document from the service.
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

        final Documents.RemoveDocumentRequest req = Documents.RemoveDocumentRequest.newBuilder()
                .setDocID(docID)
                .build();

        final Documents.RemoveDocumentResponse res = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).removeDocument(req);
        return res.getStatus();
    }

    /**
     * Uploads a document to the service for storage.
     * @param client       The StrongDoc client used to call this API.
     * @param token        The user JWT token.
     * @param documentPath The local full file path and name of the document.
     * @return The upload response.
     * @throws InterruptedException on the thread is interrupted
     * @throws FileNotFoundException when the specified document doesn't exist
     */
    public UploadDocumentResponse uploadDocument(final StrongDocServiceClient client,
                                                 final String token,
                                                 final String documentPath)
            throws InterruptedException, FileNotFoundException {

        InputStream inputStream = new FileInputStream(documentPath);
        if (inputStream == null) {
            final String errorMsg = String.format("File %s does not exist", documentPath);
            throw new FileNotFoundException(errorMsg);
        }

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
                    .setDocName(new File(documentPath).getName()).build();
            requestObserver.onNext(req);

            final int bufferSize = 10000;
            final byte[] buffer = new byte[bufferSize];
            int read = 0;
            int size = 0;
            while ((read = inputStream.read(buffer)) > 0) {
                size += read;
                final ByteString byteString = ByteString.copyFrom(buffer, 0, read);
                req = Documents.UploadDocStreamReq.newBuilder().setPlaintext(byteString).build();
                requestObserver.onNext(req);
            }
            setUploadedDocumentSize(size);
            inputStream.close();
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

    /**
     * Downloads a document from the service.
     * @param client The StrongDoc client used to call this API.
     * @param token  The user JWT token.
     * @param docID  The ID of the document.
     * @return The downloaded document.
     * @throws InterruptedException on the thread is interrupted
     */
    public byte[] downloadDocument(final StrongDocServiceClient client,
                                   final String token,
                                   final String docID)
            throws InterruptedException {

        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        final CountDownLatch finishLatch = new CountDownLatch(1);

        final StreamObserver<Documents.DownloadDocStreamResp> responseObserver =
                new StreamObserver<Documents.DownloadDocStreamResp>() {

                    @Override
                    public void onNext(final Documents.DownloadDocStreamResp value) {
                        final ByteString downloadedBytes = value.getPlaintext();
                        if (!downloadedBytes.isEmpty()) {
                            try {
                                downloadedBytes.writeTo(os);
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

        return os.toByteArray();
    }

    /**
     * Encrypts a document using the service, but do not store it.
     * Instead return the encrypted ciphertext.
     * @param client    The StrongDoc client used to call this API.
     * @param token     The user JWT token.
     * @param docName   The name of the document.
     * @param plaintext The text of the document.
     * @return The encrypt document response.
     * @throws InterruptedException on the thread is interrupted
     */
    public EncryptDocumentResponse encryptDocument(final StrongDocServiceClient client,
                                                   final String token,
                                                   final String docName,
                                                   final byte[] plaintext)
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

            final ByteString byteString = ByteString.copyFrom(plaintext);
            req = DocumentsNoStore.EncryptDocStreamReq.newBuilder().setPlaintext(byteString).build();
            requestObserver.onNext(req);
        } catch (final RuntimeException e) {
            requestObserver.onError(e);
            throw e;
        }

        requestObserver.onCompleted();

        // Can set a time limit if desired.
        // Use await(10, TimeUnit.MINUTES)
        finishLatch.await();

        return new EncryptDocumentResponse(getStreamedDocumentId(), os.toByteArray());
    }

    /**
     * Decrypts a document using the service.
     * The user must provide the ciphertext returned during the encryptDocument API call.
     * @param client     The StrongDoc client used to call this API.
     * @param token      The user JWT token.
     * @param docID      The ID of the document.
     * @param ciphertext The document ciphertext to be decrypted.
     * @return The decrypted plaintext content of the document.
     * @throws InterruptedException on the thread is interrupted
     */
    public byte[] decryptDocument(final StrongDocServiceClient client,
                                  final String token,
                                  final String docID,
                                  final byte[] ciphertext) 
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

            // Chunk up the ciphertext into smaller blocks
            int blockSize = 10000;
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
        }

        requestObserver.onCompleted();

        // Can set a time limit if desired.
        // Use await(10, TimeUnit.MINUTES)
        finishLatch.await();

        return os.toByteArray();
    }
}
