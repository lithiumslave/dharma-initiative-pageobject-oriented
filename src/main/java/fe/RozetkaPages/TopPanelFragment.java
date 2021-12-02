package fe.RozetkaPages;

import fe.Supporting.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class TopPanelFragment extends BasePage {

    By compareCounterPath = By.cssSelector("span.counter--gray");

    @FindBy(xpath="//input[@name='search']")
    private WebElement searchField;

    @FindBy(css=".button.button_color_green")
    private WebElement searchButton;

    @FindBy(css = "span.counter--gray")
    private WebElement compareCounter;

    @FindBy(css = "button[aria-label='Списки сравнения']")
    private WebElement compareButton;

    @FindBy(css = "a.comparison-modal__link")
    private WebElement firstItemInComparisonListModal;

    @FindBy(css="span.counter")
    private WebElement cartCounter;

    @FindBy(css="span.counter")
    private List<WebElement> cartCounterCollection;

    public TopPanelFragment(WebDriver driver) {
        super(driver);
    }

    public RozetkaProductsPage performSearch(String searchText) {
        enterTextIntoSearchField(searchText);
        clickSearchButton();
        return new RozetkaProductsPage(driver);
    }

    public boolean isCartEmpty() {
        return cartCounterCollection.isEmpty();
    }

    public boolean checkCorrectCompareCounterEqualsExpected(String expectedCounter) {
        wait.until(ExpectedConditions.presenceOfElementLocated(compareCounterPath));
        Assert.assertFalse(cartCounterCollection.isEmpty());
        return compareCounter.getText().equals(expectedCounter);
    }

    public void clickOnCompareButtonInHeader() {
        compareButton.click();
    }

    public RozetkaComparisonPage clickOnFirstItemInComparisonList() {
        wait.until(ExpectedConditions.elementToBeClickable(firstItemInComparisonListModal)).click();
        waitForJsToLoad();
        return new RozetkaComparisonPage(driver);
    }

    public String getCartLabelText() {
        return cartCounter.getText();
    }

    private void enterTextIntoSearchField(String searchText) {
        searchField.sendKeys(searchText);
    }

    private void clickSearchButton() {
        searchButton.click();
    }
}
