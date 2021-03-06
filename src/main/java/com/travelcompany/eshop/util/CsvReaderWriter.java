package com.travelcompany.eshop.util;

import com.travelcompany.eshop.dto.CustomerDto;

import com.travelcompany.eshop.dto.ItineraryDto;
import com.travelcompany.eshop.dto.TicketDto;
import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.Directory;
import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.model.Ticket;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CsvReaderWriter {


    public void writeCustomersToFile(List<Customer> customers) {
        Path path = Paths.get(Directory.BACKUP_DIRECTORY.getPath() + "customersBackup.csv");
        try (Writer writer = Files.newBufferedWriter(path);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            customers.forEach(customer -> {
                try {
                    csvPrinter.printRecord(customer.getId(), customer.getName(), customer.getEmailAddress(),
                            customer.getCity(), customer.getNationality(), customer.getCategory().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void writeItinerariesToFile(List<Itinerary> itineraries) {
        Path path = Paths.get(Directory.BACKUP_DIRECTORY.getPath() + "itinerariesBackup.csv");
        try (Writer writer = Files.newBufferedWriter(path);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            itineraries.forEach(itinerary -> {
                try {
                    csvPrinter.printRecord(itinerary.getId(), itinerary.getDepartureAirportId(),
                            itinerary.getDepartureAirportId(),
                            itinerary.getAirline(), itinerary.getCost());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void writeTicketsToFile(List<Ticket> tickets) {
        Path path = Paths.get(Directory.BACKUP_DIRECTORY.getPath() + "ticketsBackup.csv");
        try (Writer writer = Files.newBufferedWriter(path);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            tickets.forEach(ticket -> {
                try {
                    csvPrinter.printRecord(ticket.getId(), ticket.getCustomerId(), ticket.getItineraryId(),
                            ticket.getPaymentOption().toString(), ticket.getCostAmount());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    //@Override
    public List<CustomerDto> readCustomersFromFile(String filename, Path path) {

        path = Paths.get(Directory.FILE_DIRECTORY.getPath() + filename);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Directory.FILE_DIRECTORY.getPath() + filename));
             CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT)) {
            ArrayList<CustomerDto> customersDtoList = new ArrayList<>();
            List<String> customerDtoList = new ArrayList<>();
            List<CSVRecord> recordList = csvParser.getRecords();
            for (CSVRecord record : recordList) {
                customerDtoList.add(record.get(0));
                customerDtoList.add(record.get(1));
                customerDtoList.add(record.get(2));
                customerDtoList.add(record.get(3));
                customerDtoList.add(record.get(4));
                customerDtoList.add(record.get(5));

                customersDtoList.add(new CustomerDto(record.get(0), record.get(1), record.get(2), record.get(3), record.get(4), record.get(5).toUpperCase()));
            }

            return customersDtoList;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }

    public List<ItineraryDto> readItineraryFromFile(String filename, Path path) {
        path = Paths.get(Directory.FILE_DIRECTORY.getPath() + filename);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Directory.FILE_DIRECTORY.getPath() + filename));
             CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT)) {
            List<ItineraryDto> itineraryDtoList = new ArrayList<>();
            List<CSVRecord> recordList = csvParser.getRecords();
            for (CSVRecord record : recordList) {
                itineraryDtoList.add(new ItineraryDto(record.get(0), record.get(1), record.get(2), record.get(3), record.get(4), record.get(5)));
            }

            return itineraryDtoList;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }

    public List<TicketDto> readTicketFromFile(String filename, Path path) {
        path = Paths.get(Directory.FILE_DIRECTORY.getPath() + filename);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Directory.FILE_DIRECTORY.getPath() + filename));
             CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT)) {
            List<TicketDto> ticketDtoList = new ArrayList<>();
            List<CSVRecord> recordList = csvParser.getRecords();
            for (CSVRecord record : recordList) {
                ticketDtoList.add(new TicketDto(record.get(0), record.get(1), record.get(2), record.get(3).toUpperCase(), record.get(4)));
            }
            return ticketDtoList;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }

}
