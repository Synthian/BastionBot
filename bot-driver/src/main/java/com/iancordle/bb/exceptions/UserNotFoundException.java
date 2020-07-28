package com.iancordle.bb.exceptions;

public class UserNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    private final String userId;

    public UserNotFoundException(String id) {
        super("Could not find user on Twitch for id: " + id);
        userId = id;
    }

    public String getUserId() {
        return userId;
    }
}
