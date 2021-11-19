package BookingPages;

import Supporting.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BookingHotelsResultPage extends BasePage {

    @FindBy(id = "ss")
    private WebElement cityInResultField;

    @FindBy(css = "div[data-placeholder='Check-in date']")
    private WebElement startDayInResultField;

    @FindBy(css = "div[data-placeholder='Check-out date']")
    private WebElement endDayInResultField;

    @FindBy(css = "div[data-filters-item='review_score:review_score=90']")
    private WebElement superbReviewScore;

    @FindBy(css = "div[aria-label^='Scored']")
    private List<WebElement> scoresOfHotels;

    public BookingHotelsResultPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean checkIfCityOnResultPageEqualsExpected(String searchCity) {
        return cityInResultField.getAttribute("placeholder").equals(searchCity);
    }

    public boolean checkIfCheckInDateEqualsExpected(String month, String year, String day) {
        return startDayInResultField.getText().contains(day + " " + month + " " + year);
    }

    public boolean checkIfCheckOutDateEqualsExpected(String month, String year, String day) {
        return endDayInResultField.getText().contains(day + " " + month + " " + year);
    }

    public void clickOnSuperbReviewScore() {
        wait.until(ExpectedConditions.elementToBeClickable(superbReviewScore)).click();
        waitForJsToLoad();
    }

    public boolean checkIfScoresOnPageAreMoreThanNine() {
        boolean result = false;
        double minimalScoreExpected = 9.0;

        for (WebElement score : scoresOfHotels) {
           if (Double.parseDouble(score.getText()) >= minimalScoreExpected) {
               result = true;
           } else {
               result = false;
               break;
           }
        }

        return result;
    }

}
