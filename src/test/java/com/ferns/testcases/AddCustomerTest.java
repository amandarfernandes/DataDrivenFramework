package com.ferns.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ferns.base.TestBase;

public class AddCustomerTest extends TestBase {

	@Test(dataProvider = "getCustomerData")
	public void addCustomer(String fname, String lname, String code, String alertMsg) {

		driver.findElement(By.cssSelector(or.getProperty("addCustomerBtn"))).click();
		driver.findElement(By.cssSelector(or.getProperty("firstName"))).sendKeys(fname);
		driver.findElement(By.cssSelector(or.getProperty("lastName"))).sendKeys(lname);
		driver.findElement(By.cssSelector(or.getProperty("postCode"))).sendKeys(code);
		driver.findElement(By.cssSelector(or.getProperty("addButton"))).click();

		log.debug("adding customer with name " + fname + " " + lname + " " + code);
		Alert alert = wd.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert();
		String alertText = alert.getText();
		alert.accept();
		System.out.println(alertText);
		Assert.assertTrue(alertText.contains(alertMsg+"!!!!"),"Alert Message did not match!");
		log.debug("Customer was added. Received confirmation alert " + alertMsg);	
	}

	@DataProvider
	public Object[][] getCustomerData() {

		String sheetName = "AddCustomerTest";
		xl.setSheet(sheetName);
		int rows = xl.getRowsCount() - 1;
		int cols = xl.getColumnsCount();
		System.out.println("Rows " + rows);
		System.out.println("Columns " + cols);
		Object[][] customers = new Object[rows][cols];
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++) {
				customers[i][j] = xl.getCellData(i + 1, j);

			}
		return customers;
	}
}
