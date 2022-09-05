package bg.softuni.pages.logged;

import bg.softuni.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddOrderPage extends BasePage {

    public final String orderPageTitle = "Add Order";

    public WebElement addOrderTitle = driver.findElement(By.cssSelector("h2.text-center.text-white"));
    public WebElement orderNameInput = driver.findElement(By.cssSelector("#name"));
    public WebElement quantityInput = driver.findElement(By.cssSelector("#quantity"));
    public WebElement categoryInput = driver.findElement(By.cssSelector("#prodCategory"));
    public WebElement expiryDateInput = driver.findElement(By.cssSelector("input[type='datetime-local']"));
    public WebElement descriptionInput = driver.findElement(By.cssSelector("#description"));
    public WebElement customerInput = driver.findElement(By.cssSelector("#customer"));
    public WebElement addOrderBtn = driver.findElement(By.cssSelector("input.btn.btn-info.btn-lg[type='submit']"));

    public AddOrderPage(ChromeDriver driver) {
        super(driver);
    }

    public WebElement getOrderNameInputError() {
        return driver.findElement(By.cssSelector("div:nth-child(2) > div:nth-child(1) > p"));
    }

    public WebElement getQuantityInputError() {
        return driver.findElement(By.cssSelector("div:nth-child(2) > div:nth-child(2) > p"));
    }

    public WebElement getCategoryInputError() {
        return driver.findElement(By.cssSelector("div:nth-child(3) > div:nth-child(1) > p"));
    }

    public WebElement getExpiryDateInputError() {
        return driver.findElement(By.cssSelector("div:nth-child(3) > div:nth-child(2) > p"));
    }

    public WebElement getDescriptionInputError() {
        return driver.findElement(By.cssSelector("div:nth-child(4) > div:nth-child(1) > p"));
    }

    public WebElement getCustomerInputError() {
        return driver.findElement(By.cssSelector("div:nth-child(4) > div:nth-child(2) > p"));
    }
}
