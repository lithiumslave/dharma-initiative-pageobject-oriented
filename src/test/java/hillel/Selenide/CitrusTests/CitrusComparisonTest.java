package hillel.Selenide.CitrusTests;

import CitrusPages.CitrusComparisonPage;
import CitrusPages.CitrusMainPage;
import CitrusPages.CitrusProductListingPage;
import CitrusPages.CitrusSearchResultPage;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CitrusComparisonTest {

    String category = "Ноутбуки, планшеты, МФУ";
    String producer = "Acer";
    String searchRequest = "Asus notebook";

    @BeforeClass
    public void setUp() {
        Configuration.baseUrl = "https://www.citrus.ua";
        Configuration.browserSize = "1880x1150";
    }

    /*     1.Compare 2+1 products
    1) Click Notebooks/Acer in menu
    2) Add first and second laptop to comparison (no navigation to product page). Remember names, prices
    3) Click on comparison icon in header
    4) Verify
        - only 2 products in comparison
        - prices and names of products are correct
    5) On comparison manu enter into Search field 'Asus notebook', press enter
    6) Choose first (remember name, price)
    7) Verify
        - only 3 products in comparison
        - prices and names of products are correct*/

    @Test
    public void testComparisonPage() {
        CitrusMainPage mainPage = new CitrusMainPage();
        CitrusSearchResultPage searchResultPage;
        CitrusProductListingPage productListingPage;
        CitrusComparisonPage comparisonPage;

        productListingPage = mainPage.open().closeAdPopup()
                .hoverOverCategory(category)
                .chooseByProducer(producer);

        productListingPage.shouldHaveExpectedTextInPageTitle(producer);

        String productPriceFirst = productListingPage.getProductPriceByItemNumber(0);
        String productTitleFirst = productListingPage.getProductTitleByItemNumber(0);
        String productPriceSecond = productListingPage.getProductPriceByItemNumber(1);
        String productTitleSecond = productListingPage.getProductTitleByItemNumber(1);

        productListingPage.clickComparisonIconInProductBlockByItemNumber(0)
                .getComparisonFragment().shouldHaveExpectedCounterOnListing("1");
        productListingPage.clickComparisonIconInProductBlockByItemNumber(1)
                .getComparisonFragment().shouldHaveExpectedCounterOnListing("2");

        comparisonPage = productListingPage.getComparisonFragment()
                .clickComparisonIconOnListing();

        comparisonPage.shouldHaveProductTitle(productTitleFirst)
                .shouldHaveProductTitle(productTitleSecond)
                .shouldHaveProductPriceByItem(0, productPriceSecond)
                .shouldHaveProductPriceByItem(1, productPriceFirst)
                .shouldHaveProductsQuantity(2);

        searchResultPage = comparisonPage
                .getSearchFragment()
                .performSearchOnComparisonPage(searchRequest);

        searchResultPage.shouldHaveRequestInPageHeader(searchRequest);
        String searchResultFirstPrice = searchResultPage.getProductPriceByItemNumber(0);
        String searchResultFirstTitle = searchResultPage.getProductTitleByItemNumber(0);

        comparisonPage = searchResultPage.clickComparisonIconInProductBlockByItemNumber(0)
                .getComparisonFragment().shouldHaveExpectedCounter("3")
                .clickComparisonIconOnSearchPage();

        comparisonPage.shouldHaveProductTitle(searchResultFirstTitle)
                .shouldHaveProductTitle(productTitleFirst)
                .shouldHaveProductTitle(productTitleSecond)
                .shouldHaveProductPriceByItem(0, productPriceFirst)
                .shouldHaveProductPriceByItem(1, productPriceSecond)
                .shouldHaveProductPriceByItem(2, searchResultFirstPrice)
                .shouldHaveProductsQuantity(3);
    }
}
