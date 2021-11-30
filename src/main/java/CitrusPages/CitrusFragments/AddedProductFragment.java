package CitrusPages.CitrusFragments;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class AddedProductFragment {

    private final SelenideElement addedProductModal = $(".pr.scrollbar");
    private final SelenideElement addedProductModalOnSearchPage = $(".el-dialog--medium");
    private final SelenideElement addedProductModalHeaderOnSearchPage = $("div.sell-description");
    private final SelenideElement closeAddedProductModal = addedProductModal.$(".icon-0-2-26");
    private final SelenideElement closeAddedProductModalOnSearchPage = addedProductModalOnSearchPage.$("div.el-dialog__header .el-dialog__headerbtn:nth-of-type(2)");

    public AddedProductFragment shouldHaveProductTitle(String searchItem) {
        addedProductModal.should(appear);
        addedProductModal.shouldHave(text(searchItem));
        return this;
    }

    public AddedProductFragment shouldHaveProductTitleOnAddedProductModal(String searchItem) {
        addedProductModalOnSearchPage.should(appear);
        addedProductModalHeaderOnSearchPage.should(appear);
        addedProductModalOnSearchPage.shouldHave(text(searchItem));
        return this;
    }

    public AddedProductFragment closeAddedProductModal() {
        closeAddedProductModal.click();
        addedProductModal.should(disappear);
        return this;
    }

    public AddedProductFragment closeAddedProductModalOnSearchPage() {
        closeAddedProductModalOnSearchPage.click();
        addedProductModalOnSearchPage.should(disappear);
        return this;
    }
}
