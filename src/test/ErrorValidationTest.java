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

/**
 * ErrorValidationTest contains test cases for validating error scenarios
 * such as invalid login and product validation in the cart.
 */
public class ErrorValidationTest extends BaseTest {

    /**
     * Test to verify error message is displayed for invalid login credentials.
     * Uses retryAnalyzer to rerun the test in case of failure.
     */
    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void loginValidation() {
        // Attempt login with invalid credentials
        ProductCatalogue prodCat = landingPage.loginApplication("supriya@test1.com", "Welcome1@@");
        // Assert that the error message is as expected
        AssertJUnit.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
    }

    /**
     * Test to verify that a product not added to the cart is not present.
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void productValidation() throws IOException, InterruptedException {
        String productName = "ZARA COAT 3";
        // Login with valid credentials
        ProductCatalogue prodCat = landingPage.loginApplication("supriya@test1.com", "Welcome1@");
        // Add a product to the cart
        prodCat.addToCart(productName);
        // Navigate to the cart
        Cart cart = prodCat.clickCart();
        // Check if a non-existent product is found in the cart
        Boolean match = cart.findProducts("ZARA COAT 33");
        // Assert that the product is not found
        Assert.assertFalse(match);
    }
}
