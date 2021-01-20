package com.strongsalt.strongdoc.sdk.api.responses;

public class UnshareDocumentResponse {
    boolean success;
    boolean allowed;
    boolean alreadyUnshared;

    public UnshareDocumentResponse(boolean success, boolean allowed, boolean alreadyUnshared) {
        this.success = success;
        this.allowed = allowed;
        this.alreadyUnshared = alreadyUnshared;
    }

    public boolean getSuccess() {
        return success;
    }

    public boolean getAllowed() {
        return allowed;
    }

    public boolean getAlreadyUnshared() {
        return alreadyUnshared;
    }

}
