package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

/**
 * Created by Inka on 14-Aug-18.
 */
public class SearchTests extends CoreTestCase
{
    @Test
    public void testSearch()
    {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");

    }

    @Test
    public void testSearchAndWaitThreeArticlesByTitleAndDesription()
    {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForElementByTitleAndDescription("Java (programming language)","Object-oriented programming language");
        searchPageObject.waitForElementByTitleAndDescription("JavaScript","Programming language");
        searchPageObject.waitForElementByTitleAndDescription("Java","Island of Indonesia");

    }

    @Test
    public void testCancelSearch()
    {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch()
    {

        String search_line = "Linkin park discography";

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_line);
        int amount_of_search = searchPageObject.getAmountOfFoundArticles();


        assertTrue(
                "we found too few results",
                amount_of_search > 0);

    }
    @Test
    public void testAmountOfEmptySearch(){

        String search_line = "jkgvnvrkgktggnntf";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.waitForEmptyResultLabel();
        searchPageObject.assertThereIsNoResultOfSearch();

    }

    @Test
    public void testEx3_ReceiveAnySearchResultAndCancel() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.assertExistsResultOfSearch();
        searchPageObject.clickCancelSearch();
        searchPageObject.assertThereIsNoResultOfSearch();
    }


}
