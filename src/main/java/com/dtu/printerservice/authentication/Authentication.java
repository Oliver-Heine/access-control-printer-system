package com.dtu.printerservice.authentication;

public interface Authentication {
    void AuthenticateUser(String token);
    String login(String username, String password);
    void newUser(String username, String password);
    String getUserName(String token);
}
