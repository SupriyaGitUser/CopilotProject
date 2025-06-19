package test;

import org.testng.annotations.Test;


import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.Cart;
import pages.CheckOut;
import pages.Orders;
import pages.ProductCatalogue;
import testComponents.BaseTest;
import testComponents.Retry;

public class ErrorValidationTest extends BaseTest {
	
	
	@Test(groups={"ErrorHandling"},retryAnalyzer=Retry.class)
	public void loginValidation()
	{
		ProductCatalogue prodCat = landingPage.loginApplication("supriya@test1.com", "Welcome1@@");
		AssertJUnit.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
	}

	
	@Test
	public void productValidation() throws IOException, InterruptedException
	{
		String productName = "ZARA COAT 3";
		ProductCatalogue prodCat = landingPage.loginApplication("supriya@test1.com", "Welcome1@");
		prodCat.addToCart(productName);		
		Cart cart = prodCat.clickCart();
		Boolean match =cart.findProducts("ZARA COAT 33");
		Assert.assertFalse(match);
	}
}
