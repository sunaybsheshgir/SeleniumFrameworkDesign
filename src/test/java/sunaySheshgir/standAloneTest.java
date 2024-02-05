package sunaySheshgir;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class contains boiler plate code for checking the functionality of
 * Ecommerce website as part of Selenium Framework Design. All the steps from
 * initializing browser to getting confirmation of order are in this class as
 * one single STAND ALONE TEST.
 * 
 * Based on this, we will refactor our code and make use of reusable components.
 * 
 * @author sunay
 */
public class standAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));

		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.cssSelector("input#userEmail")).sendKeys("jeffbeck@gmail.com");
		driver.findElement(By.cssSelector("input#userPassword")).sendKeys("Welcome@123");
		driver.findElement(By.xpath("//input[@name='login']")).click();

		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector("div.mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector("div.card-body button:last-of-type")).click();

		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#toast-container")));

		driver.findElement(By.cssSelector("button[routerlink*='/dashboard/cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector("div.cartSection h3"));
		boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));

		Assert.assertTrue(match);

		driver.findElement(By.cssSelector("li.totalRow button")).click();

		driver.findElement(By.cssSelector("div.form-group input")).sendKeys("ind");

		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section.list-group")));
		driver.findElement(By.cssSelector("button.list-group-item:nth-of-type(2)")).click();
		driver.findElement(By.cssSelector("div.actions a")).click();

		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.hero-primary")));
		String confirmMessage = driver.findElement(By.cssSelector("h1.hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		System.out.println(confirmMessage);
		driver.quit();

	}
}
