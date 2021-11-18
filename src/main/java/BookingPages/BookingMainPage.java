package BookingPages;

import Supporting.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BookingMainPage extends BasePage {

    TopPanelFragment topPanelFragment;

    By datepickerBlock = By.cssSelector(".bui-calendar__wrapper");
    By datepickerMonth = By.cssSelector(".bui-calendar__month");
    By datepickerDay = By.cssSelector("td[data-bui-ref='calendar-date']");

    @FindBy(css = "input[id='ss']")
    private WebElement searchCityField;

    @FindBy(css = "div[class='xp__dates xp__group']")
    private WebElement checkInOutField;

    @FindBy(css = ".bui-calendar__wrapper")
    private List<WebElement> monthSections;

    @FindBy(css = ".xp__button")
    private WebElement searchButton;

    public BookingMainPage(WebDriver driver) {
        super(driver);
        topPanelFragment = new TopPanelFragment(driver);
    }

    public void enterCityInSearch(String city) {
        wait.until(ExpectedConditions.elementToBeClickable(searchCityField));
        searchCityField.sendKeys(city);
    }

    public void clickOnCheckInOutField() {
        wait.until(ExpectedConditions.elementToBeClickable(checkInOutField)).click();
    }

    public void clickOnSelectedDayInDatepicker(String month, String year, String day) {
        wait.until(ExpectedConditions.presenceOfElementLocated(datepickerBlock));

        for (WebElement startMonthActual : monthSections) {
            if (startMonthActual.findElement(datepickerMonth).getText().equals(month + " " + year)) {
                List<WebElement> days = startMonthActual.findElements(datepickerDay);
                for (WebElement startDayActual : days) {
                    if (startDayActual.getText().equals(day)) {
                        startDayActual.click();
                        break;
                    }
                }
            }
        }
    }

    public BookingHotelsResultPage clickOnSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        waitForJsToLoad();
        return new BookingHotelsResultPage(driver);
    }

    public TopPanelFragment getTopPanelFragment() {
        return topPanelFragment;
    }
}
