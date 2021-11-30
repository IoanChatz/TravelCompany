package com.travelcompany.eshop.dto;

import com.travelcompany.eshop.model.PaymentOption;
import com.travelcompany.eshop.model.Ticket;
import com.travelcompany.eshop.validator.Validator;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class TicketDto {

    private String id;
    private String customerId;
    private String itineraryId;
    private String paymentOption;
    private String costAmount;

    public TicketDto(String id, String customerId, String itineraryId, String paymentOption, String costAmount) {
        this.id = id;
        this.customerId = customerId;
        this.itineraryId = itineraryId;
        this.paymentOption = paymentOption;
        this.costAmount = costAmount;
    }

    public List<Ticket> createTicketBatchList(List<TicketDto> ticketDtoList, Validator validator) {
        List<Ticket> ticketsList = new ArrayList<>(0);
        for (TicketDto t : ticketDtoList) {
            Ticket ticket = new Ticket();
            if (validator.isValidNumber(t.getId()))
                ticket.setId(Integer.parseInt(t.getId()));
            if (validator.isValidNumber(t.getCustomerId()))
                ticket.setCustomerId(Integer.parseInt(t.getCustomerId()));
            if (validator.isValidNumber(t.getItineraryId()))
                ticket.setItineraryId(Integer.parseInt(t.getItineraryId()));
            if (validator.isInEnum(t.getPaymentOption(), PaymentOption.class))
                ticket.setPaymentOption(PaymentOption.valueOf(t.getPaymentOption()));
            if (validator.isValidNumber(t.getCostAmount()))
                ticket.setCostAmount(Integer.parseInt(t.getCostAmount()));
            ticketsList.add(ticket);
        }
        return ticketsList;
    }
}
