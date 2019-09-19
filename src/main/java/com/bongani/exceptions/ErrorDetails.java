package com.bongani.exceptions;

public class ErrorDetails {
    private String message;
    private int status;

    public ErrorDetails(String message, int status) {
        super();
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
