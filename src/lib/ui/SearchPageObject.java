package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

/**
 * Created by Inka on 07-Aug-18.
 */
abstract public class SearchPageObject extends MainPageObject {

    protected static String SEARCH_INIT_ELEMENT,
                                SEARCH_INIT,
                                SEARCH_INIT_BY_ID,
                                SEARCH_CANCEL_BUTTON,
                                SEARCH_RESULT_BY_SUBSTRING_TPL,
                                SEARCH_RESULT_ELEMENT,
                                SEARCH_EMPTY_RESULT_LABEL,
                                SEARCH_RESULT_BY_TITLE_DESCR_TPL ;


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void initSearchInput()
    {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element",15);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search init element after clicking search init element");
    }

    /* TEMPLATES METHODS*/
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}",substring);
    }

    private static String getResultSearchElementByTitleAndDescription(String title, String description)
    {
        return SEARCH_RESULT_BY_TITLE_DESCR_TPL.replace("{TITLE}",title).replace("{DESCRIPTION}",description);
    }
    /* TEMPLATES METHODS */

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON,"Cannot find search cancel button", 15);
    }
    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON,"The search cancel button is still present", 15);
    }
    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INIT, search_line, "Cannot find and type into search input",5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and cick search cancel button", 5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_substring = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_substring, "Cannot find search result with substring " + substring,15);
    }

    public void waitForElementByTitleAndDescription(String title, String description)
    {
        String search_result_substring = getResultSearchElementByTitleAndDescription(title, description);
        this.waitForElementPresent(search_result_substring,
                                "Cannot find search result with title " + title + " and description " + description,
                                15);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_substring = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_substring, "Cannot find and click search result with substring " + substring,15);
    }
    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "cannot find anything by request ",
                25);

        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultLabel()
    {
       // String search_result_locator = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@resource-id = 'org.wikipedia:id/page_list_item_container']";

        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_LABEL,
                "cannot find empty result label by result request " ,
                15);

    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results");
    }

    public void assertExistsResultOfSearch()
    {
        this.assertElementPresent(SEARCH_RESULT_ELEMENT, "We supposed to find any results");
    }

    public String receiveTextFromSearchInput()
    {
        return this.waitForElementPresent(SEARCH_INIT_BY_ID,
                "Cannot find Search.. input",
                15).getAttribute("text");

    }
}
