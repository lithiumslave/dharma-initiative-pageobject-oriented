package RozetkaPages;

import Supporting.BasePage;
import org.openqa.selenium.WebDriver;

public class RozetkaMainPage extends BasePage {

    RozetkaMainPageLeftMenuFragment leftMenuFragment;
    TopPanelFragment topPanelFragment;

    public RozetkaMainPage(WebDriver driver) {
        super(driver);
        leftMenuFragment = new RozetkaMainPageLeftMenuFragment(driver);
        topPanelFragment = new TopPanelFragment(driver);
    }

    public void clickOnGamingMenuItem() {
        leftMenuFragment.getGamingGoods().click();
    }

    public void clickLoyaltyItem() {
        leftMenuFragment.getLoyaltyField().click();
    }

    public RozetkaComputersNotebooksPage clickComputerAndNotebooks() {
        leftMenuFragment.getComputersAndNotebooks().click();
        waitForJsToLoad();
        return new RozetkaComputersNotebooksPage(driver);
    }

    public TopPanelFragment getTopPanelFragment() {
        return topPanelFragment;
    }
    public RozetkaMainPageLeftMenuFragment getLeftMenuFragment() { return leftMenuFragment; }
}

