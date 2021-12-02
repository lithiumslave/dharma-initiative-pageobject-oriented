package fe.RozetkaPages;

import fe.Supporting.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RozetkaProductsPageLeftMenuFragment extends BasePage {

    By appleItemInFilterChips = By.xpath("//a[contains(text(),\"Apple\")]");
    By huaweiItemInFilterChips = By.xpath("//a[contains(text(),\"Huawei\")]");

    @FindBy(css = ".sidebar input[type=\"text\"]:first-of-type")
    private WebElement minPriceField;

    @FindBy(css = ".sidebar input[type=\"text\"]:last-of-type")
    private WebElement maxPriceField;

    @FindBy(css = ".sidebar button[type=\"submit\"]")
    private WebElement priceFieldOkButton;

    @FindBy(xpath = "//span[contains(text(),\"Мобильные телефоны\")]")
    private WebElement mobilePhonesItemInFilter;

    @FindBy(xpath = "//label[contains(@for,\"Huawei\")]")
    private WebElement huaweiItemInFilter;

    @FindBy(xpath = "//label[contains(@for,\"Apple\")]")
    private WebElement appleItemInFilter;

    @FindBy(css = ".checkbox-filter__link label[for='12 ГБ']")
    private WebElement memory12RamItemInFilter;

    public RozetkaProductsPageLeftMenuFragment(WebDriver driver) {
        super(driver);
    }

    public void clickOnMobilePhonesInFilter() {
        waitForJsToLoad();
        wait.until(ExpectedConditions.elementToBeClickable(mobilePhonesItemInFilter)).click();
    }

    public void clickOnAppleItemInFilter() {
        waitForJsToLoad();
        wait.until(ExpectedConditions.elementToBeClickable(appleItemInFilter)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(appleItemInFilterChips));
    }

    public void clickOnHuaweiItemInFilter() {
        waitForJsToLoad();
        wait.until(ExpectedConditions.elementToBeClickable(huaweiItemInFilter)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(huaweiItemInFilterChips));
    }

    public void setPriceFilterRangeAndClickOkButton(String minPriceValue, String maxPriceValue) {
        setMinPriceFilterRange(minPriceValue);
        setMaxPriceFilterRange(maxPriceValue);
        clickOnPriceFilterRangeOkButton();
    }

    public void clickOn12GbRamInFilter() {
        wait.until(ExpectedConditions.elementToBeClickable(memory12RamItemInFilter)).click();
    }

    public void setMinPriceFilterRange(String minPriceValue) {
        wait.until(ExpectedConditions.elementToBeClickable(minPriceField));
        minPriceField.clear();
        minPriceField.sendKeys(minPriceValue);
    }

    public void setMaxPriceFilterRange(String maxPriceValue) {
        wait.until(ExpectedConditions.elementToBeClickable(maxPriceField));
        maxPriceField.clear();
        maxPriceField.sendKeys(maxPriceValue);
    }

    public void clickOnPriceFilterRangeOkButton() {
        wait.until(ExpectedConditions.elementToBeClickable(priceFieldOkButton)).click();
    }

}
