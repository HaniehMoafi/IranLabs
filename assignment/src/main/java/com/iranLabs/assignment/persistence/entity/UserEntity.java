package com.iranLabs.assignment.persistence.entity;

import com.iranLabs.assignment.business.serviceEnums.RoleEnum;

import javax.persistence.*;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */

@Entity
@Table(name = "USER_ENTITY")
public class UserEntity extends BaseEntity {


    @Column(name = "USER_NAME", unique = true, length = 100)
    private String username;


    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }


}
