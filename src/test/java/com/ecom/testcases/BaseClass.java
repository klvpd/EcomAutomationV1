package com.ecom.testcases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.ecom.util.ReadConfig;

public class BaseClass {
	public static WebDriver driver;
	public ReadConfig reader;
	public Logger logger;
	private String browser;

	/*
	 * @Parameters("browser")
	 * 
	 * @BeforeClass public void setup(String browser) throws FileNotFoundException,
	 * IOException { this.browser=browser; reader = new ReadConfig();
	 * 
	 * logger = Logger.getLogger("ecomTesting");
	 * PropertyConfigurator.configure("log4j.properties");
	 * 
	 * switch(browser.toLowerCase()) { case "firefox":
	 * System.setProperty(reader.getDriver(), reader.getDriverPath());
	 * //WebDriverManager.firefoxdriver().setup(); driver = new FirefoxDriver();
	 * break; case "chrome"://future case. Please disable below code and use
	 * 
	 * WebDriverManager.chromedriver().setup();
	 * System.setProperty(reader.getDriver(), reader.getDriverPath()); driver = new
	 * ChromeDriver();
	 * 
	 * break; case "ie": //WebDriverManager.iedriver().setup();
	 * System.setProperty(reader.getDriver(), reader.getDriverPath()); driver = new
	 * InternetExplorerDriver(); break; case "edge":
	 * //WebDriverManager.edgedriver().setup();
	 * System.setProperty(reader.getDriver(), reader.getDriverPath()); driver = new
	 * EdgeDriver(); break; default: throw new
	 * InvalidArgumentException("Invalid Browser details"); }
	 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 * driver.manage().window().maximize(); driver.get(reader.getUrl()); }
	 */

	@Parameters({"browser", "webdriver", "driverpath"})
	@BeforeClass
	public void setup(String browser, String webdriver, String driverpath) throws FileNotFoundException, IOException {
		this.browser=browser;
		reader = new ReadConfig();

		logger = Logger.getLogger("ecomTesting");
		PropertyConfigurator.configure("log4j.properties");

		switch(browser.toLowerCase()) {
		case "firefox":
			System.setProperty(webdriver, driverpath);
			//WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "chrome"://future case. Please disable below code and use
			/*
			 *WebDriverManager.chromedriver().setup();//not this one until selenium 4 stabilizes. 
			 *System.setProperty(webdriver, driverpath);
			 *driver = new ChromeDriver();
			 */
			break;
		case "ie":
			//WebDriverManager.iedriver().setup();
			System.setProperty(webdriver, driverpath);
			driver = new InternetExplorerDriver();
			break;
		case "edge":
			//WebDriverManager.edgedriver().setup();
			System.setProperty(webdriver, driverpath);
			driver = new EdgeDriver();
			break;
		default:
			throw new InvalidArgumentException("Invalid Browser details");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(reader.getUrl());
	}
	
	
	
	// capture failed testcase screenshot for reference.
	public void captureScreen(WebDriver driver, String testname) {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File targetFile = new File(System.getProperty("user.dir") + "/Screenshots/" + testname + ".png");
		try {
			FileUtils.copyFile(srcFile, targetFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void teardown() throws IOException {
		driver.close();
	}

	// program to close all running driver server resources in the background. These
	// resources consume memory
	@AfterSuite
	public void teardownSuite() throws IOException {
		// driver.quit();
		switch (browser.toLowerCase()) {
		case "firefox":
			Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
			break;
		case "chrome":// future case. Please disable below code and use
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			break;
		case "ie":
			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
			break;
		case "edge":
			Runtime.getRuntime().exec("taskkill /F /IM msedgedriver.exe");
			break;
		default:
			break;
		}
	}
}
