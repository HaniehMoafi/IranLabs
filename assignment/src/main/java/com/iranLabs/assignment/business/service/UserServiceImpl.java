package com.iranLabs.assignment.business.service;

import com.iranLabs.assignment.business.serviceEnums.RoleEnum;
import com.iranLabs.assignment.persistence.entity.UserEntity;
import com.iranLabs.assignment.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */

@Service

public class UserServiceImpl implements UserService /*, UserDetailsService */{


    private final UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserEntity addUser(String name) {
        UserEntity user = new UserEntity();
        user.setUsername(name);
        user.setRole(RoleEnum.USER);
        return userRepository.save(user);
    }


}
