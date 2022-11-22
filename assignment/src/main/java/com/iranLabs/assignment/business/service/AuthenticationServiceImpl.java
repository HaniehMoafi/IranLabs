package com.iranLabs.assignment.business.service;

import com.iranLabs.assignment.business.model.request.UserLoginRequest;
import com.iranLabs.assignment.business.serviceEnums.RoleEnum;
import com.iranLabs.assignment.exception.RecommendationServiceException;
import com.iranLabs.assignment.persistence.entity.UserEntity;
import com.iranLabs.assignment.persistence.repository.UserRepository;
import com.iranLabs.assignment.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository repository;


    @Autowired
    public AuthenticationServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public String login(UserLoginRequest request) throws RecommendationServiceException {
        Optional<UserEntity> foundUser = repository.findByUsernameAndRole(request.getUsername(), RoleEnum.ADMIN);
        if (foundUser.isPresent()) {
            UserEntity user = foundUser.get();
            checkPassword(user.getSecurityData().getPassword(), request.getPassword());
            return JWTUtil.generateToken(request.getUsername(), request.getPassword());
        } else throw new RecommendationServiceException("service.user.not.found");
    }

    private void checkPassword(String userPass, String inputPass) throws RecommendationServiceException {
        if (!Objects.equals(userPass, inputPass))
            throw new RecommendationServiceException("service.password.is.not.correct");
    }
}
