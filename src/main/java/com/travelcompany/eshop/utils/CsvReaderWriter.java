package com.travelcompany.eshop.utils;

import com.travelcompany.eshop.models.Category;
import com.travelcompany.eshop.models.Customer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CsvReaderWriter implements RWStrategyPatternTest{

    Set<Customer> customerSet = new HashSet<>();

    @Override
    public void  readCsvFiles() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\CSVsFile\\customers.csv"));
        String row;
        String[] data;
        long id ;
        Category category;
        while ((row = bufferedReader.readLine()) != null) {
            data = row.split(",");
            String test = data[0];
            System.out.println(test);
            //id = Long.parseLong(test);

            //customerSet.add(new Customer(id,data[1],data[2],data[3],data[4],isInEnum(data[5],Category.class)));
            for (String customer: data) {
                System.out.println(customer);
            }

        }
        bufferedReader.close();
    }

    public <E extends Enum<E>> Category isInEnum(String value, Class<E> enumClass) {
        for (E e : enumClass.getEnumConstants()) {
            if ((Category.BUSINESS).equals(value)) {
                return Category.BUSINESS;
            }else if((Category.INDIVIDUAL).equals(value)){
                return Category.INDIVIDUAL;
            }
        }
        return null;
    }

}
