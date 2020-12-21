package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

	// this is where we and all of our related advices for logging
	
	// let's start with an @Befour advice
	
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
	public void forDaoPackage() {}
	
	
//	@Before("execution(public void add*())") 
	@Before("forDaoPackage()")
	public void beforeAddAccountAdvice() {
		
		System.out.println("\n=======>>>> Executing @Before advice on method");
	}
	
	@Before("forDaoPackage()")
	public void performApiAnalitics() {
		
		System.out.println("\n=======>>>> Performing API analitics");
		
	}
	
}
