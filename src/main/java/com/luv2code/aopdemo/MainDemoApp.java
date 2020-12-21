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
		
		// call the business method
		Account theAccount=new Account();
		theAccountDAO.addAccount(theAccount, true);
		theAccountDAO.doWork();
		
		// call the accoutdao getter/setter methods
		
		theAccountDAO.setName("foobar");
		theAccountDAO.setServiceCode("silver");
		
		String name =theAccountDAO.getName();
		String code =theAccountDAO.getServiceCode();
		
		// call the method business method
		theMembershiDAO.addSillyMember();
		theMembershiDAO.goToSleep();
		

				
		// close the context
		context.close();
		
	}

}
