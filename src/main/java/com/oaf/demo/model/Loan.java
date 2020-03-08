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

    private Long interestRate;

    @NotBlank
    private Long loanAmount;

    @OneToOne(mappedBy = "loan")
    private User user;

}
