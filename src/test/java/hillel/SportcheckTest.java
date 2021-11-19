package hillel;

import SportcheckPages.SportcheckProductDetailsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class SportcheckTest{
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    JavascriptExecutor js;

    @BeforeClass
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor)driver;
        //actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    String url = "https://www.sportchek.ca/categories/women/tops/hoodies/product/champion-womens-sportswear-powerblend-applique-hoodie-color-333261000_06-333261000.html#333261000%5Bcolor%5D=333261000_06";
    String expectedQty = "3";
    String expectedTitleForCurrentCase = "CHAMPION W SW POWERBLEND APPLIQUE HDD (Q320) GREY";
    String confirmationMessage = "been added to your cart";

    SportcheckProductDetailsPage sportcheckProductDetailsPage;

    @Test
    public void addProductToCart() throws InterruptedException {
        sportcheckProductDetailsPage = new SportcheckProductDetailsPage(driver);
        driver.get(url);
        waitForJsToLoad();

        sportcheckProductDetailsPage.clickFirstUnselectedAvailableColor();
        sportcheckProductDetailsPage.clickFirstAvailableSize();
        sportcheckProductDetailsPage.setQtyOfProduct(expectedQty);
        sportcheckProductDetailsPage.clickOnAddToCartButton();

        Assert.assertTrue(sportcheckProductDetailsPage.checkIfConfirmationMessageShown(confirmationMessage));
        // wait 5 sec until confirmation modal will close
        Thread.sleep(5000);
        sportcheckProductDetailsPage.hoverCartIcon();
        Assert.assertTrue(sportcheckProductDetailsPage.checkIfQtyInModalEqualsExpectedQty(expectedQty));
        Assert.assertTrue(sportcheckProductDetailsPage.checkIfTitleInModalEqualsExpectedTitle(expectedTitleForCurrentCase));
    }

    public boolean waitForJsToLoad() {
        final String loadJsScript = "return document.readyState";
        ExpectedCondition<Boolean> jsLoad = driver -> js.executeScript(loadJsScript).toString().equals("complete");
        return wait.until(jsLoad);
    }
}
