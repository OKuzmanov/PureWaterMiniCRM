package bg.softuni.seleniumwebdrivertests;

import bg.softuni.pages.guest.LoginPage;
import bg.softuni.pages.logged.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class HomePageTests extends BaseTest{

    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeEach
    void logUser() {
        chromeDriver.navigate().to(LOGIN_URL);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser("oleg4o", "12345");

        this.homePage = new HomePage(chromeDriver);
    }

    @Test
    public void test_homePageLoadsCorrectly() {

        Assertions.assertEquals(homePage.HOME_PAGE_HEADER, homePage.homeHeader.getText());
        Assertions.assertEquals(homePage.ORDERS_TABLE_HEADER, homePage.ordersTableHeader.getText());
        Assertions.assertEquals(homePage.EMPLOYEES_TABLE_HEADER, homePage.employeesTableHeader.getText());
        Assertions.assertNotNull(homePage.ordersTable);
        Assertions.assertNotNull(homePage.usersTable);
    }

    @Test
    void test_testNavbarLinks_addOrder() {

        homePage.navigationBar.findElement(By.partialLinkText("Add Order")).click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(ADD_ORDER_URL, currentUrl);
    }

    @Test
    void test_testNavbarLinks_addCustomer() {

        homePage.navigationBar.findElement(By.partialLinkText("Add Customer")).click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(ADD_CUSTOMER_URL, currentUrl);
    }

    @Test
    void test_testNavbarLinks_addSupplier() {

        homePage.navigationBar.findElement(By.partialLinkText("Add Supplier")).click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(ADD_SUPPLIER_URL, currentUrl);
    }

    @Test
    void test_testNavbarLinks_addProducts() {

        homePage.navigationBar.findElement(By.partialLinkText("Add Products")).click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(ADD_PRODUCTS_URL, currentUrl);
    }

    @Test
    void test_testNavbarLinks_addRawMaterials() {

        homePage.navigationBar.findElement(By.partialLinkText("Add Raw Materials")).click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(ADD_RAW_MATERIALS_URL, currentUrl);
    }

    @Test
    void test_testNavbarLinks_ordersHistory() {

        homePage.navigationBar.findElement(By.partialLinkText("Orders History")).click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(ORDERS_HISTORY_URL, currentUrl);
    }

    @Test
    void test_testNavbarLinks_allCustomers() {

        homePage.getAllCustomers().click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(ALL_CUSTOMERS_URL, currentUrl);
    }

    @Test
    void test_testNavbarLinks_allSuppliers() {

        homePage.getAllSuppliers().click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(ALL_SUPPLIERS_URL, currentUrl);
    }

    @Test
    void test_testNavbarLinks_allProducts() {

        homePage.getAllProducts().click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(ALL_PRODUCTS_URL, currentUrl);
    }

    @Test
    void test_testNavbarLinks_allRawMaterials() {

        homePage.getAllRawMaterials().click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(ALL_RAW_MATERIALS_URL, currentUrl);
    }

    @Test
    void test_testNavbarLinks_userProfile() {

        homePage.navigationBar.findElement(By.cssSelector("li:nth-child(8) > div > div:nth-child(1) > a")).click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(USER_PROFILE_URL, currentUrl);
    }

    @Test
    void test_testNavbarLinks_logout() {

        homePage.navigationBar.findElement(By.cssSelector("li:nth-child(9) > form > input.btn.btn-link.nav-link")).click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(INDEX_URL, currentUrl);
    }

    @Test
    void test_orderTableReadyLink_NotEnoughProductsInStorageErr() {
        this.homePage.getFirstReadyLinkOrderTable().click();

        Assertions.assertNotNull(homePage.getNotEnoughProductsInStorageAlert());
        Assertions.assertEquals(homePage.NOT_ENOUGH_PRODUCTS, homePage.getNotEnoughProductsInStorageAlert().getText());
    }
}
