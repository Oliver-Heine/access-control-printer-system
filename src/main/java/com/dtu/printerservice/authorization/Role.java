package com.dtu.printerservice.authorization;

public enum Role {

    ADMIN("ADMIN"), JANITOR("JANITOR"), SUPERUSER("SUPERUSER"), BASIC("BASIC");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
