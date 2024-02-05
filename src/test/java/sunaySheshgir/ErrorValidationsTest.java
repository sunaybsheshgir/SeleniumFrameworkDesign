package sunaySheshgir;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import sunaySheshgir.pageObjects.CartPage;
import sunaySheshgir.pageObjects.ProductCatalogue;
import sunaySheshgir.testComponents.BaseTest;
import sunaySheshgir.testComponents.Retry;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws InterruptedException, IOException {

		landingPage.logInApplication("jefbeck@gmail.com", "Welcome123");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}

	@Test
	public void productErrorValidation() throws InterruptedException, IOException {

		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.logInApplication("susantedeschi@gmail.com", "Welcome1@123");
		productCatalogue.getProductsList();
		productCatalogue.addToCart(productName);
		CartPage cartPage = productCatalogue.clickOnCart();
		boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
}