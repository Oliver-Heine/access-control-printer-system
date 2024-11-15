package com.dtu.printerservice.operations;

import com.dtu.printerservice.authentication.Authentication;
import com.dtu.printerservice.authentication.AuthenticationImpl;

public class PrintServer implements PrinterOperations {
    private final Authentication authentication = AuthenticationImpl.getInstance();

    public PrintServer() {
    }

    @Override
    public void print(String filename, String printerName, String token) {
        authentication.AuthenticateUser(token, "PRINT");
        System.out.println("print called by user: " + authentication.getUserName(token));
    }

    @Override
    public void queue(String printerName, String token) {
        authentication.AuthenticateUser(token, "QUEUE");
        System.out.println("queue called by user: " + authentication.getUserName(token));
    }

    @Override
    public void topQueue(String printerName, int job, String token) {
        authentication.AuthenticateUser(token, "TOP_QUEUE");
        System.out.println("topQueue called by user: " + authentication.getUserName(token));
    }

    @Override
    public void start(String token) {
        authentication.AuthenticateUser(token, "START");
        System.out.println("start called by user: " + authentication.getUserName(token));
    }

    @Override
    public void stop(String token) {
        authentication.AuthenticateUser(token, "STOP");
        System.out.println("stop called by user: " + authentication.getUserName(token));
    }

    @Override
    public void restart(String token) {
        authentication.AuthenticateUser(token,  "RESTART");
        System.out.println("restart called by user: " + authentication.getUserName(token));
    }

    @Override
    public void status(String printerName, String token) {
        authentication.AuthenticateUser(token, "STATUS");
        System.out.println("status called by user: " + authentication.getUserName(token));
    }

    @Override
    public void readConfig(String token) {
        authentication.AuthenticateUser(token, "READ_CONFIG");
        System.out.println("readConfig called by user: " + authentication.getUserName(token));
    }

    @Override
    public void setConfig(String token) {
            authentication.AuthenticateUser(token, "SET_CONFIG");
            System.out.println("SetConfig called by user: " + authentication.getUserName(token));
    }
}
