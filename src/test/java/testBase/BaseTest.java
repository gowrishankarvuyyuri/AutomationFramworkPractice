package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {
	
	public static WebDriver driver;
	public Logger log;
	public FileReader fr;
	public Properties prop;
	
	@BeforeClass (groups = {"sanity", "regression","master"})
	@Parameters({"os", "browser"})
	public void setUp(String os, String browser) throws IOException {
		
		//loading prop file
		fr = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
		prop = new Properties();
		prop.load(fr);
		
		//logger class
		log = LogManager.getLogger(this.getClass());	
		log.info("Initializing the Driver setUp");
		
		//Desired/parallel/cross with paramters
		switch(browser.toLowerCase()) {
							case "chrome" : driver = new ChromeDriver();	break;
							case "edge"	  :	driver = new EdgeDriver();		break;
							case "firefox":	driver = new FirefoxDriver();	break;
							default		  : System.out.println("browser not found");  return;
						  }
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		log.info("Launching Browser");
		driver.get(prop.getProperty("appURL"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@AfterClass (groups = {"sanity", "regression", "master"})
	public void tearDown() {
		log.info("Closing the Browser");
		driver.quit();
	}
	
	public String randomString() {
		return RandomStringUtils.randomAlphabetic(7);
	}
	
	public String randomNumber() {
		return RandomStringUtils.randomNumeric(10);
	}
	
	public String randomAphlaNumber() {
		return RandomStringUtils.randomAlphanumeric(4) + 
				RandomStringUtils.randomAlphanumeric(4) + 
				"@";
	}
	
	//taking screenshot of the WebPage whenever onTestFailure listener class invokes
	public String getScreenShot(String fileName) {
		
		String dynamicTime = (new SimpleDateFormat("yyyy.MM.dd - HH.mm.ss")).format(new Date());
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + fileName + " " + dynamicTime + ".png";
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}
