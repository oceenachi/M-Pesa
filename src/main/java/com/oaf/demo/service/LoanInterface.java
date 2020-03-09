package com.oaf.demo.service;


import com.oaf.demo.model.Loan;
import org.springframework.http.ResponseEntity;

public interface LoanInterface {

    Long getTotalLoanAmount(String id);

    ResponseEntity<?> getLoanBreakdown(String id, int span);

    ResponseEntity<?> payLoan(String id, String amount);

    ResponseEntity<?> payInterest(Long id);

    Long calculateLoan();

    Loan createLoan();

    void generateInterest();
}
