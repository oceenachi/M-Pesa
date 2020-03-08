package com.oaf.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
public class MyResponse<T> {

    private HttpStatus status;
    private String message;
    private T data;
 }
