package test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import testComponents.BaseTest;

public class StandaloneTest extends BaseTest{

	@Test
	public void standaloneTestCase() throws IOException {
		// TODO Auto-generated method stub
		
		
		WebDriver driver; 
		driver = initializeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.manage().window().maximize();
		
		String productName = "ZARA COAT 3";
		
		driver.findElement(By.id("userEmail")).sendKeys("supriya@test1.com");
		driver.findElement(By.id("userPassword")).sendKeys("Welcome1@");
		
		driver.findElement(By.id("login")).click();
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		List<WebElement>requiredProducts = products.stream().filter(product->product.findElement(By.tagName("b")).getText().contains(productName)).collect(Collectors.toList());

		requiredProducts.stream().forEach(product->product.findElement(By.cssSelector("button:last-child")).click());
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-trigger-fadeIn"))));		
		
		
		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
		
		List<WebElement> dropdownValues = driver.findElements(By.cssSelector(".ta-item"));
		List<WebElement>selectedValues = dropdownValues.stream().filter(country->country.findElement(By.tagName("span")).getText().trim().equalsIgnoreCase("India")).collect(Collectors.toList());
	
		selectedValues.get(0).findElement(By.tagName("i")).click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String message = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.quit();
		
	}

}
