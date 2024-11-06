package com.dtu.printerservice.authorization;

public enum Role {

    ADMIN(3), ELEVATED(2), BASIC(1);

    private int role;

    Role(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;
    }

}
