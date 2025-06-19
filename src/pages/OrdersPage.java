package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents {
	
WebDriver driver;
	
	public OrdersPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}
	
	@FindBy(xpath="//tbody/tr/td[2]")
	List<WebElement> orders;
	
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	
	public boolean findOrders(String productName)
	{
		Boolean match = orders.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	

}
