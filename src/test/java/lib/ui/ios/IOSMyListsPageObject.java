package lib.ui.ios;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyListsPageObject extends MyListsPageObject {
    static {
        SWIPE_BY_ARTICLE_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{TITLE}')]";
        MY_READING_LIST_ITEMS = "xpath://XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeLink";
    }

    public IOSMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
