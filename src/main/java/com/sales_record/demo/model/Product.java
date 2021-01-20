package com.sales_record.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Size(max = 20)
    private String model;

    @NotBlank
    @NotNull
    @Size(max = 20)
    private String gender;

    @NotBlank
    @NotNull
    @Size(max = 20)
    private String quality;

    @NotBlank
    @NotNull
    @Size(max = 20)
    private String size;

    @NotBlank
    @NotNull
    @Size(max = 20)
    private String color;

    @NotBlank
    @NotNull
    @Size(max = 20)
    private double price;

    private int available; //0: available , 1: not available

    @OneToMany(mappedBy = "product")
    List<OrderDetail> orderDetails;
}
