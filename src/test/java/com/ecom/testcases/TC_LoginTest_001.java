package com.ecom.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ecom.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
	LoginPage loginPageTest;
	
	//Test case to validate login page is working as expected. Positive scenario
	@Test(description = "Login PositiveTesting scenario")
	public void loginPositiveTest() {
		loginPageTest = new LoginPage(driver);
		//each test step is an action method in Pageclass
		loginPageTest.signIn();
		loginPageTest.setID(reader.getID());//pass user id
		loginPageTest.setPwd(reader.getPwd());//pass pwd
		logger.info("credentials passed");
		loginPageTest.loginSubmit();//click on signin button
		
		//validate the user is able to login successfully
		if(driver.getTitle().contains("My account")){	
			logger.info("Login Successful. Test case passed");
			Assert.assertTrue(true);
			//loginPageTest.signout();
		}
		else {	
			captureScreen(driver, new Object() {}.getClass().getEnclosingMethod().getName());
			logger.warn("Login testcase failed");
			Assert.assertTrue(false);
		}
	}
}
