package com.oaf.demo.service;

import org.springframework.http.ResponseEntity;

public interface FundInterface {

    ResponseEntity<?> fundAccount(String id, Double amount);



}
