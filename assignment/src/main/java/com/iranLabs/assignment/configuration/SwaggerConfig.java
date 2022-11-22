package com.iranLabs.assignment.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */
@Configuration
@EnableWebSecurity
public class SwaggerConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}