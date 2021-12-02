package fe.CitrusPages.CitrusFragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.exec.OS;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FilterFragment {

    private final ElementsCollection priceFilterInputs = $$("input[type='tel'");
    private final SelenideElement okButtonInFilterField = $("button[type='submit']");
    private final SelenideElement filterSelectedElement = $("[class*='selected']");
    private final ElementsCollection filterSelectedElements = $$(".selected-0-2-92 a");
    private final ElementsCollection checkBoxesInFilter = $$("[class*='filterItemContainer']");
    private ElementsCollection productsTitles = $$("[class*='link line-clamp-2']");

    public FilterFragment filterProductsByMinMaxPrice(String minValue, String maxValue) {
        setMinPrice(minValue);
        setMaxPrice(maxValue);
        clickOnOkButtonInPriceFilter();
        return this;
    }

    public FilterFragment clickOnCheckBoxTitleByText(String checkboxTitle) {
        checkBoxesInFilter.find(text(checkboxTitle)).shouldBe(visible).click();
        return this;
    }

    public FilterFragment shouldHaveSelectedPrice(String minValue, String maxValue) {
        filterSelectedElements.get(1).should(appear);
        //filterSelectedElement.$("a[href*='prices[min]=" + minValue + "&prices[max]=" + maxValue + "']").should(appear);
        return this;
    }

    private void setMinPrice(String minValue) {
        //clearInputByKeys(priceFilterInputs.get(0));
        priceFilterInputs.get(0).val(minValue);
    }

    private void setMaxPrice(String maxValue) {
        clearInputByKeys(priceFilterInputs.get(1));
        priceFilterInputs.get(1).val(maxValue);
    }

    private void clickOnOkButtonInPriceFilter() {
        okButtonInFilterField.click();
    }

    private void clearInputByKeys(SelenideElement element){
        if (OS.isFamilyMac()) {
            element.sendKeys(Keys.chord(Keys.COMMAND, "a"));
            element.sendKeys(Keys.DELETE);
        } else {
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            element.sendKeys(Keys.BACK_SPACE);
        }
    }
}
