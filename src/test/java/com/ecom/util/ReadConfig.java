package com.ecom.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfig {
	public Properties prop;
	private InputStream inStream;
	private String configPath;
	
	public ReadConfig() throws FileNotFoundException, IOException {
		
		configPath = System.getProperty("user.dir")+"/config/config.properties";
		prop = new Properties();
		
		inStream = new FileInputStream(new File(configPath));
		prop.load(inStream);
	}
	
	//As of Selenium 4, Drivers are not necessary to be setup. But this might be necessary in case of Selenium 3
	/*
	 * public String getDriver() { return prop.getProperty("browser.driver"); }
	 * 
	 * public String getDriverPath() { return
	 * prop.getProperty("browser.driverpath"); }
	 */
	public String getDriver() {
		return prop.getProperty("browser.driver");
	}
	
	public String getDriverPath() {
		return prop.getProperty("browser.driverpath");
	}
	
	public String getUrl() {
		return prop.getProperty("url");
	}
	
	public String getID() {
		return prop.getProperty("user.id");
	}
	
	public String getPwd() {
		return prop.getProperty("pwd");
	}
}
