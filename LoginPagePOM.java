package POM;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import generic.WaitStatement;
import testscripts.BaseTest;

public class LoginPagePOM extends BaseTest{
	
	@FindBy(xpath="//h2[text()='Log in.']")
	private WebElement Header_LoginPage;
	
	@FindBy(id="UserName")
	private WebElement UserNameTextbox_LoginPage;
	
	@FindBy(id="Password")
	private WebElement PasswordTextbox_LoginPage;
	
	@FindBy(xpath="//input[@value='Log in']")
	private WebElement LoginButton_LoginPage;
	
	@FindBy(id="Password-error")
	private WebElement PasswordError_LoginPage;
	
	@FindBy(id="UserName-error")
	private WebElement UserNameError_LoginPage;
	
	@FindBy(linkText="Forgot your password?")
	private WebElement ForgetPasswordLink_LoginPage;
	
	@FindBy(xpath="//h4[text()='Forgot your password?.']")
	private WebElement Header_ForgotpasswordPage;
	
	@FindBy(id="Username")
	private WebElement UserNameTextbox_ForgotpasswordPage;
	
	@FindBy(id="Email")
	private WebElement EmailTextbox_ForgotpasswordPage;
	
	@FindBy(xpath="//input[@value='Send Password']")
	private WebElement SendPasswordButton_ForgotpasswordPage;
	
	@FindBy(id="Username-error")
	private WebElement UsernameError_ForgotpasswordPage;
	
	@FindBy(id="Email-error")
	private WebElement EmailError_ForgotpasswordPage;
	
	@FindBy(xpath="//p[contains(text(),'Link to reset')]")
	private WebElement EmailConfirmationMessage_ForgotpasswordPage;
	
	
	@FindBy(xpath="//form[@id='logoutForm']/ul/li/a")
	private WebElement UserDropDownMenu_Homepage;
	
	@FindBy(linkText="Log off")
	private WebElement LogoffLink_Homepage;
	
	@FindBy(xpath="//span[text()='Notification']")
	private WebElement HomePageHeader_HomePage;
	
	@FindBy(xpath="//span[text()='Notification']")
	private WebElement HomePageHeader_AgentHomePage;
	
	
	
	public LoginPagePOM(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	public void verifyLoginPageHeader(WebDriver driver)
	{
		WaitStatement.fnExplicitWait(driver, 30, Header_LoginPage);
		Assert.assertEquals(Header_LoginPage.getText(), "Log in.");
		extentTest.log(Status.INFO,"User is on Login page");
		//log.info("User is on Login page");
	}
	
	public void loginErrorValidationTest(WebDriver driver)
	{
		WaitStatement.fnExplicitWait(driver, 30, Header_LoginPage);
		Assert.assertEquals(Header_LoginPage.getText(), "Log in.");
		extentTest.log(Status.INFO,"User is on Login page");
		//log.info("User is on Login page");
		
		LoginButton_LoginPage.click();
		extentTest.log(Status.INFO,"Clicked on login button");
		//log.info("Clicked on login button");
		
		WaitStatement.fnExplicitWait(driver, 30, UserNameError_LoginPage);
		Assert.assertEquals(UserNameError_LoginPage.getText(), "The User Name field is required.");
		extentTest.log(Status.INFO,"Username Error message is correct");
		//log.info("Username Error message is correct");
		
		Assert.assertEquals(PasswordError_LoginPage.getText(), "The Password field is required.");
		extentTest.log(Status.INFO,"Password Error message is correct");
		//log.info("Password Error message is correct");
		
		
	}
	
	public void login(WebDriver driver, String aUserName, String aPassword, String aUserRole)
	{
		UserNameTextbox_LoginPage.sendKeys(aUserName);
		extentTest.log(Status.INFO,"Username filled in textbox");
		//log.info("Username filled in textbox");
		
		PasswordTextbox_LoginPage.sendKeys(aPassword);
		extentTest.log(Status.INFO,"Password filled in textbox");
		//log.info("Password filled in textbox");
		
		LoginButton_LoginPage.click();
		extentTest.log(Status.INFO,"Clicked on login button");
		//log.info("Clicked on login button");
		
		if(aUserRole.equalsIgnoreCase("gateway admin") || aUserRole.equalsIgnoreCase("firm admin"))
		{
			WaitStatement.fnExplicitWait(driver, 30, HomePageHeader_HomePage);
			Assert.assertEquals(HomePageHeader_HomePage.getText(), "Notification");
			extentTest.log(Status.INFO,"User is on home page");
			//log.info("User is on home page");
			extentTest.log(Status.INFO,"Its showing Update Company Profile page by default for "+aUserRole);
			//log.info("Its showing Update Company Profile page by default for "+aUserRole);
		}
		else
			if(aUserRole.equalsIgnoreCase("Agent"))
			{
				WaitStatement.fnExplicitWait(driver, 30, HomePageHeader_AgentHomePage);
				Assert.assertEquals(HomePageHeader_AgentHomePage.getText(), "Notification");
				extentTest.log(Status.INFO,"User is on home page");
				//log.info("User is on home page");
				extentTest.log(Status.INFO,"Its showing messages page by default for "+aUserRole);
				//log.info("Its showing messages page by default for "+aUserRole);
			}
			else
			{
				WaitStatement.fnExplicitWait(driver, 30, driver.findElement(By.xpath("//th[text()='Firm Name']")));
				Assert.assertEquals(driver.findElement(By.xpath("//th[text()='Firm Name']")).getText(), "Firm Name");
				extentTest.log(Status.INFO,"User is on home page of provana portal");
				//log.info("User is on home page of provana portal");
			}

		
	}
	
	
	public void logout(WebDriver driver) throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		/*WaitStatement.fnExplicitWait(driver, 30, LogoffLink_Homepage);
		Assert.assertEquals(LogoffLink_Homepage.getText(), "Log off");
		extentTest.log(Status.INFO,"User is on the home page");*/
		//log.info("User is on the home page");
		
		UserDropDownMenu_Homepage.click();
		Thread.sleep(100);
		LogoffLink_Homepage.click();
		extentTest.log(Status.INFO,"Clicked on log off");
		//log.info("Clicked on log off");
		
		WaitStatement.fnExplicitWait(driver, 30, Header_LoginPage);
		Assert.assertEquals(Header_LoginPage.getText(), "Log in.");
		extentTest.log(Status.INFO,"User is on Login page");
		//log.info("User is on Login page");
	}
	
	
	public void forgotPasswordErrorValidation(WebDriver driver)
	{
		WaitStatement.fnExplicitWait(driver, 30, Header_LoginPage);
		Assert.assertEquals(Header_LoginPage.getText(), "Log in.");
		extentTest.log(Status.INFO,"User is on the login page");
		//log.info("User is on the login page");
		
		ForgetPasswordLink_LoginPage.click();
		extentTest.log(Status.INFO,"Clicked on forgot password link");
		//log.info("Clicked on forgot password link");
		
		WaitStatement.fnExplicitWait(driver, 30, Header_ForgotpasswordPage);
		Assert.assertEquals(Header_ForgotpasswordPage.getText(), "Forgot your password?.");
		extentTest.log(Status.INFO,"User is on forgot password page");
		//log.info("User is on forgot password page");
		
		SendPasswordButton_ForgotpasswordPage.click();
		extentTest.log(Status.INFO,"Clicked on send password button");
		//log.info("Clicked on send password button");
		
		WaitStatement.fnExplicitWait(driver, 30, UsernameError_ForgotpasswordPage);
		Assert.assertEquals(UsernameError_ForgotpasswordPage.getText(), "The Username field is required.");
		extentTest.log(Status.INFO,"Username Error message is correct");
		//log.info("Username Error message is correct");
		
		Assert.assertEquals(EmailError_ForgotpasswordPage.getText(), "The Email field is required.");
		extentTest.log(Status.INFO,"Email Error message is correct");
		//log.info("Email Error message is correct");
		
		
	}
	
	public void forgotPassword(WebDriver driver, String aUserName, String anEmail)
	{
		
		WaitStatement.fnExplicitWait(driver, 30, Header_ForgotpasswordPage);
		Assert.assertEquals(Header_ForgotpasswordPage.getText(), "Forgot your password?.");
		extentTest.log(Status.INFO,"User is on forgot password page");
		//log.info("User is on forgot password page");
		
		UserNameTextbox_ForgotpasswordPage.sendKeys(aUserName);
		extentTest.log(Status.INFO,"Username filled in textbox");
		//log.info("Username filled in textbox");
		
		EmailTextbox_ForgotpasswordPage.sendKeys(anEmail);
		extentTest.log(Status.INFO,"Email filled in textbox");
		//log.info("Email filled in textbox");
		
		SendPasswordButton_ForgotpasswordPage.click();
		extentTest.log(Status.INFO,"Clicked on send password button");
		//log.info("Clicked on send password button");
		
		Assert.assertEquals(EmailConfirmationMessage_ForgotpasswordPage.getText(), "Link to reset your password has been sent to your registered email id.");
		extentTest.log(Status.INFO,"Email sent, confirmation message verified");
		//log.info("Email sent, confirmation message verified");
		
	}

}
