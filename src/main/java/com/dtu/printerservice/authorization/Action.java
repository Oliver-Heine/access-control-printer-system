package com.dtu.printerservice.authorization;

public enum Action {

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

    Action(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    @Override
    public String toString() {
        return action;
    }
}
