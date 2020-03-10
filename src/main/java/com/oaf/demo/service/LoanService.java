package com.oaf.demo.service;

import com.oaf.demo.exception.InsufficientBalanceException;
import com.oaf.demo.model.BankDetails;
import com.oaf.demo.model.Loan;
import com.oaf.demo.repository.BankDetailsRepository;
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
    BankDetailsRepository bankDetailsRepository;


    @Autowired
    public LoanService(LoanRepository loanRepository, BankDetailsRepository bankDetailsRepository){
        this.loanRepository = loanRepository;
        this.bankDetailsRepository = bankDetailsRepository;
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
        Double newSpan = (double) span;
        Long loanAmount = loanRepository.getLoanAmount(Long.parseLong(id));
        Double breakDown = (loanAmount + 0.00) / newSpan;
        return  new ResponseEntity<>(breakDown, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> payLoan(String id, String amountToBePaid) {
        System.out.println(amountToBePaid);
        Double accountBalance = bankDetailsRepository.getAccountBalance(Long.parseLong(id));
        Long new_balance = accountBalance.longValue();
        if(new_balance >= Long.parseLong(amountToBePaid)){
            Long loanAmount = loanRepository.getLoanAmount(Long.parseLong(id));
            Long response = loanAmount - Long.parseLong(amountToBePaid);
            new_balance -= Long.parseLong(amountToBePaid);
            bankDetailsRepository.updateAccount(new_balance + 0.00, Long.parseLong(id));
            loanRepository.updateLoanAmount(Long.parseLong(id), response);
            return new ResponseEntity<Long>(response, HttpStatus.ACCEPTED);
        }
        else{
            throw new InsufficientBalanceException("Insufficent balance in account");
        }

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
