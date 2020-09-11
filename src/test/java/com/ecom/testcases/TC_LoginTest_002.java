package com.ecom.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ecom.pageObjects.LoginPage;

public class TC_LoginTest_002 extends BaseClass {
	LoginPage loginPageTest;
	
	//Test case to validate login page is working as expected
	@Test(description = "Login Negative Testing scenario")
	public void loginNegativeTest() {
		loginPageTest = new LoginPage(driver);
		//each test step is an action method in Pageclass
		loginPageTest.signIn();
		loginPageTest.setID(reader.getID());//pass user id
		loginPageTest.setPwd("chandu+8690");//pass pwd
		logger.info("credentials passed");
		loginPageTest.loginSubmit();//click on signin button
		
		//validate the user is not able to login
		if(loginPageTest.loginMsg().contains("Authentication failed")){	
			logger.info("Login negative Test case passed");
			Assert.assertTrue(true);
		}
		else {	
			captureScreen(driver, new Object() {}.getClass().getEnclosingMethod().getName());
			logger.warn("Login successful. testcase failed");
			Assert.assertTrue(false);
			loginPageTest.signout();
		}
	}
}
