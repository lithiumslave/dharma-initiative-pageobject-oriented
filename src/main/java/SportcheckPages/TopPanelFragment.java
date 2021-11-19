package SportcheckPages;

import Supporting.BasePage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Getter
public class TopPanelFragment extends BasePage {

    By cartIconInHeader = By.cssSelector("div.header-cart");
    By modalWindow = By.cssSelector("div[class$='header-cart_confirmation']");
    By qtyInModal = By.cssSelector("dd[class='cart-item__detail__description']");
    By titleInModal = By.cssSelector("h2 > a[data-action=\"goToProductDetailsPage\"]");

    public TopPanelFragment(WebDriver driver) { super(driver);}
}
