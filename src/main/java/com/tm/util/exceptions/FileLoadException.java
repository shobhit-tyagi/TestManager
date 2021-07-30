package com.tm.util.exceptions;

public class FileLoadException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public FileLoadException() {
    	errorCode = "1003";
    }

    public FileLoadException(final String message, final Throwable cause) {
        super(message, cause);
    	errorCode = "1003";
    }

    public FileLoadException(final String message) {
        super(message);
    	errorCode = "1003";
    }

    public FileLoadException(final Throwable cause) {
        super(cause);
    	errorCode = "1003";
    }
}
