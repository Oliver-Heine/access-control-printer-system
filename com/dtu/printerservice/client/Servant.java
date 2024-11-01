package com.dtu.printerservice.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import com.dtu.printerservice.operations.PrintServer;
import com.dtu.printerservice.printer.PrinterStates;

public class Servant extends UnicastRemoteObject implements RMIService {

    private PrintServer printServer;
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
    public String start() throws RemoteException {
        printServer.start();
        return "started";
    }

    @Override
    public String stop() throws RemoteException {
        printServer.stop();
        return "Stopped";
    }

    @Override
    public String getState() throws RemoteException {
        printServer.status(printerName);
        return "Stopped";
    }

    @Override
    public String restart() throws RemoteException {
        printServer.restart();
        return "Restarted";
    }

    @Override
    public String print(String filename, String printer) throws RemoteException {
        printerName = printer;
        printServer.print(filename, printer);
        return "Print queued";
    }

    @Override
    public String getQueue() throws RemoteException {
        printServer.queue(printerName);;
        return "Queued";
    }

    @Override
    public String moveToTopOfQueue(String filename) throws RemoteException {
        printServer.topQueue(filename);
        return "Moved to top of queue";
    }
}
