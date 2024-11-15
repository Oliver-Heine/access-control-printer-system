package com.dtu.printerservice.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.dtu.printerservice.operations.PrintServer;
import com.dtu.printerservice.operations.PrinterOperations;

public class Servant extends UnicastRemoteObject implements RMIService {
    private final PrinterOperations printServer;
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
    public String start(String token) throws RemoteException {
        printServer.start(token);
        return "started";
    }

    @Override
    public String stop(String token) throws RemoteException {
        printServer.stop(token);
        return "Stopped";
    }

    @Override
    public String getState(String token) throws RemoteException {
        printServer.status(printerName, token);
        return "Stopped";
    }

    @Override
    public String restart(String token) throws RemoteException {
        printServer.restart(token);
        return "Restarted";
    }

    @Override
    public String print(String filename, String printer, String token) throws RemoteException {
        printerName = printer;
        printServer.print(filename, printer, token);
        return "Print queued";
    }

    @Override
    public String getQueue(String token) throws RemoteException {
        printServer.queue(printerName, token);
        return "Queued";
    }

    @Override
    public String moveToTopOfQueue(String filename, int jobId, String token) throws RemoteException {
        printServer.topQueue(filename, jobId, token);
        return "Moved to top of queue";
    }

    @Override
    public String readConfig(String token) throws RemoteException {
        printServer.readConfig(token);
        return "Moved to top of queue";
    }

    @Override
    public String setConfig(String token) throws RemoteException {
        printServer.setConfig(token);
        return "Moved to top of queue";
    }
}
