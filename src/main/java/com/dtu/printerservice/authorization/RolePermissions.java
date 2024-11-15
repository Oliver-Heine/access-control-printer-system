package com.dtu.printerservice.authorization;

public class RolePermissions {
    public static final Action[] ADMIN_PERMISSIONS = new Action[] {
            Action.PRINT,
            Action.QUEUE,
            Action.TOP_QUEUE,
            Action.START,
            Action.STOP,
            Action.RESTART,
            Action.STATUS,
            Action.READ_CONFIG,
            Action.SET_CONFIG
    };

    public static final Action[] JANITOR_PERMISSIONS = new Action[] {
            Action.START,
            Action.STOP,
            Action.RESTART,
            Action.READ_CONFIG,
            Action.SET_CONFIG
    };

    public static final Action[] POWERUSER_PERMISSIONS = new Action[] {
            Action.PRINT,
            Action.QUEUE,
            Action.TOP_QUEUE,
            Action.RESTART
    };

    public static final Action[] BASIC_PERMISSIONS = new Action[] {
            Action.PRINT,
            Action.RESTART
    };
}
