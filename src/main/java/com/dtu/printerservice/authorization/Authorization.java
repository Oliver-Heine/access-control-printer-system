package com.dtu.printerservice.authorization;

public interface Authorization {

    Boolean authorize(String username, String actionName);
}
