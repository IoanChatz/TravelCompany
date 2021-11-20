package com.travelcompany.eshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    private long id;
    private long passengerId;
    private long ItineraryId;
    private PaymentOption paymentOption;
    private BigDecimal costAmount;
}
