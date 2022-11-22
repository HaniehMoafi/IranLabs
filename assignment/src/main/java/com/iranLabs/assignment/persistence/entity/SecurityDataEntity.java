package com.iranLabs.assignment.persistence.entity;

import com.iranLabs.assignment.business.converter.PasswordConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */
@Entity
@Table(name = "SECURITY_DATA_ENTITY")
public class SecurityDataEntity extends BaseEntity {


    @Convert(converter = PasswordConverter.class)
    @Column(name = "PASSWORD")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
