package com.travelcompany.eshop.repositories;

import com.travelcompany.eshop.models.Customer;

import java.util.List;
import java.util.Optional;

public class CustomerRepositoryInterfaceImpl implements RepositoryInterface<Customer> {


    @Override
    public Customer create(Customer customer) {
        return null;
    }

    @Override
    public Optional<Customer> read(int customerId) {

        Customer customer = null ;//= getCustomer(customerId);

        return  (customer == null)? Optional.empty(): Optional.of(customer);
    }

    @Override
    public List<Customer> read() {

        return null;
    }

    @Override
    public Customer update(int customerId, Customer customer) {

        return null;
    }

    @Override
    public boolean delete(int id) {

        return false;
    }
}
