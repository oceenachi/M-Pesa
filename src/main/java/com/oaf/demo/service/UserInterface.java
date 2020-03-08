package com.oaf.demo.service;

import com.oaf.demo.dto.LoginDetails;
import com.oaf.demo.dto.UserDetails;
import com.oaf.demo.model.User;

public interface UserInterface {

    User createUser(UserDetails newUser);

    User getUser(String Id);

    User updateUserDetails(String Id, UserDetails updateInfo);

    void login(LoginDetails loginDetails);
}
