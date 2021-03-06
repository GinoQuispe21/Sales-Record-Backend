package com.sales_record.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "order")
    List<OrderDetail> orderDetails;
}
