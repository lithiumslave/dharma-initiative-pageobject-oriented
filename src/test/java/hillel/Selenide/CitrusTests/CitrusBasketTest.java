package hillel.Selenide.CitrusTests;

import CitrusPages.CitrusComparisonPage;
import CitrusPages.CitrusMainPage;
import CitrusPages.CitrusProductListingPage;
import CitrusPages.CitrusProductPage;
import CitrusPages.CitrusSearchResultPage;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CitrusBasketTest {

    String category = "Смартфоны";
    String producer = "Apple";
    String searchItemIphone12Detailed = "Apple iPhone 12 Pro Max 128GB Pacific Blue";
    String searchItemIphone12 = "Apple iPhone 12";
    String searchItemIphone = "Apple iPhone";

    @BeforeClass
    public void setUp() {
        Configuration.baseUrl = "https://www.citrus.ua";
        Configuration.browserSize = "1880x1150";
    }

    @Test
    public void addItemToBasketViaMenu() {

        CitrusMainPage mainPage = new CitrusMainPage();
        CitrusProductListingPage productListingPage;
        CitrusProductPage productPage;

        productListingPage = mainPage.open().closeAdPopup()
                .hoverOverCategory(category)
                .chooseByProducer(producer);

        productListingPage.shouldHaveProductTitle(searchItemIphone12Detailed);
        String productPrice = productListingPage.getProductPrice(searchItemIphone12Detailed);
        productPage = productListingPage.clickProduct(searchItemIphone12Detailed);

        productPage.shouldHaveProductTitle(searchItemIphone12Detailed)
                .shouldHaveProductPrice(productPrice)
                .clickBuyButton()
                .getAddedProductFragment()
                .shouldHaveProductTitle(searchItemIphone12Detailed)
                .closeAddedProductModal();

        productPage.getBasketFragment().openBasket()
                .shouldHaveProductsQuantity(1)
                .shouldHaveProductTitle(searchItemIphone12Detailed)
                .shouldHaveProductPrice(searchItemIphone12Detailed, productPrice)
                .shouldHaveProductTotal(searchItemIphone12Detailed)
                .shouldHaveItemQuantity(searchItemIphone12Detailed, "1")
                .shouldHaveBasketTotalCost();
    }

    @Test
    public void addItemToBasketViaSearch() {
        CitrusMainPage mainPage = new CitrusMainPage();
        CitrusSearchResultPage searchResultPage;

        searchResultPage = mainPage.open().closeAdPopup()
                .getSearchFragment().performSearch(searchItemIphone12);

        searchResultPage.shouldHaveProductTitle(searchItemIphone12Detailed);
        String productPrice = searchResultPage.getProductPrice(searchItemIphone12Detailed);
        searchResultPage.clickBasketIconInProductBlock(searchItemIphone12Detailed)
                .getAddedProductFragment()
                .shouldHaveProductTitleOnAddedProductModal(searchItemIphone12Detailed)
                .closeAddedProductModalOnSearchPage();

        searchResultPage.getBasketFragmentOnSearchPage().openBasket()
                .shouldHaveProductsQuantity(1)
                .shouldHaveProductTitle(searchItemIphone12Detailed)
                .shouldHaveProductPrice(searchItemIphone12Detailed, productPrice)
                .shouldHaveBasketTotalCostWithOneProduct(searchItemIphone12Detailed);
    }

    @Test
    public void add2ItemsToBasketViaSearch() {
        CitrusMainPage mainPage = new CitrusMainPage();
        CitrusSearchResultPage searchResultPage;

        searchResultPage = mainPage.open().closeAdPopup()
                .getSearchFragment().performSearch(searchItemIphone);

        searchResultPage.shouldHaveProductTitle(searchItemIphone);
        String productPriceFirst = searchResultPage.getProductPriceByItemNumber(0);
        String productTitleFirst = searchResultPage.getProductTitleByItemNumber(0);
        String productPriceSecond = searchResultPage.getProductPriceByItemNumber(1);
        String productTitleSecond = searchResultPage.getProductTitleByItemNumber(1);

        searchResultPage.clickBasketIconInProductBlockByItemNumber(0)
                .getAddedProductFragment()
                .shouldHaveProductTitleOnAddedProductModal(productTitleFirst)
                .closeAddedProductModalOnSearchPage();

        searchResultPage.clickBasketIconInProductBlockByItemNumber(1)
                .getAddedProductFragment()
                .shouldHaveProductTitleOnAddedProductModal(productTitleSecond)
                .closeAddedProductModalOnSearchPage();

        searchResultPage.getBasketFragmentOnSearchPage().openBasket()
                .shouldHaveProductsQuantity(2)
                .shouldHaveProductTitle(productTitleFirst)
                .shouldHaveProductPrice(productTitleFirst, productPriceFirst)
                .shouldHaveProductTitle(productTitleSecond)
                .shouldHaveProductPrice(productTitleSecond, productPriceSecond)
                .shouldHaveBasketTotalCostWithTwoProducts(productPriceFirst, productPriceSecond);
    }

    @Test
    public void add2ItemsToBasketFromComparisonViaSearch() {
        CitrusMainPage mainPage = new CitrusMainPage();
        CitrusSearchResultPage searchResultPage;
        CitrusComparisonPage comparisonPage;

        searchResultPage = mainPage.open().closeAdPopup()
                .getSearchFragment().performSearch(searchItemIphone);

        searchResultPage.shouldHaveProductTitle(searchItemIphone);
        String productPriceFirst = searchResultPage.getProductPriceByItemNumber(0);
        String productTitleFirst = searchResultPage.getProductTitleByItemNumber(0);
        String productPriceSecond = searchResultPage.getProductPriceByItemNumber(1);
        String productTitleSecond = searchResultPage.getProductTitleByItemNumber(1);

        searchResultPage.clickComparisonIconInProductBlockByItemNumber(0)
                .getComparisonFragment().shouldHaveExpectedCounter("1");
        searchResultPage.clickComparisonIconInProductBlockByItemNumber(1)
                .getComparisonFragment().shouldHaveExpectedCounter("2");

        comparisonPage = searchResultPage.getComparisonFragment()
                .clickComparisonIconOnSearchPage();

        comparisonPage.shouldHaveProductTitle(productTitleFirst)
                .shouldHaveProductTitle(productTitleSecond)
                .addFirstProductToBasketByItem()
                .getAddedProductFragment()
                .shouldHaveProductTitleOnAddedProductModal(productTitleSecond)
                .closeAddedProductModalOnSearchPage();

        comparisonPage.addSecondProductToBasketByItem()
                .getAddedProductFragment()
                .shouldHaveProductTitleOnAddedProductModal(productTitleFirst)
                .closeAddedProductModalOnSearchPage();

        comparisonPage.getBasketFragmentOnSearchPage().openBasket()
                .shouldHaveProductsQuantity(2)
                .shouldHaveProductTitle(productTitleFirst)
                .shouldHaveProductPrice(productTitleFirst, productPriceFirst)
                .shouldHaveProductTitle(productTitleSecond)
                .shouldHaveProductPrice(productTitleSecond, productPriceSecond)
                .shouldHaveBasketTotalCostWithTwoProducts(productPriceFirst, productPriceSecond);
    }
}
