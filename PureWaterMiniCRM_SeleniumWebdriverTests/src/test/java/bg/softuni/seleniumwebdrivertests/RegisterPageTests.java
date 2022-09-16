package bg.softuni.seleniumwebdrivertests;

import bg.softuni.pages.guest.RegisterPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class RegisterPageTests extends BaseTest{

    private RegisterPage registerPage;

    @BeforeEach
    public void preTestsSetup() {
        chromeDriver.navigate().to(REGISTER_URL);

        registerPage = new RegisterPage(chromeDriver);
    }

    @Test
    public void test_registerPageLoadingCorrectly() {

        Assertions.assertEquals(registerPage.REGISTER_PAGE_HEADER, registerPage.registerTitle.getText());
        Assertions.assertNotNull(registerPage.firstNameInput);
        Assertions.assertNotNull(registerPage.lastNameInput);
        Assertions.assertNotNull(registerPage.usernameInput);
        Assertions.assertNotNull(registerPage.emailInput);
        Assertions.assertNotNull(registerPage.passwordInput);
        Assertions.assertNotNull(registerPage.confirmPasswordInput);
    }

    @Test
    public void test_registerUser_invalidInput() {

        registerPage.firstNameInput.clear();
        registerPage.lastNameInput.clear();
        registerPage.usernameInput.clear();
        registerPage.emailInput.sendKeys("1@1.com");
        registerPage.passwordInput.clear();
        registerPage.confirmPasswordInput.clear();

        registerPage.submitBtn.click();

        Assertions.assertNotNull(registerPage.getFirstNameErrorValidation());
        Assertions.assertNotNull(registerPage.getLastNameErrorValidation());
        Assertions.assertNotNull(registerPage.getUsernameErrorValidation());
        Assertions.assertNotNull(registerPage.getPasswordErrorValidation());
        Assertions.assertNotNull(registerPage.getConfirmPasswordErrorValidation());
    }

    @Test
    public void test_registerUser_validInput() {

        int randInt = new Random().nextInt(99999);
        String testFirstName = "testFirstName" + randInt;
        String testLastName = "testLastName" + randInt;
        String testUsername = "testUserName" + randInt;
        String testEmail = "test" + randInt + "@gmail.com";
        String testPassword = "12345";

        registerPage.registerUser(testFirstName, testLastName, testUsername,
                testEmail, testPassword, testPassword);

        Assertions.assertEquals(LOGIN_URL, chromeDriver.getCurrentUrl());
    }

    @Test
    public void test_registerUser_usernameOccupied() {

        int randInt = new Random().nextInt(99999);
        String testFirstName = "testFirstName" + randInt;
        String testLastName = "testLastName" + randInt;
        String testUsername = "testUserName" + randInt;
        String testEmail = "test" + randInt + "@gmail.com";
        String testPassword = "12345";
        registerPage.registerUser(testFirstName, testLastName, testUsername,
                testEmail, testPassword, testPassword);

        chromeDriver.navigate().to(REGISTER_URL);

        registerPage = new RegisterPage(chromeDriver);

        registerPage.firstNameInput.sendKeys("12345");
        registerPage.lastNameInput.sendKeys("12345");
        registerPage.usernameInput.sendKeys(testUsername);
        registerPage.emailInput.sendKeys("1@1.com");
        registerPage.passwordInput.sendKeys("12345");
        registerPage.confirmPasswordInput.sendKeys("12345");

        registerPage.submitBtn.click();

        Assertions.assertEquals( registerPage.USERNAME_OCCUPIED_ERROR, registerPage.getUsernameErrorValidation().getText());
    }

    @Test
    public void test_registerUser_emailOccupied() {

        int randInt = new Random().nextInt(99999);
        String testFirstName = "testFirstName" + randInt;
        String testLastName = "testLastName" + randInt;
        String testUsername = "testUserName" + randInt;
        String testEmail = "test" + randInt + "@gmail.com";
        String testPassword = "12345";
        registerPage.registerUser(testFirstName, testLastName, testUsername,
                testEmail, testPassword, testPassword);

        chromeDriver.navigate().to(REGISTER_URL);

        registerPage = new RegisterPage(chromeDriver);

        registerPage.firstNameInput.sendKeys("12345");
        registerPage.lastNameInput.sendKeys("12345");
        registerPage.usernameInput.sendKeys("1111111");
        registerPage.emailInput.sendKeys(testEmail);
        registerPage.passwordInput.sendKeys("12345");
        registerPage.confirmPasswordInput.sendKeys("12345");

        registerPage.submitBtn.click();

        Assertions.assertEquals( registerPage.EMAIL_OCCUPIED_ERROR, registerPage.getEmailErrorValidation().getText());
    }

    @Test
    public void test_registerUser_passwordsNotMatch() {

        registerPage.firstNameInput.sendKeys("12345");
        registerPage.lastNameInput.sendKeys("12345");
        registerPage.usernameInput.sendKeys("1111111");
        registerPage.emailInput.sendKeys("1@1.com");
        registerPage.passwordInput.sendKeys("testPass");
        registerPage.confirmPasswordInput.sendKeys("differentPass");

        registerPage.submitBtn.click();

        Assertions.assertEquals( registerPage.PASSWORD_MATCH_ERROR, registerPage.getConfirmPasswordErrorValidation().getText());
    }
}
