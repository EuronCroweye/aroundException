package com.luv2code.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {

		// read spring config java class
		AnnotationConfigApplicationContext context = new
				AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the bean from spring context
		AccountDAO theAccountDAO=context.getBean("accountDAO", AccountDAO.class);
		
		// get membership bean from spring container
		MembershipDAO theMembershiDAO =
				context.getBean("membershipDAO", MembershipDAO.class);
		
		// call the method busines method
		theMembershiDAO.addAccount();
		
		// call the business method
		theAccountDAO.addAccount();
				
		// close the context
		context.close();
		
	}

}
