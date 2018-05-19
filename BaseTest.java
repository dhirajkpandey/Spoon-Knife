package testscripts;

import java.io.File;
import java.util.Date;

//mport org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;

import POM.LoginPagePOM;
import generic.LoginLogout;
import generic.Screenshot;
import generic.WaitStatement;

public class BaseTest {
	
	//protected WaitStatement wait;
		public WebDriver driver;
		public static ExtentHtmlReporter htmlReporter;
		public static ExtentReports extentReports;
		public static ExtentTest extentTest;
		//public static Logger log;
		//protected String firmAdminURL ="https://52.165.133.5/iconnectgatewayqa";
		protected String gatewayURL ="https://52.176.106.105/IConnectGatewayv1dev";
		//protected String provanaPortalURL ="https://provanaiconnect-appqa.azurewebsites.net/"; 
		
		protected String provanaPortalURL ="https://provanaiconnect-appdev.azurewebsites.net/";
		
		/**
		 * Defining BeforeMethod  contents
		 * 
		 * It will open the respective browser and navigate to the defined url
		 * 
		 * @author Dhirajkumar.pandey
		 */
		
		
		
		@BeforeClass
		public void fnpreCondition() 
		{
			//driver = new FirefoxDriver();
			System.setProperty("webdriver.chrome.driver", ".\\exefiles\\chromedriver.exe");
			//driver=new ChromeDriver();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--start-maximized");
			driver = new ChromeDriver(chromeOptions);
			
			//driver.get(firmAdminURL);
			driver.get(gatewayURL);
			//driver.manage().window().maximize();
			
			
		}
		/*@Parameters({"browser"}) 
		public void fnpreCondition(String browser) 
		{
			
	        //Launching the chrome
			if(browser.equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", ".\\exefiles\\chromedriver.exe");
				driver=new ChromeDriver();	
				//log = Logger.getLogger("BaseTest");
				//log.info("browser launched");
			}
			
			//Launching the IE
			else
				if(browser.equals("InternetExplorer"))
				{
					
					System.setProperty("webdriver.ie.driver", ".\\exefiles\\IEDriverServer.exe");
					driver= new InternetExplorerDriver();
					
				}
			
			// launching the Firefox
				else
				{
		
					driver = new FirefoxDriver();
			    }
			
			
	        
			//driver.get(firmAdminURL);

			driver.manage().window().maximize();
				
			WaitStatement.fnImplicitWait(driver, 20);
		}*/
		
		
		
		
		
		/**
		 * Defining AfterClass contents
		 *  
		 * It will close the driver
		 * 
		 * @author Dhirajkumar.pandey
		 * @throws InterruptedException 
		 */
		
		@AfterClass
		public void fnpostCondition() throws InterruptedException
		{
			
			driver.close();
			/*LoginPagePOM loginPagePOM = new LoginPagePOM(driver);
			loginPagePOM.logout(driver);*/
		}
		
		
		
		
		
		
		/**
		 * Defining AfterMethod contents
		 *  
		 * It will take screenshot if the test case got failed
		 * 
		 * @author Dhirajkumar.pandey
		 * 
		 */
		
		
		/*@AfterMethod
		@Parameters({"browser"})
		public void fnAfterMethodCondition(ITestResult t,String browser)      
		{
			String testname = t.getMethod().getMethodName();
			try
			{
				if(t.isSuccess())
				{
					extentTest.log(Status.PASS, MarkupHelper.createLabel(testname+"Test Case PASS", ExtentColor.GREEN));
					
				}
				else
				{
				
				Screenshot s = new Screenshot();
				s.fnGetScreenshot(driver,testname);
				
				
				//extent report functionality when the test case fails
				extentTest.log(Status.FAIL, MarkupHelper.createLabel(testname+"Test Case FAILED due to below reason", ExtentColor.RED));
				extentTest.fail(t.getThrowable());   // it will print the stack trace due to which the test case failed
				}
				
			
			}
			catch(Exception e)
			{
				
			}
		}*/
		
		
		
		
		/**
		 * Defining BeforeSuite contents
		 *  
		 * It will invoke the extent report components
		 * 
		 * @author Dhirajkumar.pandey
		 * 
		 */
		
		
		@BeforeSuite
		public void extentReportInvoke()
		{
			
			htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/extentreport/Firm Admin Portal - Automation Report"+new Date().getTime()+".html");
			
			extentReports = new ExtentReports();
			extentReports.attachReporter(htmlReporter);
			
			
			extentReports.setSystemInfo("HostName", "ATG - Provana India");
			extentReports.setSystemInfo("Environment", "QA - Regression");
			extentReports.setSystemInfo("Tester", "Dhiraj");
			
			
			htmlReporter.config().setDocumentTitle("Firm Portal Automation");
			htmlReporter.config().setReportName("Automation Report - Firm Admin Portal");
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			//htmlReporter.loadXMLConfig(new File("D:\\Eclipse Workspace\\Log4jInFrmwrk\\extent-config.xml"));
			
			
		}
		
		
		
		
		/**
		 * Defining AfterSuite contents
		 *  
		 * It will flush the extent report 
		 * 
		 * @author Dhirajkumar.pandey
		 * 
		 */
		
		
		@AfterSuite
		public void teardown()
		{
			extentReports.flush();
		}
	
	

}
