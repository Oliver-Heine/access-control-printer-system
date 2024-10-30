package com.dtu.printerservice.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class client {

    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {

        RMIService service = (RMIService) Naming.lookup("rmi://localhost:5099/hello");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Print Server, how might i be of service?");
        System.out.println("(1) Print a file");
        System.out.println("(2) Get printer status");
        System.out.println("(3) Stop printer");
        System.out.println("(4) Start printer");
        System.out.println("(5) Restart printer");
        System.out.println("(6) Get printer queue");
        System.out.println("(7) Move print to top of queue");
        System.out.println("(8) Move print to top of queue");

        int selection = scanner.nextInt();

        switch (selection){
            case 1: {
                System.out.println("Input filename:");
                String filename = scanner.nextLine();
                System.out.println("What printer would you like to use, Printer(1-3)?");
                int printer = scanner.nextInt();
                service.print(filename, "Printer" + printer);
            }
            case 2: {
                System.out.println("Current printer state is: " + service.getState());
            }
        }


        System.out.println(" Print Server is currently +" + service.getState();



        System.out.println("--- " + service.echo("test") + "--- " + service.getClass().getName());
    }
}
