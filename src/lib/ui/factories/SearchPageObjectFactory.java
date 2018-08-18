package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.android.AndroidSearchPageObject;
import lib.ui.ios.IOSSearchPageObject;

/**
 * Created by Inka on 18-Aug-18.
 */
public class SearchPageObjectFactory {
    public static SearchPageObject get(AppiumDriver driver)
    {
        if(Platform.getInstance().isAndroid()){
            return new AndroidSearchPageObject(driver);
        }else {
            return new IOSSearchPageObject(driver);
        }
    }
}
