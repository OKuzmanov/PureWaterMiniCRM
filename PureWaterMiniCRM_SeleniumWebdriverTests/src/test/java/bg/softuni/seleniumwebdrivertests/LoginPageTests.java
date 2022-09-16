package bg.softuni.seleniumwebdrivertests;

import bg.softuni.pages.guest.LoginPage;
import bg.softuni.pages.guest.RegisterPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.Random;

public class LoginPageTests extends BaseTest{

    private LoginPage loginPage;
    private RegisterPage registerPage;
    private WebDriverWait webDriverWait;

    @BeforeEach
    public void preTestsSetup() {
        chromeDriver.navigate().to(LOGIN_URL);

        loginPage = new LoginPage(chromeDriver);
    }

    @Test
    public void test_loginPageLoadingCorrectly() {

        Assertions.assertEquals(loginPage.LOGIN_PAGE_HEADER, loginPage.loginFormHead.getText());
        Assertions.assertNotNull(loginPage.loginFormUsernameInput);
        Assertions.assertNotNull(loginPage.loginFormUsernameInput);
    }

    @Test
    public void test_loginUser_nonExistingUser() {

        loginPage.loginUser("noSuchUsername", "noSuchPassword");

        webDriverWait = new WebDriverWait(chromeDriver, Duration.ofMinutes(5));

        WebElement loginErrorMsg = webDriverWait
                .until(ExpectedConditions.visibilityOf(loginPage.getLoginErrorMsg()));

        Assertions.assertEquals(loginPage.LOGIN_ERROR_MESSAGE, loginErrorMsg.getText());
    }

    @Test
    public void test_loginUser_existingUser() {
        chromeDriver.navigate().to(REGISTER_URL);

        registerPage = new RegisterPage(chromeDriver);

        int randInt = new Random().nextInt(99999);
        String testFirstName = "testFirstName" + randInt;
        String testLastName = "testLastName" + randInt;
        String testUsername = "testUserName" + randInt;
        String testEmail = "test" + randInt + "@gmail.com";
        String testPassword = "12345";
        registerPage.registerUser(testFirstName, testLastName, testUsername,
                testEmail, testPassword, testPassword);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser(testUsername, testPassword);

        Assertions.assertEquals( HOME_URL, chromeDriver.getCurrentUrl());
    }
}
