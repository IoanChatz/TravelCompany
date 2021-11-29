package com.travelcompany.eshop.service;

import com.travelcompany.eshop.model.Customer;


import java.util.List;
import java.util.Optional;

public interface CustomerServiceInterface {



    List< Customer > findAll();
    void save(Customer customer);
    Optional< Customer > findById(int id);
    void delete(int id);
}
