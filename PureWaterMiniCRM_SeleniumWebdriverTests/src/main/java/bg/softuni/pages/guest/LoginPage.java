package bg.softuni.pages.guest;

import bg.softuni.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage extends BasePage {

    public final String LOGIN_ERROR_MESSAGE = "No such username and password combination exists.";
    public final String LOGIN_PAGE_HEADER = "Login";

    public final WebElement loginFormHead = driver.findElement(By.cssSelector("h2.text-center.text-white"));
    public final WebElement loginFormUsernameInput = driver.findElement(By.cssSelector("#username"));
    public final WebElement loginFormPasswordInput = driver.findElement(By.cssSelector("#password"));
    public final WebElement loginSubmitBtn = driver.findElement(By.cssSelector("input.btn[type='submit']"));

    public LoginPage(ChromeDriver driver) {
        super(driver);
    }

    public void loginUser(String username, String password) {
        loginFormUsernameInput.sendKeys(username);
        loginFormPasswordInput.sendKeys(password);
        loginSubmitBtn.click();
    }

    public WebElement getLoginErrorMsg() {
        return driver.findElement(By.cssSelector("p.errors.alert.alert-danger"));
    }
}
