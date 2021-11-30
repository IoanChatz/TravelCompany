package com.travelcompany.eshop.service;

import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.repository.CustomerRepositoryInterfaceImpl;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements ServiceInterface<Customer> {

    CustomerRepositoryInterfaceImpl customerRepositoryInterface;

    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public void add(Customer customer) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public Optional<Customer> getById(int id) {
        return Optional.empty();
    }
}
