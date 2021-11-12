package hillel;


import Guru99Pages.Guru99DragNDropPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Guru99Test extends UIBaseTest{

    private final String guruDragNDropUrl = "http://demo.guru99.com/test/drag_drop.html";
    private final String expectedTextInSucceedElement = "Perfect!";
    Guru99DragNDropPage guru99DragNDropPage;

    @Test
    public void dragNDropElements() {
        driver.get(guruDragNDropUrl);
        guru99DragNDropPage = new Guru99DragNDropPage(driver);

        guru99DragNDropPage.drag5000BtnToDebitAndCreditAmountAreas();
        guru99DragNDropPage.dragBankBtnToDebitAccountArea();
        guru99DragNDropPage.dragSalesBtnToCreditAccountArea();

        Assert.assertTrue(guru99DragNDropPage.checkAppearanceOfElementByText(expectedTextInSucceedElement));
    }
}
