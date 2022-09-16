package bg.softuni.seleniumwebdrivertests;

import bg.softuni.pages.guest.LoginPage;
import bg.softuni.pages.guest.RegisterPage;
import bg.softuni.pages.logged.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.Random;

public class OrderDetailsPageTests extends BaseTest{

    private LoginPage loginPage;
    private HomePage homePage;
    private OrderDetailsPage orderDetailsPage;
    private RegisterPage registerPage;
    private UsersProfilePage usersProfilePage;

    @Test
    public void test_pageLoadsCorrectly() {
        chromeDriver.navigate().to(LOGIN_URL);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser("oleg4o", "12345");

        homePage = new HomePage(chromeDriver);

        WebElement firstOrderDetailsLink = homePage.getFirstDetailsLinkFromOrdersTable();

        if(firstOrderDetailsLink == null) {
            Assertions.fail("No orders present in DB.");
        }

        firstOrderDetailsLink.click();

         orderDetailsPage = new OrderDetailsPage(chromeDriver);

        Assertions.assertEquals(orderDetailsPage.ORDER_DETAILS_PAGE_HEADER, orderDetailsPage.orderDetailsTitle.getText());
        Assertions.assertNotNull(orderDetailsPage.orderNameInput);
        Assertions.assertNotNull(orderDetailsPage.quantityInput);
        Assertions.assertNotNull(orderDetailsPage.productCategoryInput);
        Assertions.assertNotNull(orderDetailsPage.expiryDateInput);
        Assertions.assertNotNull(orderDetailsPage.descriptionInput);
        Assertions.assertNotNull(orderDetailsPage.customerInput);
        Assertions.assertNotNull(orderDetailsPage.updateBtn);
        Assertions.assertNotNull(orderDetailsPage.deleteBtn);
    }

    @Test
    public void test_orderDetailsUpdate_unsuccessfully() {
        chromeDriver.navigate().to(LOGIN_URL);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser("oleg4o", "12345");

        homePage = new HomePage(chromeDriver);

        WebElement firstOrderDetailsLink = homePage.getFirstDetailsLinkFromOrdersTable();

        if(firstOrderDetailsLink == null) {
            Assertions.fail("No orders present in DB.");
        }

        firstOrderDetailsLink.click();

        orderDetailsPage = new OrderDetailsPage(chromeDriver);

        orderDetailsPage.updateBtn.click();

        Assertions.assertNotNull(orderDetailsPage.getUnsuccessfulAlert());
        Assertions.assertEquals(orderDetailsPage.UNSUCCESSFUL_UPDATE_ALERT, orderDetailsPage.getUnsuccessfulAlert().getText());
    }

    @Test
    public void test_orderDetailsUpdate_accessDenied() {
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

        WebElement firstOrderDetailsLink = homePage.getFirstDetailsLinkFromOrdersTable();

        if(firstOrderDetailsLink == null) {
            Assertions.fail("No orders present in DB.");
        }

        firstOrderDetailsLink.click();

        orderDetailsPage = new OrderDetailsPage(chromeDriver);

        orderDetailsPage.updateBtn.click();

        Assertions.assertEquals(ACCESS_DENIED, chromeDriver.getTitle());

        chromeDriver.navigate().to(USER_PROFILE_URL);

        usersProfilePage = new UsersProfilePage(chromeDriver);

        usersProfilePage.deleteBtn.click();
    }

    @Test
    public void test_orderDetailsDelete_accessDenied() {
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

        WebElement firstOrderDetailsLink = homePage.getFirstDetailsLinkFromOrdersTable();

        if(firstOrderDetailsLink == null) {
            Assertions.fail("No orders present in DB.");
        }

        firstOrderDetailsLink.click();

        orderDetailsPage = new OrderDetailsPage(chromeDriver);

        orderDetailsPage.deleteBtn.click();

        Assertions.assertEquals(ACCESS_DENIED, chromeDriver.getTitle());

        chromeDriver.navigate().to(USER_PROFILE_URL);

        usersProfilePage = new UsersProfilePage(chromeDriver);

        usersProfilePage.deleteBtn.click();
    }

    @Test
    public void test_orderDetailsDelete_successful() {
        chromeDriver.navigate().to(LOGIN_URL);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser("oleg4o", "12345");

        homePage = new HomePage(chromeDriver);

        int beforeRows = homePage.getOrdersTableRows().size();

        WebElement firstOrderDetailsLink = homePage.getFirstDetailsLinkFromOrdersTable();

        if(firstOrderDetailsLink == null) {
            Assertions.fail("No orders present in DB.");
        }

        firstOrderDetailsLink.click();

        orderDetailsPage = new OrderDetailsPage(chromeDriver);

        orderDetailsPage.deleteBtn.click();

        Assertions.assertEquals(HOME_URL, chromeDriver.getCurrentUrl());

        homePage = new HomePage(chromeDriver);

        int afterRows = homePage.getOrdersTableRows().size();

        Assertions.assertTrue(beforeRows > afterRows);
    }
}
