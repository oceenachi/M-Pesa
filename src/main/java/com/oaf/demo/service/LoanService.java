package com.oaf.demo.service;

import com.oaf.demo.model.Loan;
import com.oaf.demo.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


@Service
public class LoanService implements LoanInterface {
    LoanRepository loanRepository;


    @Autowired
    public LoanService(LoanRepository loanRepository){
        this.loanRepository = loanRepository;
    }

    @Override
    public Long getTotalLoanAmount(String id) {
        return loanRepository.getLoanAmount(Long.parseLong(id));
    }

    @Override
    public Loan createLoan() {
        Loan loan = new Loan();
        loan.setLoanAmount(this.calculateLoan());
        return loan;
    }

    @Override
    public ResponseEntity<?> getLoanBreakdown(String id, int span) {

        Long loanAmount = loanRepository.getLoanAmount(Long.parseLong(id));
        if(span == 3){
            Double breakDown = loanAmount + 0.00 / 3;
            return new ResponseEntity<>(breakDown, HttpStatus.OK);
        }
        else if(span == 6){
            Double breakDown = loanAmount + 0.00 / 6;
            return new ResponseEntity<>(breakDown, HttpStatus.OK);
        }
        else if(span == 12){
            Double breakDown = loanAmount + 0.00 / 12;
            return new ResponseEntity<>(breakDown, HttpStatus.OK);
        }
        return null;
    }

    @Override
    public ResponseEntity<?> payLoan(String id, String amount) {
        Long loanAmount = loanRepository.getLoanAmount(Long.parseLong(id));
        Long response = loanAmount - Long.parseLong(amount);
        loanRepository.updateLoanAmount(Long.parseLong(id), response);
        return new ResponseEntity<Long>(response, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<?> payInterest(Long id) {
        Long currentLoan = loanRepository.getLoanAmount(id);
        double interest = 0.01 * currentLoan;
        double newAmount = currentLoan + Math.ceil(interest);
        Long long_amount = Double.valueOf(newAmount).longValue();
        loanRepository.updateLoanAmount(long_amount, id);

        return new ResponseEntity<>(long_amount, HttpStatus.OK);
    }

    @Override
    @Scheduled(cron = "* * * 1 1/1 *")
    public void generateInterest(){
        List<Loan> allLoans = loanRepository.findAll();
        for(Loan loan: allLoans){
            this.payInterest(loan.getId());
        }
    }


    @Override
    public Long calculateLoan() {
        Random rand = new Random();
        Long minRange = 100000L, maxRange= 500000L;
        Long loan = (long) (minRange + rand.nextFloat() * maxRange);
        System.out.println(loan);
        return loan;
    }
}
