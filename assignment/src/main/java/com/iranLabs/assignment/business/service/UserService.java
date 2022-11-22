package com.iranLabs.assignment.business.service;

import com.iranLabs.assignment.persistence.entity.UserEntity;

import java.util.Optional;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */
public interface UserService {

    Optional<UserEntity> findByUsername(String username);

    UserEntity addUser(String name);
}
