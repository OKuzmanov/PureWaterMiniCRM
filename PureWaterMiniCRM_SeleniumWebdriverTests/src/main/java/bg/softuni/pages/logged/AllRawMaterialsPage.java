package bg.softuni.pages.logged;

import bg.softuni.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.stream.Collectors;

public class AllRawMaterialsPage extends BasePage {

    public final String pageTitle = "All Raw Materials";
    public final String typeCol = "Type";
    public final String quantityCol = "Quantity";
    public final String deliveryDateCol = "Delivery Date";
    public final String supplierCol = "Supplier";

    public WebElement allRawMaterialsHeader = driver.findElement(By.cssSelector("h1 > strong.bg-blur.rounded.text-white"));
    public WebElement allRawMaterialsTable = driver.findElement(By.cssSelector("table.table.table-dark"));

    public AllRawMaterialsPage(ChromeDriver driver) {
        super(driver);
    }

    public int getCountTableColumns() {
        List<WebElement> tableCols = allRawMaterialsTable.findElements(By.cssSelector("table > thead > tr > th"));
        return tableCols.size();
    }

    public List<String> getTableColumnNames() {
        List<WebElement> tableCols = allRawMaterialsTable.findElements(By.cssSelector("table > thead > tr > th"));

        List<String> tableColNames = tableCols
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        return tableColNames;
    }

    public WebElement getFirstRawMaterialsDetails() {
        if (getCountTableColumns() == 0) {
            return null;
        }

        List<WebElement> tableRowsList = driver.findElements(By.cssSelector("table > tbody > tr"));

        return tableRowsList.get(0).findElement(By.cssSelector("td:nth-child(5) > a"));
    }

    public WebElement getLastRawMaterialsDetails() {
        if (getCountTableRows() == 0) {
            return null;
        }

        List<WebElement> tableRowsList = driver.findElements(By.cssSelector("table > tbody > tr"));

        return tableRowsList.get(tableRowsList.size() - 1).findElement(By.cssSelector("td:nth-child(5) > a"));
    }

    public int getCountTableRows() {
        return this.allRawMaterialsTable.findElements(By.cssSelector("tr:not(.table-head)")).size();
    }
}
