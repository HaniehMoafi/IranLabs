package com.iranLabs.assignment.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SwaggerConfig extends WebSecurityConfigurerAdapter {


/*    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*        http.authorizeRequests()
                .antMatchers("/**").permitAll();*/


        http.authorizeRequests()
                .antMatchers("/recommendation/**").permitAll()
                .antMatchers("/response")
//                .antMatchers("/javax.faces.resource/**").permitAll()
                .authenticated();

        // login
        http.formLogin().loginPage("/login").permitAll()
                .usernameParameter("user").passwordParameter("pass")
                .failureUrl("/login?error=true");
        // logout
        http.logout().logoutSuccessUrl("/login");
        // not needed as JSF 2.2 is implicitly protected against CSRF
        http.csrf().disable();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}