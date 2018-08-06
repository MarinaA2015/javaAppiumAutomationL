package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Inka on 05-Aug-18.
 */
public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver)
    {
        this.driver = driver;
    }



    public WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        //By by  = By.xpath(xpath);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(By by, String errorMessage)
    {
        return waitForElementPresent(by, errorMessage, 5);
    }

    public WebElement waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMessage,timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMessage,timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }


    public boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitForElementAndClear(By by, String errorMessage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMessage,timeoutInSeconds);
        element.clear();
        return element;
    }


    public List<WebElement> waitForAllElementsPresent(By by, String textForSearch, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        //By.id("org.wikipedia:id/page_list_item_title")
    }
    public int getAmountOfElements(By by)
    {
        List<WebElement> elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent(By by, String error_mesage)
    {
        int amounts_of_elements = getAmountOfElements(by);
        if (amounts_of_elements > 0){
            String default_message =  "An element '" + by.toString() + "' supposed to be not present";
            throw  new AssertionError(default_message + " " + error_mesage);
        }

    }

    public void assertElementPresent(By by, String error_message)
    {
        if(getAmountOfElements(by) == 0){
            String default_message = "An element '" + by.toString() + "' supposed to be not present,";
            throw  new AssertionError(default_message + " " +error_message);
        }

    }

    public void searchArticleAndOpenIt(String search_by_text, String article_name)
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

    public void addingArticleToFirstFolder(String name_of_folder)
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

    public void addingArticleToExistentFolder(String name_of_folder)
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


    public void swipeUp(int timeOfSwipe){
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y = (int)(size.height*0.8);
        int end_y = (int)(size.height*0.2);
        action.press(x,start_y).waitAction(timeOfSwipe).moveTo(x,end_y).release().perform();

    }
    public void swipeUpQuick()
    {
        swipeUp(200);
    }

    public void swipeUpTllFindElements(By by, String error_message, int max_swipes){
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

    public void swipeElementToLeft(By by, String error_message)
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

    public String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds )
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);

    }
}
