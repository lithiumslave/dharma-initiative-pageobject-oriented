package fe.CitrusPages;

import fe.CitrusPages.CitrusFragments.ComparisonFragment;
import fe.CitrusPages.CitrusFragments.FilterFragment;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CitrusProductListingPage {

    private FilterFragment filterFragment = new FilterFragment();
    private ComparisonFragment comparisonFragment = new ComparisonFragment();

    private ElementsCollection products = $$("[class*=productCardCategory]");
    private final ElementsCollection notebooks = $$("div[class*='border-box pr productCardCategory']");
    private ElementsCollection productsPrices = $$("[data-price]");
    private ElementsCollection productsTitles = $$("[class*='link line-clamp-2']");
    private SelenideElement pageTitle = $("[class='mt16 mb16']");

    private SelenideElement product(String searchItem) {
        return products.find(text(searchItem));
    }

    public CitrusProductListingPage shouldHaveProductTitle(String searchItem) {
        product(searchItem).should(exist);
        return this;
    }

    public String getProductPrice(String searchItem) {
        return product(searchItem).$("[data-price]").text().replace("₴", " ₴");
    }

    public CitrusProductPage clickProduct(String searchItem) {
        product(searchItem).click();
        return new CitrusProductPage();
    }

    public CitrusProductListingPage clickComparisonIconInProductBlockByItemNumber(int itemNumber) {
        notebooks.get(itemNumber).$("button[class*='uppercase medium btn']").click();
        return this;
    }

    public String getProductTitleByItemNumber(int itemNumber) {
        return products.get(itemNumber).$("[class*='link line-clamp-2']").text();
    }

    public String getProductPriceByItemNumber(int itemNumber) {
        return products.get(itemNumber).$("[data-price]").text().replaceAll("[^0-9]", " ");
    }

    public CitrusProductListingPage shouldHaveProductsInPriceRange(String minValue, String maxValue) {
        int minPrice = Integer.parseInt(minValue);
        int maxPrice = Integer.parseInt(maxValue);

        for (WebElement price : productsPrices) {
            int priceInteger = Integer.parseInt(price.getText().replaceAll("[^0-9]", ""));
            Assert.assertTrue(maxPrice > priceInteger && priceInteger > minPrice);
        }
        return this;
    }

    public CitrusProductListingPage shouldHaveProducerInName(String producer) {
        int expectedSize = productsTitles.size();
        productsTitles.filterBy(text(producer));
        productsTitles.shouldHave(size(expectedSize));
        return this;
    }

    public CitrusProductListingPage shouldHaveExpectedTextInPageTitle(String expectedText) {
        pageTitle.shouldHave(text(expectedText));
        return this;
    }

    public CitrusProductListingPage shouldHaveExpectedMemoriesInProductsTitles(String memory32GB, String memory64GB) {
        for (WebElement title : productsTitles) {
            System.out.println(title.getText());
            Assert.assertTrue(title.getText().contains(memory32GB) || title.getText().contains(memory64GB));
        }
        return this;
    }

    public FilterFragment getFilterFragment() {
        return filterFragment;
    }

    public ComparisonFragment getComparisonFragment() { return comparisonFragment; }
}
