package com.iranLabs.assignment.view;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/23/2022
 */

@Scope(value = "session")
@Component(value = "login")
@ELBeanName(value = "login")
@Join(path = "/login", to = "/login.jsf")
public class Login {


}
