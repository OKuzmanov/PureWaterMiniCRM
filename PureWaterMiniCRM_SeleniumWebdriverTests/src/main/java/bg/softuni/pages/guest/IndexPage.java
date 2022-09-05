package bg.softuni.pages.guest;

import bg.softuni.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class IndexPage extends BasePage {
    public final String WELCOME_MESSAGE = "Welcome to Pure Water. A CRM for water bottling manufacturing companies.";

    public WebElement welcomeMessage = driver.findElement(By.cssSelector("body > div > main > h5"));
    public WebElement navigationBar = driver.findElement(By.cssSelector("body > div > header > nav"));
    public WebElement footer = driver.findElement(By.cssSelector("body > div > footer"));

    public IndexPage(ChromeDriver driver) {
        super(driver);
    }

}
