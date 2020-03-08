package com.oaf.demo.service;


import com.oaf.demo.dto.request.LoginDetails;
import com.oaf.demo.dto.request.UserDetails;
import com.oaf.demo.dto.response.CreateUserResponse;
import com.oaf.demo.dto.response.MyResponse;
import com.oaf.demo.dto.response.UserInfo;
import com.oaf.demo.exception.UserAlreadyExistsException;
import com.oaf.demo.model.User;
import com.oaf.demo.repository.LoanRepository;
import com.oaf.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Optional;

@Service
public class UserService implements UserInterface {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private LoanRepository loanRepository;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper
            ,LoanRepository loanRepository){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.loanRepository = loanRepository;

    }

    @Override
    public CreateUserResponse createUser(UserDetails newUser) {
        Optional<String> existingPhoneNumber = userRepository.findByPhoneNumber(newUser.getPhoneNumber());
        CreateUserResponse response = new CreateUserResponse();
        if(existingPhoneNumber.isPresent()){
            throw new UserAlreadyExistsException("Sorry " + newUser.getName() + "already exists");
        }
        else{
            User user = modelMapper.map(newUser, User.class);
            response.setName(user.getName());
            response.setAccountNumber(user.getPhoneNumber());
            userRepository.save(user);
        }
        return response;

    }

    @Override
    public UserInfo setUserInfo(Optional<User> optionalUser) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(optionalUser.get().getName());
        userInfo.setAccountNumber(optionalUser.get().getPhoneNumber());
        Long userId = optionalUser.get().getId();
        userInfo.setLoanAmount(Long.toString(loanRepository.getLoanAmount(userId)));

        return userInfo;
    }

    @Override
    public MyResponse<User> updateUserDetails(String Id, UserDetails updateInfo) {
        Optional<User> optionalUser = userRepository.findById(Long.parseLong(Id));
        if(updateInfo.getName() != null) {
            optionalUser.get().setName(updateInfo.getName());
        }
        if(updateInfo.getPin() != null) {
            optionalUser.get().setPin(updateInfo.getPin());
        }

        userRepository.save(optionalUser.get());
        MyResponse<User> update = new MyResponse<User>(HttpStatus.OK, "User updated successfully", optionalUser.get() );

        return update;

    }

    @Override
    public void login(LoginDetails loginDetails) {

    }
}
