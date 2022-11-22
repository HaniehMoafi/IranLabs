package com.iranLabs.assignment.controller;

import com.iranLabs.assignment.business.model.request.UserLoginRequest;
import com.iranLabs.assignment.business.model.response.UserLoginResponse;
import com.iranLabs.assignment.business.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */

@RestController
@RequestMapping("/authentication")
public class AuthenticationRestController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationRestController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> loginUser(@Valid @RequestBody UserLoginRequest request) throws Exception {
        UserLoginResponse response = new UserLoginResponse();
        response.setToken(authenticationService.login(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
