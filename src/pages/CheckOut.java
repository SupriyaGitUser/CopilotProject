package pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CheckOut {
	
	
WebDriver driver;
	
	public CheckOut(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}

/*			driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
		
		List<WebElement> dropdownValues = driver.findElements(By.cssSelector(".ta-item"));
		List<WebElement>selectedValues = dropdownValues.stream().filter(country->country.findElement(By.tagName("span")).getText().trim().equalsIgnoreCase("India")).collect(Collectors.toList());
	
		selectedValues.get(0).findElement(By.tagName("i")).click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();*/
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".ta-item")
	List<WebElement> dropdownValues;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	public void selectCountry()
	{
		country.sendKeys("ind");
		List<WebElement>selectedValues = dropdownValues.stream().filter(country->country.findElement(By.tagName("span")).getText().trim().equalsIgnoreCase("India")).collect(Collectors.toList());
		selectedValues.get(0).findElement(By.tagName("i")).click();
	}
	
	
	public Orders clickPlaceOrder()
	{
		submit.click();
		return new Orders(driver);
	}
}
