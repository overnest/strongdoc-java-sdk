/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.client;

import com.strongsalt.strongdoc.sdk.api.StrongDocDocument;
import com.strongsalt.strongdoc.sdk.proto.StrongDocServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;

import javax.net.ssl.SSLException;
import java.io.File;
import java.nio.file.FileSystems;
import java.util.concurrent.TimeUnit;

/**
 * A simple client that requests an authentication token with TLS.
 */
public class StrongDocServiceClient {
    private static final String HOST = "api.strongsalt.com";
    private static final int PORT = 9090;
    private static final String CERT_PATH = FileSystems.getDefault()
        .getPath("src", "test", "resources", "certs", "grpc.root.pem")
        .toAbsolutePath()
        .toString();

    private final ManagedChannel channel;
    private final StrongDocServiceGrpc.StrongDocServiceBlockingStub blockingStub;
    private final StrongDocServiceGrpc.StrongDocServiceStub asyncStub;
    private final StrongDocDocument streamDocument;

    /**
     * Creates an SSL context for gRPC
     *
     * @param trustCertCollectionFilePath The file path for the trusted certificates for verifying
     *                                    the remote endpoint's certificate
     * @param clientCertChainFilePath     The file path for the certificate chain
     * @param clientPrivateKeyFilePath    The file path for the private key
     * @return A SSL context
     * @throws SSLException A SSL exception
     */
    private static SslContext buildSslContext(final String trustCertCollectionFilePath,
                                             final String clientCertChainFilePath,
                                             final String clientPrivateKeyFilePath)
            throws SSLException {

        final SslContextBuilder builder = GrpcSslContexts.forClient();
        if (trustCertCollectionFilePath != null) {
            builder.trustManager(new File(trustCertCollectionFilePath));
        }
        if (clientCertChainFilePath != null && clientPrivateKeyFilePath != null) {
            builder.keyManager(new File(clientCertChainFilePath), new File(clientPrivateKeyFilePath));
        }
        return builder.build();
    }

    /**
     * Constructs a StrongDoc service client with TLS enabled.
     *
     * @throws SSLException A SSL exception
     */
    public StrongDocServiceClient() 
            throws SSLException {
        this(HOST, PORT, CERT_PATH);
    }

    /**
     * For internal use. Constructs a StrongDoc service client with TLS enabled.
     *
     * @param host       The StrongDoc service url
     * @param port       The StrongDoc service port
     * @param certPath   Path to the TLS certificate
     * @throws SSLException A SSL exception
     */
    public StrongDocServiceClient(final String host,
                                  final int port,
                                  final String certPath)
            throws SSLException {

        this(NettyChannelBuilder.forAddress(host, port)
                .negotiationType(NegotiationType.TLS)
                .sslContext(buildSslContext(certPath, null, null))
                .build());
    }

    /**
     * Constructs a StrongDoc service client with a given channel
     *
     * @param channel The channel
     */
    private StrongDocServiceClient(final ManagedChannel channel) {
        this.channel = channel;
        blockingStub = StrongDocServiceGrpc.newBlockingStub(channel);
        asyncStub = StrongDocServiceGrpc.newStub(channel);
        streamDocument = new StrongDocDocument();
    }

    /**
     * Shutdown the channel
     *
     * @throws InterruptedException on the thread is interrupted
     */
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * For internal use. Returns a stub that will make non-blocking calls to the server.
     *
     * @return A stub that will make non-blocking calls to the server
     */
    public StrongDocServiceGrpc.StrongDocServiceStub getAsyncStub() {
        return asyncStub;
    }

    /**
     * For internal use. Returns a stub that will make blocking calls to the server.
     *
     * @return A stub that will make blocking calls to the server
     */
    public StrongDocServiceGrpc.StrongDocServiceBlockingStub getBlockingStub() {
        return blockingStub;
    }

    /**
     * Returns a StrongDoc document for streaming documents
     *
     * @return A StrongDoc document for streaming documents
     */
    /*public StrongDocDocument getStreamDocument() {
        return streamDocument;
    }*/
}
