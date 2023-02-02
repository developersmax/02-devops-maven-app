package stepDefinations;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass{
	
	@Before
	public void setup() throws IOException
	{
		//Logger
		logger=Logger.getLogger("nopCommercw");//Added logger
		PropertyConfigurator.configure("log4j.properties");
		
		
		//Reading properties
		configprop =new Properties();
		FileInputStream configPropfile = new FileInputStream("config.properties");
		configprop.load(configPropfile);
		
		
		String br=configprop.getProperty("browser");
		
		if(br.equals("chrome"))
		{
			//System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//Drivers/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver",configprop.getProperty("chromepath"));

			driver= new ChromeDriver();
		

		}
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",configprop.getProperty("firefoxpath"));

			driver= new FirefoxDriver();
			

		}
		
		else if(br.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver",configprop.getProperty("iepath"));

			driver= new InternetExplorerDriver();
			

		}
		
		logger.info("*****Launching URL********");
		
	
	}
	
	
	
	@Given("User Launch Chrome browser")
	public void user_Launch_Chrome_browser() {
		
			lp=new LoginPage(driver);
		
	}

	@When("User opens URL {string}")
	public void user_opens_URL(String url) {
		logger.info("*******Opening URL********");
		driver.get(url);
		
	}

	@When("User enters Email as{string} as Password as {string}")
	public void user_enters_Email_as_as_Password_as(String email, String password) {
		
		logger.info("*****Providing Login Details********");	
		lp.setUserName(email);
		lp.setPassword(password);
		
	}

	@When("Click on Login")
	public void click_on_Login() {
		logger.info("*******Started login********");
		lp.clickLogin();
	}

	@Then("Page title should be {string}")
	public void page_title_should_be(String title) {
		
		if (driver.getPageSource().contains("Login was unsuccessful. Please correct the errors and try again.")) {
			
			driver.close();
			logger.info("*******Login Passed********");
			Assert.assertTrue(false);
		}
		else
		{
			logger.info("*******Login Failed********");
			Assert.assertEquals(title,driver.getTitle());
		}
		
		
	}

	@When("User click on Log out link")
	public void user_click_on_Log_out_link() throws InterruptedException {
		logger.info("*******Click on logout link********");	
		lp.clickLogout();
		Thread.sleep(3000);

	}

	@Then("Page Title should be {string}")
	public void page_Title_should_be(String string) {
		
		 
	}

	@Then("close browser")
	public void close_browser() {
		logger.info("*******Closing Browser********");
		driver.close();

	}


	
	
	//Customers feature step definition...............
	
	@Then("user can view Dashboard")
	public void user_can_view_Dashboard() {
	   
		addCust=new AddCustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
		
	}

	@When("User click on customer Menu")
	public void user_click_on_customer_Menu() throws InterruptedException {
	  
		Thread.sleep(2000);
		addCust.clickOnCustomersMenu();
		
	}

	@When("click on customer Menu Item")
	public void click_on_customer_Menu_Item() throws InterruptedException {
	  
		Thread.sleep(2000);
		addCust.clickOnCustomersMenuItem();
	}

	@When("click on Add new button")
	public void click_on_Add_new_button() throws InterruptedException {
	    
		Thread.sleep(2000);
		addCust.clickOnAddnew();
		
	}

	@Then("user can view add new customer page")
	public void user_can_view_add_new_customer_page() {
	   
		Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	}

	@When("user enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
	  
		
		logger.info("*******Adding new customer********");
		logger.info("*******Providing Customer Details********");
		String email= randomestring()+"@gmail.com";
		addCust.setEmail(email);
		
		addCust.setPassword("test123");
		// Registered - default
		// The customer cannot be in both 'Guests' and 'Registered' customer roles
		// Add the customer to 'Guests' or 'Registered' customer role
		addCust.setCustomerRoles("Guest");
		Thread.sleep(3000);

		addCust.setManagerOfVendor("Vendor 2");
		addCust.setGender("Male");
		addCust.setFirstName("Pavan");
		addCust.setLastName("Kumar");
		addCust.setDob("7/05/1985"); // Format: D/MM/YYY
		addCust.setCompanyName("busyQA");
		addCust.setAdminContent("This is for testing.........");

	}

	@When("click on save button")
	public void click_on_save_button() throws InterruptedException {
		logger.info("*******Saving customer details ********");
		addCust.clickOnSave();
		Thread.sleep(3000);
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String msg) {
	 
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
				.contains("The new customer has been added successfully"));
	}


	
	//steps for searching a customer using Email Id ......................
	
	@When("Enter customer Email")
	public void enter_customer_Email() throws InterruptedException {
	  searchCust = new SearchCustomerPage(driver);
	  logger.info("********* Searching customer details by Email **************");
	  
	  Thread.sleep(3000);
	  searchCust.setEmail("victoria_victoria@nopCommerce.com");
	  
	}

	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {
	 
		searchCust.clickSearch();
		Thread.sleep(3000);
	}

	@Then("User should found Email in the Search table")
	public void user_should_found_Email_in_the_Search_table() {
	   
		boolean status =searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		
		Assert.assertEquals(true, status);
	}
	
	
	//steps for searching a customer by using First Name & Last Name
	
	@When("Enter customer FirstName")
	public void enter_customer_FirstName() {
		logger.info("********* Searching customer details by Name **************");
		searchCust= new SearchCustomerPage(driver);
		searchCust.setFirstName("Victoria");
		
	}

	@When("Enter customer LastName")
	public void enter_customer_LastName() {
		
		searchCust.setLastName("Terces");
		
	   
	}

	@Then("User should found Name in the Search table")
	public void user_should_found_Name_in_the_Search_table() {
		
		boolean status=searchCust.searchCustomerByName("Victoria Terces");
		Assert.assertEquals(true, status);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
