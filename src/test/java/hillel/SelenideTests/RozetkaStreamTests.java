package hillel.SelenideTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import fe.RozetkaPages.RozetkaGlobalStreamPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class RozetkaStreamTests {
    String searchText = "lenovo";
    String expectedText = "IdeaPad";

    RozetkaGlobalStreamPage rozetkaPage;

    @BeforeTest
    public void setUp() {
        Configuration.baseUrl = "https://rozetka.com.ua";
        Configuration.browserSize = "1880x1150";
    }

   /*  Login to https://rozetka.com.ua/
    *  Search 'lenovo'
    *  Get a list of product container elements that you get on ProductListingPage
    *  Create following methods using .stream on the above found collection:
    *      - method that will return List<Integer> of all products' prices
    *      - method that will return List<String> of all products' titles
    *      - method that will return a list of product elements with price > 8000
    *      - method that will return a q-ty of products with price < 8000
    *      - method that will return max price found on page
    *      - method that will return List<Integer> of prices for those elements that have partial text 'IdeaPad' in their title
    */
    @Test
    public void test() {
        rozetkaPage = new RozetkaGlobalStreamPage();
        rozetkaPage.open()
                .performSearch(searchText);
        //List of prices
        List<Integer> productPrices = rozetkaPage.getAllProductsPrices();
        System.out.println(productPrices);

        //List of titles
        List<String> productTitles = rozetkaPage.getAllProductsTitles();
        System.out.println(productTitles);

        //List with prices > 8000
        List<Integer> pricesMoreThanExpected = rozetkaPage.getAllPricesMoreThanExpected(8000);
        System.out.println(pricesMoreThanExpected);

        //List with prices < 8000
        int pricesLessThanExpected = rozetkaPage.getQtyOfProductsWithPriceLessThanExpected(8000);
        System.out.println(pricesLessThanExpected);

        //Maximum price on page
        int maxPriceOnPage = rozetkaPage.getMaxPriceOnPage();
        System.out.println(maxPriceOnPage);

        //List of products with "IdeaPad" in title
        List<Integer> pricesOfProductsWithExpectedTextInTitle = rozetkaPage.getAllPricesOfProductsWithExpectedTextInTitles(expectedText);
        System.out.println(pricesOfProductsWithExpectedTextInTitle);
    }
}
