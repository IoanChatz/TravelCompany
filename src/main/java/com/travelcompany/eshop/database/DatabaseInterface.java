package com.travelcompany.eshop.database;

import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.model.Ticket;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public interface DatabaseInterface {
    Connection getConnection(DatabaseParameters dbParameters) throws SQLException;

    void createDatabase(DatabaseParameters dbParameters);

    boolean createCustomerTable(Connection connection, DatabaseParameters dbParameters) throws SQLException;

    boolean createItineraryTable(Connection connection, DatabaseParameters dbParameters) throws SQLException;

    boolean createTicketTable(Connection connection, DatabaseParameters dbParameters) throws SQLException;

    void loadSqlCommands();

    Class<?> loadDatabaseDriver() throws ClassNotFoundException;

    void insertDataIntoTableCustomer(Connection connection, DatabaseParameters dbParameters) throws SQLException;

    void insertDataIntoTableItinerary(Connection connection, DatabaseParameters dbParameters) throws SQLException;

    void insertDataIntoTableTicket(Connection connection, DatabaseParameters dbParameters) throws SQLException;

    public void insertDataIntoTableCustomerBatch(Connection connection, DatabaseParameters dbParameters, List<Customer> customers) throws SQLException;

    void insertDataIntoTableItineraryBatch(Connection connection, DatabaseParameters dbParameters, List<Itinerary> itineraries) throws SQLException;

    void insertDataIntoTableTicketBatch(Connection connection, DatabaseParameters dbParameters, List<Ticket> tickets) throws SQLException;
}
