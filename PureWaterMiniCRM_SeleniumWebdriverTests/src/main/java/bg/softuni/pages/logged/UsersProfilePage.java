package bg.softuni.pages.logged;

import bg.softuni.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UsersProfilePage extends BasePage {

    public final String SUCCESSFUL_UPDATE_ALERT = "Successful update!";
    public final String UNSUCCESSFUL_UPDATE_ALERT = "Update was not successful!";

    public final String USER_PROFILE_PAGE_HEADER = "User Profile";

    public final WebElement userProfileTitle = driver.findElement(By.cssSelector("h2.text-center.text-white"));
    public final WebElement firstNameInput = driver.findElement(By.cssSelector("#firstName"));
    public final WebElement lastNameInput = driver.findElement(By.cssSelector("#lastName"));
    public final WebElement usernameInput = driver.findElement(By.cssSelector("#username"));
    public final WebElement emailInput = driver.findElement(By.cssSelector("#email"));
    public final WebElement passwordInput = driver.findElement(By.cssSelector("#password"));
    public final WebElement updateBtn = driver.findElement(By.cssSelector("input.btn[type='submit'][value='Update']"));
    public final WebElement deleteBtn = driver.findElement(By.cssSelector("input.btn[type='submit'][value='Delete']"));

    public UsersProfilePage(ChromeDriver driver) {
        super(driver);
    }

    public WebElement getUserRole() {
        return driver.findElement(By.cssSelector("#roles[value='USER']"));
    }

    public WebElement getAdminRole() {
        return driver.findElement(By.cssSelector("#roles[value='ADMIN']"));
    }

    public WebElement getUnsuccessfulAlert() {
        return driver.findElement(By.cssSelector("p.errors.alert.alert-danger.width-700"));
    }

    public WebElement getSuccessfulAlert() {
        return driver.findElement(By.cssSelector("p.alert.alert-success.width-700"));
    }
}
