package com.dtu.printerservice.client;

import com.dtu.printerservice.users.User;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class client {

    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        User currentUser = null;

        RMIService service = (RMIService) Naming.lookup("rmi://localhost:5099/hello");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Print Server, how might I be of service?");
            System.out.println("(1) Print a file");
            System.out.println("(2) Get printer status");
            System.out.println("(3) Stop printer");
            System.out.println("(4) Start printer");
            System.out.println("(5) Restart printer");
            System.out.println("(6) Get printer queue");
            System.out.println("(7) Move print to top of queue");

            int selection = scanner.nextInt();

            switch (selection) {
                case 1: {
                    scanner.nextLine();
                    System.out.println("Input filename:");
                    String filename = scanner.nextLine();
                    System.out.println("What printer would you like to use, Printer(1-3)?");
                    int printer = scanner.nextInt();
                    service.print(filename, "Printer" + printer, currentUser);
                    break;
                }
                case 2: {
                    System.out.println("Current printer status is: " + service.getState(currentUser));
                    break;
                }
                case 3: {
                    System.out.println("Stopping printer: " + service.stop(currentUser));
                    break;
                }
                case 4: {
                    System.out.println("Starting printer: " + service.start(currentUser));
                    break;
                }
                case 5: {
                    System.out.println("Restarting printer: " + service.restart(currentUser));
                    break;
                }
                case 6: {
                    System.out.println("Get printer queue: " + service.getQueue(currentUser));
                    break;
                }
                case 7: {
                    System.out.println("What file would you like to move?");
                    String filename = scanner.nextLine();
                    System.out.println("Moving print to top: " + service.moveToTopOfQueue(filename, 1, currentUser));
                    break;
                }
                default: {
                    System.out.println("Unknown option");
                    break;
                }
            }
        }
    }
}
