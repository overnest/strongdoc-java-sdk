/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.client;

import com.strongsalt.crypto.exception.StrongSaltKdfException;
import com.strongsalt.crypto.exception.StrongSaltKeyException;
import com.strongsalt.crypto.exception.StrongSaltSRPException;
import com.strongsalt.crypto.kdf.StrongSaltKDF;
import com.strongsalt.crypto.key.StrongSaltKey;
import com.strongsalt.crypto.pake.srp.SRP;
import com.strongsalt.crypto.pake.srp.SRPClient;
import com.strongsalt.crypto.pake.srp.SRPSession;
import com.strongsalt.crypto.pake.srp.Verifier;
import com.strongsalt.strongdoc.sdk.exceptions.StrongDocServiceException;
import com.strongsalt.strongdoc.sdk.proto.Account;
import com.strongsalt.strongdoc.sdk.proto.Encryption;
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
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
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
    private String authToken;
    private StrongSaltKey passwordKey;
    private String passwordKeyID;
    private String userID;
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
     * @throws InterruptedException      if error occurred shutting down the previously configured client
     * @throws SSLException              if error occurred initializing SSL connection
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

    private AuthSession newAuthSession(Account.PrepareAuthResp prepareAuthResp, Account.AuthPurpose authPurpose,
                                       String userID, String orgID, String password) throws StrongDocServiceException {
        switch (prepareAuthResp.getAuthType()) {
            case AUTH_SRP:
                return authSRP(userID, password, orgID, prepareAuthResp.getAuthVersion(), authPurpose);
        }
        throw new StrongDocServiceException("Unsupported authentication type: " + prepareAuthResp.getAuthType());
    }

    public AuthSession NewAuthSession(String password) throws StrongDocServiceException {
        Account.PrepareAuthResp res = getBlockingStubNoAuth().prepareAuth(Account.PrepareAuthReq.newBuilder().build());
        return newAuthSession(res, Account.AuthPurpose.AUTH_PERSISTENT, this.userID, "", password);
    }

    private AuthSession authSRP(String userID, String password, String orgID,
                                int version, Account.AuthPurpose authPurpose) throws StrongDocServiceException {
        // srp init
        SRPClient srpClient = null;
        final Account.SrpInitReq.Builder srpInitReqBuilder = Account.SrpInitReq.newBuilder();
        try {
            SRPSession srpSession = SRP.Deserialize(version).getSession();
            srpClient = srpSession.newClient(userID.getBytes(), password.getBytes());
            String creds = srpClient.credentials();
            srpInitReqBuilder.setAuthPurpose(authPurpose);
            srpInitReqBuilder.setClientCreds(creds);
            srpInitReqBuilder.setUserID(userID);
            srpInitReqBuilder.setOrgID(orgID);
        } catch (StrongSaltSRPException e) {
            throw new StrongDocServiceException("Fail to init SRP client", e);
        }
        Account.SrpInitResp srpInitResp = getBlockingStubNoAuth().srpInit(srpInitReqBuilder.build());
        String respCreds = srpInitResp.getServerCreds();
        String authID = srpInitResp.getAuthID();

        // srp proof
        Account.SrpProofReq.Builder srpProofReqBuilder = Account.SrpProofReq.newBuilder();
        try {
            String proof = srpClient.generate(respCreds);
            srpProofReqBuilder.setClientProof(proof);
            srpProofReqBuilder.setAuthID(authID);
            srpProofReqBuilder.setUserID(userID);
        } catch (StrongSaltSRPException e) {
            throw new StrongDocServiceException("Fail to generate client proof", e);
        }
        Account.SrpProofResp srpProofResp = getBlockingStubNoAuth().srpProof(srpProofReqBuilder.build());

        try {
            boolean serverOk = srpClient.serverOk(srpProofResp.getServerProof());
            if (!serverOk) {
                throw new StrongDocServiceException("Fail to validate server proof");
            }
        } catch (StrongSaltSRPException e) {
            throw new StrongDocServiceException("Fail to validate server proof", e);
        }
        return new AuthSession(authID, Account.AuthType.AUTH_SRP, version, srpProofResp.getLoginResponse(), srpClient);
    }

    /**
     * Verifies the user and organization identity, and returns whether login is successful.
     *
     * @param userIDorEmail The login userID or email
     * @param password      The login user password
     * @param orgID         The login organization ID
     * @return void
     * @throws StrongDocServiceException on StrongDocServiceException errors
     */
    public void login(final String orgID, final String userIDorEmail, final String password)
            throws StrongDocServiceException {
        // prepare login
        final Account.PrepareLoginReq prepareLoginReq = Account.PrepareLoginReq.newBuilder()
                .setEmailOrUserID(userIDorEmail)
                .setOrgID(orgID)
                .build();
        Account.PrepareLoginResp prepareLoginResp = getBlockingStubNoAuth().prepareLogin(prepareLoginReq);

        AuthSession authSession = newAuthSession(prepareLoginResp.getPrepareAuthResp(), Account.AuthPurpose.AUTH_LOGIN,
                prepareLoginResp.getUserID(), orgID, password);

        if (authSession.getLoginResp() == null) {
            throw new StrongDocServiceException("Login err: [Received nil login response]");
        }

        Account.LoginResp loginResp = authSession.getLoginResp();
        this.authToken = loginResp.getToken();
        this.passwordKeyID = loginResp.getKeyID();
        this.userID = prepareLoginResp.getUserID();

        byte[] kdfMetaBytes = Base64.getUrlDecoder().decode(loginResp.getKdfMeta());
        try {
            StrongSaltKDF kdf = StrongSaltKDF.Deserialize(kdfMetaBytes);
            this.passwordKey = kdf.GenerateKey(password.getBytes());
            this.passwordKeyID = loginResp.getKeyID();
        } catch (StrongSaltKeyException | StrongSaltKdfException e) {
            throw new StrongDocServiceException("Fail to generate passwordKey", e);
        }
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
        Account.LogoutResp resp = getBlockingStub().logout(req);
        this.passwordKeyID = "";
        this.passwordKey = null;
        return resp.getStatus();
    }

    public void changePassword(String oldPassword, String newPassword) throws StrongDocServiceException {
        AuthSession authSession = NewAuthSession(oldPassword);
        Account.SetUserAuthMetadataReq.Builder setAuthReqBuilder = Account.SetUserAuthMetadataReq.newBuilder();

        setAuthReqBuilder.setAuthID(authSession.getAuthID());
        setAuthReqBuilder.setNewAuthType(authSession.getAuthType());
        setAuthReqBuilder.setNewAuthVersion(authSession.getAuthVersion());

        switch (authSession.getAuthType()) {
            case AUTH_SRP:
                try {
                    SRPSession srpSession = SRP.ONE.getSession();
                    Verifier newSrpVerifier = srpSession.verifier(this.userID.getBytes(), newPassword.getBytes());
                    String newSrpVerifierString = newSrpVerifier.encode()[1];
                    String authSrpVerifierStr = authSession.PrepareDataForAuth(newSrpVerifierString.getBytes());

                    setAuthReqBuilder.setSrpVerifier(authSrpVerifierStr);
                } catch (StrongSaltKeyException | StrongSaltSRPException e) {
                    throw new StrongDocServiceException("SRP Error: " + e);
                }
                break;
            default:
                throw new StrongDocServiceException("Unsupported Authentication Type: " + authSession.getAuthType());
        }

        StrongSaltKey newPasswordKey = null;
        try {
            StrongSaltKDF newKdf = StrongSaltKDF.GenerateKDF(StrongSaltKDF.KDFType.ARGON2, StrongSaltKey.KeyType.SECRETBOX);
            byte[] newKdfMetaBytes = newKdf.serialize();
            newPasswordKey = newKdf.GenerateKey(newPassword.getBytes());

            String authKdfMetaStr = authSession.PrepareDataForAuth(newKdfMetaBytes);

            setAuthReqBuilder.setKdfMeta(authKdfMetaStr);
        } catch (StrongSaltKdfException | StrongSaltKeyException | StrongSaltSRPException e) {
            throw new StrongDocServiceException("GenerateAuthKDF Error: " + e);
        }

        boolean done = false;
        int attempts = 0;
        int maxAttempts = 5;

        while (!done) {
            if (attempts >= maxAttempts) {
                throw new StrongDocServiceException("ChangePassword Error: max attempts exceeded.");
            }
            attempts++;

            Encryption.GetUserPrivateKeysReq keysReq = Encryption.GetUserPrivateKeysReq.newBuilder().build();
            Encryption.GetUserPrivateKeysResp keysResp = getBlockingStub().getUserPrivateKeys(keysReq);

            List<Encryption.EncryptedKey> oldEncKeys = keysResp.getEncryptedKeysList();
            List<Encryption.EncryptedKey> newEncKeys = new ArrayList<>();

            for (int i = 0; i < oldEncKeys.size(); i++) {
                Encryption.EncryptedKey oldEncKey = oldEncKeys.get(i);

                if (!oldEncKey.getEncryptorID().equals(this.getUserKeyID())) {
                    throw new StrongDocServiceException("ChangePassword Error: User information out of date. User must log out and log back in again before continuing.");
                }
                try {
                    byte[] keyBytes = this.UserDecryptBase64(oldEncKey.getEncKey());
                    byte[] newEncKeyBytes = newPasswordKey.encrypt(keyBytes);
                    String authEncKeyStr = authSession.PrepareDataForAuth(newEncKeyBytes);

                    Encryption.EncryptedKey newEncKey = Encryption.EncryptedKey.newBuilder()
                            .setEncKey(authEncKeyStr)
                            .setKeyID(oldEncKey.getKeyID())
                            .setKeyVersion(oldEncKey.getKeyVersion())
                            .setOwnerID(oldEncKey.getOwnerID())
                            .setOwnerType(oldEncKey.getOwnerType())
                            .build();

                    newEncKeys.add(newEncKey);
                } catch (StrongSaltKeyException | StrongSaltSRPException e) {
                    throw new StrongDocServiceException("Encryption Error: " + e);
                }

            }
            setAuthReqBuilder.addAllEncryptedKeys(newEncKeys);

            Account.SetUserAuthMetadataResp setAuthResp = getBlockingStub().setUserAuthMetadata(setAuthReqBuilder.build());

            done = !setAuthResp.getRestart();
        }
    }

    public String getUserKeyID() {
        return this.passwordKeyID;
    }

    public byte[] UserEncrypt(byte[] plaintext) throws StrongSaltKeyException {
        return this.passwordKey.encrypt(plaintext);
    }

    public String UserEncryptBase64(byte[] plaintext) throws StrongSaltKeyException {
        return this.passwordKey.encryptBase64(plaintext);
    }

    public byte[] UserDecrypt(byte[] ciphertext) throws StrongSaltKeyException {
        return this.passwordKey.decrypt(ciphertext);
    }

    public byte[] UserDecryptBase64(String ciphertext) throws StrongSaltKeyException {
        return this.passwordKey.decryptBase64(ciphertext);
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
     * For internal use. Encrypts with the logged-in user's password-derived key.
     *
     * @return Ciphertext
     */
    public byte[] userEncrypt(byte[] plaintext) throws StrongSaltKeyException {
        return this.passwordKey.encrypt(plaintext);
    }

    /**
     * For internal use. Decrypts with the logged-in user's password-derived key.
     *
     * @return Plaintext
     */
    public byte[] userDecrypt(byte[] ciphertext) throws StrongSaltKeyException {
        return this.passwordKey.decrypt(ciphertext);
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
