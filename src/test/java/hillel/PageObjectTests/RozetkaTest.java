package hillel.PageObjectTests;

import fe.RozetkaPages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RozetkaTest extends UIBaseTest {

    @BeforeMethod
    public void openSite() {
        driver.get("https://rozetka.com.ua/");
    }

    /*@AfterMethod
    public void refreshSite() {
        driver.navigate().refresh();
    }*/

    String searchTextXBox = "Xbox Series X 1Tb";
    String searchTextSamsung = "samsung";
    String appleTitle = "Apple";
    String samsungTitle = "Samsung";
    String huaweiTitle = "Huawei";
    String minPriceValueToSend = "5000";
    String maxPriceValueToSend = "15000";
    String memoryExpected = "12 ГБ";
    String expectedPriceForMonitor = "5000";
    String counterFirst = "1";
    String counterSecond = "2";
    int expectedNumberOfProductsOnComparisonPage = 2;

    RozetkaMainPage rozetkaMainPage;
    RozetkaProductsPage productsPage;
    RozetkaProductDetailsPage productDetailsPage;
    RozetkaProductsPageLeftMenuFragment phonesSearchResultPage;
    RozetkaComputersNotebooksPage computersNotebooksPage;
    RozetkaComparisonPage rozetkaComparisonPage;

    @Test
    public void testBrandFilter() {
        rozetkaMainPage = new RozetkaMainPage(driver);

        productsPage = rozetkaMainPage.getTopPanelFragment().performSearch(searchTextSamsung);

        productsPage.getProductsPageLeftMenuFragment().clickOnMobilePhonesInFilter();
        productsPage.getProductsPageLeftMenuFragment().clickOnAppleItemInFilter();
        productsPage.getProductsPageLeftMenuFragment().clickOnHuaweiItemInFilter();

        Assert.assertTrue(productsPage.checkAllProductsTitlesContainsExpectedTitles(appleTitle, samsungTitle, huaweiTitle));
    }

    @Test
    public void testPriceFilter() throws InterruptedException {
        rozetkaMainPage = new RozetkaMainPage(driver);

        productsPage = rozetkaMainPage.getTopPanelFragment().performSearch(searchTextSamsung);
        productsPage.getProductsPageLeftMenuFragment().clickOnMobilePhonesInFilter();
        productsPage.getProductsPageLeftMenuFragment().setPriceFilterRangeAndClickOkButton(minPriceValueToSend, maxPriceValueToSend);

        Assert.assertTrue(productsPage.checkAllProductsOnPageAreInExpectedPriceRange(minPriceValueToSend, maxPriceValueToSend));
    }

    @Test
    public void testRamFilter() throws InterruptedException {
        rozetkaMainPage = new RozetkaMainPage(driver);

        productsPage = rozetkaMainPage.getTopPanelFragment().performSearch(searchTextSamsung);
        productsPage.getProductsPageLeftMenuFragment().clickOnMobilePhonesInFilter();
        productsPage.getProductsPageLeftMenuFragment().clickOn12GbRamInFilter();

        Assert.assertTrue(productsPage.checkAllProductsDescriptionContainsExpectedMemory(memoryExpected));
    }

    @Test
    public void verificationOfComparisonOf2Monitors() throws InterruptedException {
        rozetkaMainPage = new RozetkaMainPage(driver);

        computersNotebooksPage = rozetkaMainPage.clickComputerAndNotebooks();
        productsPage = computersNotebooksPage.clickOnMonitorsItem();

        productDetailsPage = productsPage.clickFirstMonitorWithPriceLessThanExpected(expectedPriceForMonitor);
        String firstMonitorTitle = productDetailsPage.getProductTitleText();
        String firstMonitorPrice = productDetailsPage.getProductPrice();
        productDetailsPage.clickOnCompareButton();
        Assert.assertTrue(productDetailsPage.getTopPanelFragment().checkCorrectCompareCounterEqualsExpected(counterFirst));

        backToPreviousPage();

        productDetailsPage = productsPage.clickFirstMonitorWithPriceLessThanExpected(firstMonitorPrice);
        String secondMonitorTitle = productDetailsPage.getProductTitleText();
        String secondMonitorPrice = productDetailsPage.getProductPrice();
        productDetailsPage.clickOnCompareButton();
        Assert.assertTrue(productDetailsPage.getTopPanelFragment().checkCorrectCompareCounterEqualsExpected(counterSecond));

        productDetailsPage.getTopPanelFragment().clickOnCompareButtonInHeader();
        rozetkaComparisonPage = productDetailsPage.getTopPanelFragment().clickOnFirstItemInComparisonList();

        Assert.assertTrue(rozetkaComparisonPage.checkIfNumberOfProductsAreEqualExpected(expectedNumberOfProductsOnComparisonPage));
        Assert.assertTrue(rozetkaComparisonPage.checkIfTitlesAreEqualExpectedForTwoProducts(firstMonitorTitle, secondMonitorTitle));
        Assert.assertTrue(rozetkaComparisonPage.checkIfPricesAreEqualExpectedForTwoProducts(firstMonitorPrice, secondMonitorPrice));
    }

    @Test
    public void testAddingProductToCart() {

        rozetkaMainPage = new RozetkaMainPage(driver);

        //rozetkaMainPage.clickOnGamingMenuItem();
        productsPage = rozetkaMainPage.getTopPanelFragment().performSearch(searchTextXBox);

        productDetailsPage = productsPage.clickProductTitle();

        String actualProductTitle = productDetailsPage.getProductTitleText();
        Assert.assertTrue(actualProductTitle.toLowerCase().contains(searchTextXBox.toLowerCase()));

        productDetailsPage.addProductToCart();

        Assert.assertFalse(productDetailsPage.getTopPanelFragment().isCartEmpty());
        Assert.assertEquals(productDetailsPage.getTopPanelFragment().getCartLabelText(), "1");
    }
}
