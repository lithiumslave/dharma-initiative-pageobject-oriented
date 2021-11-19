package Supporting;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    JavascriptExecutor js;

    public BasePage(WebDriver webDriver) {
        this.driver = webDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        js = (JavascriptExecutor)driver;
        PageFactory.initElements(driver, this);
    }

    public boolean waitForJsToLoad() {
        final String loadJsScript = "return document.readyState";
        ExpectedCondition<Boolean> jsLoad = driver -> js.executeScript(loadJsScript).toString().equals("complete");
        return wait.until(jsLoad);
    }

    public void clickBack() {
        driver.navigate().back();
    }
}
