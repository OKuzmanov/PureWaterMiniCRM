package bg.softuni.pages;

import org.openqa.selenium.chrome.ChromeDriver;

public class BasePage {
    protected ChromeDriver driver;

    public BasePage(ChromeDriver driver) {
        this.driver = driver;
    }

}
