package fe.herokuappPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class HerokuappIframePage {
    WebDriver driver;
    Actions actions;

    @FindBy(css = "button[title='Italic']")
    private WebElement italicTextButton;

    @FindBy(id = "mce_0_ifr")
    private WebElement frame;

    @FindBy(id = "tinymce")
    private WebElement frameEditor;

    @FindBy(css = "div > h3")
    private WebElement pageHeader;



    public HerokuappIframePage(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        PageFactory.initElements(driver, this);
    }

    public void clickOnItalicButton() {
        italicTextButton.click();
    }

    public boolean verifyItalicButtonIsSelected() {
        return italicTextButton.getAttribute("aria-pressed").contains("true");
    }

    public void switchToFrame() {
        driver.switchTo().frame(frame);
    }

    public void switchToDefault() {
        driver.switchTo().defaultContent();
    }

    public void enterTextIntoEditor(String textToEnter) {
        clearEditor();
        sendKeysToEditor(textToEnter);
    }

    public void clearEditor() {
        frameEditor.clear();
    }

    public void sendKeysToEditor(String textToEnter) {
        frameEditor.sendKeys(textToEnter);
    }

    public boolean checkIfEditorHasExpectedText(String expectedText) {
        return frameEditor.getText().equals(expectedText);
    }

    public boolean checkIfHeaderHasExpectedHeading(String heading) {
        return pageHeader.getText().equals(heading);
    }
}
