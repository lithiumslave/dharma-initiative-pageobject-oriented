package GooglePages;

import Supporting.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class GoogleSearchResultPage extends BasePage {

    By links = By.cssSelector("div[class='TbwUpd NJjxre']");
    By nextPage = By.cssSelector("a[id='pnnext']");

    public GoogleSearchResultPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean checkIfExpectedSiteWasFoundOnFirstFivePages(String expectedWebsite) throws MaxOfPossibleResultException {
        boolean result = false;
        for (int i = 1; i <= 5; i++) {
            List<WebElement> searchResults = driver.findElements(links);
            for (WebElement results : searchResults) {
                if (results.getText().contains(expectedWebsite)) {
                    System.out.println("Found on page: " + i);
                    result = true;
                    break;
                }
            }

            if (result) break;
            else if (!(i == 5)) {
                driver.findElement(nextPage).click();
            } else {
                throw new MaxOfPossibleResultException("Expected site was not found on first 5 pages");
            }
        }
        return result;
    }

   /* public boolean checkIfExpectedSiteWasFoundOnFirstFivePages(String expectedSite) throws MaxOfPossibleResultException, InterruptedException {
        boolean result = false;
        WebElement nextPageButton = driver.findElement(nextPage);

        for (int i = 1; i < 6; i++) {
            List<WebElement> resultLinksOnPage = driver.findElements(links);

            for (WebElement link : resultLinksOnPage) {
                System.out.println(link.getText());
                if (link.getText().contains(expectedSite)) {
                    System.out.println(expectedSite.toUpperCase() + " was found on " + i + " page");
                    result = true;
                }
            }
            if (i == 5) {
                throw new MaxOfPossibleResultException("ALLO.UA not found on first 5 pages");
            } else {
                wait.until(ExpectedConditions.presenceOfElementLocated(nextPage));
                Thread.sleep(3000);
                nextPageButton.click();
            }
        }
        return result;
    }*/

}
