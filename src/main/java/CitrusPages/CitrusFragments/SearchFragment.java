package CitrusPages.CitrusFragments;

import CitrusPages.CitrusSearchResultPage;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

public class SearchFragment {
    private final SelenideElement searchField = $("input[type='text']");
    private final SelenideElement searchFieldOnComparisonPage = $(By.id("search-input"));
    private final SelenideElement searchModal = $("div[data-popper-placement='bottom-start']");
    private final SelenideElement searchModalOnComparisonPage = $(".autocomplete__wrapper");

    private SearchFragment openExtendedSearchField() {
        searchField.click();
        searchModal.should(appear);
        return this;
    }

    private SearchFragment enterSearchItem(String searchItem) {
        searchField.setValue(searchItem);
        return this;
    }

    private SearchFragment pressEnter() {
        searchField.sendKeys(Keys.ENTER);
        return this;
    }

    public CitrusSearchResultPage performSearch(String searchItem) {
        openExtendedSearchField();
        enterSearchItem(searchItem);
        pressEnter();
        return new CitrusSearchResultPage();
    }

    public CitrusSearchResultPage performSearchOnComparisonPage(String searchItem) {
        searchFieldOnComparisonPage.setValue(searchItem).sendKeys(Keys.ENTER);
        return new CitrusSearchResultPage();
    }
}
