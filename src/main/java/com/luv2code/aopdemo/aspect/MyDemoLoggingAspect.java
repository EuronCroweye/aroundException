package com.luv2code.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;
import com.luv2code.aopdemo.AroundWithLoggerDemoApp;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

	Logger myLogger = 
			Logger.getLogger(getClass().getName());
	
	
	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune (
			ProceedingJoinPoint theProcidingJoinPoint) throws Throwable{
		
		// print out method we are advising on
		String method =theProcidingJoinPoint.getSignature().toShortString();
		myLogger.info("\n======>>>>> Executing @Around on method: "
				+method);
		// get begin timestamp
		long begin=System.currentTimeMillis();
		
		// now, let's execute the method
		Object result=null;
				
		try {
			result = theProcidingJoinPoint.proceed();
		} catch (Throwable e) {

			// log the exception
			myLogger.warning(e.getMessage());
			
			// give user a custom message
		/*	result="Major accident! But no worries,"
					+ " your private helicopter is on the way!";
			*/
			throw e;
			
		}
		
		// get end timestamp
		long end =System.currentTimeMillis();
		
		// compute duration and display it
		long duration =end-begin;
		myLogger.info("\nDuration: "+ duration/1000.0 +
				" seconds");
		
		
		return result;
	}
	
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyAccountAdvice(JoinPoint theJoinPoint) {
		
		// print out witch method we are advising on
		String method =theJoinPoint.getSignature().toShortString();
		myLogger.info("\n======>>>>> Executing @After (finally) on method: "
				+method);
	}
	
	
	@AfterThrowing(
			pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing="theExc")
	public void afterThrowingFindAccountAdvice(
			JoinPoint theJoinPoint, Throwable theExc) {
		
		// print out witch method we are advising on
		String method =theJoinPoint.getSignature().toShortString();
		myLogger.info("\n======>>>>> Executing @AfterReturning on method: "
				+method);
		
		// log the exception
		myLogger.info("\n======>>>>> The exception is: "
				+theExc);
	}
	
	@AfterReturning(
			pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning="result")
	public void afterReturningFindAccountsAdvice(
			JoinPoint theJoinPoint, List<Account> result
			) {
		
		// print witch method we are advising on
		String method =theJoinPoint.getSignature().toShortString();
		myLogger.info("\n======>>>>> Executing @AfterReturning on method: "
				+method);
		
		// print out the result of the method call
		myLogger.info("\n======>>>>> result is: " + result);
		
		// let's post-process the data ... let's modify it: :-)
		
		// convert the account names to uppercase
		convertAccountNamesToUpperCase(result);
		
	}
	
	private void convertAccountNamesToUpperCase(List<Account> result) {
		
		// loop through accounts
		for (Account theAccount:result) {
		
		// get the upper case version of names
		String theUpperName=theAccount.getName().toUpperCase();
			
		// update the name on the account
		theAccount.setName(theUpperName);
		
		}
		myLogger.info("\n======>>>>> result is: " + result);
	}

	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		
		myLogger.info("\n=======>>>> Executing @Before advice on method");
		
		// display the method signature 
		MethodSignature methodSig=(MethodSignature) theJoinPoint.getSignature();
		
		myLogger.info("Method: "+methodSig);
		
		// display method arguments
		
		// get args
		Object[] args=theJoinPoint.getArgs();
		
		// loop thru args
		for (Object tempArg:args) {
			
			myLogger.info(tempArg.toString());
			
			if (tempArg instanceof Account) {
				
				Account theAccount=(Account) tempArg;
				myLogger.info("account name: "+ theAccount.getName());
				myLogger.info("account name: "+ theAccount.getLevel());
			}
		}
		
	}
	
	

	
}
