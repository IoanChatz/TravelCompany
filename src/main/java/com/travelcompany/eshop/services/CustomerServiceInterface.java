package com.travelcompany.eshop.services;

import com.travelcompany.eshop.models.Customer;


import java.util.List;
import java.util.Optional;

public interface CustomerServiceInterface {



    Customer saveCustomer(Customer customer);
    Customer removeCustomer(Customer customer);

    List< Customer > findAll();
    void save(Customer customer);
    Optional< Customer > findById(int id);
    void delete(int id);
}
