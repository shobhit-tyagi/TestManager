package com.tm.util.exceptions;

public class DatabaseException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public DatabaseException() {
    	errorCode = "1006";
    }

    public DatabaseException(final String message, final Throwable cause) {
        super(message, cause);
    	errorCode = "1006";
    }

    public DatabaseException(final String message) {
        super(message);
    	errorCode = "1006";
    }

    public DatabaseException(final Throwable cause) {
        super(cause);
    	errorCode = "1006";
    }
}
