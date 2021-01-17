package com.sales_record.demo.resource;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SaveAdminResource {

    @NotBlank
    @NotNull
    @Column(unique = true)
    @Size(max = 20)
    private String name;

    @NotBlank
    @NotNull
    @Column(unique = true)
    @Size(max = 20)
    private String phoneNumber;

    @NotBlank
    @NotNull
    @Column(unique = true)
    @Size(max = 30)
    private String email;
}
