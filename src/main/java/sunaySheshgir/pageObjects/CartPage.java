package sunaySheshgir.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sunaySheshgir.abstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div.cartSection h3")
	List<WebElement> cartProducts;

	@FindBy(css = "li.totalRow button")
	WebElement checkOutEle;

	public boolean verifyProductDisplay(String productName) {
		boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}

	public CheckOutPage goToCheckOut() {
		checkOutEle.click();
		CheckOutPage checkOut = new CheckOutPage(driver);
		return checkOut;
	}
}
