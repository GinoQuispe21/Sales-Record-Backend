package com.sales_record.demo.resource;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class SaveOrderResource {

    @NotBlank
    @NotNull
    private int paymentMethod;

    @NotBlank
    @NotNull
    private int paymentAgainstDelivery;

    private double payment;

    private double amount;

    @NotBlank
    @NotNull
    private String state;

    @NotBlank
    @NotNull
    private Date generatedDate;

    @NotBlank
    @NotNull
    private Date deliveryDate;

    @NotBlank
    @NotNull
    private double deliveryPrice;
}
