package hillel;

import DemoqaPages.DemoqaBrowserWindowsPage;
import DemoqaPages.DemoqaSamplePage;
import Supporting.TabActions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;


public class DemoqaTest extends UIBaseTest{

    private final String demoqaUrl = "https://demoqa.com/browser-windows";
    DemoqaBrowserWindowsPage demoqaBrowserWindowsPage;
    DemoqaSamplePage demoqaSamplePage;
    DemoqaSamplePage demoqaSamplePageSecond;
    //TabActions tabActions;

    @Test
    public void switchingBetweenTabs(){
        driver.get(demoqaUrl);
        demoqaBrowserWindowsPage = new DemoqaBrowserWindowsPage(driver);

        String startTab = demoqaBrowserWindowsPage.getWindowHanle();
        demoqaSamplePage = demoqaBrowserWindowsPage.clickOnNewTabButton();

        ArrayList<String> twoTabs = demoqaSamplePage.getNewArrayOfTabs();
        driver.switchTo().window(twoTabs.get(1));

        Assert.assertTrue(demoqaSamplePage.checkIfContentTextIsCorrect());

        driver.switchTo().window(startTab);
        demoqaSamplePageSecond = demoqaBrowserWindowsPage.clickOnNewTabButton();

        ArrayList<String> threeTabs = demoqaSamplePageSecond.getNewArrayOfTabs();

        Assert.assertEquals(threeTabs.size(), 3);

        driver.switchTo().window(threeTabs.get(2));
        driver.close();

        driver.switchTo().window(threeTabs.get(1));
        driver.close();

        driver.switchTo().window(startTab);
        Assert.assertTrue(demoqaBrowserWindowsPage.checkIfHeaderTextIsCorrect());

        ArrayList<String> oneTab = demoqaBrowserWindowsPage.getNewArrayOfTabs();
        Assert.assertEquals(oneTab.size(), 1);
    }


}
