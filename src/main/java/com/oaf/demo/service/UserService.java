package com.oaf.demo.service;


import com.oaf.demo.dto.request.LoginDetails;
import com.oaf.demo.dto.request.UserDetails;
import com.oaf.demo.dto.response.CreateUserResponse;
import com.oaf.demo.dto.response.MyResponse;
import com.oaf.demo.dto.response.UserInfo;
import com.oaf.demo.exception.UserAlreadyExistsException;
import com.oaf.demo.model.BankDetails;
import com.oaf.demo.model.Loan;
import com.oaf.demo.model.User;
import com.oaf.demo.repository.BankDetailsRepository;
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
    private LoanService loanService;
    private BankDetailsRepository bankDetailsRepository;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper
            ,LoanRepository loanRepository, LoanService loanService, BankDetailsRepository bankDetailsRepository){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.loanRepository = loanRepository;
        this.loanService = loanService;
        this.bankDetailsRepository = bankDetailsRepository;

    }

    @Override
    public CreateUserResponse createUser(UserDetails newUser) {
        Optional<String> existingPhoneNumber = userRepository.findByPhoneNumber(newUser.getPhoneNumber());
        System.out.println("got here");
        CreateUserResponse user_response = new CreateUserResponse();
        if(existingPhoneNumber.isPresent()){
            throw new UserAlreadyExistsException("Sorry " + newUser.getName() + " already exists");
        }
        else{
            User user = modelMapper.map(newUser, User.class);
            BankDetails bankDetails = new BankDetails();
            bankDetails.setAccountNumber(user.getPhoneNumber().substring(1));
            System.out.println(bankDetails.getAccountNumber());
            Loan user_loan = loanService.createLoan();
            user.setLoan(user_loan);
            user.setBankDetails(bankDetails);
            user_response.setName(user.getName());
            user_response.setAccountNumber(user.getPhoneNumber());

//            System.out.println("under user_loan");
//            user_loan.setUser(user);
            user_response.setCurrentLoan(user_loan.getLoanAmount());
            System.out.println(user_loan);
            System.out.println(user);
            loanRepository.save(user_loan);
            bankDetailsRepository.save(bankDetails);
            userRepository.save(user);

        }
        return user_response;

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
    public MyResponse<UserDetails> updateUserDetails(String Id, UserDetails updateInfo) {
        Optional<User> optionalUser = userRepository.findById(Long.parseLong(Id));
        if(updateInfo.getName() != null) {
            optionalUser.get().setName(updateInfo.getName());
        }
        if(updateInfo.getPin() != null) {
            optionalUser.get().setPin(updateInfo.getPin());
        }

        userRepository.save(optionalUser.get());
        MyResponse<UserDetails> update = new MyResponse<UserDetails>(HttpStatus.OK, "User updated successfully", updateInfo );

        return update;

    }

    @Override
    public void login(LoginDetails loginDetails) {

    }
}
