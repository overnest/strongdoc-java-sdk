/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api;

import com.google.common.io.ByteStreams;
import com.google.protobuf.ByteString;
import com.strongsalt.strongdoc.sdk.api.responses.DocumentInfo;
import com.strongsalt.strongdoc.sdk.api.responses.EncryptDocumentResponse;
import com.strongsalt.strongdoc.sdk.api.responses.EncryptDocumentStreamResponse;
import com.strongsalt.strongdoc.sdk.api.responses.UploadDocumentResponse;
import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient;
import com.strongsalt.strongdoc.sdk.exceptions.StrongDocServiceException;
import com.strongsalt.strongdoc.sdk.proto.Documents;
import com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocStreamResp;
import com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocStreamResp;
import com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore;
import com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocStreamResp;

import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * This class can be used to perform actions that are related to documents.
 */
public class StrongDocDocument {
    /**
     * The chunk size
     */
    private static final int BLOCK_SIZE = 1024 * 1024; // 1 MB


    // ---------------------------------- UploadDocumentStream ----------------------------------

    /**
     * Uploads a document to Strongdoc provided storage.
     *
     * @param client      The StrongDoc client used to call this API.
     * @param docName     The name of the document.
     * @param dataStream  The stream where the uploaded document will be read from.
     * @return The upload response.
     * @throws IOException on inputStream errors
     * @throws InterruptedException on CountDownLatch errors
     * @throws StrongDocServiceException on upload error
     */
    public UploadDocumentResponse uploadDocumentStream(final StrongDocServiceClient client,
                                                       final String docName,
                                                       final InputStream dataStream)
            throws IOException, InterruptedException, StrongDocServiceException {
        final CountDownLatch finishLatch = new CountDownLatch(1);

        final class UploadStreamObserver implements StreamObserver<Documents.UploadDocStreamResp> {
            private Throwable exception = null;
            private Documents.UploadDocStreamResp resp = null;

            public Documents.UploadDocStreamResp getResp() {
                return resp;
            }

            private void setResp(Documents.UploadDocStreamResp resp) {
                this.resp = resp;
            }

            @Override
            public void onNext(UploadDocStreamResp value) {
                if (getResp() == null) {
                    setResp(value);
                }
            }

            @Override
            public void onError(Throwable t) {
                setException(t);
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                finishLatch.countDown();
            }

            public Throwable getException() {
                return exception;
            }

            private void setException(Throwable exception) {
                this.exception = exception;
            }
        }

        final UploadStreamObserver responseObserver = new UploadStreamObserver();
        final StreamObserver<Documents.UploadDocStreamReq> requestObserver = client.getAsyncStub()
                .uploadDocumentStream(responseObserver);

        long bytes = 0;
        try {
            Documents.UploadDocStreamReq req = Documents.UploadDocStreamReq.newBuilder()
                    .setDocName(docName).build();
            requestObserver.onNext(req);

            final byte[] buffer = new byte[BLOCK_SIZE];
            int read = 0;

            // If the inputStream is EOF, then -1 will be returned
            while ((read = dataStream.read(buffer)) >= 0) {
                if (read > 0) {
                    bytes += read;
                    final ByteString byteString = ByteString.copyFrom(buffer, 0, read);
                    req = Documents.UploadDocStreamReq.newBuilder().setPlaintext(byteString).build();
                    requestObserver.onNext(req);
//                    System.out.println("uploadDocumentStream bytes=" + bytes);
                }
            }
            dataStream.close();
        } catch (final IOException | RuntimeException e) {
            requestObserver.onError(e);
            throw e;
        }

        requestObserver.onCompleted();

        // Can set a time limit if desired.
        // Use await(10, TimeUnit.MINUTES)
        finishLatch.await();

        if (responseObserver.getException() != null) {
            throw new StrongDocServiceException(responseObserver.getException());
        }

        return new UploadDocumentResponse(responseObserver.getResp().getDocID(), bytes);
    }

    // ---------------------------------- DownloadDocumentStream ----------------------------------

    /**
     * Downloads any document previously stored on Strongdoc provided storage.
     *
     * @param client The StrongDoc client used to call this API.
     * @param docID  The ID of the document.
     * @return The stream to from which to read the downloaded document
     * @throws InterruptedException on the thread is interrupted
     * @throws IOException on pipe stream creation error
     */
    public InputStream downloadDocumentStream(final StrongDocServiceClient client,
                                                 final String docID)
               throws InterruptedException, IOException {
        final PipedOutputStream outputStream = new PipedOutputStream();
        final PipedInputStream inputStream = new PipedInputStream(outputStream);

        final class DownloadStreamObserver implements StreamObserver<Documents.DownloadDocStreamResp> {
            private Throwable exception = null;
            private OutputStream outputStream;

            private DownloadStreamObserver(final OutputStream outputStream) {
                this.outputStream = outputStream;
            }

            @Override
            public void onNext(DownloadDocStreamResp value) {
                final ByteString downloadedBytes = value.getPlaintext();
                if (!downloadedBytes.isEmpty()) {
                    try {
                        downloadedBytes.writeTo(outputStream);
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(Throwable t) {
                this.setException(t);
                try {
                    outputStream.close();
                } catch (IOException e) {
                }
            }

            @Override
            public void onCompleted() {
                try {
                    outputStream.close();
                } catch (final IOException e) {
                    this.setException(e);
                }
            }

            public Throwable getException() {
                return exception;
            }

            private void setException(Throwable exception) {
                this.exception = exception;
            }
        } // final class DownloadStreamObserver implements StreamObserver<Documents.DownloadDocStreamResp>

        final DownloadStreamObserver responseObserver = new DownloadStreamObserver(outputStream);

        try {
            final Documents.DownloadDocStreamReq req = Documents.DownloadDocStreamReq.newBuilder()
                    .setDocID(docID).build();
            client.getAsyncStub().downloadDocumentStream(req, responseObserver);
        } catch (final RuntimeException e) {
            responseObserver.onError(e);
            throw e;
        }

        return inputStream;
    }

    // ---------------------------------- UploadDocument ----------------------------------
    // proto.UploadDocReq

    /**
     * Uploads a document to Strongdoc provided storage.
     *
     * @param client    The StrongDoc client used to call this API.
     * @param docName   The name of the document to upload.
     * @param plaintext The data of the document to upload.
     * @return The ID of the document uploaded.
     * @throws IOException on inputStream errors
     * @throws RuntimeException on gRPC errors
     * @throws InterruptedException on CountDownLatch errors
     * @throws StrongDocServiceException on upload error
     */
    public String uploadDocument(final StrongDocServiceClient client,
                                 final String docName,
                                 final byte[] plaintext)
            throws InterruptedException, IOException, RuntimeException, StrongDocServiceException {

        InputStream inputStream = new ByteArrayInputStream(plaintext);
        UploadDocumentResponse response = uploadDocumentStream(client, docName, inputStream);
        return response.getDocID();
    }

    // ---------------------------------- DownloadDocument ----------------------------------
    // proto.DownloadDocReq

    /**
     * Downloads a document stored in Strongdoc provided storage.
     *
     * @param client The StrongDoc client used to call this API.
     * @param docID  The ID of the document to download.
     * @return The decrypted data of the downloaded document.
     * @throws InterruptedException on the thread is interrupted
     * @throws IOException on pipe stream creation error
     */
    public byte[] downloadDocument(final StrongDocServiceClient client,
                                   final String docID)
            throws InterruptedException, IOException {
        InputStream inputStream = downloadDocumentStream(client, docID);
        return ByteStreams.toByteArray(inputStream);
    }

    // ---------------------------------- ShareDocument ----------------------------------

    /**
     * Shares a document to another user.
     *
     * @param client The StrongDoc client used to call this API.
     * @param docID  The ID of the document to share.
     * @param userID The user ID to share it to.
     * @return Whether the operation was successful.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public Boolean shareDocument(final StrongDocServiceClient client,
                                 final String docID,
                                 final String userID)
            throws StatusRuntimeException {

        final Documents.ShareDocumentReq req = Documents.ShareDocumentReq.newBuilder()
                .setDocID(docID)
                .setUserID(userID)
                .build();
        final Documents.ShareDocumentResp res = client.getBlockingStub().shareDocument(req);
        return res.getSuccess();
    }

    // ---------------------------------- UnshareDocument ----------------------------------

    /**
     * Unshares a document that had previously been shared to a user.
     *
     * @param client The StrongDoc client used to call this API.
     * @param docID  The ID of the document to unshare.
     * @param userID The user ID to unshare it to.
     * @return The unshared document count.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public long unshareDocument(final StrongDocServiceClient client,
                                final String docID,
                                final String userID)
            throws StatusRuntimeException {

        final Documents.UnshareDocumentReq req = Documents.UnshareDocumentReq.newBuilder()
                .setDocID(docID)
                .setUserID(userID)
                .build();
        final Documents.UnshareDocumentResp res = client.getBlockingStub().unshareDocument(req);
        return res.getCount();
    }

    // ---------------------------------- ListDocuments ----------------------------------

    /**
     * Lists the documents the user can access.
     * An administrator can see all documents belonging to the organization.
     *
     * @param client The StrongDoc client used to call this API.
     * @return The list of document info.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public ArrayList<DocumentInfo> listDocuments(final StrongDocServiceClient client)
            throws StatusRuntimeException {

        final Documents.ListDocumentsReq req = Documents.ListDocumentsReq.newBuilder().build();

        final Documents.ListDocumentsResp res = client.getBlockingStub().listDocuments(req);
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
     * @param client The StrongDoc client used to call this API.
     * @param docID  The ID of the document.
     * @return Whether the removal was a success
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public Boolean removeDocument(final StrongDocServiceClient client,
                                  final String docID)
            throws StatusRuntimeException {

        final Documents.RemoveDocumentReq req = Documents.RemoveDocumentReq.newBuilder()
                .setDocID(docID)
                .build();
        final Documents.RemoveDocumentResp res = client.getBlockingStub()
                .removeDocument(req);
        return res.getStatus();
    }

    // ---------------------------------- EncryptDocumentStream ----------------------------------

    /**
     * Encrypts a document using the service, but do not store it.
     * The encrypted ciphertext will be returned.
     *
     * @param client     The StrongDoc client used to call this API.
     * @param docName    The name of the document.
     * @param dataStream The stream where the text of the document will be read from.
     * @return The encrypt document stream response.
     * @throws InterruptedException on the thread is interrupted
     * @throws IOException on stream errors
     */
    public EncryptDocumentStreamResponse encryptDocumentStream(
                                             final StrongDocServiceClient client,
                                             final String docName,
                                             final InputStream dataStream)
            throws InterruptedException, IOException {
        final CountDownLatch finishLatch = new CountDownLatch(1);
        final PipedOutputStream outputStream = new PipedOutputStream();
        final PipedInputStream inputStream = new PipedInputStream(outputStream);

        final class EncryptRequestSendThread extends Thread {
            private AtomicBoolean running = new AtomicBoolean(false);
            private AtomicBoolean stopped = new AtomicBoolean(false);

            private int bufferSize;
            private InputStream plainStream;
            private StreamObserver<DocumentsNoStore.EncryptDocStreamReq> requestObserver;

            public EncryptRequestSendThread(
                    final int bufferSize,
                    final InputStream plainStream,
                    final StreamObserver<DocumentsNoStore.EncryptDocStreamReq> requestObserver) {
                this.bufferSize = bufferSize;
                this.plainStream = plainStream;
                this.requestObserver = requestObserver;
            }

            @Override
            public void interrupt() {
                running.set(false);
                super.interrupt();
            }

            public boolean isRunning() {
                return running.get();
            }

            public boolean isStopped() {
                return stopped.get();
            }

            @Override
            public void run() {
                running.set(true);
                stopped.set(false);
                final byte[] buffer = new byte[bufferSize];
                int read = 0;

                try {
                    // If the inputStream is EOF, then -1 will be returned
                    while (isRunning() && (read = plainStream.read(buffer)) >= 0) {
                        if (read > 0) {
                            final ByteString byteString = ByteString.copyFrom(buffer, 0, read);
                            DocumentsNoStore.EncryptDocStreamReq req = DocumentsNoStore.
                                    EncryptDocStreamReq.newBuilder().setPlaintext(byteString).build();
                            requestObserver.onNext(req);
                        }
                    }
                    plainStream.close();
                    requestObserver.onCompleted();
                } catch (IOException e) {
                    requestObserver.onError(e);
                }
                stopped.set(true);
            }
        }

        final class ResponseCallback implements StreamObserver<DocumentsNoStore.
            EncryptDocStreamResp>
        {
            private OutputStream cipherOutputStream;
            private EncryptRequestSendThread encryptRequestSendThread;
            private String docID = null;
            private IOException exception = null;

            private ResponseCallback(OutputStream outputStream) {
                this.cipherOutputStream = outputStream;
            }

            @Override
            public void onNext(EncryptDocStreamResp value) {
                if (getDocID() == null) {
                    setDocID(value.getDocID());
                    finishLatch.countDown();
                }
                final ByteString bs = value.getCiphertext();
                if (!bs.isEmpty()) {
                    try {
                        bs.writeTo(cipherOutputStream);
                    } catch (IOException e) {
                        if (getEncryptRequestSendThread() != null) {
                            getEncryptRequestSendThread().interrupt();
                        }
                        setException(e);
                    }
                }
            }

            @Override
            public void onError(Throwable t) {
                finishLatch.countDown();
                try {
                    cipherOutputStream.close();
                    if (getEncryptRequestSendThread() != null) {
                        getEncryptRequestSendThread().interrupt();
                    }
                } catch (IOException e) {
                    setException(e);
                }
            }

            @Override
            public void onCompleted() {
                finishLatch.countDown();
                try {
                    cipherOutputStream.close();
                } catch (IOException e) {
                    setException(e);
                }
            }

            public IOException getException() {
                return exception;
            }

            private void setException(IOException exception) {
                this.exception = exception;
            }

            public String getDocID() {
                return docID;
            }

            private void setDocID(String docID) {
                this.docID = docID;
            }

            public EncryptRequestSendThread getEncryptRequestSendThread() {
                return encryptRequestSendThread;
            }

            public void setEncryptRequestSendThread(EncryptRequestSendThread thread) {
                this.encryptRequestSendThread = thread;
            }
        }

        final ResponseCallback responseCallback = new ResponseCallback(outputStream);
        final StreamObserver<DocumentsNoStore.EncryptDocStreamReq> requestObserver = client.getAsyncStub()
                .encryptDocumentStream(responseCallback);

        DocumentsNoStore.EncryptDocStreamReq req = DocumentsNoStore.EncryptDocStreamReq.
                newBuilder().setDocName(docName).build();
        requestObserver.onNext(req);
        finishLatch.await();

        final EncryptRequestSendThread thread = new EncryptRequestSendThread(BLOCK_SIZE, dataStream, requestObserver);
        responseCallback.setEncryptRequestSendThread(thread);
        thread.start();

        return new EncryptDocumentStreamResponse(responseCallback.getDocID(), inputStream);
    }

    // ---------------------------------- DecryptDocumentStream ----------------------------------

    /**
     * Decrypts a document using the service.
     * The user must provide the ciphertext returned during the encryptDocument API call.
     *
     * @param client     The StrongDoc client used to call this API.
     * @param docID      The ID of the document.
     * @param dataStream The stream where the document ciphertext to be decrypted will be read from.
     * @return The stream to read the decrypted plaintext from
     * @throws InterruptedException on the thread is interrupted
     * @throws IOException on stream error
     * @throws StrongDocServiceException on requested document ID not matching returned
     */
    public InputStream decryptDocumentStream(final StrongDocServiceClient client,
                                             final String docID,
                                             final InputStream dataStream)
            throws InterruptedException, IOException, StrongDocServiceException {
        final CountDownLatch finishLatch = new CountDownLatch(1);
        final PipedOutputStream outputStream = new PipedOutputStream();
        final PipedInputStream plainInputStream = new PipedInputStream(outputStream);


        final class DecryptRequestSendThread extends Thread {
            private AtomicBoolean running = new AtomicBoolean(false);
            private AtomicBoolean stopped = new AtomicBoolean(false);

            private int bufferSize;
            private InputStream cipherStream;
            private StreamObserver<DocumentsNoStore.DecryptDocStreamReq> requestObserver;

            public DecryptRequestSendThread(
                    final int bufferSize,
                    final InputStream cipherStream,
                    final StreamObserver<DocumentsNoStore.DecryptDocStreamReq> requestObserver) {
                this.bufferSize = bufferSize;
                this.cipherStream = cipherStream;
                this.requestObserver = requestObserver;
            }

            @Override
            public void interrupt() {
                running.set(false);
                super.interrupt();
            }

            public boolean isRunning() {
                return running.get();
            }

            public boolean isStopped() {
                return stopped.get();
            }

            @Override
            public void run() {
                running.set(true);
                stopped.set(false);
                final byte[] buffer = new byte[bufferSize];
                int read = 0;

                try {
                    // If the inputStream is EOF, then -1 will be returned
                    while (isRunning() && (read = cipherStream.read(buffer)) >= 0) {
                        if (read > 0) {
                            final ByteString byteString = ByteString.copyFrom(buffer, 0, read);
                            DocumentsNoStore.DecryptDocStreamReq req = DocumentsNoStore
                                    .DecryptDocStreamReq.newBuilder()
                                    .setCiphertext(byteString)
                                    .build();
                            requestObserver.onNext(req);
                        }
                    }
                    cipherStream.close();
                    requestObserver.onCompleted();
                } catch (IOException e) {
                    requestObserver.onError(e);
                }
                stopped.set(true);
            }
        }

        final class ResponseCallback implements StreamObserver<DocumentsNoStore.
            DecryptDocStreamResp>
        {
            private OutputStream plainOutputStream;
            private DecryptRequestSendThread decryptRequestSendThread;
            private String docID = null;
            private IOException exception = null;

            private ResponseCallback(OutputStream outputStream) {
                this.plainOutputStream = outputStream;
            }

            @Override
            public void onNext(final DocumentsNoStore.DecryptDocStreamResp value) {
                if (getDocID() == null) {
                    setDocID(value.getDocID());
                    finishLatch.countDown();
                }

                final ByteString bs = value.getPlaintext();
                if (!bs.isEmpty()) {
                    try {
                         bs.writeTo(plainOutputStream);
                     } catch (IOException e) {
                         if (getDecryptRequestSendThread() != null) {
                             getDecryptRequestSendThread().interrupt();
                         }
                         setException(e);
                     }
                }
            }

            @Override
            public void onError(final Throwable t) {
                finishLatch.countDown();
                try {
                    plainOutputStream.close();
                    if (getDecryptRequestSendThread() != null) {
                        getDecryptRequestSendThread().interrupt();
                    }
                } catch (IOException e) {
                    setException(e);
                }
            }

            @Override
            public void onCompleted() {
                finishLatch.countDown();
                try {
                    plainOutputStream.close();
                } catch (IOException e) {
                    setException(e);
                }
            }

            public IOException getException() {
                return exception;
            }

            private void setException(IOException exception) {
                this.exception = exception;
            }

            public String getDocID() {
                return docID;
            }

            private void setDocID(String docID) {
                this.docID = docID;
            }

            public DecryptRequestSendThread getDecryptRequestSendThread() {
                return decryptRequestSendThread;
            }

            public void setDecryptRequestSendThread(DecryptRequestSendThread thread) {
                this.decryptRequestSendThread = thread;
            }
        }


        final ResponseCallback responseCallback = new ResponseCallback(outputStream);
        final StreamObserver<DocumentsNoStore.DecryptDocStreamReq> requestObserver = client.getAsyncStub()
                .decryptDocumentStream(responseCallback);

        DocumentsNoStore.DecryptDocStreamReq req = DocumentsNoStore.DecryptDocStreamReq.newBuilder()
                .setDocID(docID).build();
        requestObserver.onNext(req);
        finishLatch.await();

        if (responseCallback.getDocID() == null ||
                !responseCallback.getDocID().equals(docID)) {
            StrongDocServiceException e = new StrongDocServiceException(
                    "The request document ID " + docID +
                    "does not match the returned document ID " +
                            responseCallback.getDocID());
            requestObserver.onError(e);
            plainInputStream.close();
            throw e;
        }

        final DecryptRequestSendThread thread = new DecryptRequestSendThread(
                BLOCK_SIZE, dataStream, requestObserver);
        responseCallback.setDecryptRequestSendThread(thread);
        thread.start();

        return plainInputStream;
    }

    // ---------------------------------- EncryptDocument ----------------------------------
    // proto.EncryptDocReq

    /**
     * Encrypts the document.
     *
     * @param client    The StrongDoc client used to call this API.
     * @param docName   The name of the document to encrypt.
     * @param plaintext The data of the document to encrypt.
     * @return The encrypt document response. The ciphertext isn't being stored.
     * @throws InterruptedException on the thread is interrupted
     * @throws IOException on stream error
     */
    public EncryptDocumentResponse encryptDocument(final StrongDocServiceClient client,
                                                   final String docName,
                                                   final byte[] plaintext)
            throws InterruptedException, IOException {
        InputStream in = new ByteArrayInputStream(plaintext);
        EncryptDocumentStreamResponse resp = encryptDocumentStream(client, docName, in);
        return new EncryptDocumentResponse(resp.getDocID(), ByteStreams.toByteArray(resp.getCipherStream()));

    }

    // ---------------------------------- DecryptDocument ----------------------------------
    // proto.DecryptDocReq

    /**
     * Decrypts the passed in ciphertext.
     *
     * @param client     The StrongDoc client used to call this API.
     * @param docID      The ID of the document to decrypt.
     * @param ciphertext The data of the document to decrypt.
     * @return The decrypted plain text in bytes back to the user without storing it.
     * @throws InterruptedException on the thread is interrupted
     * @throws IOException on stream error
     * @throws StrongDocServiceException on requested document ID not matching returned
     */
    public byte[] decryptDocument(final StrongDocServiceClient client,
                                  final String docID,
                                  final byte[] ciphertext)
            throws InterruptedException, IOException, StrongDocServiceException {
        InputStream in = new ByteArrayInputStream(ciphertext);
        InputStream plainStream =  decryptDocumentStream(client, docID, in);
        return ByteStreams.toByteArray(plainStream);
    }
}
