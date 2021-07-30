package com.tm.util.exceptions;

public class DaoException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public DaoException() {
    	errorCode = "1009";
    }

    public DaoException(final String message, final Throwable cause) {
        super(message, cause);
    	errorCode = "1009";
    }

    public DaoException(final String message) {
        super(message);
    	errorCode = "1009";
    }

    public DaoException(final Throwable cause) {
        super(cause);
    	errorCode = "1009";
    }
}
