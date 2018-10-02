package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchTests extends CoreTestCase {

    private List<WebElement> getSearchResultsByKeyword(String keyword, SearchPageObject searchPageObject) {
        List<WebElement> listOfSearchResults = searchPageObject.search(keyword);
        //verify that search results list is not empty
        assertTrue("List is empty",
                searchPageObject.getSearchResultsCount() > 0);
        return listOfSearchResults;
    }

    @Test
    public void testSearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchElement();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchElement();
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testSearchResultCancellation() {
        //ex3
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchElement();
        searchPageObject.typeSearchLine("Java");

        //wait for search results
        searchPageObject.waitForSearchResultsToAppear();

        //verify that search results list is not empty
        assertTrue("Search results list is empty", searchPageObject.getSearchResultsCount() > 0);

        //cancel search results
        searchPageObject.clickCancelSearch();

        if (Platform.getInstance().isAndroid()) {
            hideKeyboard();
            //check that Search... element contains default text now
            searchPageObject.validateDefaultSearchElementText();
            //verify that empty search result is displayed on page now
            assertTrue("Unable to locate empty search result list element", searchPageObject.isEmptySearchResultDisplayed());
        }

        //assert that no search results displayed
        assertFalse("Search results are still present on page", searchPageObject.isSearchResultsListPresent());

    }

    @Test
    public void testAmountOfNotEmptySearch() {
        String searchLine = "Linkin Park discography";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchElement();
        searchPageObject.typeSearchLine(searchLine);
        int amountOfSearchResults = searchPageObject.getAmountOfFoundArticles();

        assertTrue("Too few results were found!",
                amountOfSearchResults > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        String searchLine = "zxcvfgretw";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchElement();
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.assertNoSearchResults();
    }

    @Test
    public void testSearchResultsValidation() {
        //ex4*
        String keyWord = "Java";

        //fill the list with search results
        List<WebElement> listOfSearchResults = getSearchResultsByKeyword(keyWord, SearchPageObjectFactory.get(driver));

        //check that each search result contains given word
        for(WebElement element : listOfSearchResults) {
            assertTrue("Search line does not contain appropriate word " + keyWord,
                    element.getText().toLowerCase().contains(keyWord.toLowerCase())
            );
        }
    }

    @Test
    public void testPositiveSearchArticleByTitleAndDescription() {
        //ex9, ex12, ex19
        String[] title, description;
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            title = new String[] {"Danny Worsnop", "We Are Harlot", "Asking Alexandria"};
            description = new String[] {"The lead singer of Asking Alexandria and We Are Harlot.", "American hard rock supergroup", "British metalcore band"};
        } else {
            title = new String[] {"Asking Alexandria", "Asking Alexandria (album)", "Into the Fire (Asking Alexandria song)"};
            description = new String[] {"British metalcore band", "Asking Alexandria album", "Asking Alexandria song"};
        }

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.search(title[0]);

        //check results
        if (searchPageObject.getSearchResultsCount() > 0) {
            for (int i = 0; i < title.length; i++) {
                assertTrue("Unable to locate search result article via title '" + title[i] + "' and description '" + description[i] + "'",
                        searchPageObject.isElementPresent(
                                searchPageObject.waitForElementByTitleAndDescription(title[i], description[i]),
                                5
                        )
                );
            }
        }
    }


    @Test
    public void testNegativeSearchArticleByTitleAndDescription() {
        String title = "Asking Alexandria";
        String description = "XYZ";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.search(title);
        assertFalse("Was able to locate search result article using title '" + title + "' and WRONG description '" + description + "'",
                searchPageObject.isElementPresent(
                        searchPageObject.waitForElementByTitleAndDescription(title, description),
                        5
                )
        );
    }

}
