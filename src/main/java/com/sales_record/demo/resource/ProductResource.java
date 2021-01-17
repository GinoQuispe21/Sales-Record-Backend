package com.sales_record.demo.resource;

import lombok.Data;

@Data
public class ProductResource {
    private Long id;
    private String model;
    private String quality;
    private String size;
    private String color;
    private double price;
    private int available;
    private String gender;
}
