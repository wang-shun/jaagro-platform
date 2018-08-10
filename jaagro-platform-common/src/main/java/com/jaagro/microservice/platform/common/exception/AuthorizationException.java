package com.jaagro.microservice.platform.common.exception;

/**
 * @author tony
 */
public class AuthorizationException extends Exception{

    public AuthorizationException() {
        super();
    }

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
