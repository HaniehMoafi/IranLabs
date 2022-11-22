package com.iranLabs.assignment.persistence.repository;

import com.iranLabs.assignment.business.serviceEnums.RoleEnum;
import com.iranLabs.assignment.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {


    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity>  findByUsernameAndRole(String username, RoleEnum role);
}
