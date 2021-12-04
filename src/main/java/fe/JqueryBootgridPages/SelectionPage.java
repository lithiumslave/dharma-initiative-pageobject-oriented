package fe.JqueryBootgridPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Collections;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SelectionPage {

    private final SelenideElement startDataExampleButton = $("button[id='init-data']");
    private final SelenideElement dataExampleBlock = $(By.id("data"));
    private final SelenideElement dataExampleSearchField = dataExampleBlock.$(".search-field");
    private final SelenideElement dataExampleActionsButtonGroup = dataExampleBlock.$("[class='actions btn-group']");
    private final SelenideElement dataExampleNumberOfRowsDropdown = dataExampleActionsButtonGroup.$(".dropdown:first-of-type");
    private final SelenideElement dataExampleShowHideDropdown = dataExampleActionsButtonGroup.$(".dropdown:last-of-type");
    private final SelenideElement dataTable = $("table[id='grid-data']");
    private final ElementsCollection idRowsInDataExampleTable = dataExampleBlock.$$("tr[data-row-id] td:first-of-type");
    private final ElementsCollection receivedColumnContent = dataTable.$$("td:nth-of-type(3)");

    public SelectionPage open() {
        Selenide.open("/");
        return this;
    }

    public SelectionPage startExample() {
        startDataExampleButton.click();
        dataTable.should(appear);
        return this;
    }

    public SelectionPage performSearchById(String id) {
        dataExampleSearchField.setValue(id);
        return this;
    }

    public SelectionPage chooseNumberOfRowsToBeShown(String numberOfRowsToBeShown) {
        openNumberOfRowsDropdown();
        clickOnNumberOfRowsDropdownItemByText(numberOfRowsToBeShown);
        return this;
    }

    public SelectionPage clickOnItemInShowHideDropdown(String itemToBeClicked) {
        openShowHideDropdown();
        clickOnShowHideDropdownItemByText(itemToBeClicked);
        return this;
    }

    public SelectionPage filterReceivedColumnAscending() {
        dataTable.$(byText("Received")).click();
        idRowsInDataExampleTable.get(0).shouldHave(text("0"));
        return this;
    }

    public SelectionPage shouldUncheckedColumnBeHided(String columnName) {
        String columnNameLower = columnName.toLowerCase();
        dataExampleBlock.$("[data-column-id='" + columnNameLower + "']").should(disappear);
        return this;
    }

    public SelectionPage shouldHaveSearchResultById(String id) {
        int expectedSize = idRowsInDataExampleTable.size();
        idRowsInDataExampleTable.filterBy(text(id));
        idRowsInDataExampleTable.shouldHave(size(expectedSize));
        return this;
    }

    public SelectionPage shouldHaveReceivedColumnAscending() {
        ArrayList<String> actual = getActualReceivedColumnContent();
        ArrayList<String> sorted = sortReceivedColumnListAscending();
        Assert.assertEquals(actual, sorted);
        return this;
    }

    private ArrayList<String> getActualReceivedColumnContent() {
        ArrayList<String> actualList = new ArrayList<>();
        for (SelenideElement row : receivedColumnContent) {
            actualList.add(row.getText());
        }
        return actualList;
    }

    private ArrayList<String> sortReceivedColumnListAscending() {
        ArrayList<String> actual = getActualReceivedColumnContent();
        ArrayList<String> sorted = new ArrayList<>();
        sorted.addAll(actual);
        Collections.sort(sorted);
        return sorted;
    }

    private SelectionPage openNumberOfRowsDropdown() {
        dataExampleNumberOfRowsDropdown.click();
        dataExampleNumberOfRowsDropdown.$("ul[role='menu']").should(appear);
        return this;
    }

    private SelectionPage openShowHideDropdown() {
        dataExampleShowHideDropdown.click();
        dataExampleShowHideDropdown.$("ul[role='menu']").should(appear);
        return this;
    }

    private SelectionPage clickOnNumberOfRowsDropdownItemByText(String itemToBeClicked) {
        dataExampleNumberOfRowsDropdown.$(byText(itemToBeClicked)).click();
        dataExampleBlock.$("[data-row-id=\"1122\"]").should(appear);
        return this;
    }

    private SelectionPage clickOnShowHideDropdownItemByText(String itemToBeClicked) {
        String itemLower = itemToBeClicked.toLowerCase();
        dataExampleShowHideDropdown.$(byText(itemToBeClicked)).click();
        dataExampleShowHideDropdown.$("[name='" + itemLower + "']").shouldNotBe(selected);
        return this;
    }


}
