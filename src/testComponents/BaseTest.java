package testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pages.LandingPage;

public class BaseTest {
	// WebDriver instance for browser automation
	public WebDriver driver;
	// LandingPage object for page interactions
	public LandingPage landingPage;
	
	// Initializes the WebDriver based on the browser specified in properties or system property
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\resources\\Global.properties");
		prop.load(fis);
		
		// Get browser name from system property or properties file
		String browserName = System.getProperty("browser") !=null?System.getProperty("browser"):prop.getProperty("browser");
		
//		String browserName = prop.getProperty("browser");
		
		// Initialize the appropriate WebDriver based on browserName
		if(browserName.contains("Chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
		}
		else if(browserName.equalsIgnoreCase("Firefox"))
		{
			 driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("Edge"))
		{
			 driver = new EdgeDriver();
		}
		// Set implicit wait and maximize window
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	// Reads a JSON file and converts it to a List of HashMaps
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		// Read JSON file content as String
		String jsonFileContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		// Convert JSON string to List<HashMap<String, String>>
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonFileContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
	
	// Takes a screenshot and saves it to the reports directory
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		File ts = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(ts, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
	
	// this method is common to all tests hence written in base test
	// Setup method to launch the application before each test
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		// Initialize driver and open landing page
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	// Teardown method to close the browser after each test
	@AfterMethod(alwaysRun=true)
	public void closeBrowser()
	{
		driver.quit();
	}

}