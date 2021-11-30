package CitrusPages;

import CitrusPages.CitrusFragments.AddedProductFragment;
import CitrusPages.CitrusFragments.BasketFragmentWithAnotherStyles;
import CitrusPages.CitrusFragments.ComparisonFragment;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CitrusSearchResultPage {

    private AddedProductFragment addedProductFragment = new AddedProductFragment();
    private BasketFragmentWithAnotherStyles basketFragmentWithAnotherStyles = new BasketFragmentWithAnotherStyles();
    private ComparisonFragment comparisonFragment = new ComparisonFragment();

    //private ElementsCollection products = $$("[class='product-card product-card--mini']");
    private ElementsCollection products = $$(".catalog-item");
    private SelenideElement pageHeader = $("h2.result-title");

    private SelenideElement product(String searchItem) {
        return products.find(text(searchItem));
    }

    public CitrusSearchResultPage shouldHaveProductTitle(String searchItem) {
        product(searchItem).should(exist);
        return this;
    }

    public CitrusSearchResultPage shouldHaveRequestInPageHeader(String searchItem) {
        pageHeader.shouldHave(text(searchItem));
        return this;
    }

    public String getProductTitleByItemNumber(int itemNumber) {
        return products.get(itemNumber).$(".title-itm").text();
    }

    public String getProductPriceByItemNumber(int itemNumber) {
        return products.get(itemNumber).$(".price-number").text().replaceAll("[^0-9]", " ");
    }

    public String getProductPrice(String searchItem) {
        return product(searchItem).$(".price-number").text().replaceAll("[^0-9]", "");
    }

    public CitrusSearchResultPage clickBasketIconInProductBlock(String searchItem) {
        product(searchItem).$("[class*='icon-new-citrus-cart']").click();
        return this;
    }

    public CitrusSearchResultPage clickBasketIconInProductBlockByItemNumber(int itemNumber) {
        products.get(itemNumber).$("[class*='icon-new-citrus-cart']").click();
        return this;
    }

    public CitrusSearchResultPage clickComparisonIconInProductBlockByItemNumber(int itemNumber) {
        products.get(itemNumber).$(".icon-comparison2").click();
        return this;
    }

    public BasketFragmentWithAnotherStyles getBasketFragmentOnSearchPage() { return basketFragmentWithAnotherStyles; }

    public AddedProductFragment getAddedProductFragment() { return addedProductFragment; }

    public ComparisonFragment getComparisonFragment() { return comparisonFragment; }
}
