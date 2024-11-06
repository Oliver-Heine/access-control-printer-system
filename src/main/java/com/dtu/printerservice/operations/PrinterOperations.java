package com.dtu.printerservice.operations;

import com.dtu.printerservice.users.User;

public interface PrinterOperations {
    /**
     * prints file filename on the specified printer
     * @param filename of the file to print
     * @param printer specific printer to print on
     */
    void print(String filename, String printer, User user, String token);

    /**
     * lists the print queue for a given printer on the user’s display
     * in lines of the form ¡job number¿ ¡file name¿
     * @param printer specific printer to get the queue from
     */
    void queue(String printer, User user, String token);

    /**
     * moves job to the top of the queue
     * @param printer specific printer to alter the queue on
     * @param job the job to put to the top of the queue
     */
    void topQueue(String printer, int job, User user, String token);

    /**
     *  starts the print server
     */
    void start(User user, String token);

    /**
     * stops the print server
     */
    void stop(User user, String token);

    /**
     * stops the print server, clears the print queue and starts the print server
     * again
     */
    void restart(User user, String token);

    /**
     * prints status of printer on the user’s display
     * @param printer specific printer to fetch the status from
     */
    void status(String printer, User user, String token);

    /**
     * prints the value of the parameter on the print server
     * to the user’s display
     * @param parameter the parameter to fetch the value from on the print server
     */
    void readConfig(String parameter, User user, String token);

    /**
     * sets the parameter on the print server to value
     * @param parameter the name of the new parameter
     * @param value the value of the parameter
     */
    void setConfig(String parameter, String value, User user, String token);
}
