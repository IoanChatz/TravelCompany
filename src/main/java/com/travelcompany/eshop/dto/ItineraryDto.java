package com.travelcompany.eshop.dto;

import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.validator.Validator;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class ItineraryDto {
    private String id;
    private String departureAirportId;
    private String destinationAirportId;
    private String departureDate;
    private String airline;
    private String cost;

    public ItineraryDto(String id, String departureAirportId, String destinationAirportId, String departureDate, String airline, String cost) {
        this.id = id;
        this.departureAirportId = departureAirportId;
        this.destinationAirportId = destinationAirportId;
        this.departureDate = departureDate;
        this.airline = airline;
        this.cost = cost;
    }

    public List<Itinerary> createItineraryBatchList(List<ItineraryDto> itineraryDtoList, Validator validator) {
        List<Itinerary> itinerariesList = new ArrayList<>(0);
        for (ItineraryDto itr : itineraryDtoList) {
            Itinerary itinerary = new Itinerary();
            if (validator.isValidNumber(itr.getId()))
                itinerary.setId(Integer.parseInt(itr.getId()));
            if (validator.isValidIATACode(itr.getDepartureAirportId()))
                itinerary.setDepartureAirportId(itr.getDepartureAirportId());
            if (validator.isValidIATACode(itr.getDestinationAirportId()))
                itinerary.setDestinationAirportId(itr.getDestinationAirportId());
            if (validator.isValidDate(itr.getDepartureDate()))
                itinerary.setDepartureDate(LocalDateTime.parse(itr.getDepartureDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            if (validator.isValidName(itr.getAirline()))
                itinerary.setAirline(itr.getAirline());
            if (validator.isValidNumber(itr.getCost()))
                itinerary.setCost(Integer.parseInt(itr.getCost()));
            itinerariesList.add(itinerary);
        }
        return itinerariesList;
    }
}
