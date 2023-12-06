package ui;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutomationTestDemo {

    public static void main(String[] args) {
	
	//System.setProperty("webdriver.chrome.driver", "C:\\browserdrivers\\chromedriver.exe");
	//System.setProperty("webdriver.gecko.driver", "C:\\browserdrivers\\geckodriver.exe");
	//System.setProperty("webdriver.edge.driver", "C:\\browserdrivers\\msedgedriver.exe");
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver = new ChromeDriver();
	//FirefoxDriver driver = new FirefoxDriver();
	//EdgeDriver driver = new EdgeDriver();
	
	driver.get("https://www.aha-versicherungen.de/");
	try {
	    Thread.sleep(200);
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	driver.findElement(By.xpath("//span[normalize-space()='Alle akzeptieren']")).click();
	
	driver.findElement(By.xpath("(//a[normalize-space()='Versicherungen'])[1]")).click();

	driver.quit();
	
    }

}
