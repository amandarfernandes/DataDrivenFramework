package com.ferns.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ferns.base.TestBase;

public class BankManagerLoginTest extends TestBase {

	@Test
	public void loginAsManager() {
	
		log.debug(or.getProperty("managerLoginBtn"));
		driver.findElement(By.cssSelector(or.getProperty("managerLoginBtn"))).click();
		log.debug("logged in as bank manager");
		
		Assert.assertTrue(isElementPresent(By.cssSelector(or.getProperty("addCustomerBtn"))));
		log.debug("Navigated to Bank Manager Storefront!");
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
