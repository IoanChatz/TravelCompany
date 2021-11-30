package com.travelcompany.eshop.database;

import com.travelcompany.eshop.model.*;
import com.travelcompany.eshop.util.ExcelWriter;
import com.travelcompany.eshop.util.TxtWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.lang.System.exit;

public class DatabaseConnection implements DatabaseInterface     {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);
    private static final Properties sqlCommands = new Properties();
    private static final String Jdbc_Driver = "com.mysql.cj.jdbc.Driver";

    @Override
    public void createDatabase(DatabaseParameters dbParameters) {
        try (Connection connection = DriverManager.getConnection(dbParameters.getDB_CONNECTION_URL() + dbParameters.getHOST(),
                dbParameters.getDB_USERNAME(), dbParameters.getDB_PASSWORD());
             Statement statement = connection.createStatement();
        ) {
            loadSqlCommands();
            int result = statement.executeUpdate(sqlCommands.getProperty("create.database"));
            logger.info("Database created successfully...", result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection(DatabaseParameters dbParameters) throws SQLException {
        Connection connection = null;
        try {
            loadDatabaseDriver();
            connection = DriverManager.getConnection(dbParameters.getDB_CONNECTION_URL()
                    + dbParameters.getHOST() + ":"
                    + dbParameters.getHOST_PORT()
                    + "/" + dbParameters.getDATABASE(), dbParameters.getDB_USERNAME(),dbParameters.getDB_PASSWORD());
            logger.info("Successful MySQL Database Connection!");
        } catch (ClassNotFoundException exception) {
            logger.info("No suitable driver was found! " + exception);
        }
        return connection;
    }

    @Override
    public boolean createCustomerTable(Connection connection, DatabaseParameters dbParameters) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            loadSqlCommands();
            int result = statement.executeUpdate(sqlCommands.getProperty("create.table.customer"));
            logger.info("Created table command was successful with result {}.", result);
        } catch (SQLException exception) {
            logger.error("Error while creating table(s).", exception);
            exit(-1);
        }
        return true;
    }

    @Override
    public boolean createItineraryTable(Connection connection, DatabaseParameters dbParameters) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            loadSqlCommands();
            int result = statement.executeUpdate(sqlCommands.getProperty("create.table.itinerary"));
            logger.info("Created table command was successful with result {}.", result);
        } catch (SQLException exception) {
            logger.error("Error while creating table(s).", exception);
            exit(-1);
        }
        return true;
    }

    @Override
    public boolean createTicketTable(Connection connection, DatabaseParameters dbParameters) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            loadSqlCommands();
            int result = statement.executeUpdate(sqlCommands.getProperty("create.table.ticket"));
            logger.info("Created table command was successful with result {}.", result);
        } catch (SQLException exception) {
            logger.error("Error while creating table(s).", exception);
            exit(-1);
        }
        return true;
    }


    @Override
    public void loadSqlCommands() {
        try (InputStream inputStream = DatabaseConnection.class.getClassLoader()
                .getResourceAsStream("sql.properties")) {
            if (inputStream == null) {
                logger.error("Unable to find sql.properties.");
                exit(-1);
            }
            sqlCommands.load(inputStream);
        } catch (IOException exception) {
            logger.error("Unable to parse sql.properties.", exception);
            exit(-1);
        }
    }

    @Override
    public Class<?> loadDatabaseDriver() throws ClassNotFoundException {
        logger.info("Driver loading...");
        return Class.forName(Jdbc_Driver);
    }

    @Override
    public void insertDataIntoTableCustomer(Connection connection, DatabaseParameters dbParameters) throws SQLException {

    }

    @Override
    public void insertDataIntoTableItinerary(Connection connection, DatabaseParameters dbParameters) throws SQLException {

    }

    @Override
    public void insertDataIntoTableTicket(Connection connection, DatabaseParameters dbParameters) throws SQLException {

    }


    @Override
    public void insertDataIntoTableCustomerBatch(Connection connection, DatabaseParameters dbParameters, List<Customer> customers) throws SQLException {
        loadSqlCommands();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlCommands.getProperty("insert.table.customer"));
        for (Customer c : customers) {
            preparedStatement.setLong(1, c.getId());
            preparedStatement.setString(2, c.getName());
            preparedStatement.setString(3, c.getEmailAddress());
            preparedStatement.setString(4, c.getCity());
            preparedStatement.setString(5, c.getNationality());
            preparedStatement.setString(6, c.getCategory().toString());
            preparedStatement.addBatch();
            int[] rowsUpdated = preparedStatement.executeBatch();
            logger.info("Successful insert into Customer table", rowsUpdated);
        }

    }

    @Override
    public void insertDataIntoTableItineraryBatch(Connection connection, DatabaseParameters dbParameters, List<Itinerary> itineraries) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sqlCommands.getProperty("insert.table.itinerary"));
        loadSqlCommands();
        for (Itinerary itr : itineraries) {
            preparedStatement.setInt(1, itr.getId());
            preparedStatement.setString(2, itr.getDepartureAirportId());
            preparedStatement.setString(3, itr.getDestinationAirportId());
            preparedStatement.setObject(4, itr.getDepartureDate());
            preparedStatement.setString(5, itr.getAirline());
            preparedStatement.setInt(6, itr.getCost());
            preparedStatement.addBatch();
            int[] rowsUpdated = preparedStatement.executeBatch();
            logger.info("Successful insert into Itinerary table ", rowsUpdated);
        }
    }

    @Override
    public void insertDataIntoTableTicketBatch(Connection connection, DatabaseParameters dbParameters, List<Ticket> tickets) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sqlCommands.getProperty("insert.table.ticket"));
        loadSqlCommands();
        for (Ticket t : tickets) {
            preparedStatement.setInt(1, t.getId());
            preparedStatement.setInt(2, t.getCustomerId());
            preparedStatement.setInt(3, t.getItineraryId());
            preparedStatement.setString(4, t.getPaymentOption().toString());
            preparedStatement.setInt(5, t.getCostAmount());

            preparedStatement.addBatch();
            int[] rowsUpdated = preparedStatement.executeBatch();
            logger.info("Successful insert into Ticket table ", rowsUpdated);
        }
    }


    public void readTotalNumberAndCost(Connection connection) throws SQLException {
        String fileNameExcel = "readTotalNumberAndCost.xml";
        String fileNameText = "readTotalNumberAndCost.txt";
        ExcelWriter excelWriter = new ExcelWriter();
        TxtWriter txtWriter = new TxtWriter();
        List<String> strings = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlCommands.getProperty("select.cost.amount.ticket"));
        while (resultSet.next()) {
            logger.info("Full Name: {}", resultSet.getString("Full_Name"));
            logger.info("Total Purchases: {}", resultSet.getString("Total_Purchases"));
            logger.info("Total Cost: {}", resultSet.getString("Total_Cost"));
            logger.info("----------------------------");
            strings.add(resultSet.getString("Full_Name"));
            strings.add(resultSet.getString("Total_Purchases"));
            strings.add(resultSet.getString("Total_Cost"));
        }
        excelWriter.writeCustomersToFile(strings, fileNameExcel);
        txtWriter.writeTxtFile(strings, fileNameText);
    }


    public void readMaxTicketsByCustomer(Connection connection) throws SQLException {
        String fileNameExcel = "readMaxTicketsByCustomer.xml";
        String fileNameText = "readMaxTicketsByCustomer.txt";
        ExcelWriter excelWriter = new ExcelWriter();
        TxtWriter txtWriter = new TxtWriter();
        List<String> strings = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlCommands.getProperty("select.customer.max.tickets"));
        while (resultSet.next()) {
            logger.info("Full Name: {}", resultSet.getString("Full_Name"));
            logger.info("Total Purchases: {}", resultSet.getString("Max_Purchases"));
            logger.info("----------------------------");
            strings.add(resultSet.getString("Full_Name"));
            strings.add(resultSet.getString("Max_Purchases"));
        }
        excelWriter.writeCustomersToFile(strings, fileNameExcel);
        txtWriter.writeTxtFile(strings, fileNameText);
    }


    public void readZeroTicketsByCustomer(Connection connection) throws SQLException {
        String fileNameExcel = "readZeroTicketsByCustomer.xml";
        String fileNameText = "readZeroTicketsByCustomer.txt";
        ExcelWriter excelWriter = new ExcelWriter();
        TxtWriter txtWriter = new TxtWriter();
        List<String> strings = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlCommands.getProperty("select.zero.tickets"));
        while (resultSet.next()) {
            logger.info("Full Name: {}", resultSet.getString("Full_Name"));
            logger.info("----------------------------");
            strings.add(resultSet.getString("Full_Name"));
        }
        excelWriter.writeCustomersToFile(strings, fileNameExcel);
        txtWriter.writeTxtFile(strings, fileNameText);
    }

    public void readOfferedItineraries(Connection connection) throws SQLException {
        String fileNameExcel = "readOfferedItineraries.xml";
        String fileNameText = "readOfferedItineraries.txt";
        ExcelWriter excelWriter = new ExcelWriter();
        TxtWriter txtWriter = new TxtWriter();
        List<String> strings = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlCommands.getProperty("select.itineraries.offered"));
        while (resultSet.next()) {
            logger.info("Departure Airport: {}", resultSet.getString("Departure_Airport"));
            logger.info("Destination Airport: {}", resultSet.getString("Destination_Airport"));
            logger.info("Total_Itineraries: {}", resultSet.getString("TOTAL_ITINERARIES"));
            logger.info("----------------------------");
            strings.add(resultSet.getString("Departure_Airport"));
            strings.add(resultSet.getString("Destination_Airport"));
            strings.add(resultSet.getString("TOTAL_ITINERARIES"));
        }
        excelWriter.writeCustomersToFile(strings, fileNameExcel);
        txtWriter.writeTxtFile(strings, fileNameText);
    }

    public List<Customer> readAllCustomers(Connection connection) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlCommands.getProperty("select.table.customer"));
        while (resultSet.next()) {
            customers.add(new Customer(resultSet.getInt("customerid"),
                    resultSet.getString("name"), resultSet.getString("email"),
                    resultSet.getString("addresscity"), resultSet.getString("nationality"),
                    Category.valueOf(resultSet.getString("category"))));
        }
        return customers;
    }

    public List<Itinerary> readAllItineraries(Connection connection) throws SQLException {
        List<Itinerary> itineraries = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlCommands.getProperty("select.table.itinerary"));
        while (resultSet.next()) {
            itineraries.add(new Itinerary(resultSet.getInt("itineraryid"), resultSet.getString("departureairportid"),
                    resultSet.getString("destinationairportid"),
                    LocalDateTime.parse(resultSet.getString("departuredate"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    resultSet.getString("airline"), resultSet.getInt("cost")));
        }
        return itineraries;
    }

    public List<Ticket> readAllITickets(Connection connection) throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlCommands.getProperty("select.table.ticket"));
        while (resultSet.next()) {
            tickets.add(new Ticket(resultSet.getInt("ticketid"), resultSet.getInt("customerid"),
                    resultSet.getInt("itineraryid"),
                    PaymentOption.valueOf(resultSet.getString("paymentmethod")), resultSet.getInt("amount")));
        }
        return tickets;
    }


    public void restoreDatabase(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            loadSqlCommands();
            int result = statement.executeUpdate(sqlCommands.getProperty("drop.database"));
            logger.info("Database restored successfully...", result);
        } catch (SQLException e) {
            logger.info("{}", e);
        }
    }


}

