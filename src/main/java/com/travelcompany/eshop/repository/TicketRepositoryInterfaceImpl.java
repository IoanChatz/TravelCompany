package com.travelcompany.eshop.repository;

import com.travelcompany.eshop.database.DatabaseParameters;
import com.travelcompany.eshop.model.Ticket;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TicketRepositoryInterfaceImpl implements RepositoryInterface<Ticket>{

    @Override
    public Ticket create(Ticket ticket) {
        return null;
    }

    @Override
    public Optional<Ticket> read(int id) {
        return Optional.empty();
    }

    @Override
    public List<Ticket> read() {
        return null;
    }

    @Override
    public Ticket update(int id, Ticket ticket) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public void insertDataIntoTable(Connection connection, DatabaseParameters dbParameters) throws SQLException {

    }

    @Override
    public void insertIntoTableBatch(Connection connection, DatabaseParameters dbParameters, List<Ticket> list) throws SQLException {

    }
}
