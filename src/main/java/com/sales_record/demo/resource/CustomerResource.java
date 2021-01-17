package com.sales_record.demo.resource;

import lombok.Data;

@Data
public class CustomerResource {

    private Long id;
    private String customerName;
    private String customerLastName;
    private String info;
    private String dni;
    private String department;
    private String district;
    private String address;
    private String phoneNumber;

}
