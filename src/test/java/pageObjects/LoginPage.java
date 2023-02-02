package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	
	
		
		WebDriver ldriver;
		
		public LoginPage(WebDriver rdriver){
			
			ldriver=rdriver;
			PageFactory.initElements(rdriver, this);
		}
		
		
		@FindBy(id="Email")
		@CacheLookup
		WebElement txtUserName;
		
		@FindBy(name="Password")
		@CacheLookup
		WebElement txtPassword;
		
		@FindBy(xpath="//button[@class='button-1 login-button']")
		@CacheLookup
		WebElement btnLogin;
		
		@FindBy(xpath="//*[@id=\"navbarText\"]/ul/li[3]/a")
		@CacheLookup
		WebElement lnkLogout;
		
		
		
		
		
		public void setUserName(String uname)
		{	
			
			txtUserName.sendKeys(uname);	
		}
		
		public void setPassword(String pwd)
		{	
			txtPassword.sendKeys(pwd);	
		}
		
		public void clickLogin()
		{	
			btnLogin.click();	
		}
		
		

		public void clickLogout() 
		{	
			lnkLogout.click();	
			
		}
		
		
		
		
		
		
		
		
		
		
		

	

}
