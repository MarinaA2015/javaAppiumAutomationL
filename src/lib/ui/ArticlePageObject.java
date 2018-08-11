package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Inka on 09-Aug-18.
 */
public class ArticlePageObject extends MainPageObject{
    private final static String
                    TITLE = "org.wikipedia:id/view_page_title_text",
                    FOOTER = "//*[@text = 'View page in browser']",
                    OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc = 'More options']",
                    OPTION_ADD_TO_MY_READING_LIST_BUTTON = "//*[@text = 'Add to reading list']",
                    ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
                    MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
                    MY_LIST_OK_BUTTON = "//*[@text = 'OK']",
                    CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc = 'Navigate up']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.id(TITLE), "Cannot find element by title", 15);
    }

    public String  getArticleTitle()
    {
        WebElement element = this.waitForTitleElement();
        return element.getText();
    }

    public void swipeToFooter()
    {
        this.swipeUpTllFindElements(By.xpath(FOOTER), "Cannot find the of article", 20);
    }

    public void addArticleToMyList(String name_of_folder)
    {


        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "cannot find button to open article options",
                25);

        this.waitForElementAndClick(
                By.xpath(OPTION_ADD_TO_MY_READING_LIST_BUTTON),
                "cannot find option to add article to reading list",
                25);


        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                //By.xpath("//*[@text = 'GOT IT']"),
                "cannot find 'GOT IT' button",
                15);

        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "cannot find input to set name of article folder",
                15);


        this.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "cannot put text into article folder input",
                5);

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "cannot find OK option ",
                5);
    }
    public void closeArticle(){
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot close article, cannot find X",
                15);

    }
}
