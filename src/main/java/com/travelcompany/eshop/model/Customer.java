package com.travelcompany.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private int id;
    private String name;
    private String emailAddress;
    private String city;
    private String nationality;
    private Category category;

}
