package bg.softuni.pages.logged;

import bg.softuni.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SupplierDetailsPage extends BasePage {

    public final String SUCCESSFUL_UPDATE_ALERT = "Successful update!";
    public final String UNSUCCESSFUL_UPDATE_ALERT = "Update was not successful!";

    public final String SUPPLIER_DETAILS_PAGE_HEADER = "Supplier Details";

    public final WebElement supplierDetailsTitle = driver.findElement(By.cssSelector("h2.text-center.text-white"));
    public final WebElement companyNameInput = driver.findElement(By.cssSelector("#companyName"));
    public final WebElement emailInput = driver.findElement(By.cssSelector("#email"));
    public final WebElement phoneNumberInput = driver.findElement(By.cssSelector("#phoneNumber"));
    public final WebElement addressInput = driver.findElement(By.cssSelector("#address"));
    public final WebElement descriptionInput = driver.findElement(By.cssSelector("#description"));
    public final WebElement updateBtn = driver.findElement(By.cssSelector("input.btn[type='submit'][value='Update']"));
    public final WebElement deleteBtn = driver.findElement(By.cssSelector("input.btn[type='submit'][value='Delete']"));

    public SupplierDetailsPage(ChromeDriver driver) {
        super(driver);
    }

    public WebElement getUnsuccessfulAlert() {
        return driver.findElement(By.cssSelector("p.errors.alert.alert-danger.width-700"));
    }

    public WebElement getSuccessfulAlert() {
        return driver.findElement(By.cssSelector("p.alert.alert-success.width-700"));
    }
}
