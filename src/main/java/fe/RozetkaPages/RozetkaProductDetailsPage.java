package fe.RozetkaPages;

import fe.Supporting.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RozetkaProductDetailsPage extends BasePage {
    private TopPanelFragment topPanelFragment;

    @FindBy(css="h1.product__title")
    private WebElement selectedProductTitle;

    @FindBy(css = "p.product-prices__big")
    private WebElement selectedProductPrice;

    @FindBy(css = "button.compare-button")
    private WebElement compareButton;

    @FindBy(css="span.buy-button__label")
    private WebElement buyButton;

    @FindBy(css="button.modal__close")
    private WebElement closeModalWindowButton;

    public RozetkaProductDetailsPage(WebDriver driver) {
        super(driver);
        topPanelFragment = new TopPanelFragment(driver);
    }

    public void clickOnCompareButton() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(compareButton)).click();
        Thread.sleep(1000);
    }

    public String getProductTitleText() {
        return selectedProductTitle.getText();
    }

    public String getProductPrice() { return selectedProductPrice.getText().replaceAll("[^0-9]", ""); }

    public void addProductToCart() {
        clickBuyButton();
        closeModalWindow();
    }

    private void clickBuyButton() {
        buyButton.click();
    }

    private void closeModalWindow() {
        closeModalWindowButton.click();
    }

    public TopPanelFragment getTopPanelFragment() {
        return topPanelFragment;
    }
}
