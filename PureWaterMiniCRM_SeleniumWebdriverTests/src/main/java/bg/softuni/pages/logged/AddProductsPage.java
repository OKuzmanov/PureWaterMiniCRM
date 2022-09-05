package bg.softuni.pages.logged;

import bg.softuni.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddProductsPage extends BasePage {

    public final String pageTitle = "Add Products";

    public WebElement addProductTitle = driver.findElement(By.cssSelector("h2.text-center.text-white"));
    public WebElement typeInput = driver.findElement(By.cssSelector("#type"));
    public WebElement quantityInput = driver.findElement(By.cssSelector("#quantity"));
    public WebElement productionDateInput = driver.findElement(By.cssSelector("#productionDate"));
    public WebElement addProductBtn = driver.findElement(By.cssSelector("input.btn.btn-info.btn-lg[type='submit']"));

    public AddProductsPage(ChromeDriver driver) {
        super(driver);
    }

    public WebElement getTypeInputError() {
        return driver.findElement(By.cssSelector("form > div:nth-child(2) > p"));
    }

    public WebElement getQuantityInputError() {
        return driver.findElement(By.cssSelector("form > div:nth-child(3) > p"));
    }

    public WebElement getProductionDateInputError() {
        return driver.findElement(By.cssSelector("form > div:nth-child(4) > p"));
    }
}
