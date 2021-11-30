package com.travelcompany.eshop.dto;

import com.travelcompany.eshop.model.Category;
import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.util.CsvReaderWriter;
import com.travelcompany.eshop.validator.Validator;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Data
public class CustomerDto {

    private String id;
    private String name;
    private String emailAddress;
    private String city;
    private String nationality;
    private String category;

    public CustomerDto(String id, String name, String emailAddress, String city, String nationality, String category) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.city = city;
        this.nationality = nationality;
        this.category = category;
    }


    public List<Customer> createCustomerBatchList(List<CustomerDto> customerDtoList, Validator validator) {
        List<Customer> customersList = new ArrayList<>(0);
        for (CustomerDto c : customerDtoList) {
            Customer customer = new Customer();
            if (validator.isValidNumber(c.getId()))
                customer.setId(Integer.parseInt(c.getId()));
            if (validator.isValidName(c.getName()))
                customer.setName(c.getName());
            if (validator.isValidEmail(c.getEmailAddress()))
                customer.setEmailAddress(c.getEmailAddress());
            if (validator.isValidName(c.getCity()))
                customer.setCity(c.getCity());
            if (validator.isValidName(c.getNationality()))
                customer.setNationality(c.getNationality());
            if (validator.isInEnum(c.getCategory(), Category.class))
                customer.setCategory(Category.valueOf(c.getCategory()));
            customersList.add(customer);
        }
        return customersList;
    }


}
