package com.walletservice.exception;

public class ServiceException extends RuntimeException {
    private String exceptionMessage;
    private String responseCode;
    public ServiceException(String exceptionMessage, String responseCode) {
        super(exceptionMessage);
        this.exceptionMessage = exceptionMessage;
        this.responseCode = responseCode;
    }

    public ServiceException(String message) {
        super(message);
        this.exceptionMessage = message;
    }

    public ServiceException(String message,Exception e) {
        super(message,e);
        this.exceptionMessage = message;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
