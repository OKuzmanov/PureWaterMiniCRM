package bg.softuni.seleniumwebdrivertests;

import bg.softuni.pages.guest.LoginPage;
import bg.softuni.pages.logged.AllProductsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AllProductsPageTests extends BaseTest{

    private AllProductsPage allProducts;
    private LoginPage loginPage;

    @BeforeEach
    public void preTestsSetup() {
        chromeDriver.navigate().to(LOGIN_URL);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser("oleg4o", "12345");

        chromeDriver.navigate().to(ALL_PRODUCTS_URL);

        allProducts = new AllProductsPage(chromeDriver);
    }

    @Test
    public void test_pageLoadsCorrectly() {
        int countTableColumns = allProducts.getCountTableColumns();
        List<String> tableColumnNames = allProducts.getTableColumnNames();

        Assertions.assertEquals(allProducts.pageTitle, allProducts.allProductsHeader.getText());
        Assertions.assertNotNull(allProducts.allProductsTable);
        Assertions.assertEquals(4, countTableColumns);
        Assertions.assertEquals(allProducts.typeCol, tableColumnNames.get(0));
        Assertions.assertEquals(allProducts.quantityCol, tableColumnNames.get(1));
        Assertions.assertEquals(allProducts.productionDateCol, tableColumnNames.get(2));
    }
}
