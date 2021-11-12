package DemoqaPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.ArrayList;

public class DemoqaBrowserWindowsPage {
    WebDriver driver;
    Actions actions;

    private String headerText = "Browser Windows";

    @FindBy(id = "tabButton")
    private WebElement newTabButton;

    @FindBy(css = "div[class='pattern-backgound playgound-header']")
    private WebElement header;

    public DemoqaBrowserWindowsPage(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        PageFactory.initElements(driver, this);
    }

    public String getWindowHanle() {
        return driver.getWindowHandle();
    }

    public ArrayList<String> getNewArrayOfTabs() {
        return new ArrayList<>(driver.getWindowHandles());
    }

    public DemoqaSamplePage clickOnNewTabButton() {
        newTabButton.click();
        return new DemoqaSamplePage(driver);
    }

    public boolean checkIfHeaderTextIsCorrect() {
        return header.getText().equals(headerText);
    }
}
