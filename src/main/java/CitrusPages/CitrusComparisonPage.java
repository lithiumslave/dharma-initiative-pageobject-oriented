package CitrusPages;

import CitrusPages.CitrusFragments.AddedProductFragment;
import CitrusPages.CitrusFragments.BasketFragmentWithAnotherStyles;
import CitrusPages.CitrusFragments.SearchFragment;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CitrusComparisonPage {

    private AddedProductFragment addedProductFragment = new AddedProductFragment();
    private SearchFragment searchFragment = new SearchFragment();
    private BasketFragmentWithAnotherStyles basketFragmentWithAnotherStyles = new BasketFragmentWithAnotherStyles();

    private final SelenideElement mainContentBox = $("div.compare");
    private final ElementsCollection products = $$("div.relative");
    private final SelenideElement firstProduct = $(".base-column");
    private final SelenideElement secondProduct = $(".swiper-slide-active");

    public CitrusComparisonPage shouldHaveProductTitle(String productTitle) {
        mainContentBox.should(appear);
        mainContentBox.shouldHave(text(productTitle));
        return this;
    }

    public CitrusComparisonPage shouldHaveProductsQuantity(int productsQty) {
        products.shouldHave(size(productsQty));
        return this;
    }

    public CitrusComparisonPage shouldHaveProductPriceByItem(int itemNumber, String itemPrice) {
        products.get(itemNumber).$("span[class='price-number']").shouldHave(text(itemPrice));
        System.out.println(itemPrice);
        System.out.println(products.get(itemNumber).$("span[class='price-number']").getText());
        return this;
    }

    public CitrusComparisonPage addFirstProductToBasketByItem() {
        firstProduct.$(".icon-new-citrus-cart").click();
        return this;
    }
    public CitrusComparisonPage addSecondProductToBasketByItem() {
        secondProduct.$(".icon-new-citrus-cart").click();
        return this;
    }

    public AddedProductFragment getAddedProductFragment() {
        return addedProductFragment;
    }

    public BasketFragmentWithAnotherStyles getBasketFragmentOnSearchPage() {
        return basketFragmentWithAnotherStyles;
    }

    public SearchFragment getSearchFragment() { return searchFragment; }
}
