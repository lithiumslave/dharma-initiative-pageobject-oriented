package hillel;

import BookingPages.BookingHotelsResultPage;
import BookingPages.BookingMainPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BookingTest extends UIBaseTest {

    @BeforeMethod
    public void openSite() {
        driver.get("https://www.booking.com");
        waitForJsToLoad();
    }

    BookingMainPage bookingMainPage;
    BookingHotelsResultPage bookingHotelsResultPage;

    String searchCity = "Paris";
    String startMonth = "November";
    String startYear = "2021";
    String startDay = "25";
    String endMonth = "December";
    String endYear = "2021";
    String endDay = "5";

    @Test
    public void positiveTripCase() throws InterruptedException {
        bookingMainPage = new BookingMainPage(driver);

        bookingMainPage.getTopPanelFragment().clickOnLanguageButton();
        bookingMainPage.getTopPanelFragment().clickOnEnglishButtonInModal();

        bookingMainPage.enterCityInSearch(searchCity);
        bookingMainPage.clickOnCheckInOutField();

        bookingMainPage.clickOnSelectedDayInDatepicker(startMonth, startYear, startDay);
        bookingMainPage.clickOnSelectedDayInDatepicker(endMonth, endYear, endDay);
        bookingHotelsResultPage = bookingMainPage.clickOnSearchButton();

        Assert.assertTrue(bookingHotelsResultPage.checkIfCityOnResultPageEqualsExpected(searchCity));
        Assert.assertTrue(bookingHotelsResultPage.checkIfCheckInDateEqualsExpected(startMonth, startYear, startDay));
        Assert.assertTrue(bookingHotelsResultPage.checkIfCheckOutDateEqualsExpected(endMonth, endYear, endDay));

        bookingHotelsResultPage.clickOnSuperbReviewScore();
        Thread.sleep(3000);
        Assert.assertTrue(bookingHotelsResultPage.checkIfScoresOnPageAreMoreThanNine());
    }
}
