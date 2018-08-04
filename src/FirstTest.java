import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Inka on 18-Jul-18....
 */

public class FirstTest {
    private AppiumDriver driver ;

    @Before
    public void setUp() throws Exception
    {

        DesiredCapabilities capabilities =new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDvice");
        capabilities.setCapability("platformVersion","6.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity","main.MainActivity");
        capabilities.setCapability("app","E:/Marina/Tel Ran/QA/JavaSeleniumLocal/javaAppAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1.:4723/wd/hub"), capabilities);

    }
    @After
    public  void tearDown(){
        driver.quit();
    }

    @Test
    public void firstTest()
    {
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                                "Cannot find Search Wikipedia input",
                                5);

        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search…')]"),
                                 "Java",
                                 "Cannot find object-oriented programming language topic searching by Java",
                                15);


        waitForElementPresent(By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),
                                    "Cannot find object-oriented programming language topic searching by Java",
                                        15);

    }

    @Test
    public void testCancelSearch()
    {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                                "Cannot find Search Wikipedia input",
                                5);

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                                  "Java",
                                    "Cannot find Search.. input",
                                    5);

        waitForElementPresent(By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),
                             "Cannot find object-oriented programming language topic searching by Java",
                                15);

        waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"),
                                "Cannot find Search.. input",
                                 15);

        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"),
                                "Cannot find X - button",
                                5);

        waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"),
                                      "X - button is still on the page",
                                        15);
    }

    @Test
    public void testCompareArticleTitle()
    {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5);

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find Search.. input",
                5);

        waitForElementAndClick(By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),
                "Cannot find object-oriented programming language topic searching by Java",
                15);

        WebElement element = waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
            "Cannot find article title",
            15);
        String title = element.getAttribute("text");

        Assert.assertEquals("We see unexpected title",
                        "Java (programming language)",
                            title);
    }

    @Test
    public void testSwipeArticle()
    {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5);

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Appium",
                "Cannot find Search.. input",
                5);

        waitForElementAndClick(By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_title'][@text = 'Appium']"),
                "Cannot find article Appium in Search",
                15);

        waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15);

        swipeUpTllFindElements(
                By.xpath("//*[@text = 'View page in browser']"),
                "Cannot find the end of the article",
                20);



    }

    @Test
    public void testEx2_TextSearchPresentInSearchField(){
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5);

        WebElement element = waitForElementPresent(By.id("org.wikipedia:id/search_src_text"),
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
        waitForElementAndClick(
                //By.id("org.wikipedia:id/page_list_item_title"),
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5);

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find Search.. input",
                15);

       WebElement element = waitForElementPresent(By.id("org.wikipedia:id/page_list_item_description"),
                                                    "No articles by search 'Java'",
                                                    25);
       Assert.assertNotEquals("No articles by search 'Java'",element,null);
      // System.out.println("element is null#1: " + (element.equals(null)));

        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X - button",
                5);

        waitForElementNotPresent(By.id("org.wikipedia:id/page_list_item_title"),
                                                "Search result is not empty",
                                                15);
        //Assert.assertTrue("Search result is not empty",bool);

    }

    @Test
    public void testEx4_ReceiveAllSearchResultAndVerifyThem()
    {
        String word = "Java";
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5);

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                word,
                "Cannot find Search.. input",
                15);

        List<WebElement> listElements = waitForAllElementsPresent(By.id("org.wikipedia:id/page_list_item_title"),
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
    public void saveNewArticleToMyList()
    {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5);

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find Search.. input",
                5);

        waitForElementAndClick(By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),
                "Cannot find object-oriented programming language topic searching by Java",
                15);

        waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                25);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc = 'More options']"),
                "cannot find button to open article options",
                5);

        waitForElementAndClick(
                By.xpath("//*[@text = 'Add to reading list']"),
                "cannot find option to add article to reading list",
                5);
        System.out.println("before clicking by 'GOT IT'");

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                //By.xpath("//*[@text = 'GOT IT']"),
                "cannot find 'GOT IT' button",
                15);

        System.out.println("after clicking by 'GOT IT'");

      /* waitForElementAndClick(
               By.id("org.wikipedia:id/create_button"),
               "Cannot find button to create a list",
               15);*/

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "cannot find input to set name of article folder",
                15);

        String name_of_folder = "Learning Programming";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "cannot put text into article folder input",
                5);

        waitForElementAndClick(
                By.xpath("//*[@text = 'OK']"),
                "cannot find OK option ",
                5);

       // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc = 'Navigate up']"),
                "Cannot close article, cannot find X",
                5);

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc = 'My lists']"),
                "Cannot find navigation button to My List",
                15);

        waitForElementAndClick(
                By.xpath("//*[@text = 'Learning Programming']"),
                "cannot find '" + name_of_folder + "' folder",
                15);
       /* waitForElementPresent(
                By.xpath("//*[@text = 'object-oriented programming language']"),
                "cannot find object-oriented programming language topic in my folder",
                35);

       waitForElementAndClick(
                By.id("org.wikipedia:id/page_list_item_action_primary"),
                "cannot find button to open article options",
                5);

        waitForElementAndClick(
                By.xpath("//*[@text = 'Remove from Learning Programming']"),
                "cannot find option to remove the article from the list",
                15);*/
       swipeElementToLeft(
               By.xpath("//*[@text = 'object-oriented programming language']"),
               "cannot find saved article");


        waitForElementNotPresent(
                By.xpath("//*[@text = 'object-oriented programming language']"),
                "the saved article wasn't deleted from the list",
                15);
    }

    @Test
    public void testAmountOfNotEmptySearch()
    {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5);

        String search_line = "Linkin park discography";
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find Search.. input",
                5);

        String search_result_locator = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@resource-id = 'org.wikipedia:id/page_list_item_container']";
        waitForElementPresent(
                By.xpath(search_result_locator),
                "cannot find anything by request " + search_line,
                25);

        int amount_of_search = getAmountOfElements(By.xpath(search_result_locator));

        Assert.assertTrue(
                "we found too few results",
                amount_of_search > 0);

    }
    @Test
    public void testAmountOfEmptySearch(){
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5);

        String search_line = "kjddkdh";
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find Search.. input",
                5);

        String search_result_locator = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@resource-id = 'org.wikipedia:id/page_list_item_container']";
        String empty_result_label = "//*[@text = 'No results found']";

        waitForElementPresent(
                By.xpath(empty_result_label),
                "cannot find empty result label by result request " + search_line,
                15);

        assertElementNotPresent(
                By.xpath(search_result_locator),
                "we found some elements by request by " + search_line);

    }

    @Test
    public void testScreenOrientationOnSearchResult()
    {
        String search_by_text = "Java";
        String article_name = "JavaScript";

        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5);
        // Searching of the article by text "Java"
        searchArticleAndOpenIt(search_by_text, article_name);

        String article_title_id = "org.wikipedia:id/view_page_title_text";

        String title_before_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "cannot find title of article by id " + article_title_id,
                15);

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "cannot find title of article after rtation by id " + article_title_id,
                15);

        Assert.assertEquals(
                "Article name have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation);

        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = waitForElementAndGetAttribute(
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

        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5);

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_by_text,
                "Cannot find Search.. input",
                5);

        waitForElementPresent(By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = '" + article_name +"']"),
                "Cannot find " + article_name + " topic searching by" +  search_by_text,
                15);

        driver.runAppInBackground(2);

        waitForElementPresent(By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = '" + article_name +"']"),
                "Cannot find " + article_name + " after returning from background",
                15);
    }

    @Test
    public void Ex5_SaveTwoArticlesToMyList()
    {
        String search_by_text1 = "Java";
        String search_by_text2 = "Oracle";
        String article1_name = "JavaScript";
        String article2_name = "Oracle Database";

        String name_of_folder = "Learning Programming";

        // Searching of the article by text "Java"
        searchArticleAndOpenIt(search_by_text1, article1_name);

        // Adding of the opened article to the first folder with the name "Learning Programming"
       addingArticleToFirstFolder(name_of_folder);

        // Exit from the article
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc = 'Navigate up']"),
                "Cannot close article '" + article1_name + "', cannot find X",
                15);

        // Searching the second article by text "Oracle"
        searchArticleAndOpenIt(search_by_text2, article2_name);

        //Adding the article to the existent folder "Learning Programming"
        addingArticleToExistentFolder(name_of_folder);

        // Exit from the article

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc = 'Navigate up']"),
                "Cannot close article '" + article2_name + "', cannot find X",
                15);

        //Open 'Learning Programming' list
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc = 'My lists']"),
                "Cannot find navigation button to My List",
                15);

        waitForElementAndClick(
                By.xpath("//*[@text = '" + name_of_folder + "']"),
                "cannot find '" + name_of_folder + "' folder",
                5);

        swipeElementToLeft(
                By.xpath("//*[@text = '" + article1_name + "']"),
                "cannot find saved article");


        waitForElementNotPresent(
                By.xpath("//*[@text = '" + article1_name + "']"),
                "the saved article wasn't deleted from the list",
                15);

        waitForElementAndClick(
                By.xpath("//*[@text = '" + article2_name + "']"),
                "cannot find the article with name '" + article2_name + "'",
                15);


       WebElement element = waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                25);

       System.out.println("Name of the article: " + element.getText());

       Assert.assertTrue(element.getText().equals(article2_name));

    }
    @Test
    public void Ex6_AssertTitle()
    {
        String search_by_text = "Java";
        String article_name = "JavaScript";
        String header_pencil_id = "org.wikipedia:id/view_page_header_edit_pencil";
        String article_title_id = "org.wikipedia:id/view_page_title_text";

        // Searching of the article by text "Java"

        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5);

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_by_text,
                "Cannot find Search.. input",
                5);

        waitForElementAndClick(By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = '" + article_name +"']"),
                "Cannot find " + article_name + " topic searching by" +  search_by_text,
                15);

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_header_edit_pencil"),
                "cannot find pencil element which has to be displayed for the article by id " + header_pencil_id,
                15);

        assertElementPresent(
                By.id(article_title_id),
                "title of the article " + article_name + " was not found");

    }




    private WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        //By by  = By.xpath(xpath);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String errorMessage)
    {
        return waitForElementPresent(by, errorMessage, 5);
    }

    private WebElement waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMessage,timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMessage,timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }


    private boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String errorMessage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMessage,timeoutInSeconds);
        element.clear();
        return element;
    }


    private List<WebElement> waitForAllElementsPresent(By by, String textForSearch, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        //By.id("org.wikipedia:id/page_list_item_title")
    }
    private int getAmountOfElements(By by)
    {
        List<WebElement> elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent(By by, String error_mesage)
    {
        int amounts_of_elements = getAmountOfElements(by);
        if (amounts_of_elements > 0){
            String default_message =  "An element '" + by.toString() + "' supposed to be not present";
            throw  new AssertionError(default_message + " " + error_mesage);
        }

    }

    private void assertElementPresent(By by, String error_message)
    {
        if(getAmountOfElements(by) == 0){
            String default_message = "An element '" + by.toString() + "' supposed to be not present,";
            throw  new AssertionError(default_message + " " +error_message);
        }

    }

    private void searchArticleAndOpenIt(String search_by_text, String article_name)
    {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5);

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_by_text,
                "Cannot find Search.. input",
                5);

        waitForElementAndClick(By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = '" + article_name +"']"),
                "Cannot find " + article_name + " topic searching by" +  search_by_text,
                15);

        waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                30);
    }

    private void addingArticleToFirstFolder(String name_of_folder)
    {
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc = 'More options']"),
                "cannot find button to open article options",
                5);

        waitForElementAndClick(
                By.xpath("//*[@text = 'Add to reading list']"),
                "cannot find option to add article to reading list",
                5);


        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                //By.xpath("//*[@text = 'GOT IT']"),
                "cannot find 'GOT IT' button",
                15);

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "cannot find input to set name of article folder",
                15);

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "cannot put text into article folder input",
                5);

        waitForElementAndClick(
                By.xpath("//*[@text = 'OK']"),
                "cannot find OK option ",
                5);
    }

    private void addingArticleToExistentFolder(String name_of_folder)
    {
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc = 'More options']"),
                "cannot find button to open article options",
                5);

        waitForElementAndClick(
                By.xpath("//*[@text = 'Add to reading list']"),
                "cannot find option to add article to reading list",
                5);

        waitForElementAndClick(
                By.xpath("//*[@text = '" + name_of_folder + "']"),
                "cannot find the existent folder with name " + name_of_folder,
                15);

    }


    protected void swipeUp(int timeOfSwipe){
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y = (int)(size.height*0.8);
        int end_y = (int)(size.height*0.2);
        action.press(x,start_y).waitAction(timeOfSwipe).moveTo(x,end_y).release().perform();

    }
    protected void swipeUpQuick()
    {
        swipeUp(200);
    }

    protected void swipeUpTllFindElements(By by, String error_message, int max_swipes){
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0)
        {
            swipeUpQuick();
            ++already_swiped;
            if (already_swiped > max_swipes)
            {
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message);
                return;
            }
        }

    }

    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                20);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y)/2;

       // System.out.println("left_x: " + left_x);
       // System.out.println("right_x: " + right_x);
       // System.out.println("upper_y: " + upper_y);
       // System.out.println("lower_y: " + lower_y);
       // System.out.println("middle_y: " + middle_y);

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x,middle_y)
                .waitAction(300)
                .moveTo(left_x,middle_y)
                .release()
                .perform();

    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds )
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);

    }
}
