package com.dtu.printerservice.operations;

import com.dtu.printerservice.authentication.AuthenticationImpl;
import com.dtu.printerservice.authorization.Role;
import com.dtu.printerservice.users.User;

public class PrintServer implements PrinterOperations {
    private AuthenticationImpl authentication = AuthenticationImpl.getInstance();

    public PrintServer() {
    }

    @Override
    public void print(String filename, String printerName, User user, String token) {
        try {
            authentication.AuthenticateUser(Role.BASIC, token);
            System.out.println("print called by user: XYZ");
        } catch (RuntimeException e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Invalid session. Please log in again.");
        }
    }

    @Override
    public void queue(String printerName, User user, String token) {
        try {


            authentication.AuthenticateUser(Role.SUPERUSER, token);
            System.out.println("queue called by user: XYZ");
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Invalid session. Please log in again.");
        }
    }

    @Override
    public void topQueue(String printerName, int job, User user, String token) {
        try {


        authentication.AuthenticateUser(Role.SUPERUSER, token);
        System.out.println("topQueue called by user: XYZ");
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Invalid session. Please log in again.");
        }
    }

    @Override
    public void start(User user, String token) {

        try {


        authentication.AuthenticateUser(Role.ADMIN, token);
        System.out.println("start called by user: XYZ");
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Invalid session. Please log in again.");
        }
    }

    @Override
    public void stop(User user, String token) {
        try {
        authentication.AuthenticateUser(Role.ADMIN, token);
        System.out.println("stop called by user: XYZ");
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Invalid session. Please log in again.");
        }
    }

    @Override
    public void restart(User user, String token) {
        try {
        authentication.AuthenticateUser(Role.ADMIN, token);
        System.out.println("restart called by user: XYZ");
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Invalid session. Please log in again.");
        }
    }

    @Override
    public void status(String printerName, User user, String token) {
        try {


        authentication.AuthenticateUser(Role.ADMIN, token);
        System.out.println("status called by user: XYZ");
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Invalid session. Please log in again.");
        }
    }

    @Override
    public void readConfig(String parameter, User user, String token) {
        try {


        authentication.AuthenticateUser(Role.ADMIN, token);
        System.out.println("readConfig called by user: XYZ");
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Invalid session. Please log in again.");
        }
    }

    @Override
    public void setConfig(String parameter, String value, User user, String token) {
        try {
            authentication.AuthenticateUser(Role.ADMIN, token);
            System.out.println("SetConfig called by user: XYZ");
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Invalid session. Please log in again.");
        }
    }
}
