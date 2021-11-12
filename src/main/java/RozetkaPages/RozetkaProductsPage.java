package RozetkaPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RozetkaProductsPage {

    WebDriver driver;

    @FindBy(css=".content_type_catalog .goods-tile__heading")
    private WebElement productTitle;

    public RozetkaProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public RozetkaProductDetailsPage clickProductTitle() {
        productTitle.click();
        return new RozetkaProductDetailsPage(driver);
    }
}
