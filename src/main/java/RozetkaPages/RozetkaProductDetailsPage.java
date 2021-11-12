package RozetkaPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RozetkaProductDetailsPage {

    WebDriver driver;

    @FindBy(css="h1.product__title")
    private WebElement selectedProductTitle;

    @FindBy(css="span.buy-button__label")
    private WebElement buyButton;

    @FindBy(css="button.modal__close")
    private WebElement closeModalWindowButton;

    @FindBy(css="span.counter")
    private WebElement cartCounter;

    @FindBy(css="span.counter")
    private List<WebElement> cartCounterCollection;


    public RozetkaProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getProductTitleText() {
        return selectedProductTitle.getText();
    }

    public void addProductToCart() {
        clickBuyButton();
        closeModalWindow();
    }

    public boolean isCartEmpty() {
        return cartCounterCollection.isEmpty();
    }

    public String getCartLabelText() {
        return cartCounter.getText();
    }

    private void clickBuyButton() {
        buyButton.click();
    }

    private void closeModalWindow() {
        closeModalWindowButton.click();
    }

}
