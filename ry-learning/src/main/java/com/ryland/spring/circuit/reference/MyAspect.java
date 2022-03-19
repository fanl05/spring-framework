package com.ryland.spring.circuit.reference;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Ryland
 */
@Component
@Aspect
@Slf4j
public class MyAspect {

	@Pointcut(value = "execution(* com.ryland.spring.circuit.reference.A.*(..)) || execution(* com.ryland.spring.circuit.reference.B.*(..))")
	public void myPointCut() {
		// point cut
	}

	@Around(value = "myPointCut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		log.debug("-----before-----");
		Object retVal = joinPoint.proceed();
		log.debug("-----after-----");
		return retVal;
	}

}
