package RozetkaPages;

import Supporting.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RozetkaComputersNotebooksPage extends BasePage {

    RozetkaProductsPage rozetkaProductsPage;

    @FindBy(xpath = "//img[contains(@src, 'monitory')]")
    public WebElement monitorsItem;

    public RozetkaComputersNotebooksPage(WebDriver webDriver) {
        super(webDriver);
    }

    public RozetkaProductsPage clickOnMonitorsItem() {
        wait.until(ExpectedConditions.elementToBeClickable(monitorsItem)).click();
        return new RozetkaProductsPage(driver);
    }

}
