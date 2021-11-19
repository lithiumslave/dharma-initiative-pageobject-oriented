package hillel;

import GooglePages.GoogleMainPage;
import GooglePages.GoogleSearchResultPage;
import GooglePages.MaxOfPossibleResultException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*Create 1 automation test for testing of search result listing in google by search text "iphone kyiv buy".
Link to "allo.ua" should be present on any of first 5 pages of results.
After text execution you have to log in console which page contained searched link ("ALLO.UA found on 2 page") or print "ALLO.UA not found on first 5 pages".
Use Page Object + Page Factory.*/

public class GoogleTest extends UIBaseTest{

    @BeforeMethod
    public void openSite(){
        driver.get("https://www.google.com/");
        waitForJsToLoad();
    }

    GoogleMainPage googleMainPage;
    GoogleSearchResultPage googleSearchResultPage;

    String searchText = "iphone kyiv buy";
    //String expectedLink = "allo.ua";
    //String expectedLink = "istudio.ua";
    String expectedLink = "asdffasdfasdf";

    @Test
    public void searchResultListingTest() throws MaxOfPossibleResultException{
        googleMainPage = new GoogleMainPage(driver);
        googleSearchResultPage = googleMainPage.performSearch(searchText);
        Assert.assertTrue(googleSearchResultPage.checkIfExpectedSiteWasFoundOnFirstFivePages(expectedLink));

    }

}
