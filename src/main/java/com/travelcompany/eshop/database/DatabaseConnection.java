package com.travelcompany.eshop.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection implements DatabaseInterface {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);

    public Connection getConnection(DatabaseParameters dbParameters) throws SQLException {
        Connection connection = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", dbParameters.getDB_USERNAME());
        connectionProps.put("password", dbParameters.getDB_PASSWORD());

        if (("jdbc:mysql://").equals(dbParameters.getDB_CONNECTION_URL())) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); //Register The Driver
                connection = DriverManager.getConnection(dbParameters.getDB_CONNECTION_URL()
                        + dbParameters.getHOST() + ":"
                        + dbParameters.getHOST_PORT()
                        + "/" + dbParameters.getDATABASE(), connectionProps);
                logger.info("Successful MySQL Database Connection!");
            } catch (ClassNotFoundException e) {
                logger.info("No suitable driver was found! " + e);
            }

        } else {
            logger.info("Wrong MySQL Url Parameters");
        }
        return connection;
    }
}

