package com.dtu.printerservice.operations;

import com.dtu.printerservice.authentication.AuthenticationImpl;
import com.dtu.printerservice.authorization.Role;
import com.dtu.printerservice.users.User;

public class PrintServer implements PrinterOperations {
    private AuthenticationImpl authentication = AuthenticationImpl.getInstance();

    public PrintServer() {
    }

    @Override
    public void print(String filename, String printerName, String token) {
        try {
            authentication.AuthenticateUser(Role.BASIC, token, "PRINT");
            System.out.println("print called by user: XYZ");
        } catch (RuntimeException e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Invalid session. Please log in again.");
        }
    }

    @Override
    public void queue(String printerName, String token) {
        try {


            authentication.AuthenticateUser(Role.SUPERUSER, token, "QUEUE");
            System.out.println("queue called by user: XYZ");
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Invalid session. Please log in again.");
        }
    }

    @Override
    public void topQueue(String printerName, int job, String token) {
        try {


        authentication.AuthenticateUser(Role.SUPERUSER, token, "TOP_QUEUE");
        System.out.println("topQueue called by user: XYZ");
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Invalid session. Please log in again.");
        }
    }

    @Override
    public void start(String token) {
        try {


        authentication.AuthenticateUser(Role.ADMIN, token, "START");
        System.out.println("start called by user: XYZ");
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Invalid session. Please log in again.");
        }
    }

    @Override
    public void stop(String token) {
        try {
        authentication.AuthenticateUser(Role.ADMIN, token, "STOP");
        System.out.println("stop called by user: XYZ");
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Invalid session. Please log in again.");
        }
    }

    @Override
    public void restart(String token) {
        try {
        authentication.AuthenticateUser(Role.ADMIN, token,  "RESTART");
        System.out.println("restart called by user: XYZ");
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Invalid session. Please log in again.");
        }
    }

    @Override
    public void status(String printerName, String token) {
        try {


        authentication.AuthenticateUser(Role.ADMIN, token, "STATUS");
        System.out.println("status called by user: XYZ");
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Invalid session. Please log in again.");
        }
    }

    @Override
    public void readConfig(String token) {
        try {


        authentication.AuthenticateUser(Role.ADMIN, token, "READ_CONFIG");
        System.out.println("readConfig called by user: XYZ");
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Invalid session. Please log in again.");
        }
    }

    @Override
    public void setConfig(String token) {
        try {
            authentication.AuthenticateUser(Role.ADMIN, token, "SET_CONFIG");
            System.out.println("SetConfig called by user: XYZ");
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Invalid session. Please log in again.");
        }
    }
}
