package com.sales_record.demo.resource;

import lombok.Data;

import java.util.Date;

@Data
public class OrderResource {
    private Long id;
    private int paymentMethod;
    private int paymentAgainstDelivery;
    private double payment;
    private double amount;
    private String state;
    private Date generatedDate;
    private Date deliveryDate;
    private double deliveryPrice;
}
