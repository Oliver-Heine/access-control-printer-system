package com.dtu.printerservice.client;

import com.dtu.printerservice.authentication.Authentication;
import com.dtu.printerservice.authentication.AuthenticationImpl;
import com.dtu.printerservice.exceptions.InvalidTokenException;
import com.dtu.printerservice.exceptions.UnauthenticatedException;
import com.dtu.printerservice.exceptions.UnauthorizedException;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Objects;
import java.util.Scanner;

public class client {

    private static final Authentication authentication = AuthenticationImpl.getInstance();
    private static final RMIService service;

    static {
        try {
            service = (RMIService) Naming.lookup("rmi://localhost:5099/hello");
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    private static String token = null; //This represents a web cookie

    public static void main(String[] args) throws RemoteException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Print Server, how might I be of service?");
        System.out.println("You must first login to the system");
        userLogin();

        while (true) {
            System.out.println("Welcome " + authentication.getUserName(token));
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
                    try {
                        scanner.nextLine();
                        System.out.println("Input filename:");
                        String filename = scanner.nextLine();
                        System.out.println("What printer would you like to use, Printer(1-3)?");
                        int printer = scanner.nextInt();
                        service.print(filename, "Printer" + printer, token);
                    } catch (UnauthenticatedException e) {
                    System.out.println("You must login again.");
                    userLogin();
                    } catch (UnauthorizedException e) {
                        System.out.println("You do not have permission to use this operation.");
                    }
                    break;
                }
                case 2: {
                    try {
                        System.out.println("Current printer status is: " + service.getState(token));
                    } catch (UnauthenticatedException e) {
                        System.out.println("You must login again.");
                        userLogin();
                    } catch (UnauthorizedException e) {
                        System.out.println("You do not have permission to use this operation.");
                    }
                    break;
                }
                case 3: {
                    try {
                        System.out.println("Stopping printer: " + service.stop(token));
                    } catch (UnauthenticatedException e) {
                        System.out.println("You must login again.");
                        userLogin();
                    } catch (UnauthorizedException e) {
                        System.out.println("You do not have permission to use this operation.");
                    }
                    break;
                }
                case 4: {
                    try {
                        System.out.println("Starting printer: " + service.start(token));
                    } catch (UnauthenticatedException e) {
                        System.out.println("You must login again.");
                        userLogin();
                    } catch (UnauthorizedException e) {
                        System.out.println("You do not have permission to use this operation.");
                    }
                    break;
                }
                case 5: {
                    try {
                        System.out.println("Restarting printer: " + service.restart(token));
                    } catch (UnauthenticatedException e) {
                        System.out.println("You must login again.");
                        userLogin();
                    } catch (UnauthorizedException e) {
                        System.out.println("You do not have permission to use this operation.");
                    }
                    break;
                }
                case 6: {
                    try {
                        System.out.println("Get printer queue: " + service.getQueue(token));
                    } catch (UnauthenticatedException e) {
                        System.out.println("You must login again.");
                        userLogin();
                    } catch (UnauthorizedException e) {
                        System.out.println("You do not have permission to use this operation.");
                    }
                    break;
                }
                case 7: {
                    try {
                        System.out.println("What file would you like to move?");
                        String filename = scanner.nextLine();
                        System.out.println("Moving print to top: " + service.moveToTopOfQueue(filename, 1, token));
                    } catch (UnauthenticatedException e) {
                        System.out.println("You must login again.");
                        userLogin();
                    } catch (UnauthorizedException e) {
                        System.out.println("You do not have permission to use this operation.");
                    }
                    break;
                }
                case 8: {
                    try {
                        System.out.println("Read config: " + service.readConfig(token));
                    } catch (UnauthenticatedException e) {
                        System.out.println("You must login again.");
                        userLogin();
                    } catch (UnauthorizedException e) {
                        System.out.println("You do not have permission to use this operation.");
                    }
                    break;
                }
                case 9: {
                    try {
                        System.out.println("Set config: " + service.setConfig(token));
                    } catch (UnauthenticatedException e) {
                        System.out.println("You must login again.");
                        userLogin();
                    } catch (UnauthorizedException e) {
                        System.out.println("You do not have permission to use this operation.");
                    }
                    break;
                }
                default: {
                    System.out.println("Unknown option");
                    break;
                }
            }
        }
    }

    public static void userLogin(){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            token = service.authenticate(username, password);
        } catch (InvalidTokenException | RemoteException e) {
            userLogin();
        }
    }
}
