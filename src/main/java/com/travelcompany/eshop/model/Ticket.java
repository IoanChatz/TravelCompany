package com.travelcompany.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    private int id;
    private int customerId;
    private int itineraryId;
    private PaymentOption paymentOption;
    private int costAmount;
}
