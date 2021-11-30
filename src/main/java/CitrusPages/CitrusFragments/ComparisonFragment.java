package CitrusPages.CitrusFragments;

import CitrusPages.CitrusComparisonPage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ComparisonFragment {

    private final SelenideElement comparisonIcon = $(".user-actions .icon-comparison2");
    private final SelenideElement comparisonIconOnListingPage = $("a[class*='compare']");
    private final SelenideElement comparisonCounter = comparisonIcon.$(".counter");
    private final SelenideElement comparisonCounterOnListingPage = $("[class*='badge']");

    public CitrusComparisonPage clickComparisonIconOnSearchPage() {
        comparisonIcon.click();
        return new CitrusComparisonPage();
    }

    public CitrusComparisonPage clickComparisonIconOnListing() {
        comparisonIconOnListingPage.click();
        return new CitrusComparisonPage();
    }

    public ComparisonFragment shouldHaveExpectedCounter(String expectedCounter) {
            comparisonCounter.shouldHave(text(expectedCounter));
            return this;
    }

    public ComparisonFragment shouldHaveExpectedCounterOnListing(String expectedCounter) {
        comparisonCounterOnListingPage.shouldHave(text(expectedCounter));
        return this;
    }

}
