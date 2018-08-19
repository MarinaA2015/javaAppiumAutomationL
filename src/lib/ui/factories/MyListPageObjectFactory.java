package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

import lib.ui.MyListsPageObject;
import lib.ui.android.AndroidMyListPageObject;
import lib.ui.ios.IOSArticlePageObject;
import lib.ui.ios.IOSMyListPageObject;

/**
 * Created by Inka on 19-Aug-18.
 */
public class MyListPageObjectFactory {
    public static MyListsPageObject get(AppiumDriver driver){
        if(Platform.getInstance().isAndroid()){
            return new AndroidMyListPageObject(driver);
        }else {
            return new IOSMyListPageObject(driver);
        }
    }
}
