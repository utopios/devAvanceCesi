package com.cesi.apireservation.aspect;

import com.cesi.apireservation.model.UserApp;
import com.cesi.apireservation.service.UserAppService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AdminAspect {

    @Autowired
    private UserAppService userAppService;

    @Before("execution(public * *.*Admin(..))")
    public void isAdmin(JoinPoint joinPoint) throws Exception {
        UserApp user = userAppService.getUserFromToken();
        if(!user.isAdmin()) {
            throw new Exception("Not Allowed");
        }
    }
}
