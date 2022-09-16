package bg.softuni.seleniumwebdrivertests;

import bg.softuni.pages.guest.IndexPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class IndexPageTests extends BaseTest{

    private IndexPage indexPage;

    @BeforeEach
    public void preTestsSetup() {
        chromeDriver.navigate().to(INDEX_URL);

        indexPage = new IndexPage(chromeDriver);
    }

    @Test
    void test_indexPageLoadsCorrectly() {

        String resultMessage = indexPage.welcomeMessage.getText();

        Assertions.assertEquals(indexPage.WELCOME_MESSAGE, resultMessage);
        Assertions.assertNotNull(indexPage.navigationBar);
        Assertions.assertNotNull(indexPage.footer);
    }

    @Test
    void test_testNavbarLinks_about() {

        indexPage.navigationBar.findElement(By.partialLinkText("About")).click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(ABOUT_URL, currentUrl);
    }

    @Test
    void test_testNavbarLinks_login() {

        indexPage.navigationBar.findElement(By.partialLinkText("Login")).click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(LOGIN_URL, currentUrl);
    }

    @Test
    void test_testNavbarLinks_register() {

        indexPage.navigationBar.findElement(By.partialLinkText("Register")).click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(REGISTER_URL, currentUrl);
    }

    @Test
    void test_testNavbarLinks_logo() {

        indexPage.navigationBar.findElement(By.cssSelector("a.navbar-brand")).click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(INDEX_URL, currentUrl);
    }
}
