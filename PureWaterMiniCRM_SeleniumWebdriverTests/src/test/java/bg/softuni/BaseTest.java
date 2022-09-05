package bg.softuni;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.net.URL;

public class BaseTest {
    protected static final String CHROME_DRIVER_FILE_NAME = "chromedriver.exe";

    protected final String INDEX_URL = "http://localhost:8080/";
    protected final String ABOUT_URL = "http://localhost:8080/about";
    protected final String LOGIN_URL = "http://localhost:8080/users/login";
    protected final String REGISTER_URL = "http://localhost:8080/users/register";
    protected final String HOME_URL = "http://localhost:8080/home";
    protected final String ADD_ORDER_URL = "http://localhost:8080/orders/add";
    protected final String ADD_CUSTOMER_URL = "http://localhost:8080/customers/add";
    protected final String ADD_SUPPLIER_URL = "http://localhost:8080/suppliers/add";
    protected final String ADD_PRODUCTS_URL = "http://localhost:8080/products/add";
    protected final String ADD_RAW_MATERIALS_URL = "http://localhost:8080/materials/add";
    protected final String ORDERS_HISTORY_URL = "http://localhost:8080/orders/history";
    protected final String ALL_CUSTOMERS_URL = "http://localhost:8080/customers/all";
    protected final String ALL_SUPPLIERS_URL = "http://localhost:8080/suppliers/all";
    protected final String ALL_PRODUCTS_URL = "http://localhost:8080/products/all";
    protected final String ALL_RAW_MATERIALS_URL = "http://localhost:8080/materials/all";
    protected final String USER_PROFILE_URL = "http://localhost:8080/users/profile";
    protected final String ACCESS_DENIED = "Access Denied";

    protected static ChromeDriver chromeDriver;

    @BeforeAll
    public static void setup() {

//        ChromeDriverService service = new ChromeDriverService.Builder()
//                .usingDriverExecutable(new File("C:\\Users\\Oleg\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe"))
//                .build();

        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(getChromeDriverFileName()))
                .build();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--start-maximized");

        chromeDriver = new ChromeDriver(service, chromeOptions);
    }

    @AfterEach
    void deleteAllCookies() {
        chromeDriver.manage().deleteAllCookies();
    }

    @AfterAll
    public static void tearDown() {
        chromeDriver.quit();
    }

    private static String getChromeDriverFileName() {
        ClassLoader classLoader = BaseTest.class.getClassLoader();

        URL driverURL = classLoader.getResource(CHROME_DRIVER_FILE_NAME);

        if (driverURL == null) {
            Assertions.fail("Unable to locate chrome driver");
        }

        return driverURL.getFile();
    }
}
