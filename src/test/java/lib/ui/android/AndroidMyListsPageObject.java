package lib.ui.android;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListsPageObject extends MyListsPageObject {
    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
        SWIPE_BY_ARTICLE_TPL = "xpath://*[@text='{TITLE}']";
        MY_READING_LIST_ITEMS = "id:org.wikipedia:id/page_list_item_container";
    }

    public AndroidMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
