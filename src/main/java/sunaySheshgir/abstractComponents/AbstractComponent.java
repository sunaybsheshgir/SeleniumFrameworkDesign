package sunaySheshgir.abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sunaySheshgir.pageObjects.CartPage;
import sunaySheshgir.pageObjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "button[routerlink*='/dashboard/cart']")
	WebElement cartIcon;

	By cartIconBy = By.cssSelector("button[routerlink*='/dashboard/cart']");

	@FindBy(css = "button[routerlink*='/dashboard/myorders']")
	WebElement orderHeader;

	public OrderPage goToOrdersPage() throws InterruptedException {
		waitForWebElementToAppear(orderHeader);
		Thread.sleep(1000); // Using this because of some issue at the backend
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}

	public void waitForElementToAppear(By findBy) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOf(findBy));
	}

	public void waitForElementToDisappear(WebElement element) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.invisibilityOf(element));
	}

	public CartPage clickOnCart() throws InterruptedException {
		waitForElementToAppear(cartIconBy);
		Thread.sleep(1000); // Using this because of some issue at the backend
		cartIcon.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
}
