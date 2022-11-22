package com.iranLabs.assignment.aspect;

import com.iranLabs.assignment.business.service.UserService;
import com.iranLabs.assignment.exception.AuthorizationServiceException;
import com.iranLabs.assignment.persistence.entity.UserEntity;
import com.iranLabs.assignment.persistence.repository.UserRepository;
import com.iranLabs.assignment.util.JWTUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Optional;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */

@Aspect
@Component
public class AdminPanelAspect {


    private final UserRepository repository;
    private final UserService userService;

    @Autowired
    public AdminPanelAspect(UserRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Pointcut("execution(* com.iranLabs.assignment.controller.ResponseRestController.*(..))")
    public void adminPanel() {
    }

    @Before(value = "adminPanel()")
    public void beforeGivingResponse(JoinPoint pjp) throws Exception {
        Object[] exArgs = pjp.getArgs();
        Map<String, String> headers;
        headers = (Map<String, String>) exArgs[0];

        checkToken(headers);
    }

    private void checkToken(Map<String, String> headers) throws Exception {
        String authorizationToken = headers.get("authorization");
        if (StringUtils.hasText(authorizationToken)) {
            if (authorizationToken.startsWith("JWT")) {
                String[] split = authorizationToken.split("\\s");
                Map<String, String> credentialData = JWTUtil.validateTokenAndRetrieveSubject(split[1]);
                Optional<UserEntity> byUsername = userService.findByUsername(credentialData.get("username"));
                if (byUsername.isEmpty()) {
                    throw new AuthorizationServiceException();
                } else {
                    if (!byUsername.get().getSecurityData().getPassword().equals(credentialData.get("password"))) {
                        throw new AuthorizationServiceException();
                    }
                }
            } else throw new AuthorizationServiceException("service.auth.wrong.token");
        } else throw new AuthorizationServiceException("service.auth.no.token");
    }
}
