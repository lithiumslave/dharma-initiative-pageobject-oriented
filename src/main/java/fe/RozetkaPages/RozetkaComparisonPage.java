package fe.RozetkaPages;

import fe.Supporting.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class RozetkaComparisonPage extends BasePage {

    By recentlyViewedTitle = By.cssSelector("h4.recently-viewed__heading");
    By characteristicSectionTitle = By.cssSelector("button[aria-controls='comparisonCharacteristicSection1']");

    @FindBy(css = "li.products-grid__cell")
    private List<WebElement> productFullBlocs;

    @FindBy(css = "a.product__heading")
    private List<WebElement> productsTitles;

    @FindBy(css = "div.product__price")
    private List<WebElement> productsPrices;

    public RozetkaComparisonPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean checkIfNumberOfProductsAreEqualExpected(int expectedNumber) {
        wait.until(ExpectedConditions.presenceOfElementLocated(characteristicSectionTitle));
        int sizeOfTheList = productFullBlocs.size();
        return sizeOfTheList == expectedNumber;
    }

    public boolean checkIfTitlesAreEqualExpectedForTwoProducts(String expectedFirstTitle, String expectedSecondTitle) {
        boolean result = false;

        for (WebElement title : productsTitles) {
            if (title.getText().equals(expectedFirstTitle) || title.getText().equals(expectedSecondTitle)) {
                result = true;
            }
        }
        return result;
    }

    public boolean checkIfPricesAreEqualExpectedForTwoProducts(String expectedFirstPrice, String expectedSecondPrice) {
        boolean result = false;

        for (WebElement price : productsPrices) {
            String actualPrice = price.getText().replaceAll("[^0-9]", "").substring(4, 8);

            if (actualPrice.equals(expectedFirstPrice) || actualPrice.equals(expectedSecondPrice)) {
                result = true;
            }
        }
        return result;
    }
}
