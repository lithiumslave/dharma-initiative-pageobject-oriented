package hillel.PageObjectTests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.*;

public class RyanairTest {

    @BeforeTest
    public void setUp() {
        Configuration.timeout = 10000;
        Configuration.baseUrl = "https://www.ryanair.com";
    }

    @Test
    public void selectTickets() {

        String departureCity = "Berlin";
        String arrivalCity = "Kyiv";

        open("/gb/en");

        $(".cookie-popup-with-overlay__button").click();

        $(By.id("input-button__departure")).click();
        $(By.id("input-button__departure")).setValue(departureCity);

        $("[data-ref=airport-item__name]").shouldHave(text(departureCity))
                .click();
        $(By.id("input-button__departure")).shouldHave(value(departureCity));

        //$(By.id("input-button__destination")).click();
        $(By.id("input-button__destination")).val(arrivalCity);

        $$("[data-ref=airport-item__name]").find(text(arrivalCity)).click();

        $("[data-id='2021-12-15']").click();
        $("[data-id='2021-12-19']").click();

        $("[data-ref=passengers-picker__adults]").$(".counter__value").shouldHave(text("1"));
        $("[data-ref=passengers-picker__adults]").$(".counter__button-wrapper--enabled").click();

        $("[data-ref='input-button__passengers'] [data-ref='input-button__display-value']")
                .shouldHave(text("2 Adults"));

        $("button.flight-search-widget__start-search").click();

        $$x("//journey").shouldHave(CollectionCondition.size(2))
                .shouldHave(CollectionCondition.texts("Berlin Brandenburg to Kyiv", "Kyiv to Berlin Brandenburg"));

        $("div.details__bottom-bar").shouldHave(text("15 Dec")).shouldHave(text("19 Dec"));

    }
}
