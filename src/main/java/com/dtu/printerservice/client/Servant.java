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
    public String start(User user) throws RemoteException {
        printServer.start(user);
        return "started";
    }

    @Override
    public String stop( User user) throws RemoteException {
        printServer.stop(user);
        return "Stopped";
    }

    @Override
    public String getState(User user) throws RemoteException {
        printServer.status(printerName, user);
        return "Stopped";
    }

    @Override
    public String restart(User user) throws RemoteException {
        printServer.restart(user);
        return "Restarted";
    }

    @Override
    public String print(String filename, String printer, User user) throws RemoteException {
        printerName = printer;
        printServer.print(filename, printer, user);
        return "Print queued";
    }

    @Override
    public String getQueue(User user) throws RemoteException {
        printServer.queue(printerName, user);
        return "Queued";
    }

    @Override
    public String moveToTopOfQueue(String filename, int jobId, User user) throws RemoteException {
        printServer.topQueue(filename, jobId, user);
        return "Moved to top of queue";
    }
}
