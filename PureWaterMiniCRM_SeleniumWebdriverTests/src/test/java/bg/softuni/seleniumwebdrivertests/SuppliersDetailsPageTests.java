package bg.softuni.seleniumwebdrivertests;

import bg.softuni.pages.guest.LoginPage;
import bg.softuni.pages.guest.RegisterPage;
import bg.softuni.pages.logged.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.Random;

public class SuppliersDetailsPageTests extends BaseTest {

    private LoginPage loginPage;
    private AllSuppliersPage allSuppliersPage;
    private SupplierDetailsPage supplierDetailsPage;
    private RegisterPage registerPage;
    private UsersProfilePage usersProfilePage;
    private AddSupplierPage addSupplierPage;

    @Test
    public void test_pageLoadsCorrectly() {
        chromeDriver.navigate().to(LOGIN_URL);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser("oleg4o", "12345");

        chromeDriver.navigate().to(ALL_SUPPLIERS_URL);

        allSuppliersPage = new AllSuppliersPage(chromeDriver);

        WebElement firstSuppliersDetails = allSuppliersPage.getFirstSupplierDetails();

        if(firstSuppliersDetails == null) {
            Assertions.fail("No suppliers present in DB.");
        }

        firstSuppliersDetails.click();

        supplierDetailsPage = new SupplierDetailsPage(chromeDriver);

        Assertions.assertEquals(supplierDetailsPage.SUPPLIER_DETAILS_PAGE_HEADER, supplierDetailsPage.supplierDetailsTitle.getText());
        Assertions.assertNotNull(supplierDetailsPage.companyNameInput);
        Assertions.assertNotNull(supplierDetailsPage.emailInput);
        Assertions.assertNotNull(supplierDetailsPage.phoneNumberInput);
        Assertions.assertNotNull(supplierDetailsPage.addressInput);
        Assertions.assertNotNull(supplierDetailsPage.descriptionInput);
        Assertions.assertNotNull(supplierDetailsPage.updateBtn);
        Assertions.assertNotNull(supplierDetailsPage.deleteBtn);
    }

    @Test
    public void test_supplierDetailsUpdate_successfully() {
        chromeDriver.navigate().to(LOGIN_URL);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser("oleg4o", "12345");

        chromeDriver.navigate().to(ALL_SUPPLIERS_URL);

        allSuppliersPage = new AllSuppliersPage(chromeDriver);

        WebElement firstSupplierDetails = allSuppliersPage.getFirstSupplierDetails();

        if(firstSupplierDetails == null) {
            Assertions.fail("No suppliers present in DB.");
        }

        firstSupplierDetails.click();

        supplierDetailsPage = new SupplierDetailsPage(chromeDriver);

        String testDescription = "Updated description";
        supplierDetailsPage.descriptionInput.clear();
        supplierDetailsPage.descriptionInput.sendKeys(testDescription);

        supplierDetailsPage.updateBtn.click();

        Assertions.assertNotNull(supplierDetailsPage.getSuccessfulAlert());
        Assertions.assertEquals(supplierDetailsPage.SUCCESSFUL_UPDATE_ALERT, supplierDetailsPage.getSuccessfulAlert().getText());
    }

    @Test
    public void test_supplierDetailsUpdate_unsuccessfully() {
        chromeDriver.navigate().to(LOGIN_URL);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser("oleg4o", "12345");

        chromeDriver.navigate().to(ALL_SUPPLIERS_URL);

        allSuppliersPage = new AllSuppliersPage(chromeDriver);

        WebElement firstSupplierDetails = allSuppliersPage.getFirstSupplierDetails();

        if(firstSupplierDetails == null) {
            Assertions.fail("No suppliers present in DB.");
        }

        firstSupplierDetails.click();

        supplierDetailsPage = new SupplierDetailsPage(chromeDriver);

        supplierDetailsPage.descriptionInput.clear();

        supplierDetailsPage.updateBtn.click();

        Assertions.assertNotNull(supplierDetailsPage.getUnsuccessfulAlert());
        Assertions.assertEquals(supplierDetailsPage.UNSUCCESSFUL_UPDATE_ALERT, supplierDetailsPage.getUnsuccessfulAlert().getText());
    }

    @Test
    public void test_supplierDetailsUpdate_accessDenied() {
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

        chromeDriver.navigate().to(ALL_SUPPLIERS_URL);

        allSuppliersPage = new AllSuppliersPage(chromeDriver);

        WebElement firstSupplierDetails = allSuppliersPage.getFirstSupplierDetails();

        if(firstSupplierDetails == null) {
            Assertions.fail("No customers present in DB.");
        }

        firstSupplierDetails.click();

        supplierDetailsPage = new SupplierDetailsPage(chromeDriver);

        supplierDetailsPage.updateBtn.click();

        Assertions.assertEquals(ACCESS_DENIED, chromeDriver.getTitle());

        chromeDriver.navigate().to(USER_PROFILE_URL);

        usersProfilePage = new UsersProfilePage(chromeDriver);

        usersProfilePage.deleteBtn.click();
    }

    @Test
    public void test_supplierDetailsDelete() {
        chromeDriver.navigate().to(LOGIN_URL);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser("oleg4o", "12345");

        chromeDriver.navigate().to(ADD_SUPPLIER_URL);

        addSupplierPage = new AddSupplierPage(chromeDriver);

        int rnd = new Random().nextInt(999);
        String testCompanyName = "name" + rnd;
        String testEmail = rnd + "1@1.c";
        String testAddress = "address" + rnd;
        String testPhone = "1234567" + rnd;
        String testDescription = "description" + rnd;
        addSupplierPage.addSupplier(testCompanyName, testEmail, testAddress, testPhone, testDescription);

        chromeDriver.navigate().to(ALL_SUPPLIERS_URL);

        allSuppliersPage = new AllSuppliersPage(chromeDriver);

        int countColumnsBefore = allSuppliersPage.getCountTableRows();

        WebElement lastSupplierDetails = allSuppliersPage.getLastSupplierDetails();

        lastSupplierDetails.click();

        supplierDetailsPage = new SupplierDetailsPage(chromeDriver);

        supplierDetailsPage.deleteBtn.click();

        Assertions.assertEquals(ALL_SUPPLIERS_URL, chromeDriver.getCurrentUrl());

        allSuppliersPage = new AllSuppliersPage(chromeDriver);

        int countColumnsAfter = allSuppliersPage.getCountTableRows();

        Assertions.assertTrue(countColumnsBefore > countColumnsAfter);
    }

    @Test
    public void test_supplierDetailsDelete_AccessDenied() {
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

        chromeDriver.navigate().to(ALL_SUPPLIERS_URL);

        allSuppliersPage = new AllSuppliersPage(chromeDriver);

        WebElement firstSupplierDetails = allSuppliersPage.getLastSupplierDetails();

        if (firstSupplierDetails == null) {
            Assertions.fail("No suppliers present in DB.");
        }

        firstSupplierDetails.click();

        supplierDetailsPage = new SupplierDetailsPage(chromeDriver);

        supplierDetailsPage.deleteBtn.click();

        Assertions.assertEquals(ACCESS_DENIED, chromeDriver.getTitle());

        chromeDriver.navigate().to(USER_PROFILE_URL);

        usersProfilePage = new UsersProfilePage(chromeDriver);

        usersProfilePage.deleteBtn.click();
    }
}
