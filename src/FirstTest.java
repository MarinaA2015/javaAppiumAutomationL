import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

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
        capabilities.setCapability("platformVersion","8.0");
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
    public void testTextSearchPresentInSearchField(){
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
    public void testReceiveAnySearchResultAndCancel()
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

        boolean bool = waitForElementNotPresent(By.id("org.wikipedia:id/page_list_item_title"),
                                                "Search result is not empty",
                                                15);
        Assert.assertTrue("Search result is not empty",bool);

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
        //By by = By.id(id);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String errorMessage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMessage,timeoutInSeconds);
        element.clear();
        return element;
    }


    private List<WebElement> waitForAllElements(By by, String textForSearch, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        //By.id("org.wikipedia:id/page_list_item_title")
    }
}
