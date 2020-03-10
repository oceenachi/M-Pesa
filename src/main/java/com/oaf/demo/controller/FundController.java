package com.oaf.demo.controller;


import com.oaf.demo.service.FundService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/fund")
public class FundController {

    FundService fundService;

    public FundController( FundService fundService){
        this.fundService = fundService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> fundAccount(@PathVariable("id") String id, @RequestBody Double amount){
        return fundService.fundAccount(id, amount);
    }
}
