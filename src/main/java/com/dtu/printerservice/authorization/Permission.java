package com.dtu.printerservice.authorization;

public enum Permission {
    PRINT("PRINT"),
    QUEUE("QUEUE"),
    TOP_QUEUE("TOP_QUEUE"),
    START("START"),
    STOP("STOP"),
    RESTART("RESTART"),
    STATUS("STATUS"),
    READ_CONFIG("READ_CONFIG"),
    SET_CONFIG("SET_CONFIG");

    private final String action;

    Permission(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
