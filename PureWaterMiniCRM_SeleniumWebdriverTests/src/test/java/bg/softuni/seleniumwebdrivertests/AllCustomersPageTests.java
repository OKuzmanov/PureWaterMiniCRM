package bg.softuni.seleniumwebdrivertests;

import bg.softuni.pages.guest.LoginPage;
import bg.softuni.pages.logged.AllCustomersPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AllCustomersPageTests extends BaseTest{

    private AllCustomersPage allCustomersPage;
    private LoginPage loginPage;

    @BeforeEach
    public void preTestsSetup() {
        chromeDriver.navigate().to(LOGIN_URL);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser("oleg4o", "12345");

        chromeDriver.navigate().to(ALL_CUSTOMERS_URL);

        allCustomersPage = new AllCustomersPage(chromeDriver);
    }

    @Test
    public void test_pageLoadsCorrectly() {
        int countTableColumns = allCustomersPage.getCountTableColumns();
        List<String> tableColumnNames = allCustomersPage.getTableColumnNames();

        Assertions.assertEquals(allCustomersPage.pageTitle, allCustomersPage.allCustomersHeader.getText());
        Assertions.assertNotNull(allCustomersPage.allCustomersTable);
        Assertions.assertEquals(6, countTableColumns);
        Assertions.assertEquals(allCustomersPage.companyNameCol, tableColumnNames.get(0));
        Assertions.assertEquals(allCustomersPage.emailCol, tableColumnNames.get(1));
        Assertions.assertEquals(allCustomersPage.addressCol, tableColumnNames.get(2));
        Assertions.assertEquals(allCustomersPage.phoneNumberCol, tableColumnNames.get(3));
        Assertions.assertEquals(allCustomersPage.descriptionCol, tableColumnNames.get(4));
    }
}
