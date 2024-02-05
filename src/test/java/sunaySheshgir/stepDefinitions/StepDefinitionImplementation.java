package sunaySheshgir.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sunaySheshgir.pageObjects.CartPage;
import sunaySheshgir.pageObjects.CheckOutPage;
import sunaySheshgir.pageObjects.ConfirmationPage;
import sunaySheshgir.pageObjects.LandingPage;
import sunaySheshgir.pageObjects.ProductCatalogue;
import sunaySheshgir.testComponents.BaseTest;

public class StepDefinitionImplementation extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmation;

	@Given("I landed on Ecommerce website")
	public void I_landed_on_Ecommerce_website() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {
		productCatalogue = landingPage.logInApplication(username, password);
	}

	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName) {
		List<WebElement> products = productCatalogue.getProductsList();
		productCatalogue.addToCart(productName);
	}

	@When("^Checkout (.+) and submit order$")
	public void Checkout_and_submit_order(String productName) throws InterruptedException {
		CartPage cartPage = productCatalogue.clickOnCart();
		boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOut = cartPage.goToCheckOut();

		checkOut.enterCountry().sendKeys("India");
		confirmation = checkOut.placeOrder();
	}

	@Then("{string} is displayed on confirmation page")
	public void message_is_displayed_on_confirmation_page(String string) {
		String confirmMessage = confirmation.confirmation();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.quit();
	}

	@Then("{string} message is displayed")
	public void login_error_message_isDisplayed(String strArg1) {
		Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		driver.quit();
	}

}
