package bg.softuni.pages.logged;

import bg.softuni.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.stream.Collectors;

public class AllSuppliersPage extends BasePage {

    public final String pageTitle = "All Suppliers";
    public final String companyNameCol = "Company Name";
    public final String emailCol = "Email";
    public final String addressCol = "Address";
    public final String phoneNumberCol = "Phone Number";
    public final String descriptionCol = "Description";

    public WebElement allSuppliersHeader = driver.findElement(By.cssSelector("h1 > strong.bg-blur.rounded.text-white"));
    public WebElement allSuppliersTable = driver.findElement(By.cssSelector("table.table.table-dark"));

    public AllSuppliersPage(ChromeDriver driver) {
        super(driver);
    }

    public int getCountTableColumns() {
        List<WebElement> tableCols = allSuppliersTable.findElements(By.cssSelector("table > thead > tr > th"));
        return tableCols.size();
    }

    public List<String> getTableColumnNames() {
        List<WebElement> tableCols = allSuppliersTable.findElements(By.cssSelector("table > thead > tr > th"));

        List<String> tableColNames = tableCols
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        return tableColNames;
    }

    public WebElement getFirstSupplierDetails() {
        if (getCountTableRows() == 0) {
            return null;
        }

        List<WebElement> tableRowsList = driver.findElements(By.cssSelector("table > tbody > tr"));

        return tableRowsList.get(0).findElement(By.cssSelector("td:nth-child(6) > a"));
    }

    public int getCountTableRows() {
        return this.allSuppliersTable.findElements(By.cssSelector("tr:not(.table-head)")).size();
    }

    public WebElement getLastSupplierDetails() {
        if (getCountTableRows() == 0) {
            return null;
        }

        List<WebElement> tableRowsList = driver.findElements(By.cssSelector("table > tbody > tr"));

        return tableRowsList.get(tableRowsList.size() - 1).findElement(By.cssSelector("td:nth-child(6) > a"));
    }
}
