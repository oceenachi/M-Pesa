package com.oaf.demo.service;

import com.oaf.demo.dto.request.LoginDetails;
import com.oaf.demo.dto.request.UserDetails;
import com.oaf.demo.dto.response.CreateUserResponse;
import com.oaf.demo.dto.response.MyResponse;
import com.oaf.demo.dto.response.UserInfo;
import com.oaf.demo.model.User;

import java.util.Optional;

public interface UserInterface {

    CreateUserResponse createUser(UserDetails newUser);

    UserInfo setUserInfo(Optional<User> optionalUser);

    MyResponse<UserDetails> updateUserDetails(String Id, UserDetails updateInfo);

    void login(LoginDetails loginDetails);
}
