package Guru99Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Guru99DragNDropPage {
    WebDriver driver;
    Actions actions;

    @FindBy(css="#fourth:nth-of-type(2)")
    private WebElement first5000BtnElement;

    @FindBy(css=".block14.ui-draggable")
    private WebElement bankBtnElement;

    @FindBy(css=".block15.ui-draggable")
    private WebElement salesBtnElement;

    @FindBy(css="[itemtype] td:nth-of-type(1) td:nth-of-type(2) .placeholder")
    private WebElement amountDebitAreaToDrop;

    @FindBy(css="[itemtype] td:nth-of-type(2) td:nth-of-type(2) .placeholder")
    private WebElement amountCreditAreaToDrop;

    @FindBy(css="[itemtype] [width='100\\%']:nth-child(10)")
    private WebElement succeedElementExpected;

    @FindBy(id="bank")
    private WebElement accountDebitAreaToDrop;

    @FindBy(id="loan")
    private WebElement accountCreditAreaToDrop;

    public Guru99DragNDropPage(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }


    public void drag5000BtnToDebitAndCreditAmountAreas() {
        actions.dragAndDrop(first5000BtnElement, amountCreditAreaToDrop).perform();
        actions.dragAndDrop(first5000BtnElement, amountDebitAreaToDrop).perform();
    }

    public void dragBankBtnToDebitAccountArea() {
        actions.dragAndDrop(bankBtnElement, accountDebitAreaToDrop).perform();
    }

    public void dragSalesBtnToCreditAccountArea() {
        actions.dragAndDrop(salesBtnElement, accountCreditAreaToDrop).perform();
    }

    public boolean checkAppearanceOfElementByText(String textToCompare) {
        return succeedElementExpected.getText().equals(textToCompare);
    }
}
