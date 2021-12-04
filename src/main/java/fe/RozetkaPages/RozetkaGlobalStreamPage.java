package fe.RozetkaPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class RozetkaGlobalStreamPage {

    private final SelenideElement searchField = $("input[name='search']");
    private final ElementsCollection productBoxes = $$("div.goods-tile__inner");

    public RozetkaGlobalStreamPage open() {
        Selenide.open("/");
        return this;
    }

    public RozetkaGlobalStreamPage performSearch(String searchText) {
        searchField.setValue(searchText).sendKeys(Keys.ENTER);
        Selenide.sleep(3000);
        return this;
    }

    public ElementsCollection getProductBoxes() {
        return productBoxes;
    }

    public List<Integer> getAllProductsPrices() {
        List<Integer> prices = getProductBoxes().stream()
                .map(i -> Integer.parseInt(i.$(".goods-tile__price-value").getText().replaceAll("[^0-9]", "")))
                .collect(Collectors.toList());

        return prices;
    }

    public List<String> getAllProductsTitles() {
        List<String> titles = getProductBoxes().stream()
                .map(t -> t.$(".goods-tile__title").getText())
                .collect(Collectors.toList());

        return titles;
    }

    public List<Integer> getAllPricesMoreThanExpected(int price) {
        List<Integer> pricesMoreThanExpected = getAllProductsPrices().stream()
                .filter(f -> f > price)
                .collect(Collectors.toList());

        return pricesMoreThanExpected;
    }

    public int getQtyOfProductsWithPriceLessThanExpected(int price) {
        int pricesLessThanExpected = (int) getAllProductsPrices().stream()
                .filter(f -> f < price)
                .count();

        return pricesLessThanExpected;
    }

    public int getMaxPriceOnPage() {
        int maxPrice = getAllProductsPrices().stream()
                .mapToInt(v -> v)
                .max().orElseThrow(NoSuchElementException::new);
        return maxPrice;
    }

    public List<Integer> getAllPricesOfProductsWithExpectedTextInTitles(String expectedText) {
        List<Integer> prices = getProductBoxes().stream()
                .filter(p -> p.$(".goods-tile__title").getText().contains(expectedText))
                .map(p -> Integer.parseInt(p.$(".goods-tile__price-value").getText().replaceAll("[^0-9]", "")))
                .collect(Collectors.toList());

        return prices;
    }
}
