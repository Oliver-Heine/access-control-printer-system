package com.dtu.printerservice.operations;

import com.dtu.printerservice.authentication.AuthenticationImpl;
import com.dtu.printerservice.users.User;

public class PrintServer implements PrinterOperations {
    private AuthenticationImpl authentication = new AuthenticationImpl();
    public PrintServer() {
    }

    @Override
    public void print(String filename, String printerName, User user) {
        authentication.AuthenticateUser(user);
        System.out.println("print called by user: XYZ");
    }

    @Override
    public void queue(String printerName, User user) {
        authentication.AuthenticateUser(user);
        System.out.println("queue called by user: XYZ");
    }

    @Override
    public void topQueue(String printerName, int job, User user) {
        authentication.AuthenticateUser(user);
        System.out.println("topQueue called by user: XYZ");
    }

    @Override
    public void start(User user) {
        authentication.AuthenticateUser(user);
        System.out.println("start called by user: XYZ");
    }

    @Override
    public void stop(User user) {
        authentication.AuthenticateUser(user);
        System.out.println("stop called by user: XYZ");
    }

    @Override
    public void restart(User user) {
        authentication.AuthenticateUser(user);
        System.out.println("restart called by user: XYZ");
    }

    @Override
    public void status(String printerName, User user) {
        authentication.AuthenticateUser(user);
        System.out.println("status called by user: XYZ");
    }

    @Override
    public void readConfig(String parameter, User user) {
        authentication.AuthenticateUser(user);
        System.out.println("readConfig called by user: XYZ");
    }

    @Override
    public void setConfig(String parameter, String value, User user) {
        authentication.AuthenticateUser(user);
        System.out.println("SetConfig called by user: XYZ");
    }
}
