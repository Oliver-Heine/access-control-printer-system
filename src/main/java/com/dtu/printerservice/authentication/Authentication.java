package com.dtu.printerservice.authentication;

import com.dtu.printerservice.authorization.Role;

public interface Authentication {
    void AuthenticateUser(String token, String action);
    String login(String username, String password);
    void newUser(String username, String password);
    Role getRole(String token);
    String getUserName(String token);
}
