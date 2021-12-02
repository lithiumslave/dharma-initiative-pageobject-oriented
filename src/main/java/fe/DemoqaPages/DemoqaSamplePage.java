package fe.DemoqaPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.ArrayList;

public class DemoqaSamplePage {
    WebDriver driver;

    private String contentText = "This is a sample page";

    @FindBy(id = "sampleHeading")
    private WebElement newTabButton;

    public DemoqaSamplePage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        PageFactory.initElements(driver, this);
    }

    public boolean checkIfContentTextIsCorrect() {
        return newTabButton.getText().equals(contentText);
    }

    public ArrayList<String> getNewArrayOfTabs() {
        return new ArrayList<>(driver.getWindowHandles());
    }

}
