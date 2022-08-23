package bg.softuni;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.net.URL;

public class ChromeTest {
    private static final String CHROME_DRIVER_FILE_NAME = "chromedriver";

    private ChromeDriver chromeDriver;

    @BeforeEach
    void setup() {

        //Works
//        ChromeDriverService service = new ChromeDriverService.Builder()
//                .usingDriverExecutable(new File("C:\\Users\\Oleg\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe"))
//                .build();

        //TODO: Doesn't work
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(this.getChromeDriverFileName()))
                .build();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--start-maximized");

        chromeDriver = new ChromeDriver(service, chromeOptions);
    }

    @AfterEach
    void tearDown() {
        chromeDriver.quit();
    }

    @Test
    void testBoot() {
        chromeDriver.navigate().to("https://pure-water-mini-crm.herokuapp.com/");
    }


    private String getChromeDriverFileName() {
        ClassLoader classLoader = ChromeTest.class.getClassLoader();

        URL driverURL = classLoader.getResource(CHROME_DRIVER_FILE_NAME);

        //TODO: Compile time error - can't find class java.util.function.Supplier
//        if (driverURL == null) {
//            Assertions.fail("Unable to locate chrome driver");
//        }

        return driverURL.getFile();
    }
}
