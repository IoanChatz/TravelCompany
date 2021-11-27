package com.travelcompany.eshop;

import com.travelcompany.eshop.database.DatabaseParameters;
import com.travelcompany.eshop.database.DatabaseConnection;
import com.travelcompany.eshop.utils.CsvReaderWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;

import static java.lang.System.exit;


public class MainApplication {
    private static final Logger logger = LoggerFactory.getLogger(MainApplication.class);
    private static final Properties sqlCommands = new Properties();
    public static void main(String[] args) throws SQLException {

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

        Connection connection = null; ;
        myConnection.createDatabase(dbParameters);
        myConnection.createCustomerTable(connection,dbParameters);
        myConnection.createItineraryTable(connection,dbParameters);
        myConnection.createTicketTable(connection,dbParameters);
        myConnection.insertDataIntoTableCustomer(connection,dbParameters);

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
    }
    }

