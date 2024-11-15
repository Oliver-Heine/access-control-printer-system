package com.dtu.printerservice.authorization;

public interface Authorization {

    Boolean authorize(Role role, String actionName);
}
