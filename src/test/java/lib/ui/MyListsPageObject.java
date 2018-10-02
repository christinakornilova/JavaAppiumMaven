package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TPL,
            SWIPE_BY_ARTICLE_TPL,
            MY_READING_LIST_ITEMS,
            REMOVE_FROM_SAVED_BUTTON_TPL;

    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    private static String getFolderXpathByName(String folderName) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", folderName);
    }

    private static String getSavedArticleXpathByTitle(String articleTitle) {
        return SWIPE_BY_ARTICLE_TPL.replace("{TITLE}", articleTitle);
    }

    private static String getRemoveButtonByTitle(String articleTitle) {
        return REMOVE_FROM_SAVED_BUTTON_TPL.replace("{TITLE}", articleTitle);
    }

    public void openReadingListByName(String folderName) {
        this.waitForElementAndClick(
                getFolderXpathByName(folderName),
                "Unable to find created folder by name " + folderName,
                5
        );
    }

    public void swipeArticleToDelete(String articleTitle) {
        this.waitForArticleToAppearByTitle(articleTitle);
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            this.swipeElementLeft(
                    articleXpath,
                    "Unable to find saved article by title " + articleTitle
            );
        } else {
            String removeLocator = getRemoveButtonByTitle(articleTitle);
            this.waitForElementAndClick(removeLocator, "Unable to click button to remove article from saved", 10);
        }

        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(articleXpath, "Unable to find saved article ");
        }

        if (Platform.getInstance().isMW()) {
            while (this.isElementPresent(articleXpath)) {
                driver.navigate().refresh();
            }
        }

        this.waitForArticleToDisappearByTitle(articleTitle);
    }

    public void waitForArticleToDisappearByTitle(String articleTitle) {
        this.waitForElementNotPresent(
                getSavedArticleXpathByTitle(articleTitle),
                "Unable to delete saved article by title " + articleTitle,
                5
        );
    }

    public void waitForArticleToAppearByTitle(String articleTitle) {
        this.waitForElementToBePresent(
                getSavedArticleXpathByTitle(articleTitle),
                "Unable to find saved article by title " + articleTitle,
                5
        );
    }

    public int getAmountOfArticlesInTheReadingList() {
        return this.getAmountOfElements(MY_READING_LIST_ITEMS);
    }

    public void navigateToArticlesPage(String articleTitle) {
        this.waitForElementAndClick(getSavedArticleXpathByTitle(articleTitle), "Unable to click on first article title in the list after second one was deleted", 15);
    }

}
