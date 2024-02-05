package sunaySheshgir.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sunaySheshgir.abstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div.form-group input")
	WebElement country;
	
	@FindBy(css = "button.list-group-item:nth-of-type(2)")
	WebElement groupCountry;
	
	@FindBy(css = "div.actions a")
	WebElement placeOrderButton;	
	
	By listGroup = By.cssSelector("section.list-group");

	public WebElement enterCountry() {
		return country;
	}
	
	public ConfirmationPage placeOrder() {
		waitForElementToAppear(listGroup);
		groupCountry.click();
		placeOrderButton.click();
		ConfirmationPage confirmation = new ConfirmationPage(driver);
		return confirmation;
	}	
	
}
