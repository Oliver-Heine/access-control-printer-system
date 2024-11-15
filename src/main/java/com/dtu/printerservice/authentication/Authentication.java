package com.dtu.printerservice.authentication;

public interface Authentication {
    void AuthenticateUser(String token, String action);
    String login(String username, String password);
    void newUser(String username, String password);
    Role getRole(String token);
    String getUserName(String token);
}
