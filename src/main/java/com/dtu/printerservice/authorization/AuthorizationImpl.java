package com.dtu.printerservice.authorization;

import java.util.Arrays;


public class AuthorizationImpl implements Authorization{
    @Override
    public Boolean authorize(Role role, String actionName) {
        Action action = Action.valueOf(actionName);

        return Arrays.asList(role.getPermissions()).contains(action);
    }
}
