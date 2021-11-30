package CitrusPages.CitrusFragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import junit.framework.AssertionFailedError;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class BasketFragment {

    private final SelenideElement basketIcon = $x("//div[contains(@class, 'basket')]");
    private final SelenideElement basketModal = $(".pr.border-box.scrollbar");
    private final SelenideElement basketModalOnSearchPage = $(".el-dialog--medium");
    private final String productTitle = "[class^='pr linkBlock'] .link";

    public BasketFragment openBasket() {
        basketIcon.click();
        basketModal.should(appear);
        return this;
    }

    // check that product with title is present in basket
    public BasketFragment shouldHaveProductTitle(String searchItem) {
        basketModal.shouldHave(text(searchItem));
        return this;
    }

    // check that price for a given item is correct
    public BasketFragment shouldHaveProductPrice(String itemTitle, String productPrice) {
        this.itemPrice(itemTitle).shouldHave(exactText(productPrice));
        return this;
    }

    // after we checked that price and q-ty for single item is correct,
    // calculate expected cost as: price * quantity
    // compare with product's total displayed in basket
    public BasketFragment shouldHaveProductTotal(String itemTitle) {
        int expectedItemTotal = getExpectedItemTotalCost(itemTitle);
        int actualItemTotal = getIntValueForCost(itemTotalCost(itemTitle).text());
        boolean areEqual = (expectedItemTotal == actualItemTotal);
        if(!areEqual) {
            throw new AssertionFailedError("Total price for a product is incorrect\n" +
                    "expected: " + expectedItemTotal +
                    "; but got: " + actualItemTotal);
        }
        return this;
    }

    // after we checked that price and q-ty for all single items is correct,
    // calculate expected cost as: sum of all single product total cost
    // compare with basket total displayed in basket
    public BasketFragment shouldHaveBasketTotalCost() {
        int expectedBasketTotal = getExpectedBasketTotalCost();
        int actualBasketTotal = getIntValueForCost(basketTotalCost().text());
        boolean areEqual = (expectedBasketTotal == actualBasketTotal);
        if(!areEqual) {
            throw new AssertionFailedError("Total price for all products in basket is incorrect\n" +
                    "expected: " + expectedBasketTotal +
                    "; but got: " + actualBasketTotal);
        }
        return this;
    }

    // check if total q-ty of different added items is correct
    public BasketFragment shouldHaveProductsQuantity(int productsQty) {
        productsInBasket().shouldHave(size(productsQty));
        return this;
    }

    // check if total q-ty of same added items is correct
    public BasketFragment shouldHaveItemQuantity(String itemTitle, String itemQuantity) {
        itemQuantity(itemTitle).shouldHave(attribute("value", itemQuantity));
        return this;
    }

    private ElementsCollection productsInBasket() {
        return basketModal.$$("[class^=main] > div");
    }

    private SelenideElement productInBasket(String itemTitle) {
        return productsInBasket().find(text(itemTitle));
    }

    private SelenideElement itemQuantity(String itemTitle) {
        return productInBasket(itemTitle).$("input");
    }

    private SelenideElement itemPrice(String itemTitle) {
        return productInBasket(itemTitle).$("[class^=priceBlock] .final-price");
    }

    private SelenideElement itemTotalCost(String itemTitle) {
        return productInBasket(itemTitle).$("[class^=totalBlock] .final-price");
    }

    private SelenideElement basketTotalCost() {
        return basketModal.$("[class^=orderButtonBlock] .final-price");
    }

    private int getExpectedItemTotalCost(String itemName) {
        return getIntValueForCost(itemPrice(itemName).text())
                * Integer.parseInt(itemQuantity(itemName).val());
    }

    private int getExpectedBasketTotalCost() {
        return productsInBasket().stream()
                .map(product -> product.$(productTitle).text())
                .map(this::getExpectedItemTotalCost)
                .reduce(0, Integer::sum);
    }

    private int getIntValueForCost(String itemCost) {
        return Integer.parseInt(itemCost.replaceAll("[^0-9]", ""));
    }
}
