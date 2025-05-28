package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseTest;

public class ExtentReport_ListenerClass implements ITestListener{
	
	public ExtentSparkReporter esparkReporter;
	public ExtentReports ereport;
	public ExtentTest etest;

	public void onStart(ITestContext context) {
		
		 /* esparkReporter has two things	- it will load the file
		  									- it interacts only with UI elements of the report*/
		
		 //dynamic time fetching
		 String dynamicTime = (new SimpleDateFormat("yyyy.MM.dd - HH.mm.ss")).format(new Date());
		 String filePath = System.getProperty("user.dir") + "\\extent_reports\\" + "openCart " + dynamicTime + ".html" ;
		 
		 esparkReporter = new ExtentSparkReporter(filePath);
		 esparkReporter.config().setDocumentTitle("Automation Test Report");
		 esparkReporter.config().setReportName("Open Cart Testing");
		 esparkReporter.config().setTheme(Theme.DARK);
		 
		 
		 /* ereport has two things	- it will attach the esparkReport, on top it 
		  							- it will fetches the common information to the reports*/
		
		 ereport = new ExtentReports();
		 ereport.attachReporter(esparkReporter);
		 ereport.setSystemInfo("Application Name", "OpenCart");
		 ereport.setSystemInfo("Environment", "QA");
		 ereport.setSystemInfo("User Name", System.getProperty("user.dir"));
		 
		 //dynamic fetching informattion
		 String operatingSystem = context.getCurrentXmlTest().getParameter("os");
		 ereport.setSystemInfo("Operating System", operatingSystem);
		 
		 String browserMode = context.getCurrentXmlTest().getParameter("browser");
		 ereport.setSystemInfo("Browser Mode", browserMode);
		 
		 List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		 if(!includedGroups.isEmpty())
			 ereport.setSystemInfo("Grouping Name", includedGroups.toString());
 
	}
	
	public void onTestSuccess(ITestResult result) {
		
		String testClass = result.getTestClass().getName();
		etest = ereport.createTest(testClass.replace(".", " - ")+ " - " + result.getMethod().getMethodName());
	    
		/* etest has two things	  - it will create a testcase module under ereport
		 							pass the test case as fail, pass or skip along with functionalites*/
		
		//etest = ereport.createTest(result.getTestClass().getName() + " - " + result.getMethod().getMethodName());
		etest.assignCategory(result.getMethod().getGroups());
		etest.log(Status.PASS, "Successfully executed at " + result.getName());
		
	}
	
	public void onTestFailure(ITestResult result) {
		
		String testClass = result.getTestClass().getName();
		etest = ereport.createTest(testClass.replace(".", " - ")+ " - " + result.getMethod().getMethodName());
		
		//etest = ereport.createTest(result.getTestClass().getName() + " - " + result.getMethod().getMethodName());
		etest.assignCategory(result.getMethod().getGroups());
		etest.log(Status.FAIL, "Failed executed at " + result.getName());
		etest.log(Status.INFO, "Exception : " +result.getThrowable().getMessage());
		
		/*caputuring the screenshot when fails	- create a getScreenShot class in BaseTest 
		 										  takes the screenshot using driver instance and TakeScreenShot Interface
		 										  in BaseTest the driverInstance declaration must be in public*/
		
		try {
			String filePath = (new BaseTest()).getScreenShot(result.getName());
			etest.addScreenCaptureFromPath(filePath);
		}
		catch(Exception e) {
			e.getMessage();
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		
		String testClass = result.getTestClass().getName();
		etest = ereport.createTest(testClass.replace(".", " - ")+ " - " + result.getMethod().getMethodName());
	    
		//etest = ereport.createTest(result.getTestClass().getName() + " - " + result.getMethod().getMethodName());
		etest.assignCategory(result.getMethod().getGroups());
		etest.log(Status.SKIP, "Skipped the execution at " + result.getName());
		etest.log(Status.INFO, "Exception : " + result.getThrowable().getMessage());
	  }
	
	public void onFinish(ITestContext context) {
	    ereport.flush();
	  }
}
