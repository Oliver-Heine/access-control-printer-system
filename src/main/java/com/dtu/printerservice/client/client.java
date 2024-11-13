package com.dtu.printerservice.client;

import com.dtu.printerservice.authentication.AuthenticationImpl;
import com.dtu.printerservice.users.User;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Objects;
import java.util.Scanner;

public class client {

    private static User currentUser = null;
    private static final AuthenticationImpl authentication = AuthenticationImpl.getInstance();
    private static String token = null; //This represents a web cookie

    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        RMIService service = (RMIService) Naming.lookup("rmi://localhost:5099/hello");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Print Server, how might I be of service?");
        System.out.println("You must first login to the system");
        userLogin();


        while (true) {
            System.out.println("Welcome " + currentUser.getName());
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
                    service.print(filename, "Printer" + printer, currentUser, token);
                    break;
                }
                case 2: {
                    System.out.println("Current printer status is: " + service.getState(currentUser, token));
                    break;
                }
                case 3: {
                    System.out.println("Stopping printer: " + service.stop(currentUser, token));
                    break;
                }
                case 4: {
                    System.out.println("Starting printer: " + service.start(currentUser, token));
                    break;
                }
                case 5: {
                    System.out.println("Restarting printer: " + service.restart(currentUser, token));
                    break;
                }
                case 6: {
                    System.out.println("Get printer queue: " + service.getQueue(currentUser, token));
                    break;
                }
                case 7: {
                    System.out.println("What file would you like to move?");
                    String filename = scanner.nextLine();
                    System.out.println("Moving print to top: " + service.moveToTopOfQueue(filename, 1, currentUser, token));
                    break;
                }
                default: {
                    System.out.println("Unknown option");
                    break;
                }
            }
        }
    }

    private static boolean userLogin(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        if (Objects.equals(username, "8")) {
            System.out.print("Enter username: ");
            username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            authentication.newUser(username, password);
            authentication.login(username, password);
        } else {
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            authentication.login(username, password);
        }




        //TODO extend to validate login. See AuthenticationImpl.java
        currentUser = new User(username, "Admin");

        return true;
    }
}
