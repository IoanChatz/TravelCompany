package com.travelcompany.eshop.service;

import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.repository.ItineraryRepositoryInterfaceImpl;

import java.util.List;
import java.util.Optional;

public class ItineraryServiceImpl implements ServiceInterface<Itinerary> {

    ItineraryRepositoryInterfaceImpl itineraryRepositoryInterface;

    @Override
    public List<Itinerary> getAll() {
        return null;
    }

    @Override
    public void add(Itinerary itinerary) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Itinerary itinerary) {

    }

    @Override
    public Optional<Itinerary> getById(int id) {
        return Optional.empty();
    }
}
