package com.oaf.demo.service;


import com.oaf.demo.repository.BankDetailsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FundService implements FundInterface{

    private BankDetailsRepository bankDetailsRepository;

    public FundService(BankDetailsRepository bankDetailsRepository){

        this.bankDetailsRepository = bankDetailsRepository;
    }


    @Override
    public ResponseEntity<?> fundAccount(Double amount, Long id) {
         Double previousAmount = bankDetailsRepository.getCurrentLoan(id);
         Double newAmount = previousAmount + amount;
         bankDetailsRepository.updateAccount(newAmount, id);
         return new ResponseEntity<String>("Success the account has been updated with amount " + amount + ". "
                 +"\n" + "Current account balance is " + newAmount + ". ", HttpStatus.ACCEPTED);
    }
}
