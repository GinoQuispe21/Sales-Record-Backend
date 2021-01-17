package com.sales_record.demo.resource;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SaveProductResource {
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

    private int available;
}
