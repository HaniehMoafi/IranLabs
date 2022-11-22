package com.iranLabs.assignment.business.service;

import com.iranLabs.assignment.business.serviceEnums.RoleEnum;
import com.iranLabs.assignment.persistence.entity.SecurityDataEntity;
import com.iranLabs.assignment.persistence.entity.UserEntity;
import com.iranLabs.assignment.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */
@Service
public class ApplicationStartup {

    private final UserRepository userRepository;

    @Autowired
    public ApplicationStartup(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostConstruct
    private void init() {

        UserEntity entity = new UserEntity();
        entity.setUsername("admin");
        entity.setRole(RoleEnum.ADMIN);
        SecurityDataEntity securityData = new SecurityDataEntity();
        securityData.setPassword("admin");
        entity.setSecurityData(securityData);
        userRepository.save(entity);
    }
}
