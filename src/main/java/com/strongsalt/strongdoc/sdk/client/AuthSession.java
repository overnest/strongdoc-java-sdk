package com.strongsalt.strongdoc.sdk.client;

import com.strongsalt.crypto.exception.StrongSaltKeyException;
import com.strongsalt.crypto.exception.StrongSaltSRPException;
import com.strongsalt.crypto.key.StrongSaltKey;
import com.strongsalt.crypto.pake.srp.SRPClient;
import com.strongsalt.strongdoc.sdk.exceptions.StrongDocServiceException;
import com.strongsalt.strongdoc.sdk.proto.Account;

public class AuthSession {
    private String authID;
    private Account.AuthType authType;
    private int authVersion;
    // login
    private Account.LoginResp loginResp;
    // SRP
    private SRPClient srpClient;
    private StrongSaltKey srpSharedKey;

    public AuthSession(String authID, Account.AuthType authType, int authVersion, Account.LoginResp loginResp, SRPClient srpClient) {
        this.authID = authID;
        this.authType = authType;
        this.authVersion = authVersion;
        this.loginResp = loginResp;
        this.srpClient = srpClient;
    }

    String getAuthID() {
        return this.authID;
    }

    Account.AuthType getAuthType() {
        return authType;
    }

    int getAuthVersion() {
        return authVersion;
    }

    Account.LoginResp getLoginResp() {
        return this.loginResp;
    }

    boolean canAuthenticateData() throws StrongSaltSRPException {
        if (this.srpSharedKey == null) {
            if (this.srpClient == null) {
                return false;
            }
            this.srpSharedKey = this.srpClient.StrongSaltKey();
        }
        return true;
    }

    String PrepareDataForAuth(byte[] plaintext) throws StrongDocServiceException, StrongSaltKeyException, StrongSaltSRPException {
        if (this.srpSharedKey == null) {
            if (this.srpClient == null) {
                throw new StrongDocServiceException("This Authentication Session cannot authenticate data.");
            }
            this.srpSharedKey = this.srpClient.StrongSaltKey();
        }
        return this.srpSharedKey.encryptBase64(plaintext);
    }
}
