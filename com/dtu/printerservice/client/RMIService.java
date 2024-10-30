package com.dtu.printerservice.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIService extends Remote {

    public String echo(String input) throws RemoteException;
    public String start() throws RemoteException;
    public String stop() throws RemoteException;
    public String print(String filename, String printer) throws RemoteException;
    public String getState() throws RemoteException;
    public String restart() throws RemoteException;
    public String getQueue() throws RemoteException
    public String moveToTopOfQueue(String filename) throws RemoteException;
}
