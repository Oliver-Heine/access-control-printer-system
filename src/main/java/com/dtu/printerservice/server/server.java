package com.dtu.printerservice.server;

import com.dtu.printerservice.authorization.RolePermissions;
import com.dtu.printerservice.client.Servant;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class server {
    public static void main(String[] args) throws RemoteException {
        System.out.println("Configures role permissions");
        RolePermissions.configureRolePermissions();
        System.out.println("Role permissions configured");

        Registry registry = LocateRegistry.createRegistry(5099);
        registry.rebind("hello", new Servant());
    }
}
