package com.strongsalt.strongdoc.sdk.exceptions;

public class StrongDocServiceException extends StrongDocException{
    public StrongDocServiceException() {
        super();
    }

    public StrongDocServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public StrongDocServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public StrongDocServiceException(String message) {
        super(message);
    }

    public StrongDocServiceException(Throwable cause) {
        super(cause);
    }
}
