package com.ferns.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import come.ferns.utilities.ExcelReader;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class TestBase {

	 /*
	 * Setup for
	 * WebDriver
	 * Properties
	 * Logs
	 * Excel
	 * ExtentReports
	 * DB
	 * Mail
	 */
	
	public static WebDriver driver = null;
	private static String resourcePath=System.getProperty("user.dir")+"\\src\\test\\resources";
	public static Properties config = new Properties();
	public static Properties or = new Properties();
	public static FileInputStream fis = null;
	public static Logger log = LogManager.getLogger(TestBase.class.getName());
	public static ExcelReader xl = new ExcelReader(resourcePath+"\\excel\\TestData.xlsx");
	public static WebDriverWait wd = null;
	@BeforeSuite
	public void setUp() {
		
		if (driver == null) {
			try {
				fis = new FileInputStream(resourcePath+"\\properties\\Config.properties");
				config.load(fis);
				log.debug("Config File Loaded!");
				
				fis = new FileInputStream(resourcePath+"\\properties\\OR.properties");
				or.load(fis);
				log.debug("Object Repo loaded!");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (config.getProperty("browser").equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",resourcePath+"\\executables\\chromedriver.exe");
			driver = new ChromeDriver();
			log.debug("Chrome Launched!");
		} else if (config.getProperty("browser").equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",resourcePath+"\\executables\\geckodriver.exe");
			driver = new FirefoxDriver();
			log.debug("Firefox Launched!");
		} else if (config.getProperty("browser").equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.gecko.driver",resourcePath+"\\executables\\IEDriver.exe");
			driver = new InternetExplorerDriver();
			log.debug("IE Launched!");
		}
		
		driver.get(config.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitWait")), TimeUnit.SECONDS);
		log.info("Loaded website at "+config.getProperty("url"));	
		wd = new WebDriverWait(driver,10);
	}
	
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			log.debug(by.toString()+" is present!");
			return true;
		} catch (NoSuchElementException e) {
			log.debug(by.toString()+" is not present!");
			return false;
		}
	}
	
	@AfterSuite
	public void tearDown() {
		if (driver != null) {	
			driver.quit();
			log.debug("Quit the browser!");
		}
	}
}
