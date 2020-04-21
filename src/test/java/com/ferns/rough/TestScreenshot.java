package com.ferns.rough;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestScreenshot {

	public static void main(String[] args) {
		String resourcePath=System.getProperty("user.dir")+"\\src\\test\\resources";
		System.setProperty("webdriver.chrome.driver",resourcePath+"\\executables\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File("c:\\selenium-work\\test.png"));
			System.out.println("Screen shot taken");
		} catch (Exception e) {
			System.out.println("No screenshot taken");
			e.printStackTrace();
		}

	}

}
