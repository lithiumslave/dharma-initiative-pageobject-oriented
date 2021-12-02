package hillel.SelenideTests.CitrusTests;

import fe.CitrusPages.CitrusMainPage;
import fe.CitrusPages.CitrusProductListingPage;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CitrusFilterTest {

    String category = "Смартфоны";
    String producerSam = "Samsung";
    String producerXia = "Xiaomi";
    String minPriceValue = "5467";
    String maxPriceValue = "10000";
    String memory32 = "32";
    String memory64 = "64";

    @BeforeClass
    public void setUp() {
        Configuration.baseUrl = "https://www.citrus.ua";
        Configuration.browserSize = "1880x1150";
    }

    @Test
    public void checkMinAndMaxFilter() throws InterruptedException {
        CitrusMainPage mainPage = new CitrusMainPage();
        CitrusProductListingPage productListingPage;

        productListingPage = mainPage.open().closeAdPopup()
                .hoverOverCategory(category)
                .chooseByProducer(producerSam);

        productListingPage.getFilterFragment()
                .filterProductsByMinMaxPrice(minPriceValue, maxPriceValue);
                //.shouldHaveSelectedPrice(minPriceValue, maxPriceValue);
        Thread.sleep(1000);
        productListingPage.shouldHaveProductsInPriceRange(minPriceValue, maxPriceValue)
                        .shouldHaveProducerInName(producerSam);
    }

    @Test
    public void checkMemorySizeFilter() {
        CitrusMainPage mainPage = new CitrusMainPage();
        CitrusProductListingPage productListingPage;

        productListingPage = mainPage.open().closeAdPopup()
                .hoverOverCategory(category)
                .chooseByProducer(producerXia);

        productListingPage.getFilterFragment()
                .clickOnCheckBoxTitleByText(memory64)
                .clickOnCheckBoxTitleByText(memory32);

        productListingPage.shouldHaveExpectedTextInPageTitle(memory64)
                .shouldHaveExpectedTextInPageTitle(memory32)
                .shouldHaveProducerInName(producerXia)
                .shouldHaveExpectedMemoriesInProductsTitles(memory64, memory32);
    }
}
