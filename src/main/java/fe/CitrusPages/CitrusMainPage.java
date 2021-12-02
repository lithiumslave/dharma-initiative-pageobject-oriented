package fe.CitrusPages;

import fe.CitrusPages.CitrusFragments.SearchFragment;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CitrusMainPage {

    private SearchFragment searchFragment = new SearchFragment();
    private final SelenideElement adPopupCloseButton = $("[class*=closeAdvertising]");

    public CitrusMainPage open() {
        Selenide.open("/");
        return this;
    }

    public CitrusMainPage closeAdPopup() {
        if(adPopupCloseButton.isDisplayed()) {
            adPopupCloseButton.click();
        }
        return this;
    }

    public CitrusMainPage hoverOverCategory(String category) {
        $(byText(category)).hover();
        return this;
    }

    public CitrusProductListingPage chooseByProducer(String producer) {
        $(byText(producer)).click();
        return new CitrusProductListingPage();
    }

    public SearchFragment getSearchFragment() {
        return searchFragment;
    }
}
