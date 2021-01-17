package com.sales_record.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "admins")
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @NotBlank
    @NotNull
    @Size(max = 15)
    private String password;
}
