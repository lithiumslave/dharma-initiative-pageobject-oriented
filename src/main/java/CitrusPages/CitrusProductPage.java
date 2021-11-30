package CitrusPages;

import CitrusPages.CitrusFragments.AddedProductFragment;
import CitrusPages.CitrusFragments.BasketFragment;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class CitrusProductPage {

    private AddedProductFragment addedProductFragment = new AddedProductFragment();
    private BasketFragment basketFragment = new BasketFragment();

    private SelenideElement productDetails = $("div[class^=right]");
    private SelenideElement productPrice = productDetails.$("[data-price]");
    private SelenideElement buyButton = productDetails.$("[class*='buyButton']");

    public CitrusProductPage shouldHaveProductTitle(String searchItem) {
        productDetails.shouldHave(text(searchItem));
        return this;
    }

    public CitrusProductPage shouldHaveProductPrice(String expectedProductPrice) {
        productPrice.shouldHave(exactText(expectedProductPrice));
        return this;
    }

    public CitrusProductPage clickBuyButton() {
        buyButton.shouldBe(visible).click();
        return this;
    }

    public AddedProductFragment getAddedProductFragment() {
        return addedProductFragment;
    }

    public BasketFragment getBasketFragment() {
        return basketFragment;
    }

}
