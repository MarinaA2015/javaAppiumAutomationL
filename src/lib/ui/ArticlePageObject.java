package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

/**
 * Created by Inka on 09-Aug-18.
 */
abstract public class ArticlePageObject extends MainPageObject{
    protected static String
                    TITLE,
                    FOOTER,
                    OPTIONS_BUTTON,
                    OPTION_ADD_TO_MY_READING_LIST_BUTTON,
                    ADD_TO_MY_LIST_OVERLAY,
                    MY_LIST_NAME_INPUT,
                    MY_LIST_OK_BUTTON,
                    CLOSE_ARTICLE_BUTTON,
                    EXISTENT_FOLDER_BY_NAME_TMPL,
                    HEADER_PENCIL_ID,
                    ARTICLE_TITE_ID;

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

    public WebElement waitForTitleElementByTitle(String article_title){
        String title_id = TITLE.replace("{ARTICLE}",article_title);
        return this.waitForElementPresent(title_id, "Cannot find element by title", 25);

    }
    public void assertTitleElementPresent(String article_title, String error_message){
        String title_id = TITLE.replace("{ARTICLE}",article_title);
        this.assertElementPresent(title_id, error_message);
    }

    public String  getArticleTitle()
    {
        WebElement element = this.waitForTitleElement();
        if (Platform.getInstance().isAndroid()){
            return element.getText();
        }else{
            return element.getAttribute("name");
        }

    }

    public void swipeToFooter()
    {
        if(Platform.getInstance().isAndroid())
        {
            this.swipeUpTllFindElements(FOOTER, "Cannot find the of article", 40);
        }
        else
            {
                this.swipeUpTillElementAppear(FOOTER, "Cannot find the of article", 40);
            }
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

    public void addArticleToMySaved(){
        this.waitForElementAndClick(OPTION_ADD_TO_MY_READING_LIST_BUTTON, "cannot find option to add article to reading list", 15);
    };
}
