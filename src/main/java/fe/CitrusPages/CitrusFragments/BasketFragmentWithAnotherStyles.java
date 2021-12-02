package fe.CitrusPages.CitrusFragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class BasketFragmentWithAnotherStyles {

    private final SelenideElement basketIcon = $(".user-actions .icon-new-citrus-cart");
    private final SelenideElement basketModalOnSearchPage = $(".el-dialog--medium");
    private final String productTitle = "[class^='pr linkBlock'] .link";
    private final SelenideElement basketTotal = $("span[class*='ctrs-basket-footer__new-price']");

    public BasketFragmentWithAnotherStyles openBasket() {
        basketIcon.click();
        basketModalOnSearchPage.should(appear);
        return this;
    }

    public BasketFragmentWithAnotherStyles shouldHaveProductsQuantity(int productsQty) {
        productsInBasket().shouldHave(size(productsQty));
        return this;
    }

    public BasketFragmentWithAnotherStyles shouldHaveProductTitle(String searchItem) {
        basketModalOnSearchPage.shouldHave(text(searchItem));
        return this;
    }

    public BasketFragmentWithAnotherStyles shouldHaveProductPrice(String itemTitle, String productPrice) {
        System.out.println(itemPrice(itemTitle));
        System.out.println(productPrice);
        Assert.assertTrue(itemPrice(itemTitle).contains(productPrice.replaceAll("[^0-9]", "")));
        return this;
    }

    public BasketFragmentWithAnotherStyles shouldHaveBasketTotalCostWithOneProduct(String itemTitle) {
        String itemPriceCut = productInBasket(itemTitle).$(".ctrs-mixed-price").getText().replaceAll("[^0-9]", "").substring(5, 10);
        String totalNum = basketTotal.getText().replaceAll("[^0-9]", "");
        Assert.assertEquals(itemPriceCut, totalNum);
        return this;
    }

    public BasketFragmentWithAnotherStyles shouldHaveBasketTotalCostWithTwoProducts(String productPriceFirst, String productPriceSecond) {
        int firstPriceInt = Integer.parseInt(productPriceFirst.replaceAll("[^0-9]", ""));
        int secondPriceInt = Integer.parseInt(productPriceSecond.replaceAll("[^0-9]", ""));
        int sumOf2Prices = firstPriceInt + secondPriceInt;
        int totalOnPage = Integer.parseInt(basketTotal.getText().replaceAll("[^0-9]", ""));
        System.out.println(sumOf2Prices);
        System.out.println(totalOnPage);

        Assert.assertEquals(sumOf2Prices, totalOnPage);
        return this;
    }

    private ElementsCollection productsInBasket() {
        return basketModalOnSearchPage.$$(".ctrs-basket-item__products");
    }

    private SelenideElement productInBasket(String itemTitle) {
        return productsInBasket().find(text(itemTitle));
    }

    private String itemPrice(String itemTitle) {
        return productInBasket(itemTitle).$(".ctrs-mixed-price").getText().replaceAll("[^0-9]", "");
    }

}
