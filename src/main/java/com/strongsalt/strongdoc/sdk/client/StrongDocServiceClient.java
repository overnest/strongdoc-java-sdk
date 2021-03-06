/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.client;

import com.strongsalt.strongdoc.sdk.exceptions.StrongDocServiceException;
import com.strongsalt.strongdoc.sdk.proto.Account;
import com.strongsalt.strongdoc.sdk.proto.StrongDocServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
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
    private static String CERT_PATH = FileSystems.getDefault()
            .getPath("src", "test", "resources", "certs", "grpc.root.pem").toAbsolutePath().toString();
    private static String LOCAL_CERT_PATH = FileSystems.getDefault()
            .getPath("src", "test", "resources", "certs", "grpc.root.localhost.pem").toAbsolutePath().toString();

    /**
     * Valid locations of the StrongDoc service. This should be used to
     * initialize the StrongDocServiceClient.
     */
    public static enum ServiceLocation {
        PRODUCTION("api.strongsalt.com", 9090, CERT_PATH),
        SANDBOX("api.sandbox.strongsalt.com", 9090, CERT_PATH),
        QA("api.strongsaltqa.com", 9090, CERT_PATH),
        LOCAL("localhost", 9090, LOCAL_CERT_PATH);

        private String host;
        private int port;
        private String certPath;

        /**
         * For internal use. Constructs a service location for StrongDoc
         *
         * @param host       The StrongDoc service host
         * @param port       The StrongDoc service port
         * @param certPath   Path to the TLS certificate
         */
        private ServiceLocation(final String host, final int port, final String certPath) {
            this.setHost(host);
            this.setPort(port);
            this.setCertPath(certPath);
        }

        public String getHost() {
            return host;
        }
        private void setHost(String host) {
            this.host = host;
        }
        public int getPort() {
            return port;
        }
        private void setPort(int port) {
            this.port = port;
        }
        public String getCertPath() {
            return certPath;
        }
        private void setCertPath(String certPath) {
            this.certPath = certPath;
        }
    }

    private final ServiceLocation location;
    private String authToken = null;
    private final ManagedChannel channel;
    private final StrongDocServiceGrpc.StrongDocServiceBlockingStub blockingStub;
    private final StrongDocServiceGrpc.StrongDocServiceStub asyncStub;


    // Singleton
      private static StrongDocServiceClient client = null;

      /**
       * Initializes the singleton StrongDocServiceClient to use a specific
       * ServiceLocation
       *
       * @param location The service location to initialize the client with
       * @param reset    If a service location is already set, a reset will close the
       *                 old singleton client and recreate it with the new service
       *                 location
       * @return The initialized StrongDocServiceClient
       * @throws StrongDocServiceException if singleton already initialized, and reset is not set
       * @throws InterruptedException if error occurred shutting down the previously configured client
       * @throws SSLException if error occurred initializing SSL connection
       */
      public static StrongDocServiceClient initDefaultClient(final ServiceLocation location, final boolean reset)
              throws StrongDocServiceException, InterruptedException, SSLException {
          if (location == null) {
              return null;
          }

          if (client != null) {
              if (client.getLocation() == location) {
                  return client;
              }

              if (!reset) {
                  throw new StrongDocServiceException("StrongDocServiceClient already configured for " +
                          client.getLocation());
              }
          }

          if (client == null || reset) {
              synchronized (StrongDocServiceClient.class) {
                  if (client != null) {
                      client.shutdown();
                  }
                  client = createStrongDocServiceClient(location);
              }
          }
          return client;
      }

      /**
       * Retrieves the previously initialized StrongDocServiceClient singleton.
       *
       * @return The StrongDocServiceClient singleton
       * @throws StrongDocServiceException if singleton is not initialized
       */
      public static StrongDocServiceClient getDefaultClient() throws StrongDocServiceException {
          if (StrongDocServiceClient.client == null) {
              throw new StrongDocServiceException("Please call initStrongDocClient to initialize the client first");
          }
          return client;
      }


      /**
       * Creates a StrongDocServiceClient to use a specific ServiceLocation. This is
       * for users who wish to keep several clients with different credentials logged
       * in concurrently.
       *
       * @param location The service location to initialize the client with
       * @return The created StrongDocServiceClient
       * @throws SSLException if error occurred initializing SSL connection
       */
      public static StrongDocServiceClient createStrongDocServiceClient(final ServiceLocation location)
              throws SSLException {
          return new StrongDocServiceClient(location);
      }


      /**
       * For internal use. Constructs a StrongDoc service client with TLS enabled.
       *
       * @throws SSLException A SSL exception
       */
      private StrongDocServiceClient() throws SSLException {
          this(ServiceLocation.PRODUCTION);
      }

      /**
       * For internal use. Constructs a StrongDoc service client with TLS enabled.
       *
       * @param location      The StrongDoc service location
       * @throws SSLException A SSL exception
       */
      private StrongDocServiceClient(ServiceLocation location) throws SSLException {
          this.location = location;
          this.channel = NettyChannelBuilder.forAddress(location.getHost(), location.getPort())
                  .negotiationType(NegotiationType.TLS)
                  .sslContext(buildSslContext(location.getCertPath(), null, null))
                  .build();
          blockingStub = StrongDocServiceGrpc.newBlockingStub(channel);
          asyncStub = StrongDocServiceGrpc.newStub(channel);
      }

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
     * Verifies the user and organization identity, and returns a JWT token for future API use.
     *
     * @param userID       The login user ID
     * @param userPassword The login user password
     * @param orgID        The login organization ID
     * @return The JWT token used to authenticate user/org when using StrongDoc APIs.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public String login(final String orgID, final String userID, final String userPassword)
            throws StatusRuntimeException {
        final Account.LoginReq req = Account.LoginReq.newBuilder()
                .setUserID(userID)
                .setPassword(userPassword)
                .setOrgID(orgID)
                .build();
        this.authToken = getBlockingStubNoAuth().login(req).getToken();
        return getAuthToken();
    }

    /**
     * Invalidates the JWT token and ending the current user session.
     *
     * @return Status of the logout.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public String logout() throws StatusRuntimeException {
        final Account.LogoutReq req = Account.LogoutReq.newBuilder().build();
        return getBlockingStub().logout(req).getStatus();
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
     * @return A stub that will make non-blocking calls to the server with authentication token
     */
    public StrongDocServiceGrpc.StrongDocServiceStub getAsyncStub() {
        return asyncStub.withCallCredentials(JwtCallCredential.getCallCredential(getAuthToken()));
    }

    /**
     * For internal use. Returns a stub that will make non-blocking calls to the server.
     *
     * @return A stub that will make non-blocking calls to the server
     */
    public StrongDocServiceGrpc.StrongDocServiceStub getAsyncStubNoAuth() {
        return asyncStub;
    }

    /**
     * For internal use. Returns a stub that will make blocking calls to the server.
     *
     * @return A stub that will make blocking calls to the server with authentication token
     */
    public StrongDocServiceGrpc.StrongDocServiceBlockingStub getBlockingStub() {
        return blockingStub.withCallCredentials(JwtCallCredential.getCallCredential(getAuthToken()));
    }

    /**
     * For internal use. Returns a stub that will make blocking calls to the server.
     *
     * @return A stub that will make blocking calls to the server
     */
    public StrongDocServiceGrpc.StrongDocServiceBlockingStub getBlockingStubNoAuth() {
        return blockingStub.withCallCredentials(JwtCallCredential.getCallCredential(getAuthToken()));
    }

    /**
     * For internal use. Returns the service location configured with this client.
     *
     * @return The service location configured with this client
     */
    public ServiceLocation getLocation() {
        return location;
    }

    /**
     * For internal use. Returns the authorization token associated with this client.
     * This token is obtained during a successful login
     *
     * @return The authorization token
     */
    public String getAuthToken() {
        return authToken;
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
