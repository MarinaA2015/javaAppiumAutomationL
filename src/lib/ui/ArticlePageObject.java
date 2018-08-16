package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

/**
 * Created by Inka on 09-Aug-18.
 */
public class ArticlePageObject extends MainPageObject{
    private final static String
                    TITLE = "id:org.wikipedia:id/view_page_title_text",
                    FOOTER = "xpath://*[@text = 'View page in browser']",
                    OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc = 'More options']",
                    OPTION_ADD_TO_MY_READING_LIST_BUTTON = "xpath://*[@text = 'Add to reading list']",
                    ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
                    MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
                    MY_LIST_OK_BUTTON = "xpath://*[@text = 'OK']",
                    CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc = 'Navigate up']",
                    EXISTENT_FOLDER_BY_NAME_TMPL = "xpath://*[@text = '{FOLDER_NAME}']",
                    HEADER_PENCIL_ID = "id:org.wikipedia:id/view_page_header_edit_pencil",
                    ARTICLE_TITE_ID = "id:org.wikipedia:id/view_page_title_text";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getXPathFolderByName(String name_of_folder)
    {
        return EXISTENT_FOLDER_BY_NAME_TMPL.replace("{FOLDER_NAME}",name_of_folder);
    }
    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find element by title", 25);
    }

    public String  getArticleTitle()
    {
        WebElement element = this.waitForTitleElement();
        return element.getText();
    }

    public void swipeToFooter()
    {
        this.swipeUpTllFindElements(FOOTER, "Cannot find the of article", 20);
    }

    public void addArticleToMyList(String name_of_folder)
    {

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "cannot find button to open article options",
                25);

        this.waitForElementAndClick(
                OPTION_ADD_TO_MY_READING_LIST_BUTTON,
                "cannot find option to add article to reading list",
                25);


        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                //By.xpath("//*[@text = 'GOT IT']"),
                "cannot find 'GOT IT' button",
                15);

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "cannot find input to set name of article folder",
                15);


        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "cannot put text into article folder input",
                5);

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "cannot find OK option ",
                5);
    }
    public void closeArticle(){
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article, cannot find X",
                15);

    }
    public void addArticleToExistentFolderInMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "cannot find button to open article options",
                25);

        this.waitForElementAndClick(
                OPTION_ADD_TO_MY_READING_LIST_BUTTON,
                "cannot find option to add article to reading list",
                25);


        this.waitForElementAndClick(
                getXPathFolderByName(name_of_folder),
                "cannot find the existent folder with name " + name_of_folder,
                15);

    }
    public void waitForPencilElementToEditHeader()
    {
        this.waitForElementPresent(
                HEADER_PENCIL_ID,
                "cannot find pencil element which has to be displayed for the article by id " + HEADER_PENCIL_ID,
                15);
    }

    public void assertExistsArticleTitle()
    {
        this.assertElementPresent(
                ARTICLE_TITE_ID,
                "title of the article was not found");
    }


}
