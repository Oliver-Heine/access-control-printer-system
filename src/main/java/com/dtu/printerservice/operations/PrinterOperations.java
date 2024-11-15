package com.dtu.printerservice.operations;

public interface PrinterOperations {
    /**
     * prints file filename on the specified printer
     * @param filename of the file to print
     * @param printer specific printer to print on
     */
    void print(String filename, String printer, String token);

    /**
     * lists the print queue for a given printer on the user’s display
     * in lines of the form ¡job number¿ ¡file name¿
     * @param printer specific printer to get the queue from
     */
    void queue(String printer, String token);

    /**
     * moves job to the top of the queue
     * @param printer specific printer to alter the queue on
     * @param job the job to put to the top of the queue
     */
    void topQueue(String printer, int job, String token);

    /**
     *  starts the print server
     */
    void start(String token);

    /**
     * stops the print server
     */
    void stop(String token);

    /**
     * stops the print server, clears the print queue and starts the print server
     * again
     */
    void restart(String token);

    /**
     * prints status of printer on the user’s display
     * @param printer specific printer to fetch the status from
     */
    void status(String printer, String token);

    /**
     * prints the value of the parameter on the print server
     * to the user’s display
     */
    void readConfig(String token);

    /**
     * sets the parameter on the print server to value
     */
    void setConfig(String token);
}
