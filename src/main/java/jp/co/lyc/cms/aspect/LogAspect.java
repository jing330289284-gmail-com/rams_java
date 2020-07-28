package jp.co.lyc.cms.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Before("execution(* jp.co.lyc.cms.controller.LoginController.*(..))")
    public void startLog(JoinPoint joinpoint){
    	//TODO: log.debug("メソッド開始: " + joinpoint.getSignature());
        System.out.println("メソッド開始: " + joinpoint.getSignature());
    }
    
    @After("execution(* jp.co.lyc.cms.controller.LoginController.*(..))")
    public void endLog(JoinPoint joinpoint){
    	//TODO: log.debug("メソッド終了: " + joinpoint.getSignature());
        System.out.println("メソッド終了: " + joinpoint.getSignature());
    }
}
