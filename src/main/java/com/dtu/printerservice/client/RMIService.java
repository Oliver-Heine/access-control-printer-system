package com.dtu.printerservice.client;

import com.dtu.printerservice.users.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIService extends Remote {

    public String echo(String input) throws RemoteException;
    public String start(User user, String token) throws RemoteException;
    public String stop(User user, String token) throws RemoteException;
    public String print(String filename, String printer, User user, String token) throws RemoteException;
    public String getState(User user, String token) throws RemoteException;
    public String restart(User user, String token) throws RemoteException;
    public String getQueue(User user, String token) throws RemoteException;
    public String moveToTopOfQueue(String filename, int jobID, User user, String token) throws RemoteException;
}