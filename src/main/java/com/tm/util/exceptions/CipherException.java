package com.tm.util.exceptions;

public class CipherException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public CipherException() {
    	errorCode = "1005";
    }

    public CipherException(final String message, final Throwable cause) {
        super(message, cause);
    	errorCode = "1005";
    }

    public CipherException(final String message) {
        super(message);
    	errorCode = "1005";
    }

    public CipherException(final Throwable cause) {
        super(cause);
    	errorCode = "1005";
    }
}
