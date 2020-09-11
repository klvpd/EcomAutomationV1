package com.ecom.testcases;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.ecom.pageObjects.LoginPage;

//To validate all the links are working as expected on Index page after login
public class TC_HomePageLinkValidation_004 extends BaseClass{

	@Test(priority=0)
	public void login() {
		LoginPage login = new LoginPage(driver);
		login.signIn();
		login.setID(reader.getID());
		login.setPwd(reader.getPwd());
		login.loginSubmit();
	}

	@Test(description ="Validate links on Index page after login", priority=1)
	public void linkValidate() {


		List<WebElement> linkList = new ArrayList<WebElement>();
		linkList = driver.findElements(By.tagName("a"));

		logger.info("Total number of links on Index page are : "+linkList.size());
		logger.info("Validating the links now : ");

		for(WebElement link : linkList) {
			String url = link.getAttribute("href");

			if(url==null || url.isEmpty()) {
				logger.warn("Url is not configured for this link : "+link.getText());
				continue;
			}
			//Skip other domain links
			else if(!url.startsWith("http://automationpractice.com/")) {
				logger.info("Skipping this link from another domain : "+link.getText());
			}
			else {
				try {
					HttpURLConnection con = (HttpURLConnection)(new URL(url).openConnection());
					con.setRequestMethod("GET");
					con.connect();
					int responseCode = con.getResponseCode();
					if(responseCode>=400) {
						logger.warn(link.getText()+" is a broken link");
						File src = link.getScreenshotAs(OutputType.FILE);
						File target = new File(System.getProperty("user.dir") + "/Screenshots/" + link.getText() + ".png");
						try {
							FileUtils.copyFile(src, target);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else
						logger.info(link.getText()+" is a valid link");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}