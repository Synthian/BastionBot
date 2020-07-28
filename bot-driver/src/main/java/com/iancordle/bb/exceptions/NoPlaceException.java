package com.iancordle.bb.exceptions;

public class NoPlaceException extends Exception {
    private static final long serialVersionUID = 1L;

    private final String runId;

    public NoPlaceException(String id) {
        super("Could not find run in leaderboard for id: " + id);
        runId = id;
    }

    public String getRunId() {
        return runId;
    }
}
