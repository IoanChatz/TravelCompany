package com.travelcompany.eshop.repository;


import com.travelcompany.eshop.database.DatabaseParameters;
import com.travelcompany.eshop.model.Itinerary;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ItineraryRepositoryInterfaceImpl implements RepositoryInterface<Itinerary> {
    @Override
    public Itinerary create(Itinerary itinerary) {
        return null;
    }

    @Override
    public Optional<Itinerary> read(int id) {
        return Optional.empty();
    }

    @Override
    public List<Itinerary> read() {
        return null;
    }

    @Override
    public Itinerary update(int id, Itinerary itinerary) {
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
    public void insertIntoTableBatch(Connection connection, DatabaseParameters dbParameters, List<Itinerary> list) throws SQLException {

    }
}
