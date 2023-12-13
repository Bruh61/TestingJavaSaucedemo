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

public class LoginTest {

    public static String browser = "chrome";
    public static WebDriver driver;

    public static void main(String[] args) {

	//driver use
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

	// Login process
	By usernameLocator = RelativeLocator.with(By.tagName("input")).above(By.id("password"));
	driver.findElement(usernameLocator).sendKeys("standard_user");
	By passwordLocator = RelativeLocator.with(By.tagName("input")).below(By.id("user-name"));
	driver.findElement(passwordLocator).sendKeys("secret_sauce");
	By submitLocator = RelativeLocator.with(By.tagName("input")).below(By.id("password"));
	driver.findElement(submitLocator).click();

	
	LoginTest test = new LoginTest();
	test.verifyHomepageTitle();

	System.out.println(driver.getCurrentUrl());

	// not used yet
	/*
	 * try { WebElement element =
	 * driver.findElement(By.className("inventory_list"));
	 * 
	 * List<WebElement> elements = element.findElements(By.tagName("div")); for
	 * (WebElement e : elements) { System.out.println(e.getText() + "\n"); }
	 */

	// not used yet
	// List<WebElement> product =
	// driver.findElements(By.className("inventory_item"));

	// not used yet
	String randomArticle = driver.findElement(By.xpath("//*[@id=\"item_1_title_link\"]")).getAttribute("id");
	System.out.println(randomArticle);

	// random articles (1-5) are stored in elementArticle

	int a = test.getRandomNumber(0, 5);
	WebElement elementArticle = driver.findElement(By.id("item_" + a + "_title_link"));

	test.checkArticle(a, elementArticle);

	driver.findElement(By.cssSelector(".shopping_cart_link")).click();
	driver.findElement(By.cssSelector("#checkout")).click();
	
	test.addPersonalInformation();
	test.checkCartOverview();

	driver.findElement(By.cssSelector("#finish")).click();
	
	test.checkFinishedOverview();
	
	// pause for miliseconds
	/*try {
	    Thread.sleep(2000);
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}*/

	driver.close();
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

    public int checkArticle(int a, WebElement elementArticle) {
	switch (a) {
	case 0:
	    String tmpMemory0 = "Sauce Labs Bike Light";
	    elementArticle.getText().equals(tmpMemory0);
	    driver.findElement(By.name("add-to-cart-sauce-labs-bike-light")).click();
	    System.out.println("Getestet wurde: " + elementArticle.getText());
	    break;
	case 1:
	    String tmpMemory1 = "Sauce Labs Bolt T-Shirt";
	    elementArticle.getText().equals(tmpMemory1);
	    driver.findElement(By.name("add-to-cart-sauce-labs-bolt-t-shirt")).click();
	    System.out.println("Getestet wurde: " + elementArticle.getText());
	    break;
	case 2:
	    String tmpMemory2 = "Sauce Labs Onesie";
	    elementArticle.getText().equals(tmpMemory2);
	    driver.findElement(By.name("add-to-cart-sauce-labs-onesie")).click();
	    System.out.println("Getestet wurde: " + elementArticle.getText());
	    break;
	case 3:
	    String tmpMemory3 = "Test.allTheThings() T-Shirt (Red)";
	    elementArticle.getText().equals(tmpMemory3);
	    driver.findElement(By.name("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
	    System.out.println("Getestet wurde: " + elementArticle.getText());
	    break;
	case 4:
	    String tmpMemory4 = "Sauce Labs Backpack";
	    elementArticle.getText().equals(tmpMemory4);
	    driver.findElement(By.name("add-to-cart-sauce-labs-backpack")).click();
	    System.out.println("Getestet wurde: " + elementArticle.getText());
	    break;
	case 5:
	    String tmpMemory5 = "Sauce Labs Fleece Jacket";
	    elementArticle.getText().equals(tmpMemory5);
	    driver.findElement(By.name("add-to-cart-sauce-labs-fleece-jacket")).click();
	    System.out.println("Getestet wurde: " + elementArticle.getText());
	    break;
	}
	return a;
    }

    public void addPersonalInformation() {
	driver.findElement(By.cssSelector("#first-name")).sendKeys("Max");
	driver.findElement(By.cssSelector("#last-name")).sendKeys("Mustermann");
	driver.findElement(By.cssSelector("#postal-code")).sendKeys("89988 Berlin/Hambugerstr. 150");
	driver.findElement(By.cssSelector("#continue")).click();
    }
    public void checkCartOverview() {
	String Inhalt = driver.findElement(By.className("summary_info")).getText();
	Inhalt.contains("Payment Information");
	Inhalt.contains("SauceCard");
	Inhalt.contains("Shipping Information");
	Inhalt.contains("Free Pony Express Delivery!");
	Inhalt.contains("Price Total");
	Inhalt.contains("Item total:");
	Inhalt.contains("Tax:");
	Inhalt.contains("Total:");
	Inhalt.contains("Cancel");
	Inhalt.contains("Finish");
    }
    public void checkFinishedOverview() {
	String Inhalt = driver.findElement(By.cssSelector("#checkout_complete_container")).getText();
	Inhalt.contains("Thank you for your order!");
	Inhalt.contains("Your order has been dispatched, and will arrive just as fast as the pony can get there!");
	Inhalt.contains("Back Home");
	driver.findElement(By.cssSelector("#back-to-products")).click();
    }
}
