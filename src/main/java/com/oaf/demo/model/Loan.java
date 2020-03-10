package com.oaf.demo.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String interestRate = "12%";

    @NotNull
    private Long loanAmount;
//
//    @OneToOne(mappedBy = "loan")
//    private User user;

}
