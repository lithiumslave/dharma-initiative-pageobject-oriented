package fe.RozetkaPages;

import fe.Supporting.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class RozetkaProductsPage extends BasePage {

    By productDescriptionTextElement = By.cssSelector("p.goods-tile__description_type_text");
    By productPricePath = By.cssSelector("span.goods-tile__price-value");
    RozetkaProductsPageLeftMenuFragment productsPageLeftMenuFragment;
    JavascriptExecutor js;


    @FindBy(css = ".content_type_catalog .goods-tile__heading")
    private WebElement productTitle;

    @FindBy(css = ".content_type_catalog .goods-tile__price-value")
    private WebElement productPrice;

    @FindBy(css = "p.goods-tile__description_type_text")
    private WebElement productDescriptionText;

    @FindBy(css = ".catalog-grid > li")
    private WebElement productFullBlock;

    @FindBy(css = ".content_type_catalog .goods-tile__heading")
    private List<WebElement> productsTitles;

    @FindBy(css = ".content_type_catalog .goods-tile__price-value")
    private List<WebElement> productsPrices;

    @FindBy(css = "li.pagination__item")
    private List<WebElement> allPagesOfProductsButtons;

    @FindBy(css = ".catalog-grid > li")
    private List<WebElement> productFullBlocks;

    public RozetkaProductsPage(WebDriver driver) {
        super(driver);
        productsPageLeftMenuFragment = new RozetkaProductsPageLeftMenuFragment(driver);
        js = (JavascriptExecutor) driver;
    }

    public RozetkaProductDetailsPage clickProductTitle() {
        productTitle.click();
        waitForJsToLoad();
        return new RozetkaProductDetailsPage(driver);
    }

    public boolean checkAllProductsTitlesContainsExpectedTitles(String expected1, String expected2, String expected3) {
        boolean result = false;

        for (WebElement pageButton : allPagesOfProductsButtons) {
            pageButton.click();
            waitForJsToLoad();
            //wait.until(ExpectedConditions.elementToBeClickable(productTitle));

            for (WebElement product : productsTitles) {
                String productTitle = product.getText().toLowerCase();

                if (productTitle.contains(expected1.toLowerCase()) || productTitle.contains(expected2.toLowerCase()) || productTitle.contains(expected3.toLowerCase())) {
                    result = true;
                } else {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    public RozetkaProductDetailsPage clickFirstMonitorWithPriceLessThanExpected(String expectedPrice) {
        int expectedPriceInt = Integer.parseInt(expectedPrice);

        wait.until(ExpectedConditions.elementToBeClickable(productTitle));

        for (WebElement pageButton : allPagesOfProductsButtons) {
            pageButton.click();

            boolean result = false;

            for (WebElement goodBlock : productFullBlocks) {
                int priceValue = Integer.parseInt(goodBlock.findElement(productPricePath).getText().replaceAll("[^0-9]", ""));

                //System.out.println(priceValue + " < " + expectedPriceInt);
                if (priceValue < expectedPriceInt) {
                    result = true;
                    goodBlock.click();
                    waitForJsToLoad();
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        return new RozetkaProductDetailsPage(driver);
    }

    public boolean checkAllProductsOnPageAreInExpectedPriceRange(String expectedMin, String expectedMax) throws InterruptedException {
        int expectedMinInt = Integer.parseInt(expectedMin);
        int expectedMaxInt = Integer.parseInt(expectedMax);
        boolean result = true;

        // wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".catalog-grid > li:last-of-type")));
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(), \"5000 - 15000\")]")));
        waitForJsToLoad();
        /*Thread.sleep(4000);*/
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".catalog-grid > li")));

        for (WebElement prices : productsPrices) {
            wait.until(ExpectedConditions.elementToBeClickable(productPrice));

            String priceInString = prices.getText();
            int value = Integer.parseInt(priceInString.replaceAll("[^0-9]", ""));

            if (value <= expectedMinInt || value >= expectedMaxInt) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean checkAllProductsDescriptionContainsExpectedMemory(String expectedMemory) throws InterruptedException {
        boolean result = false;
        String memory = "RAM " + expectedMemory;

        waitForJsToLoad();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".catalog-grid > li")));

        for (WebElement goodBlock : productFullBlocks) {
            js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");

            actions.moveToElement(goodBlock).perform();
            wait.until(ExpectedConditions.presenceOfElementLocated(productDescriptionTextElement));

            WebElement descriptionFull = goodBlock.findElement(productDescriptionTextElement);
            System.out.println(descriptionFull.getText());
            if (goodBlock.findElement(productDescriptionTextElement).getText().contains(memory)) {
                result = true;
            } else {
                result = false;
                break;
            }
        }
        return result;
    }

    public RozetkaProductsPageLeftMenuFragment getProductsPageLeftMenuFragment() {
        return productsPageLeftMenuFragment;
    }
}
