package hillel.SelenideTests.JqueryBootgridComTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import fe.JqueryBootgridPages.SelectionPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BootgridTest {

    String numberOfRowsDropdownItemAll = "All";
    String showHideDropdownItemLink = "Link";
    String randomId = "112";

    SelectionPage selectionPage;

    @BeforeClass
    public void setUp() {
        Configuration.baseUrl = "http://www.jquery-bootgrid.com/examples#selection";
        Configuration.browserSize = "1880x1150";
    }

    @Test
    public void testDisableColumn() {
        selectionPage = new SelectionPage();

        selectionPage.open()
                .startExample()
                .chooseNumberOfRowsToBeShown(numberOfRowsDropdownItemAll)
                .clickOnItemInShowHideDropdown(showHideDropdownItemLink)
                .shouldUncheckedColumnBeHided(showHideDropdownItemLink);
    }

    @Test
    public void testSearchById() {
        selectionPage = new SelectionPage();

        selectionPage.open()
                .startExample()
                .chooseNumberOfRowsToBeShown(numberOfRowsDropdownItemAll)
                .performSearchById(randomId)
                .shouldHaveSearchResultById(randomId);
    }

    @Test
    public void testReceivedColumn() {
        selectionPage = new SelectionPage();

        selectionPage.open()
                .startExample()
                .chooseNumberOfRowsToBeShown(numberOfRowsDropdownItemAll)
                .filterReceivedColumnAscending()
                .shouldHaveReceivedColumnAscending();

        //Selenide.sleep(2000);
    }
}
