package com.travelcompany.eshop;

import com.travelcompany.eshop.database.DatabaseParameters;
import com.travelcompany.eshop.database.DatabaseConnection;
import com.travelcompany.eshop.utils.CsvReaderWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;

import static java.lang.System.exit;

public class MainApplication {
    private static final Logger logger = LoggerFactory.getLogger(MainApplication.class);
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
        DatabaseConnection connection = new DatabaseConnection();
        DatabaseParameters dbParameters = new DatabaseParameters();
        Connection myConnection = connection.getConnection(dbParameters);
         final Properties sqlCommands = new Properties();
            try (Statement statement = myConnection.createStatement()) {
                int result = statement.executeUpdate("CREATE TABLE if not exists `travelcompany`.`payment` (`paymentid` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(60) NOT NULL,PRIMARY KEY (`paymentid`));");
                logger.info("Created table command was successful with result {}.", result);
            } catch (SQLException ex) {
                logger.error("Error while creating table(s).", ex);
                exit(-1);
            }
        }
    }

