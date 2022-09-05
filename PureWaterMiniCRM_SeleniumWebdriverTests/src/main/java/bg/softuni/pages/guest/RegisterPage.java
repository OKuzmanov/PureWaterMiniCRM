package bg.softuni.pages.guest;

import bg.softuni.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage extends BasePage {

    public final String REGISTER_PAGE_HEADER = "Register User";
    public final String EMAIL_OCCUPIED_ERROR = "This email is already occupied!";
    public final String USERNAME_OCCUPIED_ERROR = "This username is already occupied!";
    public final String PASSWORD_MATCH_ERROR = "Passwords do not match.";

    public final WebElement registerTitle = driver.findElement(By.cssSelector("h2.text-center.text-white"));
    public final WebElement firstNameInput = driver.findElement(By.cssSelector("#firstName"));
    public final WebElement lastNameInput = driver.findElement(By.cssSelector("#lastName"));
    public final WebElement usernameInput = driver.findElement(By.cssSelector("#username"));
    public final WebElement emailInput = driver.findElement(By.cssSelector("#email"));
    public final WebElement passwordInput = driver.findElement(By.cssSelector("#password"));
    public final WebElement confirmPasswordInput = driver.findElement(By.cssSelector("#confirmPassword"));
    public final WebElement submitBtn = driver.findElement(By.cssSelector("input.btn[type='submit']"));

    public RegisterPage(ChromeDriver driver) {
        super(driver);
    }

    public void registerUser(String firstName, String lastName, String username,
                             String email, String password, String confirmPassword) {
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
        usernameInput.clear();
        usernameInput.sendKeys(username);
        emailInput.clear();
        emailInput.sendKeys(email);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys(confirmPassword);

        submitBtn.click();
    }

    public WebElement getFirstNameErrorValidation() {
        return new WebDriverWait(driver, Duration.ofMinutes(5))
                .until(driver -> driver.findElement(By.cssSelector("div:nth-child(2) > div:nth-child(1) > p")));
    }

    public WebElement getLastNameErrorValidation() {
        return new WebDriverWait(driver, Duration.ofMinutes(5))
                .until(driver -> driver.findElement(By.cssSelector("div:nth-child(2) > div:nth-child(2) > p")));
    }

    public WebElement getUsernameErrorValidation() {
        return new WebDriverWait(driver, Duration.ofMinutes(5))
                .until(driver -> driver.findElement(By.cssSelector("div:nth-child(3) > div:nth-child(1) > p")));
    }

    public WebElement getEmailErrorValidation() {
        return new WebDriverWait(driver, Duration.ofMinutes(5))
                .until(driver -> driver.findElement(By.cssSelector("div:nth-child(3) > div:nth-child(2) > p")));
    }

    public WebElement getPasswordErrorValidation() {
        return new WebDriverWait(driver, Duration.ofMinutes(5))
                .until(driver -> driver.findElement(By.cssSelector("div:nth-child(4) > div:nth-child(1) > p")));
    }

    public WebElement getConfirmPasswordErrorValidation() {
        return new WebDriverWait(driver, Duration.ofMinutes(5))
                .until(driver -> driver.findElement(By.cssSelector("div:nth-child(4) > div:nth-child(2) > p")));
    }
}
