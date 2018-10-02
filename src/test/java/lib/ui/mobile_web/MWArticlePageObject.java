package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "css:#content h1";
        ARTICLE_IDENTIFIER_TPL = "";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "css:#page-actions li#ca-watch.mw-ui-icon-mf-watch button";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#page-actions li#ca-watch button";
//        ADD_TO_MY_LIST_OVERLAY_GOT_IT = "";
//        CLOSE_ARTICLE_BUTTON = "id:Back";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
