package testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;


public class AddCustomerTest extends TestBase {

	@Test(dataProvider = "getData")
	public void addCustomerTest(String firstName, String lastName, String postCode,String alertText) throws InterruptedException {

		driver.findElement(By.cssSelector(OR.getProperty("addCustBtn"))).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(OR.getProperty("firstName"))).sendKeys(firstName);
		driver.findElement(By.cssSelector(OR.getProperty("lastName"))).sendKeys(lastName);
		driver.findElement(By.cssSelector(OR.getProperty("postCode"))).sendKeys(postCode);
		driver.findElement(By.cssSelector(OR.getProperty("addBtn"))).click();
		
		Alert alert= wait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertTrue(alert.getText().contains(alertText));
		Thread.sleep(3000);
		alert.accept(); 
		Thread.sleep(3000);
	}

	@DataProvider
	public Object[][] getData() {
		String sheetName = "AddCustomerTest";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows-1][cols];

		for (int rowNum = 2; rowNum <= rows; rowNum++) {

			for (int colNum = 0; colNum < cols; colNum++) {

				data[rowNum - 2][colNum] = String.valueOf(excel.getCellData(sheetName, colNum, rowNum));
				
			}

		}
// @purpose: to get the data and count in object data[][]
 /* int count=0;
		System.out.println(data.length);
		for(int i=0; i<data.length;i++)
		{
			for(int j=0; j<data[i].length; j++) {
				System.out.println(data[i][j].toString());
				count++;
			}
		}
		System.out.println("\n\n\ntotal count of data: " + count);
		*/
		
		return data;
	}

}
