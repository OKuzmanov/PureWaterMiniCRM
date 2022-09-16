package bg.softuni.seleniumwebdrivertests;

import bg.softuni.pages.guest.LoginPage;
import bg.softuni.pages.guest.RegisterPage;
import bg.softuni.pages.logged.HomePage;
import bg.softuni.pages.logged.UsersProfilePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.Random;

public class UserProfilePageTests extends BaseTest{

    private UsersProfilePage usersProfilePage;
    private RegisterPage registerPage;
    private LoginPage loginPage;
    private HomePage homePage;
    @Test
    public void test_pageLoadsCorrectly_Admin() {
        //Arrange
        chromeDriver.navigate().to(LOGIN_URL);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser("oleg4o", "12345");

        chromeDriver.navigate().to(USER_PROFILE_URL);

        usersProfilePage = new UsersProfilePage(chromeDriver);

        //Assert
        Assertions.assertEquals(usersProfilePage.USER_PROFILE_PAGE_HEADER, usersProfilePage.userProfileTitle.getText());
        Assertions.assertNotNull(usersProfilePage.firstNameInput);
        Assertions.assertNotNull(usersProfilePage.lastNameInput);
        Assertions.assertNotNull(usersProfilePage.usernameInput);
        Assertions.assertNotNull(usersProfilePage.emailInput);
        Assertions.assertNotNull(usersProfilePage.passwordInput);
        Assertions.assertNotNull(usersProfilePage.deleteBtn);
        Assertions.assertNotNull(usersProfilePage.updateBtn);
        Assertions.assertNotNull(usersProfilePage.getUserRole());
        Assertions.assertNotNull(usersProfilePage.getAdminRole());
    }

    @Test
    public void test_pageLoadsCorrectly_User() {
        //Arrange
        chromeDriver.navigate().to(LOGIN_URL);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser("benati", "12345");

        chromeDriver.navigate().to(USER_PROFILE_URL);

        usersProfilePage = new UsersProfilePage(chromeDriver);

        //Assert
        Assertions.assertEquals(usersProfilePage.USER_PROFILE_PAGE_HEADER, usersProfilePage.userProfileTitle.getText());
        Assertions.assertNotNull(usersProfilePage.firstNameInput);
        Assertions.assertNotNull(usersProfilePage.lastNameInput);
        Assertions.assertNotNull(usersProfilePage.usernameInput);
        Assertions.assertNotNull(usersProfilePage.emailInput);
        Assertions.assertNotNull(usersProfilePage.passwordInput);
        Assertions.assertNotNull(usersProfilePage.deleteBtn);
        Assertions.assertNotNull(usersProfilePage.updateBtn);
        Assertions.assertThrows(NoSuchElementException.class,() -> usersProfilePage.getUserRole());
        Assertions.assertThrows(NoSuchElementException.class,() -> usersProfilePage.getAdminRole());
    }

    @Test
    public void test_UpdateUserProfile_Successful() {
        //Arrange
        chromeDriver.navigate().to(LOGIN_URL);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser("oleg4o", "12345");

        chromeDriver.navigate().to(USER_PROFILE_URL);

        usersProfilePage = new UsersProfilePage(chromeDriver);

        //Act
        usersProfilePage.passwordInput.sendKeys("12345");
        usersProfilePage.updateBtn.click();

        //Assert
        Assertions.assertEquals(usersProfilePage.SUCCESSFUL_UPDATE_ALERT, usersProfilePage.getSuccessfulAlert().getText());
    }

    @Test
    public void test_UpdateUserProfile_Unsuccessful() {
        //Arrange
        chromeDriver.navigate().to(LOGIN_URL);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser("oleg4o", "12345");

        chromeDriver.navigate().to(USER_PROFILE_URL);

        usersProfilePage = new UsersProfilePage(chromeDriver);

        //Act
        usersProfilePage.passwordInput.clear();
        usersProfilePage.updateBtn.click();

        //Assert
        Assertions.assertEquals(usersProfilePage.UNSUCCESSFUL_UPDATE_ALERT, usersProfilePage.getUnsuccessfulAlert().getText());
    }

    @Test
    public void test_DeleteUserProfile() {
        int rnd = new Random().nextInt( 9999);

        String testFirstName = "fname" + rnd;
        String testLastName = "lname" + rnd;
        String testUsername = "user" + rnd;
        String testEmail =  rnd + "@1.c";
        String testPassword = "12345" + rnd;
        String testConfirmPassword = "12345" + rnd;

        //Arrange
        chromeDriver.navigate().to(REGISTER_URL);

        registerPage = new RegisterPage(chromeDriver);

        registerPage.registerUser(testFirstName, testLastName, testUsername, testEmail, testPassword, testConfirmPassword);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser(testUsername, testPassword);

        chromeDriver.navigate().to(USER_PROFILE_URL);

        usersProfilePage = new UsersProfilePage(chromeDriver);

        //Act
        usersProfilePage.deleteBtn.click();

        //Assert
        Assertions.assertEquals(INDEX_URL, chromeDriver.getCurrentUrl());
    }

    @Test
    public void test_userProfileUpdate_accessDenied() {
        chromeDriver.navigate().to(REGISTER_URL);

        registerPage = new RegisterPage(chromeDriver);

        int rnd = new Random().nextInt( 9999);
        String testFirstName = "fname" + rnd;
        String testLastName = "lname" + rnd;
        String testUsername = "user" + rnd;
        String testEmail =  rnd + "@1.c";
        String testPassword = "12345" + rnd;
        String testConfirmPassword = "12345" + rnd;
        registerPage.registerUser(testFirstName, testLastName, testUsername,
                testEmail, testPassword, testConfirmPassword);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser(testUsername, testPassword);

        homePage = new HomePage(chromeDriver);

        WebElement firstDetailsLinkFromUserTable = homePage.getFirstDetailsLinkFromUserTable();

        if (firstDetailsLinkFromUserTable == null) {
            Assertions.fail("No users in the DB.");
        }

        firstDetailsLinkFromUserTable.click();

        usersProfilePage = new UsersProfilePage(chromeDriver);

        usersProfilePage.updateBtn.click();

        Assertions.assertEquals(ACCESS_DENIED, chromeDriver.getTitle());

        chromeDriver.navigate().to(USER_PROFILE_URL);

        usersProfilePage = new UsersProfilePage(chromeDriver);

        usersProfilePage.deleteBtn.click();
    }

    @Test
    public void test_userProfileDelete_accessDenied() {
        chromeDriver.navigate().to(REGISTER_URL);

        registerPage = new RegisterPage(chromeDriver);

        int rnd = new Random().nextInt( 9999);
        String testFirstName = "fname" + rnd;
        String testLastName = "lname" + rnd;
        String testUsername = "user" + rnd;
        String testEmail =  rnd + "@1.c";
        String testPassword = "12345" + rnd;
        String testConfirmPassword = "12345" + rnd;
        registerPage.registerUser(testFirstName, testLastName, testUsername,
                testEmail, testPassword, testConfirmPassword);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser(testUsername, testPassword);

        homePage = new HomePage(chromeDriver);

        WebElement firstDetailsLinkFromUserTable = homePage.getFirstDetailsLinkFromUserTable();

        if (firstDetailsLinkFromUserTable == null) {
            Assertions.fail("No users in the DB.");
        }

        firstDetailsLinkFromUserTable.click();

        usersProfilePage = new UsersProfilePage(chromeDriver);

        usersProfilePage.deleteBtn.click();

        Assertions.assertEquals(ACCESS_DENIED, chromeDriver.getTitle());

        chromeDriver.navigate().to(USER_PROFILE_URL);

        usersProfilePage = new UsersProfilePage(chromeDriver);

        usersProfilePage.deleteBtn.click();
    }
}
