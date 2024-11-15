package com.dtu.printerservice.authorization;

import java.util.Set;

public enum Role {
    ADMIN("ADMIN",  RolePermissions.ADMIN_PERMISSIONS),
    JANITOR("JANITOR",  RolePermissions.JANITOR_PERMISSIONS),
    SUPERUSER("SUPERUSER",  RolePermissions.POWERUSER_PERMISSIONS),
    BASIC("BASIC",  RolePermissions.BASIC_PERMISSIONS);

    private String name;
    private final Action[] permissions;

    Role(String name, Action[] permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public Action[] getPermissions() {
        return permissions;
    }

    public static Role getRole(String role){
        return switch (role) {
            case "ADMIN" -> Role.ADMIN;
            case "JANITOR" -> Role.JANITOR;
            case "SUPERUSER" -> Role.SUPERUSER;
            case "BASIC" -> Role.BASIC;
            default -> null;
        };
    }

}
