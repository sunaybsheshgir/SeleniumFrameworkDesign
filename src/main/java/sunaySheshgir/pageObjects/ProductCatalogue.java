package sunaySheshgir.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sunaySheshgir.abstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div.mb-3")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By productsBy = By.cssSelector("div.mb-3");
	By addToCart = By.cssSelector("div.card-body button:last-of-type");
	By toastMessage = By.cssSelector("div#toast-container");

	public List<WebElement> getProductsList() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement prod = getProductsList().stream()
				.filter(product -> product.findElement(By.cssSelector("b"))
						.getText().equals(productName))
				.findFirst().orElse(null);
		return prod;
	}

	public void addToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}
}
