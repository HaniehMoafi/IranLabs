package com.iranLabs.assignment.business.service;

import com.iranLabs.assignment.business.model.request.UserLoginRequest;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */
public interface AuthenticationService {

    String login(UserLoginRequest request) throws Exception;
}
