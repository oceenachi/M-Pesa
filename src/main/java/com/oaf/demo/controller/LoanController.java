package com.oaf.demo.controller;


import com.oaf.demo.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/loan")
public class LoanController {

    private LoanService loanService;

    @Autowired
    public LoanController (LoanService loanService){
        this.loanService = loanService;
    }

    @PostMapping("/pay/{id}")
    public ResponseEntity<?> payLoan(@PathVariable("id") String id, @RequestBody String amount){
        return loanService.payLoan(id, amount);
    }

    @PostMapping("/breakdown/{id}")
    public ResponseEntity<?> getBreakdown(@PathVariable("id") String id, @RequestBody int span){
        return loanService.getLoanBreakdown(id, span);
    }
}
