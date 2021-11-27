package com.travelcompany.eshop.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import static java.lang.System.exit;

public class DatabaseConnection implements DatabaseInterface {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);
    private static final Properties sqlCommands = new Properties();
    private static final String Jdbc_Driver = "com.mysql.cj.jdbc.Driver";

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


    public Connection getConnection(DatabaseParameters dbParameters) throws SQLException {
        Connection connection = null;
        Properties connectionProperties = new Properties();
        connectionProperties.put("user", dbParameters.getDB_USERNAME());
        connectionProperties.put("password", dbParameters.getDB_PASSWORD());

        if (("jdbc:mysql://").equals(dbParameters.getDB_CONNECTION_URL())) {
            try {
                loadDatabaseDriver();
                connection = DriverManager.getConnection(dbParameters.getDB_CONNECTION_URL()
                        + dbParameters.getHOST() + ":"
                        + dbParameters.getHOST_PORT()
                        + "/" + dbParameters.getDATABASE(), connectionProperties);
                logger.info("Successful MySQL Database Connection!");
            } catch (ClassNotFoundException e) {
                logger.info("No suitable driver was found! " + e);
            }
        } else {
            logger.info("Wrong MySQL Url Parameters");
        }
        return connection;
    }

    public boolean createCustomerTable(Connection connection, DatabaseParameters dbParameters) throws SQLException {
        connection = getConnection(dbParameters);
        try (Statement statement = connection.createStatement()) {
            loadSqlCommands();
            int result = statement.executeUpdate(sqlCommands.getProperty("create.table.customer"));
            logger.info("Created table command was successful with result {}.", result);
        } catch (SQLException ex) {
            logger.error("Error while creating table(s).", ex);
            exit(-1);
        }
        return true;
    }

    public boolean createItineraryTable(Connection connection, DatabaseParameters dbParameters) throws SQLException {
        connection = getConnection(dbParameters);
        try (Statement statement = connection.createStatement()) {
            loadSqlCommands();
            int result = statement.executeUpdate(sqlCommands.getProperty("create.table.itinerary"));
            logger.info("Created table command was successful with result {}.", result);
        } catch (SQLException ex) {
            logger.error("Error while creating table(s).", ex);
            exit(-1);
        }
        return true;
    }

    public boolean createTicketTable(Connection connection, DatabaseParameters dbParameters) throws SQLException {
        connection = getConnection(dbParameters);
        try (Statement statement = connection.createStatement()) {
            loadSqlCommands();
            int result = statement.executeUpdate(sqlCommands.getProperty("create.table.ticket"));
            logger.info("Created table command was successful with result {}.", result);
        } catch (SQLException ex) {
            logger.error("Error while creating table(s).", ex);
            exit(-1);
        }
        return true;
    }


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

    public Class<?> loadDatabaseDriver() throws ClassNotFoundException {
        logger.info("Driver loading...");
        return Class.forName(Jdbc_Driver);
    }

    public void insertDataIntoTableCustomer(Connection connection,DatabaseParameters dbParameters) throws SQLException {
        connection = getConnection(dbParameters);
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `travelcompany`.`customer`(`name`,`email`,`addresscity`,`nationality`,`category`) VALUES(?,?,?,?,?);");

            preparedStatement.setString(1, "John"); // (1, code)
        preparedStatement.setString(2, "blabla@gmail.com");  // (2, name)
        preparedStatement.setString(3, "BBBBBBB");
        preparedStatement.setString(4, "GRE");
        preparedStatement.setString(5,"ind");

        int rowsAffected = preparedStatement.executeUpdate();
        System.out.println("Successful:" + rowsAffected);
    }


}

