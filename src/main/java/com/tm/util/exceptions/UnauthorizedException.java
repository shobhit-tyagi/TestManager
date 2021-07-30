package com.tm.util.exceptions;

public class UnauthorizedException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public UnauthorizedException() {
    	super();
    }

    public UnauthorizedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedException(final String message) {
        super(message);
    }

    public UnauthorizedException(final Throwable cause) {
        super(cause);
    }

}
