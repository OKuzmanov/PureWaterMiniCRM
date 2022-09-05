package bg.softuni.pages.logged;

import bg.softuni.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddSupplierPage extends BasePage {
    public final String pageTitle = "Add Supplier";

    public WebElement addSupplierTitle = driver.findElement(By.cssSelector("h2.text-center.text-white"));
    public WebElement companyNameInput = driver.findElement(By.cssSelector("#companyName"));
    public WebElement emailInput = driver.findElement(By.cssSelector("#email"));
    public WebElement addressInput = driver.findElement(By.cssSelector("#address"));
    public WebElement phoneNumberInput = driver.findElement(By.cssSelector("#phoneNumber"));
    public WebElement descriptionInput = driver.findElement(By.cssSelector("#description"));
    public WebElement addSupplierBtn = driver.findElement(By.cssSelector("input.btn.btn-info.btn-lg[type='submit']"));

    public AddSupplierPage(ChromeDriver driver) {
        super(driver);
    }

    public WebElement getCompanyNameInputError() {
        return driver.findElement(By.cssSelector("div:nth-child(2) > div:nth-child(1) > p"));
    }

    public WebElement getEmailInputError() {
        return driver.findElement(By.cssSelector("div:nth-child(2) > div:nth-child(2) > p"));
    }

    public WebElement getAddressInputError() {
        return driver.findElement(By.cssSelector("div:nth-child(3) > div:nth-child(1) > p"));
    }

    public WebElement getPhoneNumberInputError() {
        return driver.findElement(By.cssSelector("div:nth-child(3) > div:nth-child(2) > p"));
    }

    public WebElement getDescriptionInputError() {
        return driver.findElement(By.cssSelector("div:nth-child(4) > div:nth-child(1) > p"));
    }

    public void addSupplier(String companyName, String email, String address, String phoneNumber, String description) {
        this.companyNameInput.sendKeys(companyName);
        this.emailInput.sendKeys(email);
        this.addressInput.sendKeys(address);
        this.phoneNumberInput.sendKeys(phoneNumber);
        this.descriptionInput.sendKeys(description);
        this.addSupplierBtn.click();
    }
}
