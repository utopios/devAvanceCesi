package com.cnp.api.apirestcnp.exception;

public class LocalException extends Exception {

    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public LocalException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public LocalException() {
        super();
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}




