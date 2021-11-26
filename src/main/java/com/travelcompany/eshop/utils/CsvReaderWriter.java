package com.travelcompany.eshop.utils;

import com.travelcompany.eshop.models.Category;
import com.travelcompany.eshop.models.Customer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CsvReaderWriter implements RWStrategyPatternTest{

    Set<Customer> customerSet = new HashSet<>();

    @Override// "C:\\Users\\CSVsFile\\customers.csv"
    public void  readCsvFiles() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Directory.FILE_DIRECTORY.getPath() + "customers.csv"));
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

////////////////////////////////////////for TESTING


   /* public void writeCustomersToFile(List<Customer> customers, String filename) {
        Path path = Paths.get(Directory.FILE_DIRECTORY.getPath() + filename);
        try(Writer writer = Files.newBufferedWriter(path);
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)){
            customers.forEach(customer -> {
                try {
                    csvPrinter.printRecord(customer.getName(), customer.getGender(), customer.getYearOfBirth(), customer.getAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public Optional<List<Customer>> readCustomersFromFile(String filename) {
        Path path = Paths.get(Directory.FILE_DIRECTORY.getPath() + filename);
        try(Reader reader = Files.newBufferedReader(path);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)){
            ArrayList<Customer> customers = new ArrayList<>();
            List<CSVRecord> recordList = csvParser.getRecords();
            recordList.forEach(record -> {
                Customer customer = new Customer();
                customer.setName(record.get(0));
                customer.setGender(record.get(1));
                customer.setYearOfBirth(Integer.parseInt(record.get(2)));
                customer.setAddress(record.get(3));
                customers.add(customer);
            });
            return Optional.of(customers);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return Optional.empty();
    }*/

}
