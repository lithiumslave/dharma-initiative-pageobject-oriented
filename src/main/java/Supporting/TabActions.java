package Supporting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.ArrayList;

public interface TabActions {
    WebDriver driver = new ChromeDriver();

    default String getWindowHanle() {
        return driver.getWindowHandle();
    }

    default ArrayList<String> getNewArrayOfTabs() {
        return new ArrayList<>(driver.getWindowHandles());
    }

    default void switchToFirstTab(String firstTab) {
        driver.switchTo().window(firstTab);
    }

    default void switchToSecondTab(ArrayList<String> tabs) {
        driver.switchTo().window(tabs.get(1));
    }

    default void switchToThirdTab(ArrayList<String> tabs) {
        driver.switchTo().window(tabs.get(2));
    }
}
