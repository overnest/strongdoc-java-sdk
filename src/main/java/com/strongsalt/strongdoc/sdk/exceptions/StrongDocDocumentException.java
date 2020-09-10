package com.strongsalt.strongdoc.sdk.exceptions;

public class StrongDocDocumentException extends StrongDocException{
    public StrongDocDocumentException() {
        super();
    }

    public StrongDocDocumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public StrongDocDocumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public StrongDocDocumentException(String message) {
        super(message);
    }

    public StrongDocDocumentException(Throwable cause) {
        super(cause);
    }
}
