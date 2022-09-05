package bg.softuni.pages.logged;

import bg.softuni.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class OrderDetailsPage extends BasePage {

    public final String SUCCESSFUL_UPDATE_ALERT = "Successful update!";
    public final String UNSUCCESSFUL_UPDATE_ALERT = "Update was not successful!";

    public final String ORDER_DETAILS_PAGE_HEADER = "Order Details";

    public final WebElement orderDetailsTitle = driver.findElement(By.cssSelector("h2.text-center.text-white"));
    public final WebElement orderNameInput = driver.findElement(By.cssSelector("#name"));
    public final WebElement quantityInput = driver.findElement(By.cssSelector("#quantity"));
    public final WebElement productCategoryInput = driver.findElement(By.cssSelector("#prodCategory"));
    public final WebElement expiryDateInput = driver.findElement(By.cssSelector("#expiryDate"));
    public final WebElement descriptionInput = driver.findElement(By.cssSelector("#description"));
    public final WebElement customerInput = driver.findElement(By.cssSelector("#customer"));
    public final WebElement updateBtn = driver.findElement(By.cssSelector("input.btn[type='submit'][value='Update']"));
    public final WebElement deleteBtn = driver.findElement(By.cssSelector("input.btn[type='submit'][value='Delete']"));

    public OrderDetailsPage(ChromeDriver driver) {
        super(driver);
    }

    public WebElement getUnsuccessfulAlert() {
        return driver.findElement(By.cssSelector("p.errors.alert.alert-danger.width-700"));
    }

    public WebElement getSuccessfulAlert() {
        return driver.findElement(By.cssSelector("p.alert.alert-success.width-700"));
    }
}
