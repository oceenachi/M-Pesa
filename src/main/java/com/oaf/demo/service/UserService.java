package com.oaf.demo.service;


import com.oaf.demo.dto.LoginDetails;
import com.oaf.demo.dto.UserDetails;
import com.oaf.demo.model.User;
import com.oaf.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserInterface {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;

    }

    @Override
    public User createUser(UserDetails newUser) {
        Optional<String> existingPhoneNumber = userRepository.findByPhoneNumber(newUser.getPhoneNumber());
        if(existingPhoneNumber.isPresent()){
            throw new UserAlreadyExistsException("Sorry " + newUser.getName() + "already exists");
        }

    }

    @Override
    public User getUser(String Id) {
        return null;
    }

    @Override
    public User updateUserDetails(String Id, UserDetails updateInfo) {
        return null;
    }

    @Override
    public void login(LoginDetails loginDetails) {

    }
}
