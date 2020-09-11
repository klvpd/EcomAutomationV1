package com.ecom.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver localDriver = null;
	
	public LoginPage(WebDriver remoteDriver) {
		localDriver = remoteDriver;
		PageFactory.initElements(localDriver, this);
	}

	@FindBy(how = How.XPATH, using = "/html/body/div/div[1]/header/div[2]/div/div/nav/div[1]/a")
	@CacheLookup
	WebElement signIn;
	
	@FindBy(how = How.XPATH, using ="//*[@id=\"email\"]")
	@CacheLookup
	WebElement email;
	
	@FindBy(how = How.XPATH, using ="//*[@id=\"passwd\"]")
	@CacheLookup
	WebElement pwd;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"SubmitLogin\"]")
	@CacheLookup
	WebElement signInBtn;
	
	@FindBy(how = How.XPATH, using="/html/body/div/div[1]/header/div[2]/div/div/nav/div[2]/a")
	@CacheLookup
	WebElement signout;
	
	@FindBy(how = How.XPATH, using="/html/body/div/div[2]/div/div[3]/div/div[1]/ol/li")
	@CacheLookup
	WebElement loginErrorMsg;
	
	//action methods
	public void signIn() {
		signIn.click();
	}
	
	public void setID(String uname) {
		email.clear();
		email.sendKeys(uname);
	}
	
	public void setPwd(String pwd) {
		this.pwd.clear();
		this.pwd.sendKeys(pwd);
	}
	
	public void loginSubmit() {
		signInBtn.click();
	}
	
	public void signout() {
		signout.click();
	}
	
	public String loginMsg() {
		return loginErrorMsg.getText();
	}
}
