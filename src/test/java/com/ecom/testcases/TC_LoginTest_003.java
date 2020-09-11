package com.ecom.testcases;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ecom.pageObjects.LoginPage;
import com.ecom.util.XLUtils;

public class TC_LoginTest_003 extends BaseClass {
	LoginPage loginPageTest;


	@Test(description = "Login DDT scenario", dataProvider="login credentials")
	public void loginDDTTest(String uname, String pwd) {
		loginPageTest = new LoginPage(driver);
		//each test step is an action method in Pageclass
		loginPageTest.signIn();
		loginPageTest.setID(uname);//pass user id
		loginPageTest.setPwd(pwd);//pass pwd
		logger.info("credentials passed");
		loginPageTest.loginSubmit();//click on signin button

		//validate the user is able to login successfully
		if(driver.getTitle().contains("My account")){	
			logger.info("Login Successful. Test case passed");
			Assert.assertTrue(true);
			loginPageTest.signout();
		}
		else {	
			captureScreen(driver, new Object() {}.getClass().getEnclosingMethod().getName()+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
			logger.warn("Login testcase failed");
			Assert.assertTrue(false);
		}
	}

	@DataProvider(name="login credentials")
	String[][] getCredentials() throws IOException {
		String filePath  = System.getProperty("user.dir")+"/src/test/java/com/ecom/testData/LoginData.xlsx";
		String loginData[][];
		int rowCount = XLUtils.getRowCount(filePath, "Sheet1");
		int cellCount = XLUtils.getCellCount(filePath, "Sheet1", 1);

		loginData = new String[rowCount][cellCount];

		for(int i=1; i<=rowCount; i++)
			for(int j=0;j<cellCount; ++j)
				loginData[i-1][j] = XLUtils.getCellData(filePath, "Sheet1", i, j);

		return loginData;
	}
}
