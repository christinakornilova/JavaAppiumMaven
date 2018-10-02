package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {

    static {
        SWIPE_BY_ARTICLE_TPL = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON_TPL = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}')]/../../div[contains(@class,'watched')]";
        MY_READING_LIST_ITEMS = "css:ul.watchlist li.page-summary";////li[@class='page-summary with-watchstar']
    }

    public MWMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
