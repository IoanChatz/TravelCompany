package com.travelcompany.eshop.repository;


import com.travelcompany.eshop.model.Itinerary;

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
}
