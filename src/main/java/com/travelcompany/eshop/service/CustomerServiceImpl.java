package com.travelcompany.eshop.service;

import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.repository.CustomerRepositoryInterfaceImpl;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerServiceInterface {

   CustomerRepositoryInterfaceImpl customerRepositoryInterface;

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public void save(Customer customer) {

    }

    @Override
    public Optional<Customer> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void delete(int id) {

    }
}
