package bg.softuni.seleniumwebdrivertests;

import bg.softuni.pages.guest.LoginPage;
import bg.softuni.pages.guest.RegisterPage;
import bg.softuni.pages.logged.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.Random;

public class CustomersDetailsPageTests extends BaseTest{

    private RegisterPage registerPage;
    private AddCustomerPage addCustomerPage;
    private CustomerDetailsPage customerDetailsPage;
    private AllCustomersPage allCustomersPage;
    private LoginPage loginPage;
    private UsersProfilePage usersProfilePage;

    @Test
    public void test_pageLoadsCorrectly() {
        chromeDriver.navigate().to(LOGIN_URL);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser("oleg4o", "12345");

        chromeDriver.navigate().to(ALL_CUSTOMERS_URL);

        allCustomersPage = new AllCustomersPage(chromeDriver);

        WebElement firstCustomerDetails = allCustomersPage.getFirstCustomerDetails();

        if(firstCustomerDetails == null) {
            Assertions.fail("No customers present in DB.");
        }

        firstCustomerDetails.click();

        customerDetailsPage = new CustomerDetailsPage(chromeDriver);

        Assertions.assertEquals(customerDetailsPage.CUSTOMER_DETAILS_PAGE_HEADER, customerDetailsPage.customerDetailsTitle.getText());
        Assertions.assertNotNull(customerDetailsPage.companyNameInput);
        Assertions.assertNotNull(customerDetailsPage.emailInput);
        Assertions.assertNotNull(customerDetailsPage.phoneNumberInput);
        Assertions.assertNotNull(customerDetailsPage.addressInput);
        Assertions.assertNotNull(customerDetailsPage.descriptionInput);
        Assertions.assertNotNull(customerDetailsPage.updateBtn);
        Assertions.assertNotNull(customerDetailsPage.deleteBtn);
    }

    @Test
    public void test_customerDetailsUpdate_successfully() {
        chromeDriver.navigate().to(LOGIN_URL);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser("oleg4o", "12345");

        chromeDriver.navigate().to(ALL_CUSTOMERS_URL);

        allCustomersPage = new AllCustomersPage(chromeDriver);

        WebElement firstCustomerDetails = allCustomersPage.getFirstCustomerDetails();

        if(firstCustomerDetails == null) {
            Assertions.fail("No customers present in DB.");
        }

        firstCustomerDetails.click();

        customerDetailsPage = new CustomerDetailsPage(chromeDriver);

        String testDescription = "Updated description";
        customerDetailsPage.descriptionInput.clear();
        customerDetailsPage.descriptionInput.sendKeys(testDescription);

        customerDetailsPage.updateBtn.click();

        Assertions.assertNotNull(customerDetailsPage.getSuccessfulAlert());
        Assertions.assertEquals(customerDetailsPage.SUCCESSFUL_UPDATE_ALERT, customerDetailsPage.getSuccessfulAlert().getText());
    }

    @Test
    public void test_customerDetailsUpdate_unsuccessfully() {
        chromeDriver.navigate().to(LOGIN_URL);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser("oleg4o", "12345");

        chromeDriver.navigate().to(ALL_CUSTOMERS_URL);

        allCustomersPage = new AllCustomersPage(chromeDriver);

        WebElement firstCustomerDetails = allCustomersPage.getFirstCustomerDetails();

        if(firstCustomerDetails == null) {
            Assertions.fail("No customers present in DB.");
        }

        firstCustomerDetails.click();

        customerDetailsPage = new CustomerDetailsPage(chromeDriver);

        customerDetailsPage.descriptionInput.clear();

        customerDetailsPage.updateBtn.click();

        Assertions.assertNotNull(customerDetailsPage.getUnsuccessfulAlert());
        Assertions.assertEquals(customerDetailsPage.UNSUCCESSFUL_UPDATE_ALERT, customerDetailsPage.getUnsuccessfulAlert().getText());
    }

    @Test
    public void test_customerDetailsUpdate_accessDenied() {
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

        chromeDriver.navigate().to(ALL_CUSTOMERS_URL);

        allCustomersPage = new AllCustomersPage(chromeDriver);

        WebElement firstCustomerDetails = allCustomersPage.getFirstCustomerDetails();

        if(firstCustomerDetails == null) {
            Assertions.fail("No customers present in DB.");
        }

        firstCustomerDetails.click();

        customerDetailsPage = new CustomerDetailsPage(chromeDriver);

        customerDetailsPage.updateBtn.click();

        Assertions.assertEquals(ACCESS_DENIED, chromeDriver.getTitle());

        chromeDriver.navigate().to(USER_PROFILE_URL);

        usersProfilePage = new UsersProfilePage(chromeDriver);

        usersProfilePage.deleteBtn.click();
    }

    @Test
    public void test_customerDetailsDelete() {
        chromeDriver.navigate().to(LOGIN_URL);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser("oleg4o", "12345");

        chromeDriver.navigate().to(ADD_CUSTOMER_URL);

        addCustomerPage = new AddCustomerPage(chromeDriver);

        int rnd = new Random().nextInt(999);
        String testCompanyName = "name" + rnd;
        String testEmail = rnd + "1@1.c";
        String testAddress = "address" + rnd;
        String testPhone = "1234567" + rnd;
        String testDescription = "description" + rnd;
        addCustomerPage.addCustomer(testCompanyName, testEmail, testAddress, testPhone, testDescription);

        chromeDriver.navigate().to(ALL_CUSTOMERS_URL);

        allCustomersPage = new AllCustomersPage(chromeDriver);

        int countColumnsBefore = allCustomersPage.getCountTableRows();

        WebElement lastCustomerDetails = allCustomersPage.getLastCustomerDetails();

        lastCustomerDetails.click();

        customerDetailsPage = new CustomerDetailsPage(chromeDriver);

        customerDetailsPage.deleteBtn.click();

        Assertions.assertEquals(ALL_CUSTOMERS_URL, chromeDriver.getCurrentUrl());

        allCustomersPage = new AllCustomersPage(chromeDriver);

        int countColumnsAfter = allCustomersPage.getCountTableRows();

        Assertions.assertTrue(countColumnsBefore > countColumnsAfter);
    }

    @Test
    public void test_customerDetailsDelete_AccessDenied() {
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

        chromeDriver.navigate().to(ALL_CUSTOMERS_URL);

        allCustomersPage = new AllCustomersPage(chromeDriver);

        WebElement firstCustomerDetails = allCustomersPage.getFirstCustomerDetails();

        if(firstCustomerDetails == null) {
            Assertions.fail("No present customers in DB.");
        }

        firstCustomerDetails.click();

        customerDetailsPage = new CustomerDetailsPage(chromeDriver);

        customerDetailsPage.deleteBtn.click();

        Assertions.assertEquals(ACCESS_DENIED, chromeDriver.getTitle());

        chromeDriver.navigate().to(USER_PROFILE_URL);

        usersProfilePage = new UsersProfilePage(chromeDriver);

        usersProfilePage.deleteBtn.click();
    }
}
