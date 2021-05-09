package com.iancordle.bb.exceptions;

public class ConfigurationException extends Exception {
    private static final long serialVersionUID = 1L;

    public ConfigurationException() {
        super();
    }

    public ConfigurationException(String message) {
        super(message);
    }
}
