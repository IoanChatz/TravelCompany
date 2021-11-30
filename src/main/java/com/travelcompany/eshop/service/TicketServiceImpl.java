package com.travelcompany.eshop.service;

import com.travelcompany.eshop.model.Ticket;
import com.travelcompany.eshop.repository.TicketRepositoryInterfaceImpl;

import java.util.List;
import java.util.Optional;

public class TicketServiceImpl implements ServiceInterface<Ticket> {

    TicketRepositoryInterfaceImpl ticketRepositoryInterface;

    @Override
    public List<Ticket> getAll() {
        return null;
    }

    @Override
    public void add(Ticket ticket) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Ticket ticket) {

    }

    @Override
    public Optional<Ticket> getById(int id) {
        return Optional.empty();
    }
}
