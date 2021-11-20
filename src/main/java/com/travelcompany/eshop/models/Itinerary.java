package com.travelcompany.eshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Itinerary {
    private long id;
    private String departureAirportId;
    private String destinationAirportId;
    private LocalDateTime departureDate;
    private String airline;
    private BigDecimal cost;
}
