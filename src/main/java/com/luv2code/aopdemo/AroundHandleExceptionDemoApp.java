package com.luv2code.aopdemo;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
import com.luv2code.aopdemo.service.TrafficFortuneService;

public class AroundHandleExceptionDemoApp {

	
	public static void main(String[] args) {

		Logger myLogger = 
				Logger.getLogger(AroundHandleExceptionDemoApp.class.getName());
		
		// read spring config java class
		AnnotationConfigApplicationContext context = new
				AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the bean from spring context
		TrafficFortuneService theFortuneService=
				context.getBean("trafficFortuneService", TrafficFortuneService.class);		

	myLogger.info("\nMain Program: AroundDemoApp");
		
	myLogger.info("Calling getFortune");
	
	boolean tripWhire = true;
	String data = theFortuneService.getFortune(tripWhire);
	
	myLogger.info("\nMy fortune is: "+ data);
	
	myLogger.info("Finished");
		// close the context
		context.close();
		
	}

}
