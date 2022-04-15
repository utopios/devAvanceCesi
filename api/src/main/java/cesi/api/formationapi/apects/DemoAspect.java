package cesi.api.formationapi.apects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DemoAspect {

    //créer pointcut
    @Pointcut("execution(public * cesi.api.formationapi.services.*Service.*(..)))")
    public void methodPointCut() {
    }

    //Greffon
    @Before("methodPointCut()")
    public void actiobGreffon(JoinPoint joinPoint) {
        System.out.println("action greffon "+ joinPoint.toShortString());
    }

    @AfterThrowing(pointcut = "methodPointCut()", throwing = "ex")
    public void error(JoinPoint joinPoint, Throwable ex) {
        System.out.println("action greffon "+ joinPoint.toShortString() + "execption : "+ ex.getClass().getSimpleName());
    }
}
