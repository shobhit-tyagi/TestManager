package com.tm.util.exceptions;

public class InternalApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public InternalApplicationException() {
    	errorCode = "1001";
    }

    public InternalApplicationException(final String message, final Throwable cause) {
        super(message, cause);
    	errorCode = "1001";
    }

    public InternalApplicationException(final String message) {
        super(message);
    	errorCode = "1001";
    }

    public InternalApplicationException(final Throwable cause) {
        super(cause);
    	errorCode = "1001";
    }
}
