package com.tm.util.exceptions;

public class EmailServiceFailureException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public EmailServiceFailureException() {
    	errorCode = "1004";
    }

    public EmailServiceFailureException(final String message, final Throwable cause) {
        super(message, cause);
    	errorCode = "1004";
    }

    public EmailServiceFailureException(final String message) {
        super(message);
    	errorCode = "1004";
    }

    public EmailServiceFailureException(final Throwable cause) {
        super(cause);
    	errorCode = "1004";
    }
}
