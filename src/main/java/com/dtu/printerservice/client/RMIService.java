package com.dtu.printerservice.client;

import com.dtu.printerservice.users.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIService extends Remote {

    String echo(String input) throws RemoteException;
    String start(String token) throws RemoteException;
    String stop(String token) throws RemoteException;
    String print(String filename, String printer, String token) throws RemoteException;
    String getState(String token) throws RemoteException;
    String restart(String token) throws RemoteException;
    String getQueue(String token) throws RemoteException;
    String moveToTopOfQueue(String filename, int jobID, String token) throws RemoteException;
    String readConfig(String token) throws RemoteException;
    String setConfig(String token) throws RemoteException;
}
