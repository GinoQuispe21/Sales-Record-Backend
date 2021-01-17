package com.sales_record.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private int paymentMethod; /* 0: full payment
                                  1: advance payment
                                  2: payment against delivery */
    @NotBlank
    @NotNull
    private int paymentAgainstDelivery; /* 0: null
                                           1: cash
                                           2: transfer */

    private double payment; //Advance payment of the amount

    private double amount; //Total Amount

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
