package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import abstractComponents.AbstractComponents;

public class Cart extends AbstractComponents{
	
	
WebDriver driver;
	
	public Cart(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}

/*	List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	
	Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	
	Assert.assertTrue(match);
	
	driver.findElement(By.cssSelector(".totalRow button")).click();*/
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	
	public boolean findProducts(String productName)
	{
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOut checkout()
	{
		checkOut.click();
		return new CheckOut(driver);
	}
	
}
