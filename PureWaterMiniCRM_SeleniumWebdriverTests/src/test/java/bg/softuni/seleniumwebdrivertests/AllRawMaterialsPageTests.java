package bg.softuni.seleniumwebdrivertests;

import bg.softuni.pages.guest.LoginPage;
import bg.softuni.pages.logged.AllRawMaterialsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AllRawMaterialsPageTests extends BaseTest{

    private AllRawMaterialsPage allRawMaterialsPage;
    private LoginPage loginPage;

    @BeforeEach
    public void preTestsSetup() {
        chromeDriver.navigate().to(LOGIN_URL);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser("oleg4o", "12345");

        chromeDriver.navigate().to(ALL_RAW_MATERIALS_URL);

        allRawMaterialsPage = new AllRawMaterialsPage(chromeDriver);
    }

    @Test
    public void test_pageLoadsCorrectly() {
        int countTableColumns = allRawMaterialsPage.getCountTableColumns();
        List<String> tableColumnNames = allRawMaterialsPage.getTableColumnNames();

        Assertions.assertEquals(allRawMaterialsPage.pageTitle, allRawMaterialsPage.allRawMaterialsHeader.getText());
        Assertions.assertNotNull(allRawMaterialsPage.allRawMaterialsTable);
        Assertions.assertEquals(5, countTableColumns);
        Assertions.assertEquals(allRawMaterialsPage.typeCol, tableColumnNames.get(0));
        Assertions.assertEquals(allRawMaterialsPage.quantityCol, tableColumnNames.get(1));
        Assertions.assertEquals(allRawMaterialsPage.deliveryDateCol, tableColumnNames.get(2));
        Assertions.assertEquals(allRawMaterialsPage.supplierCol, tableColumnNames.get(3));
    }
}
