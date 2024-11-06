package com.dtu.printerservice.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.dtu.printerservice.operations.PrintServer;
import com.dtu.printerservice.users.User;

public class Servant extends UnicastRemoteObject implements RMIService {

    private final PrintServer printServer;
    private String printerName;

    public Servant() throws RemoteException {
        super();
        this.printServer = new PrintServer();
    }

    @Override
    public String echo(String input) throws RemoteException {
        return "echo not supported";
    }

    @Override
    public String start(User user, String token) throws RemoteException {
        printServer.start(user, token);
        return "started";
    }

    @Override
    public String stop( User user, String token) throws RemoteException {
        printServer.stop(user, token);
        return "Stopped";
    }

    @Override
    public String getState(User user, String token) throws RemoteException {
        printServer.status(printerName, user, token);
        return "Stopped";
    }

    @Override
    public String restart(User user, String token) throws RemoteException {
        printServer.restart(user, token);
        return "Restarted";
    }

    @Override
    public String print(String filename, String printer, User user, String token) throws RemoteException {
        printerName = printer;
        printServer.print(filename, printer, user, token);
        return "Print queued";
    }

    @Override
    public String getQueue(User user, String token) throws RemoteException {
        printServer.queue(printerName, user, token);
        return "Queued";
    }

    @Override
    public String moveToTopOfQueue(String filename, int jobId, User user, String token) throws RemoteException {
        printServer.topQueue(filename, jobId, user, token);
        return "Moved to top of queue";
    }
}
