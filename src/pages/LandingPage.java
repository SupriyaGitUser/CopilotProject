package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}
	
//	driver.findElement(By.id("userEmail")).sendKeys("supriya@test1.com");
//	driver.findElement(By.id("userPassword")).sendKeys("Welcome1@");
	
	@FindBy(id="userEmail")
	WebElement userEmail;	
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	@FindBy(css=".toast-message")
	WebElement errorMessage;
	

	
	public ProductCatalogue loginApplication(String email,String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginButton.click();	
		return new ProductCatalogue(driver);
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		waitForElementVisibility(errorMessage);
		String errorMessageText = errorMessage.getText();
		return errorMessageText;
	}
	
}
