package com.tm.util.exceptions;

public class ApplicationException extends RuntimeException {

	protected String errorCode;
	
    private static final long serialVersionUID = 1L;

    public ApplicationException() {
        super();
    }

    public ApplicationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(final String message) {
        super(message);
    }

    public ApplicationException(final Throwable cause) {
        super(cause);
    }
    
    public String getErrorCode() {
    	return errorCode;
    }
}
