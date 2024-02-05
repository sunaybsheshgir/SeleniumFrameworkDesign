package sunaySheshgir.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sunaySheshgir.abstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input#userEmail")
	WebElement userEmail;

	@FindBy(css = "input#userPassword")
	WebElement passWord;

	@FindBy(xpath = "//input[@name='login']")
	WebElement logIn;

	@FindBy(css = "div[class*='toast-message']")
	WebElement errorMessage;
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public ProductCatalogue logInApplication(String email, String pw) {
		userEmail.sendKeys(email);
		passWord.sendKeys(pw);
		logIn.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
}
