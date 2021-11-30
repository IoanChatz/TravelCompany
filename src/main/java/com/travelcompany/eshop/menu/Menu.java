package com.travelcompany.eshop.menu;


import com.travelcompany.eshop.database.DatabaseConnection;
import com.travelcompany.eshop.database.DatabaseParameters;
import com.travelcompany.eshop.dto.CustomerDto;
import com.travelcompany.eshop.dto.ItineraryDto;
import com.travelcompany.eshop.dto.TicketDto;
import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.model.Ticket;
import com.travelcompany.eshop.util.CsvReaderWriter;
import com.travelcompany.eshop.validator.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Logger logger = LoggerFactory.getLogger(Menu.class);

    boolean exit = false;
    CsvReaderWriter csvReaderWriter = new CsvReaderWriter();
    Validator validator = new Validator();
    DatabaseConnection myConnection = new DatabaseConnection();
    DatabaseParameters dbParameters = new DatabaseParameters();
    CustomerDto customerDto = new CustomerDto();
    ItineraryDto itineraryDto = new ItineraryDto();
    TicketDto ticketDto = new TicketDto();
    List<CustomerDto> customerDtoList = new ArrayList<>();
    List<ItineraryDto> itineraryDtoList = new ArrayList<>();
    List<TicketDto> ticketDtoList = new ArrayList<>();
    List<Customer> customersList = new ArrayList<>();
    List<Itinerary> itineraryList = new ArrayList<>();
    List<Ticket> ticketList;
    Path path = null;
    String customerFileName = "customers.csv";
    String itineraryFileName = "itineraries.csv";
    String ticketFileName = "orderedTickets.csv";

    public void printMainMenu() {
        logger.info("---------- TRAVEL COMPANY MENU ----------");
        logger.info("\nPlease make a Selection:");
        logger.info("1.Read Files and create Database.");
        logger.info("2.Print List of the total number and cost of tickets for all customers");
        logger.info("3.Print List of the total offered itineraries per destination and departure");
        logger.info("4.Print List of the customer(s) who purchased the most tickets and the number of purchases");
        logger.info("5.Print List of the customer(s) who have not purchased any tickets");
        logger.info("6.Create Database Backup Files.");
        logger.info("0. For EXIT.");
    }

    private int getInput() {
        int selection = -1;
        Scanner sc = new Scanner(System.in);
        while (selection < 0 || selection > 6)
            try {
                System.out.print("\nInput your choice: ");
                selection = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Selection. Try again.");
            }
        return selection;
    }

    public void runMenu() throws Exception {
        myConnection.createDatabase(dbParameters);
        Connection connection = myConnection.getConnection(dbParameters);
        while (!exit) {
            printMainMenu();
            int sel = getInput();
            switch (sel) {
                case 0:
                    myConnection.restoreDatabase(connection);
                    exit = true;
                    break;
                case 1:
                    customerDtoList = csvReaderWriter.readCustomersFromFile(customerFileName, path);
                    itineraryDtoList = csvReaderWriter.readItineraryFromFile(itineraryFileName, path);
                    ticketDtoList = csvReaderWriter.readTicketFromFile(ticketFileName, path);
                    customersList = customerDto.createCustomerBatchList(customerDtoList, validator);
                    itineraryList = itineraryDto.createItineraryBatchList(itineraryDtoList, validator);
                    ticketList = ticketDto.createTicketBatchList(ticketDtoList, validator);
                    myConnection.createCustomerTable(connection, dbParameters);
                    myConnection.createItineraryTable(connection, dbParameters);
                    myConnection.createTicketTable(connection, dbParameters);
                    myConnection.insertDataIntoTableCustomer(connection, dbParameters);
                    myConnection.insertDataIntoTableCustomerBatch(connection, dbParameters, customersList);
                    myConnection.insertDataIntoTableItineraryBatch(connection, dbParameters, itineraryList);
                    myConnection.insertDataIntoTableTicketBatch(connection, dbParameters, ticketList);
                    break;
                case 2:
                    myConnection.readTotalNumberAndCost(connection);
                    break;
                case 3:
                    myConnection.readOfferedItineraries(connection);
                    break;
                case 4:
                    myConnection.readMaxTicketsByCustomer(connection);
                    break;
                case 5:
                    myConnection.readZeroTicketsByCustomer(connection);
                    break;
                case 6:
                    csvReaderWriter.writeCustomersToFile(myConnection.readAllCustomers(connection));
                    csvReaderWriter.writeItinerariesToFile(myConnection.readAllItineraries(connection));
                    csvReaderWriter.writeTicketsToFile(myConnection.readAllITickets(connection));
                    break;
                default:
                    break;
            }

        }
    }

}
