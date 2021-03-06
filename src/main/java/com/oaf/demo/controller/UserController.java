package com.oaf.demo.controller;


import com.oaf.demo.dto.request.UserDetails;
import com.oaf.demo.dto.response.CreateUserResponse;
import com.oaf.demo.dto.response.MyResponse;
import com.oaf.demo.model.User;
import com.oaf.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/health")
    public String checkStatus(){
        return "we are here at register. all's good";
    }

    @PostMapping("/create")
    public MyResponse<CreateUserResponse> createNewUser(@RequestBody UserDetails newUser){
        CreateUserResponse response = userService.createUser(newUser);
        return new MyResponse(HttpStatus.CREATED, "User successfully created", response);

    }

    @PutMapping("{id}")
    public  MyResponse<UserDetails> updateUserInfo(@PathVariable("id") String id, @RequestBody UserDetails update){
        return userService.updateUserDetails(id, update);
    }

}
