package com.oaf.demo.service;


import org.springframework.http.ResponseEntity;

public interface LoanInterface {

    ResponseEntity<?> getTotalLoanAmount();

    ResponseEntity<?> getLoanBreakdown();

    ResponseEntity<?> payLoan();
}
