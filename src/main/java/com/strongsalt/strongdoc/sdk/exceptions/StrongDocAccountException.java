package com.strongsalt.strongdoc.sdk.exceptions;

public class StrongDocAccountException extends StrongDocException{
    public StrongDocAccountException() {
        super();
    }

    public StrongDocAccountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public StrongDocAccountException(String message, Throwable cause) {
        super(message, cause);
    }

    public StrongDocAccountException(String message) {
        super(message);
    }

    public StrongDocAccountException(Throwable cause) {
        super(cause);
    }
}
