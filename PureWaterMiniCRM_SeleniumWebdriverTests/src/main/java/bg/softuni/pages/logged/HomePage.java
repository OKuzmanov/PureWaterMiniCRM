package bg.softuni.pages.logged;

import bg.softuni.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class HomePage extends BasePage {
    public final String HOME_PAGE_HEADER = "All Due Orders";
    public final String ORDERS_TABLE_HEADER = "Total revenue of all orders:";
    public final String EMPLOYEES_TABLE_HEADER = "Orders By Employee";
    public final String NOT_ENOUGH_PRODUCTS = "Not enough products in storage to complete the order!";

    public final WebElement homeHeader = driver.findElement(By.cssSelector("h1 > strong.bg-blur.rounded"));
    public final WebElement ordersTableHeader = driver.findElement(By.cssSelector("div.col-6.bg-blur.rounded.m-auto.pb-3.table-padding > h3 > span"));
    public final WebElement employeesTableHeader = driver.findElement(By.cssSelector("div.col-5.bg-blur.rounded.m-auto.pb-3 > h3"));
    public final WebElement ordersTable = driver.findElement(By.cssSelector("div.col-6.bg-blur.rounded.m-auto.pb-3.table-padding > table"));
    public final WebElement usersTable = driver.findElement(By.cssSelector("div.col-5.bg-blur.rounded.m-auto.pb-3 > table"));
    public WebElement navigationBar = driver.findElement(By.cssSelector("body > div > header > nav"));
    public WebElement footer = driver.findElement(By.cssSelector("body > div > footer"));

    public HomePage(ChromeDriver driver) {
        super(driver);
    }

    public void clickDropdown() {

    }

    public WebElement getAllCustomers() {
        WebElement allDropdown = this.navigationBar.findElement(By.cssSelector("#navbarDropdownReports"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", allDropdown);

        return this.navigationBar.findElement(By.cssSelector("li.nav-item.dropdown > div > a:nth-child(1)"));
    }

    public WebElement getAllSuppliers() {
        WebElement allDropdown = this.navigationBar.findElement(By.cssSelector("#navbarDropdownReports"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", allDropdown);

        return this.navigationBar.findElement(By.cssSelector("li.nav-item.dropdown > div > a:nth-child(2)"));
    }

    public WebElement getAllProducts() {
        WebElement allDropdown = this.navigationBar.findElement(By.cssSelector("#navbarDropdownReports"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", allDropdown);

        return this.navigationBar.findElement(By.cssSelector("li.nav-item.dropdown > div > a:nth-child(3)"));
    }

    public WebElement getAllRawMaterials() {
        WebElement allDropdown = this.navigationBar.findElement(By.cssSelector("#navbarDropdownReports"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", allDropdown);

        return this.navigationBar.findElement(By.cssSelector("li.nav-item.dropdown > div > a:nth-child(4)"));
    }

    public WebElement getOrdersTable() {
        return this.driver.findElement(By.cssSelector("div.col-6.table-padding > table"));
    }

    public List<WebElement> getOrdersTableRows() {
        return getOrdersTable().findElements(By.cssSelector("tr:not(.table-head)"));
    }

    public WebElement getFirstDetailsLinkFromOrdersTable() {
        List<WebElement> tableRowsList = this.getOrdersTable().findElements(By.cssSelector("tr:not(.table-head)"));
        if (tableRowsList.size() == 0) {
            return null;
        }
        return tableRowsList.get(0).findElement(By.cssSelector("td:nth-child(6) > a"));
    }

    public WebElement getFirstReadyLinkOrderTable() {
        List<WebElement> tableRowsList = this.getOrdersTable().findElements(By.cssSelector("tr:not(.table-head)"));
        if (tableRowsList.size() == 0) {
            return null;
        }
        return tableRowsList.get(0).findElement(By.cssSelector("td:nth-child(5) > a"));
    }

    public WebElement getNotEnoughProductsInStorageAlert() {
        return this.driver.findElement(By.cssSelector("p.errors.alert.alert-danger"));
    }

    public WebElement getFirstDetailsLinkFromUserTable() {
        WebElement usersTable = this.driver.findElement(By.cssSelector("div.col-5 > table"));
        List<WebElement> tableRows = usersTable.findElements(By.cssSelector("tr:not(.table-head)"));
        if(tableRows.size() == 0) {
            return null;
        }
        return tableRows.get(0).findElement(By.cssSelector("td:nth-child(4) > a"));
    }
}
