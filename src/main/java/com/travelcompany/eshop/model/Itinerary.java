package com.travelcompany.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Itinerary {
    private int id;
    private String departureAirportId;
    private String destinationAirportId;
    private LocalDateTime departureDate;
    private String airline;
    private int cost;
}
