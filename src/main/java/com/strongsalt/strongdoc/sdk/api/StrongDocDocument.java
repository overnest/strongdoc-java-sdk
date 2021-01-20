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
import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient;
import com.strongsalt.strongdoc.sdk.exceptions.StrongDocServiceException;
import com.strongsalt.strongdoc.sdk.proto.Documents;
import com.strongsalt.strongdoc.sdk.proto.Documents.*;
import com.strongsalt.strongdoc.sdk.proto.Documents.E2EEUploadDocStreamReq.EncKeyList;
import com.strongsalt.strongdoc.sdk.proto.Documents.E2EEUploadDocStreamReq.PostMetaDataType;
import com.strongsalt.strongdoc.sdk.proto.Documents.E2EEUploadDocStreamResp.UploadRespStageDataCase;
import com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore;
import com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocStreamResp;
import com.strongsalt.strongdoc.sdk.proto.Encryption;
import com.strongsalt.strongdoc.sdk.proto.Encryption.EncryptedKey;
import com.strongsalt.strongdoc.sdk.proto.Encryption.Key;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
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
    public UploadDocumentResponse e2eeUploadDocumentStream(final StrongDocServiceClient client,
                                                       final String docName,
                                                       final InputStream dataStream)
            throws IOException, InterruptedException, StrongDocServiceException {
        final Semaphore semaphore = new Semaphore(0);
        
        final class UploadStreamObserver implements StreamObserver<Documents.E2EEUploadDocStreamResp> {
            private Throwable exception = null;
            private Documents.E2EEUploadDocStreamResp resp = null;

            public Documents.E2EEUploadDocStreamResp getResp() {
                return resp;
            }

            private void setResp(Documents.E2EEUploadDocStreamResp resp) {
                this.resp = resp;
            }

            @Override
            public void onNext(E2EEUploadDocStreamResp value) {
                setResp(value);
                semaphore.release();
                /*if (getResp() == null) {
                    setResp(value);
                }*/
            }

            @Override
            public void onError(Throwable t) {
                setException(t);
                semaphore.release();
            }

            @Override
            public void onCompleted() {
                semaphore.release();
            }

            public Throwable getException() {
                return exception;
            }

            private void setException(Throwable exception) {
                this.exception = exception;
            }
        }

        final UploadStreamObserver responseObserver = new UploadStreamObserver();
        final StreamObserver<Documents.E2EEUploadDocStreamReq> requestObserver = client.getAsyncStub()
                .e2EEUploadDocumentStream(responseObserver);

        // generate doc key
        StrongSaltKey docKey;
        byte[] serialDocKey;
        try {
            docKey = StrongSaltKey.GenerateKey(StrongSaltKey.KeyType.XCHACHA20HMAC);
            serialDocKey = docKey.serialize();
        } catch (StrongSaltKeyException e) {
            requestObserver.onError(e);
            throw new StrongDocServiceException(e);
        }

        // send premetadata
        Documents.E2EEUploadDocStreamReq req = Documents.E2EEUploadDocStreamReq.newBuilder()
                    .setPreMetaData(Documents.E2EEUploadDocStreamReq.PreMetaDataType.newBuilder()
                        .setDocName(docName))
                    .build();
        requestObserver.onNext(req);

        // wait for server to respond
        semaphore.acquire();

        Documents.E2EEUploadDocStreamResp resp = responseObserver.getResp();
        
        final Base64.Decoder base64decoder = Base64.getUrlDecoder();
        final Base64.Encoder base64encoder = Base64.getUrlEncoder();
        byte[] serialPubKey;
        StrongSaltKey pubKey;
        byte[] encSerialDocKey;
        Encryption.EncryptedKey protoEncKey;
        EncKeyList.Builder encDocKeysBuilder;
        while (!resp.getReadyForData()) {
            if (resp.getUploadRespStageDataCase() != UploadRespStageDataCase.ENCRYPTORS) {
                throw new StrongDocServiceException("Expected server response to contain Encryptors.");
            }

            encDocKeysBuilder = EncKeyList.newBuilder();
            List<Key> pubKeys = resp.getEncryptors().getPubKeysList();

            for (Key protoPubKey : pubKeys) {
                serialPubKey = base64decoder.decode(protoPubKey.getKey());
                try {
                    pubKey = StrongSaltKey.Deserialize(serialPubKey);
                    encSerialDocKey = pubKey.encrypt(serialDocKey);
                } catch (StrongSaltKeyException e) {
                    requestObserver.onError(e);
                    throw new StrongDocServiceException(e);
                }
            
                protoEncKey = EncryptedKey.newBuilder()
                    .setEncKey(base64encoder.encodeToString(encSerialDocKey))
                    .setOwnerID(protoPubKey.getOwnerID())
                    .setEncryptorID(protoPubKey.getKeyID())
                    .setOwnerType(protoPubKey.getOwnerType())
                    .setEncryptorVersion(protoPubKey.getVersion())
                    .build();

                encDocKeysBuilder.addEncDocKeys(protoEncKey);
            }
            req = Documents.E2EEUploadDocStreamReq.newBuilder()
                    .setEncDocKeys(encDocKeysBuilder.build())
                    .build();
            requestObserver.onNext(req);

            // wait for response to keys message
            semaphore.acquire();
            resp = responseObserver.getResp();
        }    
        
        long bytes = 0;
        try {
            final byte[] buffer = new byte[BLOCK_SIZE];
            byte[] bufferToSend;

            int inputRead = 0;
            int encryptorRead = 0;

            final Encryptor encryptor = docKey.encryptStream();
            final byte[] nonce = encryptor.getNonce();
            docKey.MACWrite(nonce);

            ByteString byteString = ByteString.copyFrom(nonce, 0, nonce.length);

            req = Documents.E2EEUploadDocStreamReq.newBuilder()
                .setCipherText(byteString)
                .build();
            requestObserver.onNext(req);

            // If the inputStream is EOF, then -1 will be returned

            while ((inputRead = dataStream.read(buffer)) >= 0) {
                if (inputRead > 0) {
                    bufferToSend = buffer;
                    if (inputRead != buffer.length) {
                        bufferToSend = Arrays.copyOfRange(buffer, 0, inputRead);
                    }
                    
                    encryptorRead = encryptor.write(bufferToSend);
                    if (encryptorRead != inputRead) {
                        throw new StrongDocServiceException("Error writing to encryptor. Wrong number of bytes.");
                    }

                    encryptorRead = encryptor.read(bufferToSend);

                    if (encryptorRead == 0) {
                        continue;
                    }

                    bytes += encryptorRead;
                    if (encryptorRead != buffer.length) {
                        bufferToSend = Arrays.copyOfRange(bufferToSend, 0, encryptorRead);
                    }
                    docKey.MACWrite(bufferToSend);

                    byteString = ByteString.copyFrom(bufferToSend);
                    req = Documents.E2EEUploadDocStreamReq.newBuilder().setCipherText(byteString).build();
                    requestObserver.onNext(req);
                }
            }
            dataStream.close();
            bufferToSend = encryptor.readLast();
            docKey.MACWrite(bufferToSend);
            bytes += bufferToSend.length;

            byteString = ByteString.copyFrom(bufferToSend);
            req = Documents.E2EEUploadDocStreamReq.newBuilder().setCipherText(byteString).build();
            requestObserver.onNext(req);

        } catch (final IOException | RuntimeException e) {
            requestObserver.onError(e);
            throw e;
        } catch (final StrongSaltKeyException e) {
            requestObserver.onError(e);
            throw new StrongDocServiceException(e);
        }

        try {
            byte[] mac = docKey.MACSum();
            req = Documents.E2EEUploadDocStreamReq.newBuilder()
                .setPostMetaData(PostMetaDataType.newBuilder()
                    .setMacOfCipherText(base64encoder.encodeToString(mac)).build())
                .build();
            requestObserver.onNext(req);
        } catch (StrongSaltKeyException e) {
            requestObserver.onError(e);
            throw new StrongDocServiceException(e);
        }
        // wait for DocID response
        semaphore.acquire();

        // Can set a time limit if desired.
        // Use await(10, TimeUnit.MINUTES)
        requestObserver.onCompleted();

        // wait for server to close connection
        semaphore.acquire();

        if (responseObserver.getException() != null) {
            throw new StrongDocServiceException(responseObserver.getException());
        }

        return new UploadDocumentResponse(responseObserver.getResp().getDocID(), bytes);
    }

    private class DecryptedProtoKey {
        byte[] keyBytes;
        String keyID;
        int keyVersion;

        private DecryptedProtoKey(byte[] keyBytes, String keyID, int keyVersion) {
            this.keyBytes = keyBytes;
            this.keyID = keyID;
            this.keyVersion = keyVersion;
        }
    }

    private DecryptedProtoKey decryptKeyChain(final StrongDocServiceClient client, final List<EncryptedKey> encKeyChain)
        throws StrongSaltKeyException {
        if (encKeyChain == null || encKeyChain.size() == 0) {
            // error
        }

        final Base64.Decoder base64decoder = Base64.getUrlDecoder();

        EncryptedKey protoEncKey = encKeyChain.get(0);
        byte[] encKeyBytes = base64decoder.decode(protoEncKey.getEncKey());
        byte[] keyBytes = client.userDecrypt(encKeyBytes);
        StrongSaltKey key;

        for (int i = 1; i < encKeyChain.size(); i++) {
            key = StrongSaltKey.Deserialize(keyBytes);

            protoEncKey = encKeyChain.get(i);
            encKeyBytes = base64decoder.decode(protoEncKey.getEncKey());
            keyBytes = key.decrypt(encKeyBytes);
        }

        return new DecryptedProtoKey(keyBytes, protoEncKey.getKeyID(), protoEncKey.getKeyVersion());
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
               throws InterruptedException, IOException, StrongDocServiceException {
        

        E2EEPrepareDownloadDocResp prepareResp;
        try {
            E2EEPrepareDownloadDocReq prepareReq = E2EEPrepareDownloadDocReq.newBuilder().setDocID(docID).build();
            prepareResp = client.getBlockingStub().e2EEPrepareDownloadDocument(prepareReq);
        } catch (StatusRuntimeException e) {
            throw new StrongDocServiceException(e);
        }

        if (prepareResp != null && prepareResp.getDocumentAccessMetadata().getIsClientSide()) {
            return clientSideDownloadDocumentStream(client, docID, prepareResp.getDocumentAccessMetadata());
        }
        
        return serverSideDownloadDocumentStream(client, docID);
    }

    private InputStream serverSideDownloadDocumentStream(final StrongDocServiceClient client,
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

    private InputStream clientSideDownloadDocumentStream(final StrongDocServiceClient client,
                                                 final String docID, final DocumentAccessMetadata metadata)
               throws InterruptedException, IOException, StrongDocServiceException {
        final PipedOutputStream outputStream = new PipedOutputStream();
        final PipedInputStream inputStream = new PipedInputStream(outputStream);
        

        final class DownloadInputStream extends FilterInputStream {
            private Throwable exception = null;

            private DownloadInputStream(InputStream in) {
                super(in);
            }

            @Override
            public int read() throws IOException {
                if (exception != null) {
                    throw new IOException(exception);
                }
                return super.read();
            }

            @Override
            public int read(byte[] b) throws IOException {
                if (exception != null) {
                    throw new IOException(exception);
                }
                return read(b, 0, b.length);
            }
            @Override
            public int read(byte[] b, int off, int len) throws IOException {
                if (exception != null) {
                    throw new IOException(exception);
                }
                return super.read(b, off, len);
            }

            public void setException(Throwable e) {
                this.exception = e;
            }
        }

        final class DownloadStreamObserver implements StreamObserver<Documents.E2EEDownloadDocStreamResp> {
            private Throwable exception = null;
            private OutputStream outputStream;
            private DownloadInputStream inputStream;
            private Decryptor decryptor;
            private StrongSaltKey macKey;
            private byte[] mac;

            private DownloadStreamObserver(final OutputStream outputStream, final DownloadInputStream inputStream, final StrongSaltKey docKey, final byte[] mac) throws StrongSaltKeyException {
                this.outputStream = outputStream;
                this.inputStream = inputStream;
                this.decryptor = docKey.decryptStream(0);
                this.macKey = docKey;
                this.mac = mac;
            }

            @Override
            public void onNext(E2EEDownloadDocStreamResp value) {
                final ByteString downloadedBytes = value.getCipherText();
                if (!downloadedBytes.isEmpty()) {
                    try {
                        byte[] downloadedBytesArr = downloadedBytes.toByteArray();
                        int n = decryptor.write(downloadedBytesArr);
                        if (n != downloadedBytesArr.length) {
                            this.onError(new StrongDocServiceException("Error writing to decryptor. Wrong number of bytes."));
                            return;
                        }
                        macKey.MACWrite(downloadedBytesArr);

                        byte[] plaintext = new byte[n];
                        n = decryptor.read(plaintext);
                        
                        outputStream.write(plaintext, 0, n);
                    } catch (final IOException | StrongSaltKeyException e) {
                        this.onError(e);
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(Throwable t) {
                this.setException(t);
                inputStream.setException(t);
                try {
                    outputStream.close();
                } catch (IOException e) {
                }
            }

            @Override
            public void onCompleted() {
                System.out.println("OnCompleted");
                try {
                    boolean macOk = macKey.MACVerify(mac);
                    if (!macOk) {
                        inputStream.setException(new StrongDocServiceException("Document integrity check failed."));
                    } else {
                        outputStream.write(decryptor.readLast());
                    }
                    outputStream.close();
                } catch (final IOException | StrongSaltKeyException e) {
                    this.inputStream.setException(e);
                    this.setException(e);
                }
                System.out.println("OnCompleted completed");
            }

            public Throwable getException() {
                return exception;
            }

            private void setException(Throwable exception) {
                this.exception = exception;
            }
        } // final class DownloadStreamObserver implements StreamObserver<Documents.DownloadDocStreamResp>

        final DownloadInputStream downloadInputStream = new DownloadInputStream(inputStream);

        if (metadata == null) {
            throw new StrongDocServiceException("Client-Side Download metadata is null.");
        }

        final Base64.Decoder base64decoder = Base64.getUrlDecoder();

        byte[] mac = base64decoder.decode(metadata.getMac());

        StrongSaltKey docKey;
        DownloadStreamObserver responseObserver;
        try {
            DecryptedProtoKey docKeyData = decryptKeyChain(client, metadata.getDocKeyChainList());
            docKey = StrongSaltKey.Deserialize(docKeyData.keyBytes);
            responseObserver = new DownloadStreamObserver(outputStream, downloadInputStream, docKey, mac);
        } catch (StrongSaltKeyException e) {
            throw new StrongDocServiceException(e);
        }

        try {
            final Documents.E2EEDownloadDocStreamReq req = Documents.E2EEDownloadDocStreamReq.newBuilder()
                    .setDocID(docID).build();
            client.getAsyncStub().e2EEDownloadDocumentStream(req, responseObserver);
        } catch (final RuntimeException e) {
            responseObserver.onError(e);
            throw e;
        }

        return downloadInputStream;
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

    // ---------------------------------- E2EEUploadDocument ----------------------------------
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
    public String e2eeUploadDocument(final StrongDocServiceClient client,
                                 final String docName,
                                 final byte[] plaintext)
            throws InterruptedException, IOException, RuntimeException, StrongDocServiceException {

        InputStream inputStream = new ByteArrayInputStream(plaintext);
        UploadDocumentResponse response = e2eeUploadDocumentStream(client, docName, inputStream);
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
            throws InterruptedException, IOException, StrongDocServiceException {
        InputStream inputStream = downloadDocumentStream(client, docID);
        return ByteStreams.toByteArray(inputStream);
    }

    // ---------------------------------- ShareDocument ----------------------------------

    public ShareDocumentResponse bulkShareDocWithUsers(final StrongDocServiceClient client,
                                                       final String docID,
                                                       final List<String> receiverIDs) throws StatusRuntimeException, StrongSaltKeyException {
        return bulkShareDoc(client, docID, receiverIDs, Encryption.AccessType.USER);
    }

    public ShareDocumentResponse bulkShareDocWithOrgs(final StrongDocServiceClient client,
                                                      final String docID,
                                                      final List<String> receiverIDs) throws StatusRuntimeException, StrongSaltKeyException {
        return bulkShareDoc(client, docID, receiverIDs, Encryption.AccessType.ORG);
    }


    /**
     * Shares a document to another org or user.
     *
     * @param client       The StrongDoc client used to call this API.
     * @param docID        The ID of the document to share.
     * @param receiverIDs  The receiver IDs to share it to.
     * @param receiverType The receiver Type
     * @return ShareDocumentResponse
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public ShareDocumentResponse bulkShareDoc(final StrongDocServiceClient client,
                                              final String docID,
                                              final List<String> receiverIDs,
                                              Encryption.AccessType receiverType) throws StatusRuntimeException, StrongSaltKeyException {
        final Documents.PrepareShareDocumentReq.Builder prepareShareBuilder = Documents.PrepareShareDocumentReq.newBuilder();
        prepareShareBuilder.setDocID(docID);
        prepareShareBuilder.setReceiverType(receiverType);
        prepareShareBuilder.addAllReceiverIDs(receiverIDs);

        final Documents.PrepareShareDocumentResp prepareShareRes = client.getBlockingStub()
                .prepareShareDocument(prepareShareBuilder.build());

        // user has no access to the doc or cannot share the document
        DocumentAccessMetadata accessMetadata = prepareShareRes.getAccessMetaData();
        ShareDocumentResponse res = new ShareDocumentResponse(accessMetadata.getIsAccessible(), accessMetadata.getIsSharable());
        if (!accessMetadata.getIsAccessible() || !accessMetadata.getIsSharable()) {
            return res;
        }
        // receivers who already have access, no need to share
        for (String alreadySharedReceiver : prepareShareRes.getReceiversWithDocList()) {
            res.addAlreadyAccessibleReceivers(alreadySharedReceiver);
        }
        // receivers who cannot be shared with this doc
        for (String unsharableReciever : prepareShareRes.getUnsharableReceiversList()) {
            res.addUnsharableReceiver(unsharableReciever);
        }
        // the document is encrypted on client side
        if (accessMetadata.getIsClientSide()) {
            StrongDocDocument.DecryptedProtoKey decryptedProtoKey = decryptKeyChain(client, accessMetadata.getDocKeyChainList());
            byte[] docKeyBytes = decryptedProtoKey.keyBytes;
            String keyID = decryptedProtoKey.keyID;
            int keyVersion = decryptedProtoKey.keyVersion;

            for (Key encryptor : prepareShareRes.getEncryptorsList()) {

                while (true) {
                    byte[] keyBytes = Base64.getUrlDecoder().decode(encryptor.getKey());
                    StrongSaltKey pubKey = StrongSaltKey.Deserialize(keyBytes);

                    byte[] encDocKeyBytes = pubKey.encrypt(docKeyBytes);
                    Documents.ShareDocumentReq shareDocumentReq = Documents.ShareDocumentReq.newBuilder()
                            .setDocID(docID)
                            .setReceiverType(receiverType)
                            .setReceiverID(encryptor.getOwnerID())
                            .setEncDocKey(EncryptedKey.newBuilder()
                                    .setEncryptorID(encryptor.getKeyID())
                                    .setEncKey(Base64.getUrlEncoder().encodeToString(encDocKeyBytes))
                                    .setEncryptorVersion(encryptor.getVersion())
                                    .setKeyID(keyID)
                                    .setKeyVersion(keyVersion)
                                    .build())
                            .build();
                    final Documents.ShareDocumentResp shareDocRes = client.getBlockingStub()
                            .shareDocument(shareDocumentReq);
                    if (shareDocRes.getPubKey() != null && shareDocRes.getPubKey().getKey().length() > 0) {
                        System.out.println(shareDocRes);
                        encryptor = shareDocRes.getPubKey();
                    } else {
                        if (shareDocRes.getSuccess()) {
                            res.addSharedReceivers(encryptor.getOwnerID());
                        } else if (shareDocRes.getReceiverUnsharable()) {
                            res.addUnsharableReceiver(encryptor.getOwnerID());
                        } else if (shareDocRes.getReceiverAlreadyAccessible()) {
                            res.addAlreadyAccessibleReceivers(encryptor.getOwnerID());
                        }
                        break;
                    }
                }
            }
        } else {
            // the document is encrypted on server side
            for (String receiverID : receiverIDs) {
                Documents.ShareDocumentReq shareDocumentReq = Documents.ShareDocumentReq.newBuilder()
                        .setDocID(docID)
                        .setReceiverType(receiverType)
                        .setReceiverID(receiverID)
                        .build();
                final Documents.ShareDocumentResp shareDocRes = client.getBlockingStub()
                        .shareDocument(shareDocumentReq);
                if (shareDocRes.getReceiverUnsharable()) {
                    res.addUnsharableReceiver(receiverID);
                } else if (shareDocRes.getReceiverAlreadyAccessible()) {
                    res.addAlreadyAccessibleReceivers(receiverID);
                } else if (shareDocRes.getSuccess()) {
                    res.addSharedReceivers(receiverID);
                }
            }
        }
        return res;
    }

    // ---------------------------------- UnshareDocument ----------------------------------

    public UnshareDocumentResponse unshareDocumentWithUser(final StrongDocServiceClient client,
                                                           final String docID,
                                                           final String receiverID) {
        return unshareDocument(client, docID, receiverID, Encryption.AccessType.USER);
    }

    public UnshareDocumentResponse unshareDocumentWithOrg(final StrongDocServiceClient client,
                                                          final String docID,
                                                          final String receiverID) {
        return unshareDocument(client, docID, receiverID, Encryption.AccessType.ORG);
    }

    /**
     * Unshares a document that had previously been shared to a user.
     *
     * @param client       The StrongDoc client used to call this API.
     * @param docID        The ID of the document to unshare.
     * @param receiverID   The receiver ID to unshare it to.
     * @param receiverType The receiver Type
     * @return UnshareDocumentResponse
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public UnshareDocumentResponse unshareDocument(final StrongDocServiceClient client,
                                                   final String docID,
                                                   final String receiverID,
                                                   final Encryption.AccessType receiverType)
            throws StatusRuntimeException {

        final Documents.UnshareDocumentReq req = Documents.UnshareDocumentReq.newBuilder()
                .setDocID(docID)
                .setReceiverID(receiverID)
                .setReceiverType(receiverType)
                .build();
        final Documents.UnshareDocumentResp res = client.getBlockingStub().unshareDocument(req);
        return new UnshareDocumentResponse(res.getSuccess(), res.getAllowed(), res.getAlreadyUnshared());
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
