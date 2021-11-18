package RozetkaPages;

import Supporting.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
public class RozetkaMainPageLeftMenuFragment extends BasePage {

    @FindBy(xpath = "//a[contains(@href, 'computers-notebooks') and @class='menu-categories__link']")
    private WebElement computersAndNotebooks;

    @FindBy(xpath="//a[contains(@href, 'game-zone') and @class='menu-categories__link']")
    private WebElement gamingGoods;

    @FindBy(xpath="//a[contains(@href, 'premium') and @class='ng-star-inserted']")
    private WebElement premiumField;

    @FindBy(xpath="//a[contains(@href, 'loyalty') and @class='ng-star-inserted']")
    private WebElement loyaltyField;

    public RozetkaMainPageLeftMenuFragment(WebDriver driver) {
        super(driver);
    }
}
