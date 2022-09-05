package bg.softuni.pages.logged;

import bg.softuni.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.stream.Collectors;

public class AllProductsPage extends BasePage {

    public final String pageTitle = "All Products";
    public final String typeCol = "Type";
    public final String quantityCol = "Quantity";
    public final String productionDateCol = "Production Date";

    public WebElement allProductsHeader = driver.findElement(By.cssSelector("h1 > strong.bg-blur.rounded.text-white"));
    public WebElement allProductsTable = driver.findElement(By.cssSelector("table.table.table-dark"));

    public AllProductsPage(ChromeDriver driver) {
        super(driver);
    }

    public int getCountTableColumns() {
        List<WebElement> tableCols = allProductsTable.findElements(By.cssSelector("table > thead > tr > th"));
        return tableCols.size();
    }

    public List<String> getTableColumnNames() {
        List<WebElement> tableCols = allProductsTable.findElements(By.cssSelector("table > thead > tr > th"));

        List<String> tableColNames = tableCols
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        return tableColNames;
    }

    public WebElement getFirstProductDetails() {
        if (getCountTableColumns() == 0) {
            return null;
        }

        List<WebElement> tableRowsList = driver.findElements(By.cssSelector("table > tbody > tr"));

        return tableRowsList.get(0).findElement(By.cssSelector("td:nth-child(4) > a"));
    }

    public WebElement getLastProductDetails() {
        if (getCountTableRows() == 0) {
            return null;
        }

        List<WebElement> tableRowsList = driver.findElements(By.cssSelector("table > tbody > tr"));

        return tableRowsList.get(tableRowsList.size() - 1).findElement(By.cssSelector("td:nth-child(4) > a"));
    }

    public int getCountTableRows() {
        return this.allProductsTable.findElements(By.cssSelector("tr:not(.table-head)")).size();
    }
}
