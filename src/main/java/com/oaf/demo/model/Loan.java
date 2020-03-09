package com.oaf.demo.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Data
@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String interestRate = "12%";

    @NotBlank
    private Long loanAmount;

    @OneToOne(mappedBy = "loan")
    private User user;

}
