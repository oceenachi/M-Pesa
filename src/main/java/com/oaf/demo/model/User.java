package com.oaf.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO )
    private int id;

    @NotBlank
    @Size(max = 125)
    private String name;

    @NotBlank
    @Size(max = 125)
    private String address;

    @NotBlank
    @Size(max = 12)
    @Column(unique = true)
    private String phoneNumber;


    @NotBlank
    @Size(max= 52)
    private String pin;


}
