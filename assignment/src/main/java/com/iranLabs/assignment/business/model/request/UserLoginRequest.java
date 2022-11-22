package com.iranLabs.assignment.business.model.request;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */
public class UserLoginRequest implements Serializable {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
