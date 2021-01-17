package com.sales_record.demo.resource;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SaveCustomerResource {

    @NotBlank
    @NotNull
    @Size(max = 20)
    private String customerName;

    @NotBlank
    @NotNull
    @Size(max = 20)
    private String customerLastName;

    @NotBlank
    @NotNull
    @Size(max = 100)
    private String info;

    @NotBlank
    @NotNull
    @Column(unique = true)
    @Size(max = 8)
    private String dni;

    @NotBlank
    @NotNull
    @Size(max = 20)
    private String department;

    @NotBlank
    @NotNull
    @Size(max = 20)
    private String district;

    @NotBlank
    @NotNull
    @Size(max = 20)
    private String address;

    @NotBlank
    @NotNull
    @Column(unique = true)
    @Size(max = 20)
    private String phoneNumber;
}
