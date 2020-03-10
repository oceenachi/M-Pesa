package com.oaf.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY )
    private Long id;

    @NotBlank
    @Size(max = 125)
    private String name;

    @NotBlank
    @Size(max = 12)
    @Column(unique = true)
    private String phoneNumber;

    @NotBlank
    @Size(max= 52)
    private String pin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loanId", referencedColumnName = "id")
    private Loan loan;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_id", referencedColumnName = "id")
    private BankDetails bankDetails;

}
