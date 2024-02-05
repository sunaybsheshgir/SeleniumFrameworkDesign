package sunaySheshgir.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sunaySheshgir.abstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "h1.hero-primary")
	WebElement confirmation;

	By confirmationBy = By.cssSelector("h1.hero-primary");

	public String confirmation() {
		waitForElementToAppear(confirmationBy);
		String confirmMessage = confirmation.getText();
		return confirmMessage;
	}

}
