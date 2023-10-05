package com.niit.AuthApp.controller;

import com.niit.AuthApp.domain.User;
import com.niit.AuthApp.service.IUserService;
import com.niit.AuthApp.service.SecurityTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/user/v1")
public class UserController {
    private IUserService iUserService;
    private SecurityTokenGenerator securityTokenGenerator;
    @Autowired
    public UserController(IUserService iUserService, SecurityTokenGenerator securityTokenGenerator) {
        this.iUserService = iUserService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User user)
    {
        user.setRole("User_Role");
        return new ResponseEntity<>(iUserService.register(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user)
    {
        User retrievedDetails = iUserService.checkLogin(user);
        System.out.println(retrievedDetails);
        if(retrievedDetails != null)
        {
            return new ResponseEntity<>(securityTokenGenerator.generateToken(retrievedDetails),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Login Failed...",HttpStatus.EXPECTATION_FAILED);
        }
    }
}
