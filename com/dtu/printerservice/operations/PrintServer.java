package com.dtu.printerservice.operations;

public class PrintServer implements PrinterOperations {
    public PrintServer() {
    }

    @Override
    public void print(String filename, String printerName) {
        System.out.println("print called by user: XYZ");
    }

    @Override
    public void queue(String printerName) {
        System.out.println("queue called by user: XYZ");
    }

    @Override
    public void topQueue(String printerName, int job) {
        System.out.println("topQueue called by user: XYZ");
    }

    @Override
    public void start() {
        System.out.println("start called by user: XYZ");
    }

    @Override
    public void stop() {
        System.out.println("stop called by user: XYZ");
    }

    @Override
    public void restart() {
        System.out.println("restart called by user: XYZ");
    }

    @Override
    public void status(String printerName) {
        System.out.println("status called by user: XYZ");
    }

    @Override
    public void readConfig(String parameter) {
        System.out.println("readConfig called by user: XYZ");
    }

    @Override
    public void setConfig(String parameter, String value) {
        System.out.println("SetConfig called by user: XYZ");
    }
}
