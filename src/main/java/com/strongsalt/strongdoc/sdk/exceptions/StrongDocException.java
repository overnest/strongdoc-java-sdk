package com.strongsalt.strongdoc.sdk.exceptions;

public class StrongDocException extends Exception{

    public StrongDocException() {
        super();
    }

    public StrongDocException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public StrongDocException(String message, Throwable cause) {
        super(message, cause);
    }

    public StrongDocException(String message) {
        super(message);
    }

    public StrongDocException(Throwable cause) {
        super(cause);
    }
}
