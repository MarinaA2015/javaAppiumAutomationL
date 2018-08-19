package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

/**
 * Created by Inka on 19-Aug-18.
 */
public class IOSMyListPageObject extends MyListsPageObject{
    static {
        //ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[@name='{ARTICLE_TITLE}']";
        ARTICLE_BY_TITLE_TPL ="xpath://XCUIElementTypeLink[@name=\"Java (programming language) Object-oriented programming language\"]/..";
    }
    public IOSMyListPageObject(AppiumDriver driver){
        super(driver);
    }
}
