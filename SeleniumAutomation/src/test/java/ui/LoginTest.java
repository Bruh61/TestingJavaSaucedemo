package ui;

import java.util.Random;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

import java.util.List;

public class LoginTest {

    public static String browser = "chrome";
    public static WebDriver driver;

    public static void main(String[] args) {

	if (browser.equals("FireFox")) {
	    WebDriverManager.firefoxdriver().setup();
	    driver = new FirefoxDriver();
	} else if (browser.equals("chrome")) {
	    WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	} else if (browser.equals("edge")) {
	    WebDriverManager.edgedriver().setup();
	    driver = new EdgeDriver();
	}

	driver.get("https://www.saucedemo.com/");
	driver.manage().window().maximize();

	System.out.println(driver.getCurrentUrl());

	/*
	 * driver.findElement(By.id("user-name")).sendKeys("standard_user");
	 * driver.findElement(By.id("password")).sendKeys("secret_sauce");
	 * driver.findElement(By.xpath("//input[@id='login-button']")).click();
	 */

	By usernameLocator = RelativeLocator.with(By.tagName("input")).above(By.id("password"));
	driver.findElement(usernameLocator).sendKeys("standard_user");
	By passwordLocator = RelativeLocator.with(By.tagName("input")).below(By.id("user-name"));
	driver.findElement(passwordLocator).sendKeys("secret_sauce");
	By submitLocator = RelativeLocator.with(By.tagName("input")).below(By.id("password"));
	driver.findElement(submitLocator).click();

	LoginTest test = new LoginTest();
	test.verifyHomepageTitle();

	System.out.println(driver.getCurrentUrl());
	// driver.findElement(By.className("inventory_list"));
	/*
	 * try { WebElement element =
	 * driver.findElement(By.className("inventory_list"));
	 * 
	 * List<WebElement> elements = element.findElements(By.tagName("div")); for
	 * (WebElement e : elements) { System.out.println(e.getText() + "\n"); }
	 */
	int a = test.getRandomNumber(1, 6);
	System.out.println("Atrikel Nummer: " + a);
	List<WebElement> product = driver.findElements(By.className("inventory_item"));
	// System.out.println("ATTRIBUT: " +
	// driver.findElement(By.id("item_4_title_link")).getAttribute("item_4_title_link"));
	// System.out.println("ATTRIBUT: " +  driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]")).getAttribute("id"));
	/* WebElement randomArticle = */
	driver.findElement(By.id("item_" + a + "_title_link"));

	// By cartButtonLocator = RelativeLocator.with(By.id("item_" + a + "_title_link")).below(By.xpath("\'Add to cart\'"));
	// System.out.println("cartButtonLocator: " + cartButtonLocator);
	// driver.findElement(cartButtonLocator).click();

	System.out.println(driver.findElement(By.id("item_" + a + "_title_link")));

	switch (a) {
	case 0:
	    System.out.println(driver.findElement(By.className("inventory_item_name")));
	    break;
	case 1:
	    System.out.println(driver.findElement(By.className("inventory_item_name")));
	    break;
	case 2:
	    System.out.println(driver.findElement(By.className("inventory_item_name")));
	    break;
	case 3:
	    System.out.println(driver.findElement(By.className("inventory_item_name")));
	    break;
	case 4:
	    System.out.println(driver.findElement(By.className("inventory_item_name")));
	    break;
	case 5:
	    System.out.println(driver.findElement(By.className("inventory_item_name")));
	    break;
	}
	// System.out.println("ATTRIBUT2: " +
	// driver.findElement(By.className("item_4_title_link")));

	/*
	 * System.out.println("Produktmenge" + " " + product.size()); for (WebElement
	 * products : product) { //System.out.println("Produktinfo:"+ " " +
	 * products.getText()); }
	 */

	// WebElement myArticleList =
	// driver.findElement(By.className("inventory_list"));
	// driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack")).click();

	// pause for miliseconds
	try {
	    Thread.sleep(2000);
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	// driver.findElement(By.cssSelector(".shopping_cart_link")).click();

	// }finally {
	driver.close();
	// }
    }

    public void verifyHomepageTitle() {
	String title = driver.getTitle();
	if (title == "Swag Labs")
	    System.out.println("Its the correct Homepage after Login");
	else
	    System.out.println("Page after login is not the intended one");
    }

    public int getRandomNumber(int min, int max) {
	Random random = new Random();
	return random.ints(min, max).findFirst().getAsInt();
    }
}
