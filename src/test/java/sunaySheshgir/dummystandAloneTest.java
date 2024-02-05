package sunaySheshgir;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sunaySheshgir.pageObjects.CartPage;
import sunaySheshgir.pageObjects.CheckOutPage;
import sunaySheshgir.pageObjects.ConfirmationPage;
import sunaySheshgir.pageObjects.OrderPage;
import sunaySheshgir.pageObjects.ProductCatalogue;
import sunaySheshgir.testComponents.BaseTest;

public class dummystandAloneTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = {"Purchase"})
	public void submitOrderTest(HashMap<String, String> input)
			throws InterruptedException, IOException {

		String country = "India";

		ProductCatalogue productCatalogue = landingPage.logInApplication(input.get("email"), input.get("password"));
		productCatalogue.addToCart(input.get("product"));
		CartPage cartPage = productCatalogue.clickOnCart();
		boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkOut = cartPage.goToCheckOut();

		checkOut.enterCountry().sendKeys(country);
		ConfirmationPage confirmation = checkOut.placeOrder();
		String confirmMessage = confirmation.confirmation();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods = {"submitOrderTest"})
	public void orderHistoryTest() throws InterruptedException {
		ProductCatalogue productCatalogue = landingPage
				.logInApplication("jeffbeck@gmail.com", "Welcome@123");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir")
						+ "//src//test//java//sunaySheshgir//data//PurchaseOrder.json");
		return new Object[][]{{data.get(0)}, {data.get(1)}};
	}
}