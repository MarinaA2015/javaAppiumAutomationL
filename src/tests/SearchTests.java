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

        /*mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5);*/
        searchPageObject.waitForEmptyResultLabel();
        searchPageObject.assertThereIsNoResultOfSearch();



        //String search_result_locator = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@resource-id = 'org.wikipedia:id/page_list_item_container']";


    }


}
