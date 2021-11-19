package GooglePages;

import Supporting.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleMainPage extends BasePage {

    @FindBy(css = "input.gLFyf")
    private WebElement searchField;

    public GoogleMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public GoogleSearchResultPage performSearch(String searchText) {
        searchField.click();
        searchField.sendKeys(searchText, Keys.ENTER);
        return new GoogleSearchResultPage(driver);
    }
}
