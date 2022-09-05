package bg.softuni.pages.logged;

import bg.softuni.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RawMaterialDetailsPage extends BasePage {

    public final String SUCCESSFUL_UPDATE_ALERT = "Successful update!";
    public final String UNSUCCESSFUL_UPDATE_ALERT = "Update was not successful!";

    public final String RAW_MATERIAL_DETAILS_PAGE_HEADER = "Raw Materials Details";

    public final WebElement rawMaterialDetailsTitle = driver.findElement(By.cssSelector("h2.text-center.text-white"));
    public final WebElement typeInput = driver.findElement(By.cssSelector("#type"));
    public final WebElement quantityInput = driver.findElement(By.cssSelector("#quantity"));
    public final WebElement deliveredAtInput = driver.findElement(By.cssSelector("#deliveredAt"));
    public final WebElement supplierNameInput = driver.findElement(By.cssSelector("#supplierName"));
    public final WebElement updateBtn = driver.findElement(By.cssSelector("input.btn[type='submit'][value='Update']"));
    public final WebElement deleteBtn = driver.findElement(By.cssSelector("input.btn[type='submit'][value='Delete']"));

    public RawMaterialDetailsPage(ChromeDriver driver) {
        super(driver);
    }

    public WebElement getUnsuccessfulAlert() {
        return driver.findElement(By.cssSelector("p.errors.alert.alert-danger.width-700"));
    }

    public WebElement getSuccessfulAlert() {
        return driver.findElement(By.cssSelector("p.alert.alert-success.width-700"));
    }
}
