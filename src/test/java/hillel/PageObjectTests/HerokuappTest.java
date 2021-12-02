package hillel.PageObjectTests;

import fe.herokuappPages.HerokuappIframePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HerokuappTest extends UIBaseTest{

    private final String herokuappIframeUrl = "http://the-internet.herokuapp.com/iframe";
    private final String enteredText = "Biba i boba";
    private final String pageHeaderText = "An iFrame containing the TinyMCE WYSIWYG Editor";
    HerokuappIframePage herokuappIframePage;

    @Test
    public void iframeTest() {
        driver.get(herokuappIframeUrl);
        herokuappIframePage = new HerokuappIframePage(driver);

        herokuappIframePage.clickOnItalicButton();
        Assert.assertTrue(herokuappIframePage.verifyItalicButtonIsSelected());

        herokuappIframePage.switchToFrame();

        herokuappIframePage.enterTextIntoEditor(enteredText);
        herokuappIframePage.checkIfEditorHasExpectedText(enteredText);

        herokuappIframePage.switchToDefault();

        Assert.assertTrue(herokuappIframePage.checkIfHeaderHasExpectedHeading(pageHeaderText));
    }
}
