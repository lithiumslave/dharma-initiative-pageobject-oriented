package RozetkaPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RozetkaMainPage {

    WebDriver driver;

    @FindBy(xpath="//input[@name='search']")
    private WebElement searchField;

    @FindBy(css=".button.button_color_green")
    private WebElement searchButton;

    public RozetkaMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public RozetkaProductsPage performSearch(String searchText) {
        enterTextIntoSearchField(searchText);
        clickSearchButton();
        return new RozetkaProductsPage(driver);
    }

    private void enterTextIntoSearchField(String searchText) {
        searchField.sendKeys(searchText);
    }

    private void clickSearchButton() {
        searchButton.click();
    }
}

