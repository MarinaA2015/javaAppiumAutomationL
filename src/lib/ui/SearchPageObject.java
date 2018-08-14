package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

/**
 * Created by Inka on 07-Aug-18.
 */
public class SearchPageObject extends MainPageObject {

    private static final String SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
                                SEARCH_INIT = "//*[contains(@text,'Search…')]",
                                SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
                                SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = '{SUBSTRING}']",
                                SEARCH_RESULT_ELEMENT = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@resource-id = 'org.wikipedia:id/page_list_item_container']",
                                SEARCH_EMPTY_RESULT_LABEL = "//*[@text = 'No results found']";


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void initSearchInput()
    {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element",15);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search init element after clicking search init element");
    }

    /* TEMPLATES METHODS*/
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}",substring);
    }
    /* TEMPLATES METHODS */

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON),"Cannot find search cancel button", 15);
    }
    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON),"The search cancel button is still present", 15);
    }
    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INIT), search_line, "Cannot find and type into search input",5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and cick search cancel button", 5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_substring = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_substring), "Cannot find search result with substring " + substring,15);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_substring = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_substring), "Cannot find and click search result with substring " + substring,15);
    }
    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "cannot find anything by request ",
                25);

        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultLabel()
    {
       // String search_result_locator = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@resource-id = 'org.wikipedia:id/page_list_item_container']";

        this.waitForElementPresent(
                By.xpath(SEARCH_EMPTY_RESULT_LABEL),
                "cannot find empty result label by result request " ,
                15);

    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We supposed not to find any results");
    }

    public void assertExistsResultOfSearch()
    {
        this.assertElementPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We supposed to find any results");
    }
}
