package pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}
	
//	driver.findElement(By.id("userEmail")).sendKeys("supriya@test1.com");
//	driver.findElement(By.id("userPassword")).sendKeys("Welcome1@");
	
//	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	List<WebElement> products;	
	
	@FindBy(css=".ng-trigger-fadeIn")
	WebElement loadingGif;
	

	
	By productBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector("button:last-child");
	By toastMessage = By.cssSelector(".toast-container");
	
	//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-trigger-fadeIn"))));
	//ait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-container"))
	
	public List<WebElement> getProductList()
	{
		waitForElementVisibility(productBy);
		return products;
	}
	
	public WebElement getProductName(String productName)
	{
		WebElement product = getProductList().stream().filter(prod->prod.findElement(By.tagName("b")).getText().contains(productName)).findFirst().orElse(null);
		return product;
	}
	
	public void addToCart(String productName) throws InterruptedException
	{
		WebElement product = getProductName(productName);
		product.findElement(addToCart).click();
		waitForElementVisibility(toastMessage);
		waitForElementInvisibility(loadingGif);
	}
	
//	driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
	

	
}
