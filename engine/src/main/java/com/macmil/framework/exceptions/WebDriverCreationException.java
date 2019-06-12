package com.macmil.framework.exceptions;

public class WebDriverCreationException extends RuntimeException {
    public WebDriverCreationException(String message) { super(message); }

    public WebDriverCreationException(String message, Throwable reason) { super(message,reason); }
}
