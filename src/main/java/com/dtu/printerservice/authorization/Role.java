package com.dtu.printerservice.authorization;

import java.util.Set;

public enum Role {

    ADMIN("ADMIN",  RolePermissions.ADMIN_PERMISSIONS),
    JANITOR("JANITOR",  RolePermissions.JANITOR_PERMISSIONS),
    SUPERUSER("SUPERUSER",  RolePermissions.POWERUSER_PERMISSIONS),
    BASIC("BASIC",  RolePermissions.BASIC_PERMISSIONS);

    private String name;
    private Action[] permissions;

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

}
