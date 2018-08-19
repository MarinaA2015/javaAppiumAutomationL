package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

/**
 * Created by Inka on 19-Aug-18.
 */
public class AndroidMyListPageObject extends MyListsPageObject{
    static {
            FOLDER_BY_NAME_TPL = "xpath://*[@text = '{FOLDER_NAME}']";
            ARTICLE_BY_TITLE_TPL = "xpath://*[@text = '{ARTICLE_TITLE}']";
    }
    public AndroidMyListPageObject(AppiumDriver driver){
        super(driver);
    }

}
