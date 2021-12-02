package hillel.SelenideTests;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

public class TripmydreamTest {
    @BeforeTest
    public void setUp() {
        Configuration.timeout = 10000;
        Configuration.browserSize = "1880x1150";
    }

    @Test
    public void orderTripPositiveCase() throws InterruptedException {
        String departureCity = "Berlin";
        String arrivalCity = "London";
        String startDate = "15 Dec";
        String endDate = "19 Dec";

        open ("https://en.tripmydream.com/");
        $("div.s-form__place-btn").click();

        $("input[autocomplete='origin']").setValue(departureCity);
        $("label[data-code='BER']").click();

        $("input[autocomplete='destination']").setValue(arrivalCity);
        $("label[data-code='LON']").click();

        $("td[data-date='2021-12-15']").click();
        Thread.sleep(500);
        $("td[data-date='2021-12-19']").click();

        $("[class='presearch__cell hc-peopleselector']").click();
        $("div[class='select__people people'] [data-index='0']").$("[class='people__cell hc-ps-row-val']").shouldHave(text("1"));
        $("div[class='select__people people'] [data-index='0']").$("button[data-type='inc']").click();

        $("[class='btn btn_orange btn_lg-md presearch__s-btn hc-sb-submit']").click();
        switchTo().window(0).close();
        switchTo().window(0);

        $("[class='popup__close h-pricealert-close-btn']").click();

        $x("//div[@class='flight hc-sr-tile flight_timeBold'][1]")
                .$$(By.xpath(".//span[@class='hidden-mob'][1]"))
                .shouldHave(texts(departureCity, arrivalCity));

        $x("//div[@class='flight hc-sr-tile flight_timeBold'][1]")
                .$$(By.xpath(".//div[@class='flight__info-date hidden-mob']//div[@class='flight__info-dateValue']"))
                .shouldHave(texts(startDate, endDate));
    }
}
