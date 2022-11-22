package com.iranLabs.assignment.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */

@Entity
@Table(name = "RESPONSE_ENTITY")
public class ResponseEntity extends BaseEntity {

    @Column(name = "RESPONSE")
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
