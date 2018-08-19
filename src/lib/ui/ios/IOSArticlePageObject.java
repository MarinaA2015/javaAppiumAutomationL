package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

/**
 * Created by Inka on 19-Aug-18.
 */
public class IOSArticlePageObject extends ArticlePageObject{
    static {
                TITLE = "id:Java (programming language)";
                FOOTER = "id:View article in browser";
                OPTION_ADD_TO_MY_READING_LIST_BUTTON = "id:Save for later";
                MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
                MY_LIST_OK_BUTTON = "xpath://*[@text = 'OK']";
                CLOSE_ARTICLE_BUTTON = "id:Back";
                EXISTENT_FOLDER_BY_NAME_TMPL = "xpath://*[@text = '{FOLDER_NAME}']";
                HEADER_PENCIL_ID = "id:org.wikipedia:id/view_page_header_edit_pencil";
                ARTICLE_TITE_ID = "id:org.wikipedia:id/view_page_title_text";
    }
    public IOSArticlePageObject(AppiumDriver driver){
        super(driver);
    }
}
