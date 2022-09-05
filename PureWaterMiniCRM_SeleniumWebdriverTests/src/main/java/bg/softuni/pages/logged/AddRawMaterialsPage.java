package bg.softuni.pages.logged;

import bg.softuni.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddRawMaterialsPage extends BasePage {

    public final String pageTitle = "Add Raw Materials";

    public WebElement addRawMaterialsTitle = driver.findElement(By.cssSelector("h2.text-center.text-white"));
    public WebElement typeInput = driver.findElement(By.cssSelector("#type"));
    public WebElement quantityInput = driver.findElement(By.cssSelector("#quantity"));
    public WebElement deliveredAtInput = driver.findElement(By.cssSelector("#deliveredAt"));
    public WebElement supplierNameInput = driver.findElement(By.cssSelector("#supplierName"));
    public WebElement addRawMaterialsBtn = driver.findElement(By.cssSelector("input.btn.btn-info.btn-lg[type='submit']"));

    public AddRawMaterialsPage(ChromeDriver driver) {
        super(driver);
    }

    public WebElement getTypeInputError() {
        return driver.findElement(By.cssSelector("div:nth-child(2) > div:nth-child(1) > p"));
    }

    public WebElement getQuantityInputError() {
        return driver.findElement(By.cssSelector("div:nth-child(2) > div:nth-child(2) > p"));
    }

    public WebElement getDeliveredOnInputError() {
        return driver.findElement(By.cssSelector("div:nth-child(3) > div:nth-child(1) > p"));
    }

    public WebElement getSupplierNameInputError() {
        return driver.findElement(By.cssSelector("div:nth-child(3) > div:nth-child(2) > p"));
    }
}
