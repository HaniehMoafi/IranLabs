package com.iranLabs.assignment.business.model.response;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */
public class UserLoginResponse extends BaseResponse{

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
