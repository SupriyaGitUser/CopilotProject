package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Orders {
	
	
WebDriver driver;
	
	public Orders(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}

/*		
		String message = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));*/
	
	@FindBy(css=".hero-primary")
	WebElement thankYouMessage;
	
	public void verifyThankYou()
	{
		String message = thankYouMessage.getText();
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));		
	}
}
