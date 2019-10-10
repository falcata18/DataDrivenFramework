package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utilites.ExcelReader;

public class TestBase {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log =Logger.getLogger("devpinoyLogger");
    public static ExcelReader excel=new ExcelReader (System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\testdata.xlsx");
    public static WebDriverWait wait;
	@BeforeSuite 
	public void setUp() throws IOException {
		
		if(driver==null) {
			
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.Properties");
			config.load(fis);
		    log.debug("Config File Loaded !!!");
			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
			OR.load(fis);
			log.debug("OR File Loaded !!!");
		}
		
		if (config.getProperty("browser").equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "\\src\\test\\resources\\executable\\chromedriver.exe ");
			driver = new ChromeDriver();
			log.debug("chrome Launch !!!");
			
		} else if (config.getProperty("browser").equalsIgnoreCase("ie")) {
			driver = new InternetExplorerDriver();
			log.debug("ie Launch !!!");
			
		} else if (config.getProperty("browser").equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			log.debug("firefox Launch !!!");
		}

		
        driver.get(config.getProperty("testsiteurl"));
        log.debug("Navigated to :"+config.getProperty("testsiteurl"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),TimeUnit.SECONDS);
        
        wait = new WebDriverWait(driver,5);
	} 


	public  boolean isElementPresent(By by) {
		
		try {
			driver.findElement(by);
			return true;
		}catch(NoSuchElementException e ) {
			return false;
		}
		
	}
	
	
    @AfterSuite
	public void tearDown() {

		  if(driver!=null) {
				driver.quit();
		         }
		  log.debug("Execution completed");
	}

}
