package abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.Cart;
import pages.OrdersPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	@FindBy(css="button[routerlink*='cart']")
	WebElement cart;
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement orders;
	
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		this.driver = driver;
	}

	public void waitForElementVisibility(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));		
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementVisibility(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));		
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitForElementInvisibility(WebElement ele) throws InterruptedException
	{
	//	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	//	wait.until(ExpectedConditions.invisibilityOf(ele));
		Thread.sleep(1000);
	}
	
	public Cart clickCart()
	{
		cart.click();
		return new Cart(driver);
	}

	
	public OrdersPage clickOrders()
	{
		orders.click();
		return new OrdersPage(driver);
	}
	
}
