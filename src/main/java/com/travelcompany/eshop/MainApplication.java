package com.travelcompany.eshop;

import com.travelcompany.eshop.utils.CsvReaderWriter;

import java.io.IOException;

public class MainApplication {
    public static void main(String[] args) {

        //TODO Design and implement database and tables.
        //TODO Restore Function (Reset)
        //TODO Logging Mechanism.
        //TODO Purchase Methods according to the criteria.
        //TODO Improved Design and implement of the service layer (CustomerService, TicketService)
        //TODO Reports: 1. List of the total number and cost of tickets for all customers.
        //TODO          2. List of the total offered itineraries per destination and departure.
        //TODO          3. List of the customer(s) who purchased the most tickets and the number of purchases.
        //TODO          4. List of the customer(s) who have not purchased any tickets.
        //TODO Reports saved in a text file and an Excel document.
        //TODO Database Connection , Functionalities , Backup(Validation the Files don't exist)
        //TODO Improve Models

        CsvReaderWriter csvReaderWriter =  new CsvReaderWriter();
        try {
            csvReaderWriter.readCsvFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
