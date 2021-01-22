package com.sales_record.demo.model;

import lombok.Data;

@Data
public class CartLine {
    private Long id;
    private String model;
    private String gender;
    private String quality;
    private String size;
    private String color;
    private double price;
    private int quantity;
    private int typeSale;
}
