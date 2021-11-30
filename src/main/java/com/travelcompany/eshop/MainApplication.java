package com.travelcompany.eshop;

import com.travelcompany.eshop.database.DatabaseParameters;
import com.travelcompany.eshop.database.DatabaseConnection;
import com.travelcompany.eshop.dto.CustomerDto;
import com.travelcompany.eshop.dto.ItineraryDto;
import com.travelcompany.eshop.dto.TicketDto;
import com.travelcompany.eshop.model.*;
import com.travelcompany.eshop.util.CsvReaderWriter;
import com.travelcompany.eshop.validator.CustomerValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.nio.file.Path;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;


public class MainApplication {
    private static final Logger logger = LoggerFactory.getLogger(MainApplication.class);
    private static final Properties sqlCommands = new Properties();

    public static void main(String[] args) throws SQLException {

        //TODO Design and implement database and tables.
        //TODO Restore Function (Reset)
        //TODO Logging Mechanism.
        //TODO Purchase Methods according to the criteria.
        //TODO Improved Design and implement of the service layer (CustomerService, TicketService)
        //TODO Reports: 1. List of the total number and cost of tickets for all customers. x
        //TODO          2. List of the total offered itineraries per destination and departure.
        //TODO          3. List of the customer(s) who purchased the most tickets and the number of purchases.
        //TODO          4. List of the customer(s) who have not purchased any tickets.
        //TODO Reports saved in a text file and an Excel document.
        //TODO Database Connection , Functionalities , Backup(Validation the Files don't exist)
        //TODO Improve Models

        CsvReaderWriter csvReaderWriter = new CsvReaderWriter();
        CustomerValidator validator = new CustomerValidator();
        Path path = null;
        String s = null;
        List<Customer> customers = new ArrayList<>(0);
        //Optional<List<String>> customerListOptional = csvReaderWriter.readCustomersFromFile(s,path);
        List<CustomerDto> list;

        list = csvReaderWriter.readCustomersFromFile(s, path);
        for (CustomerDto c : list) {
            Customer customer = new Customer();
            if (validator.isValidNumber(c.getId()))
                customer.setId(Integer.parseInt(c.getId()));
            if (validator.isValidName(c.getName()))
                customer.setName(c.getName());
            if (validator.isValidEmail(c.getEmailAddress()))
                customer.setEmailAddress(c.getEmailAddress());
            if (validator.isValidName(c.getCity()))
                customer.setCity(c.getCity());
            if (validator.isValidName(c.getNationality()))
                customer.setNationality(c.getNationality());
            if (validator.isInEnum(c.getCategory(), Category.class))
                customer.setCategory(Category.valueOf(c.getCategory()));
            customers.add(customer);
        }
        System.out.println(customers);

        List<ItineraryDto> itineraryList;
        List<Itinerary> itineraries = new ArrayList<>(0);
        itineraryList = csvReaderWriter.readItineraryFromFile(s, path);
        for (ItineraryDto itr : itineraryList) {
            Itinerary itinerary = new Itinerary();
            if (validator.isValidNumber(itr.getId()))
                itinerary.setId(Integer.parseInt(itr.getId()));
            if (validator.isValidIATACode(itr.getDepartureAirportId()))
                itinerary.setDepartureAirportId(itr.getDepartureAirportId());
            if (validator.isValidIATACode(itr.getDestinationAirportId()))
                itinerary.setDestinationAirportId(itr.getDestinationAirportId());
            if (validator.isValidDate(itr.getDepartureDate()))
                itinerary.setDepartureDate(LocalDateTime.parse(itr.getDepartureDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            if (validator.isValidName(itr.getAirline()))
                itinerary.setAirline(itr.getAirline());
            if (validator.isValidNumber(itr.getCost()))
                itinerary.setCost(Integer.parseInt(itr.getCost()));
            itineraries.add(itinerary);
        }
        System.out.println(itineraries);

        List<TicketDto> ticketDtoList;
        List<Ticket> tickets = new ArrayList<>(0);
        ticketDtoList = csvReaderWriter.readTicketFromFile(s, path);
        for (TicketDto t : ticketDtoList) {
            Ticket ticket = new Ticket();
            if (validator.isValidNumber(t.getId()))
               ticket.setId(Integer.parseInt(t.getId()));
            if (validator.isValidNumber(t.getCustomerId()))
                ticket.setCustomerId(Integer.parseInt(t.getCustomerId()));
            if (validator.isValidNumber(t.getItineraryId()))
                ticket.setItineraryId(Integer.parseInt(t.getItineraryId()));
            if (validator.isInEnum(t.getPaymentOption(), PaymentOption.class))
                ticket.setPaymentOption(PaymentOption.valueOf(t.getPaymentOption()));
            if (validator.isValidNumber(t.getCostAmount()))
                ticket.setCostAmount(Integer.parseInt(t.getCostAmount()));
            tickets.add(ticket);
        }
        System.out.println(ticketDtoList);
        System.out.println(tickets);


        // customerListOptional.stream().collect(Collectors.toList()).forEach( c -> logger.info("{}",c));
        // customerListOptional.stream().forEach(c -> logger.info("{}",c));
        //  customerListOptional.stream().forEach(c -> list.add(c));



         /*Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelcompany","root","root");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select * from customer;");
        while (resultSet.next()){
            System.out.println(resultSet.getString("name"));
        }*/
      /* DatabaseConnection db = new DatabaseConnection();
        DatabaseParameters dbParameters = new DatabaseParameters();

        try {
            Connection myConnection = db.getConnection(dbParameters);

        } catch (SQLException ex) {
            Logger.getLogger(MainApplication.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        DatabaseConnection myConnection = new DatabaseConnection();
        DatabaseParameters dbParameters = new DatabaseParameters();

        myConnection.createDatabase(dbParameters);
        Connection connection = myConnection.getConnection(dbParameters);
        myConnection.createCustomerTable(connection, dbParameters);
        myConnection.createItineraryTable(connection, dbParameters);
        myConnection.createTicketTable(connection, dbParameters);
        // myConnection.insertDataIntoTableCustomer(connection,dbParameters);
       // myConnection.insertDataIntoTableCustomerBatch(connection, dbParameters, customers);
       // myConnection.insertDataIntoTableItineraryBatch(connection, dbParameters, itineraries);
       // myConnection.insertDataIntoTableTicketBatch(connection, dbParameters, tickets);
        myConnection.readData(connection);
        myConnection.readMaxTicketsByCustomer(connection);
        myConnection.readZeroTicketsByCustomer(connection);
        myConnection.readOfferedItineraries(connection);
        List<Customer> test = myConnection.readAllCustomers(connection);
        test.stream().forEach(customer -> logger.info("{}",customer));
        List<Itinerary> test2 = myConnection.readAllItineraries(connection);
        test2.stream().forEach(itinerary -> logger.info("{}",itinerary));
        //csvReaderWriter.writeCustomersToFile(test,s);

          /*  try (Statement statement = myConnection.createStatement()) {
                loadSqlCommands();
                int result = statement.executeUpdate(sqlCommands.getProperty("create.table.payment"));
                logger.info("Created table command was successful with result {}.", result);
            } catch (SQLException ex) {
                logger.error("Error while creating table(s).", ex);
                exit(-1);
            }
        }*/

   /* public static void loadSqlCommands() {
        try (InputStream inputStream = DatabaseConnection.class.getClassLoader()
                .getResourceAsStream("sql.properties")) {
            if (inputStream == null) {
                logger.error("Sorry, unable to find sql.properties, exiting application.");
                exit(-1);
            }
            sqlCommands.load(inputStream);
        } catch (IOException ex) {
            logger.error("Sorry, unable to parse sql.properties, exiting application.", ex);
            exit(-1);
        }*/
        //BACKUP
        String command = "mysqldump --host=" + dbParameters.getHOST() + " --user=" + dbParameters.getDB_USERNAME() + " --password=" + dbParameters.getDB_PASSWORD() + " "
                + dbParameters.getDATABASE() + " > " + "C:\\Users\\chatziio\\CSVsFile\\" + "/ofm_mnu_backup_" + new Date() + ".sql";



        //RESTORE
        //String command = "mysqldump --host=" + dataBase.getHost() + " --user=" + dataBase.getUserName() + " --password=" + dataBase.getPassword() + " "
             //   + dataBase.getDatabaseName() + " < " + dataBase.getBackupPath() + "/ofm_mnu_backup_" + bkDate + ".sql";
    }
}

