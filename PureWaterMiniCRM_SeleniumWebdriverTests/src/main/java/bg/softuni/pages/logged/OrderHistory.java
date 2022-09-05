package bg.softuni.pages.logged;

import bg.softuni.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.stream.Collectors;

public class OrderHistory extends BasePage {

    public final String pageTitle = "Order History";
    public final String nameCol = "Name";
    public final String typeCol = "Type";
    public final String quantityCol = "Quantity";
    public final String priceCol = "Price";
    public final String completionDateCol = "Completion Date";

    public WebElement orderHistoryHeader = driver.findElement(By.cssSelector("h1 > strong.bg-blur.rounded.text-white"));
    public WebElement orderHistoryTable = driver.findElement(By.cssSelector("table.table.table-dark"));

    public OrderHistory(ChromeDriver driver) {
        super(driver);
    }

    public int getCountTableColumns() {
        List<WebElement> tableCols = orderHistoryTable.findElements(By.cssSelector("table > thead > tr > th"));
        return tableCols.size();
    }

    public List<String> getTableColumnNames() {
        List<WebElement> tableCols = orderHistoryTable.findElements(By.cssSelector("table > thead > tr > th"));

        List<String> tableColNames = tableCols
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        return tableColNames;
    }
}
