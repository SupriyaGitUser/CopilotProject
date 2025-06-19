package test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;

import pages.Cart;
import pages.CheckOut;
import pages.LandingPage;
import pages.Orders;
import pages.OrdersPage;
import pages.ProductCatalogue;
import pages.RegistrationPage;
import testComponents.BaseTest;
import testComponents.Retry;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = "purchase", retryAnalyzer = Retry.class)
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		// Instantiate the RegistrationPage
		RegistrationPage registrationPage = new RegistrationPage(driver);

		// Call the completeRegistration method to fill out and submit the registration form
		registrationPage.completeRegistration(
				input.get("firstName"),
				input.get("lastName"),
				input.get("email"),
				input.get("mobile"),
				input.get("occupation"),
				input.get("gender"),
				input.get("password"),
				input.get("confirmPassword")
		);

		// Initialize the LandingPage
		LandingPage landingPage = new LandingPage(driver);

		// Log in to the application
		ProductCatalogue prodCat = landingPage.loginApplication(input.get("email"), input.get("password"));

		// Add the specified product to the cart
		prodCat.addToCart(input.get("product"));

		// Navigate to the cart
		Cart cart = prodCat.clickCart();

		// Verify that the product is in the cart
		Boolean match = cart.findProducts(input.get("product"));
		Assert.assertTrue(match);

		// Proceed to checkout
		CheckOut checkout = cart.checkout();

		// Select the country for shipping
		checkout.selectCountry();

		// Place the order
		Orders orders = checkout.clickPlaceOrder();

		// Verify the thank you message
		orders.verifyThankYou();
	}

	@Test(dependsOnMethods = {"submitOrder"})
	public void verifyOrderHistory() {
		// Initialize the LandingPage
		LandingPage landingPage = new LandingPage(driver);

		// Log in to the application
		ProductCatalogue prodCat = landingPage.loginApplication("supriya@test1.com", "Welcome1@");

		// Navigate to the orders page
		OrdersPage orderPage = prodCat.clickOrders();

		// Verify that the product is in the order history
		Boolean match = orderPage.findOrders(productName);
		Assert.assertTrue(match);
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		// Read test data from a JSON file and return it as a 2D array
		List<HashMap<String, String>> data = getJsonDataToMap("C:\\Users\\supri\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\data\\purchase.json");
		return new Object[][]{{data.get(0)}, {data.get(1)}};
	}
}