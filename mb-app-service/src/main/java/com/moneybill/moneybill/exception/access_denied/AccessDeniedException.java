package com.moneybill.moneybill.exception.access_denied;

public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException(String message) {
        super(message);
    }
}
