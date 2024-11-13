package com.dtu.printerservice.client;

import com.dtu.printerservice.users.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIService extends Remote {

    public String echo(String input) throws RemoteException;
    public String start(String token) throws RemoteException;
    public String stop(String token) throws RemoteException;
    public String print(String filename, String printer, String token) throws RemoteException;
    public String getState(String token) throws RemoteException;
    public String restart(String token) throws RemoteException;
    public String getQueue(String token) throws RemoteException;
    public String moveToTopOfQueue(String filename, int jobID, String token) throws RemoteException;
    public String readConfig(String token) throws RemoteException;
    public String setConfig(String token) throws RemoteException;
}
