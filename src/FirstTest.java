import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import lib.CoreTestCase;
import lib.ui.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.print.attribute.standard.OrientationRequested;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Inka on 18-Jul-18....
 */

public class FirstTest extends CoreTestCase{
    private MainPageObject mainPageObject;

    protected void setUp()throws Exception
    {
        super.setUp();
        mainPageObject = new MainPageObject(driver);

    }

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
    public void testCompareArticleTitle()
    {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        //articlePageObject.waitForTitleElement();

        String title = articlePageObject.getArticleTitle();

        Assert.assertEquals("We see unexpected title",
                        "Java (programming language)",
                            title);
    }

    @Test
    public void testSwipeArticle()
    {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.waitForSearchResult("Appium");
        searchPageObject.clickByArticleWithSubstring("Appium");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();

    }

    @Test
    public void testEx2_TextSearchPresentInSearchField(){
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5);

        WebElement element = mainPageObject.waitForElementPresent(By.id("org.wikipedia:id/search_src_text"),
                "Cannot find Search.. input",
                15);

        String textInSearchField = element.getAttribute("text");

        Assert.assertEquals("We do not see 'Search..' text",
                "Search…",
                textInSearchField);
    }

    @Test
    public void testEx3_ReceiveAnySearchResultAndCancel()
    {
        mainPageObject.waitForElementAndClick(
                //By.id("org.wikipedia:id/page_list_item_title"),
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5);

        mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find Search.. input",
                15);

       WebElement element = mainPageObject.waitForElementPresent(By.id("org.wikipedia:id/page_list_item_description"),
                                                    "No articles by search 'Java'",
                                                    25);
       Assert.assertNotEquals("No articles by search 'Java'",element,null);
      // System.out.println("element is null#1: " + (element.equals(null)));

        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X - button",
                5);

        mainPageObject.waitForElementNotPresent(By.id("org.wikipedia:id/page_list_item_title"),
                                                "Search result is not empty",
                                                15);
        //Assert.assertTrue("Search result is not empty",bool);

    }

    @Test
    public void testEx4_ReceiveAllSearchResultAndVerifyThem()
    {
        String word = "Java";
        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5);

        mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                word,
                "Cannot find Search.. input",
                15);

        List<WebElement> listElements = mainPageObject.waitForAllElementsPresent(By.id("org.wikipedia:id/page_list_item_title"),
                                                                word,
                                                                "Not all articles by search '" + word +"'",
                                                                25);
        int counter = 0;
        int length = listElements.size();
        List<String> listError = new LinkedList<>();
        for(int i=0; i < length; i++){
            if(listElements.get(i).getAttribute("text").toLowerCase().contains(word.toLowerCase())) counter++;
            else listError.add(listElements.get(i).getAttribute("text"));
            //System.out.println(listElements.get(i).getAttribute("text").toLowerCase());
        }

        Assert.assertEquals("Some search results are not contains the word: " + Arrays.toString(new List[]{listError}), counter,length);
    }

    @Test
    public void testSaveNewArticleToMyList()
    {
        String search_line = "Java";
        String subtitle = "Object-oriented programming language";
        String name_of_folder = "Learning Programming";

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.waitForSearchResult(subtitle);
        searchPageObject.clickByArticleWithSubstring(subtitle);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String article_title = articlePageObject.getArticleTitle();



        articlePageObject.addArticleToMyList(name_of_folder);
        articlePageObject.closeArticle();


        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickToMyList();

        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openFolderByName(name_of_folder);
        myListsPageObject.swipeArticleToDelete(article_title);

    }

    @Test
    public void testAmountOfNotEmptySearch()
    {
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5);

        String search_line = "Linkin park discography";
        mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find Search.. input",
                5);

        String search_result_locator = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@resource-id = 'org.wikipedia:id/page_list_item_container']";
        mainPageObject.waitForElementPresent(
                By.xpath(search_result_locator),
                "cannot find anything by request " + search_line,
                25);

        int amount_of_search = mainPageObject.getAmountOfElements(By.xpath(search_result_locator));

        Assert.assertTrue(
                "we found too few results",
                amount_of_search > 0);

    }
    @Test
    public void testAmountOfEmptySearch(){
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5);

        String search_line = "kjddkdh";
        mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find Search.. input",
                5);

        String search_result_locator = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@resource-id = 'org.wikipedia:id/page_list_item_container']";
        String empty_result_label = "//*[@text = 'No results found']";

        mainPageObject.waitForElementPresent(
                By.xpath(empty_result_label),
                "cannot find empty result label by result request " + search_line,
                15);

        mainPageObject.assertElementNotPresent(
                By.xpath(search_result_locator),
                "we found some elements by request by " + search_line);

    }

    @Test
    public void testScreenOrientationOnSearchResult()
    {
        String search_by_text = "Java";
        String article_name = "JavaScript";

        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5);
        // Searching of the article by text "Java"
        mainPageObject.searchArticleAndOpenIt(search_by_text, article_name);

        String article_title_id = "org.wikipedia:id/view_page_title_text";

        String title_before_rotation = mainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "cannot find title of article by id " + article_title_id,
                15);

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = mainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "cannot find title of article after rtation by id " + article_title_id,
                15);

        Assert.assertEquals(
                "Article name have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation);

        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = mainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "cannot find title of article after rtation by id " + article_title_id,
                15);

        Assert.assertEquals(
                "Article name have been changed after screen rotation",
                title_after_rotation,
                title_after_second_rotation);

    }

    @Test
    public void testSearchArticleInBackground()
    {
        String search_by_text = "Java";
        String article_name = "JavaScript";

        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5);

        mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_by_text,
                "Cannot find Search.. input",
                5);

        mainPageObject.waitForElementPresent(By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = '" + article_name +"']"),
                "Cannot find " + article_name + " topic searching by" +  search_by_text,
                15);

        driver.runAppInBackground(2);

        mainPageObject.waitForElementPresent(By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = '" + article_name +"']"),
                "Cannot find " + article_name + " after returning from background",
                15);

    }

    @Test
    public void testEx5_SaveTwoArticlesToMyList()
    {
        String search_by_text1 = "Java";
        String search_by_text2 = "Oracle";
        String article1_name = "JavaScript";
        String article2_name = "Oracle Database";

        String name_of_folder = "Learning Programming";

        // Searching of the article by text "Java"
        mainPageObject.searchArticleAndOpenIt(search_by_text1, article1_name);

        // Adding of the opened article to the first folder with the name "Learning Programming"
        mainPageObject.addingArticleToFirstFolder(name_of_folder);

        // Exit from the article
        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc = 'Navigate up']"),
                "Cannot close article '" + article1_name + "', cannot find X",
                15);

        // Searching the second article by text "Oracle"
        mainPageObject.searchArticleAndOpenIt(search_by_text2, article2_name);

        //Adding the article to the existent folder "Learning Programming"
        mainPageObject.addingArticleToExistentFolder(name_of_folder);

        // Exit from the article

        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc = 'Navigate up']"),
                "Cannot close article '" + article2_name + "', cannot find X",
                15);

        //Open 'Learning Programming' list
        mainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc = 'My lists']"),
                "Cannot find navigation button to My List",
                15);

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text = '" + name_of_folder + "']"),
                "cannot find '" + name_of_folder + "' folder",
                5);

        mainPageObject.swipeElementToLeft(
                By.xpath("//*[@text = '" + article1_name + "']"),
                "cannot find saved article");


        mainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text = '" + article1_name + "']"),
                "the saved article wasn't deleted from the list",
                15);

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text = '" + article2_name + "']"),
                "cannot find the article with name '" + article2_name + "'",
                15);


       WebElement element = mainPageObject.waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                25);



       Assert.assertTrue(element.getText().equals(article2_name));

    }
    @Test
    public void testEx6_AssertTitle()
    {
        String search_by_text = "Java";
        String article_name = "JavaScript";
        String header_pencil_id = "org.wikipedia:id/view_page_header_edit_pencil";
        String article_title_id = "org.wikipedia:id/view_page_title_text";

        // Searching of the article by text "Java"

        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5);

        mainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_by_text,
                "Cannot find Search.. input",
                5);

        mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = '" + article_name +"']"),
                "Cannot find " + article_name + " topic searching by" +  search_by_text,
                15);

        mainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_header_edit_pencil"),
                "cannot find pencil element which has to be displayed for the article by id " + header_pencil_id,
                15);

        mainPageObject.assertElementPresent(
                By.id(article_title_id),
                "title of the article " + article_name + " was not found");

    }



}
