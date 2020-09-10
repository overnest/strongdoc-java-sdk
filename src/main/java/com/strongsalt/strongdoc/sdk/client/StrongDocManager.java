package com.strongsalt.strongdoc.sdk.client;

import com.strongsalt.crypto.exception.StrongSaltKdfException;
import com.strongsalt.crypto.exception.StrongSaltKeyException;
import com.strongsalt.crypto.kdf.StrongSaltKDF;
import com.strongsalt.crypto.key.StrongSaltKey;
import com.strongsalt.strongdoc.sdk.api.StrongDocAccount;
import com.strongsalt.strongdoc.sdk.exceptions.StrongDocServiceException;
import com.strongsalt.strongdoc.sdk.proto.Account;

import javax.net.ssl.SSLException;
import java.nio.file.FileSystems;


/**
 * StrongDocManager
 * encapsulates the client object that allows connection to the remote service
 */
public class StrongDocManager {
    private static StrongDocManager manager = null;
    StrongDocServiceClient client;

    // singleton
    public static StrongDocManager getInstance(){
        if( manager == null){
            manager = new StrongDocManager();
        }
        return manager;
    }

    public StrongDocServiceClient getStrongDocClient() throws StrongDocServiceException {
        if( manager != null){
            if(manager.client == null){
                try{
                    initClient();
                }catch(SSLException e){
                    throw new StrongDocServiceException("Can not init StrongDocService client");
                }
            }
            return manager.client;
        }
        throw new StrongDocServiceException("Can not init StrongDocService client without StrongDocManager instance");
    }

    StrongSaltKey passwordKey;
    String passwordKeyID;

    //todo different params for different mode
    private void initClient() throws SSLException {
        String HOST = "localhost";
        int PORT = 9090;
        String CERT_PATH = FileSystems.getDefault()
                .getPath("src", "test", "resources", "certs", "grpc.root.localhost.pem")
                .toAbsolutePath()
                .toString();
        client = new StrongDocServiceClient(HOST, PORT, CERT_PATH);
    }

    public StrongSaltKey getPasswordKey(){
        if( manager != null){
            return manager.passwordKey;
        }
        return null;
    }

    public void setPasswordKey(String passwordKeyID){
        this.passwordKeyID  = passwordKeyID;
    }

    public String getPasswordKeyID(){
        if( manager != null){
            return manager.passwordKeyID;
        }
        return null;
    }

    public void setPasswordKey(StrongSaltKey passwordKey){
        this.passwordKey = passwordKey;
    }

    public void close() throws StrongDocServiceException {
        if (manager != null && client != null){
            try{
                client.shutdown();
            }catch(InterruptedException e){
                throw new StrongDocServiceException("Can not close StrongDocService client");
            }
        }
    }
}
