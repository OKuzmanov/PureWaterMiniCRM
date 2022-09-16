package bg.softuni.seleniumwebdrivertests;

import bg.softuni.pages.guest.LoginPage;
import bg.softuni.pages.logged.AddSupplierPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class AddSupplierPageTests extends BaseTest{
    private AddSupplierPage addSupplierPage;
    private LoginPage loginPage;

    @BeforeEach
    public void preTestsSetup() {
        chromeDriver.navigate().to(LOGIN_URL);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser("oleg4o", "12345");

        chromeDriver.navigate().to(ADD_SUPPLIER_URL);

        addSupplierPage = new AddSupplierPage(chromeDriver);
    }

    @Test
    public void test_pageLoadsCorrectly() {
        Assertions.assertEquals(addSupplierPage.pageTitle, addSupplierPage.addSupplierTitle.getText());
        Assertions.assertNotNull(addSupplierPage.companyNameInput);
        Assertions.assertNotNull(addSupplierPage.emailInput);
        Assertions.assertNotNull(addSupplierPage.addressInput);
        Assertions.assertNotNull(addSupplierPage.phoneNumberInput);
        Assertions.assertNotNull(addSupplierPage.descriptionInput);
        Assertions.assertNotNull(addSupplierPage.addSupplierBtn);
    }

    @Test
    public void test_validationsWorkCorrectly() {
        addSupplierPage.emailInput.sendKeys("1@1.com");
        addSupplierPage.addSupplierBtn.click();
        Assertions.assertNotNull(addSupplierPage.getCompanyNameInputError());
        Assertions.assertNotNull(addSupplierPage.getAddressInputError());
        Assertions.assertNotNull(addSupplierPage.getPhoneNumberInputError());
        Assertions.assertNotNull(addSupplierPage.getDescriptionInputError());
    }

    @Test
    public void test_createCustomer_validInput() {
        int rnd = new Random().nextInt(999);
        addSupplierPage.companyNameInput.sendKeys("test name" + rnd);
        addSupplierPage.emailInput.sendKeys(rnd + "1@1.com");
        addSupplierPage.addressInput.sendKeys("test address" + rnd);
        addSupplierPage.phoneNumberInput.sendKeys("12345678" + rnd);
        addSupplierPage.descriptionInput.sendKeys("test description" + rnd);
        addSupplierPage.addSupplierBtn.click();

        Assertions.assertEquals(HOME_URL, chromeDriver.getCurrentUrl());
    }
}
