package BookingPages;

import Supporting.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TopPanelFragment extends BasePage {

    @FindBy(css = "button[data-modal-id='language-selection']")
    private WebElement languageButton;

    @FindBy(css = "a[data-lang='en-gb']")
    private WebElement englishButtonInModal;

    public TopPanelFragment(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickOnLanguageButton() {
        wait.until(ExpectedConditions.elementToBeClickable(languageButton)).click();
    }

    public void clickOnEnglishButtonInModal() {
        wait.until(ExpectedConditions.elementToBeClickable(englishButtonInModal)).click();
    }

}
