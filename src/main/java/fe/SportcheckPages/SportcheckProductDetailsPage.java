package fe.SportcheckPages;

import fe.Supporting.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SportcheckProductDetailsPage extends BasePage {

    TopPanelFragment topPanelFragment;

    By colors = By.cssSelector("[data-control-type='color']");
    By sizes = By.cssSelector("[data-control-type='size']");
    By qtyField = By.id("qty-selector");
    By addToCartButton = By.cssSelector("[class='add-cart product-detail__button product-detail__button-icon']");
    By titleOnProductPage = By.cssSelector("h1.global-page-header__title");

    public SportcheckProductDetailsPage(WebDriver webDriver) {
        super(webDriver);
        topPanelFragment = new TopPanelFragment(webDriver);
    }

    public void clickFirstUnselectedAvailableColor() {
        wait.until(ExpectedConditions.presenceOfElementLocated(colors));
        List<WebElement> availableColors = driver.findElements(colors);

        for (WebElement color : availableColors) {
            if (!color.getAttribute("class").contains("selected")) {
                color.click();
                break;
            }
        }
    }

    public void clickFirstAvailableSize() {
        wait.until(ExpectedConditions.presenceOfElementLocated(sizes));
        List<WebElement> availableSizes = driver.findElements(sizes);

        for (WebElement size : availableSizes) {
            if (!size.getAttribute("class").contains("option-tiles__item--out-of-stock")) {
                size.click();
                break;
            }
        }
    }

    public void setQtyOfProduct(String expectedQty) {
        wait.until(ExpectedConditions.presenceOfElementLocated(qtyField));
        WebElement qtyOfProduct = driver.findElement(qtyField);

        qtyOfProduct.click();
        qtyOfProduct.sendKeys(Keys.BACK_SPACE);
        qtyOfProduct.sendKeys(expectedQty);
    }

    public void clickOnAddToCartButton() {
        wait.until(ExpectedConditions.presenceOfElementLocated(addToCartButton));
        driver.findElement(addToCartButton).click();
    }

    public boolean checkIfConfirmationMessageShown(String confirmationMessage) {
        wait.until(ExpectedConditions.presenceOfElementLocated(topPanelFragment.getModalWindow()));
        return driver.findElement(topPanelFragment.getModalWindow()).getText().toLowerCase().contains(confirmationMessage.toLowerCase());
    }

    public void hoverCartIcon() throws InterruptedException {
        actions.moveToElement(driver.findElement(topPanelFragment.getCartIconInHeader())).perform();
        Thread.sleep(1000);
    }

    public boolean checkIfQtyInModalEqualsExpectedQty(String expectedQty) {
        wait.until(ExpectedConditions.presenceOfElementLocated(topPanelFragment.getQtyInModal()));
        return driver.findElement(topPanelFragment.getQtyInModal()).getText().equals(expectedQty);
    }

    public boolean checkIfTitleInModalEqualsExpectedTitle(String expectedTitle) {
        wait.until(ExpectedConditions.presenceOfElementLocated(topPanelFragment.getTitleInModal()));
        return driver.findElement(topPanelFragment.getTitleInModal())
                                    .getText().toLowerCase()
                                    .equals(expectedTitle.toLowerCase());
    }

    // По хорошему должно быть так:

    /*public boolean checkIfTitleInModalEqualsExpected() {
        return  driver.findElement(topPanelFragment.getTitleInModal()).getText().toLowerCase().equals(driver.findElement(titleOnProductPage).getText().toLowerCase());
    }*/

}
