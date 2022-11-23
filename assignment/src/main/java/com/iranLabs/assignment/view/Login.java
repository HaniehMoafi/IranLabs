package com.iranLabs.assignment.view;

import com.iranLabs.assignment.business.model.request.UserLoginRequest;
import com.iranLabs.assignment.business.model.response.GetAllRecommendation;
import com.iranLabs.assignment.business.model.response.UserLoginResponse;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/23/2022
 */

@Scope(value = "session")
@Component(value = "login")
@ELBeanName(value = "login")
@Join(path = "/login", to = "/login.jsf")
public class Login {


    @Autowired
    private RestTemplate restTemplate;


    private String user;
    private String pwd;

    public String login() {
         UserLoginRequest request = new UserLoginRequest();
         request.setPassword(pwd);
         request.setUsername(user);
        UserLoginResponse response = restTemplate.postForEntity("http://localhost:8080/authentication/login", request,
                UserLoginResponse.class).getBody();
       String token = response.getToken();
        return "response-add.xhtml?faces-redirect=true";
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
