package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;

public class BankManagerLoginTest extends TestBase{
	
	@Test
	public void loginAsBankManager() throws InterruptedException {
		
		log.debug("Inside Login test");
		String button=OR.getProperty("bmlBtn");
		driver.findElement(By.xpath(button)).click();
		Thread.sleep(3000);
		
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn"))));
		log.debug("Login successfully Executed");
		
	}
	

}
