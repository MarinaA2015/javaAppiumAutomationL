package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

/**
 * Created by Inka on 19-Aug-18.
 */
public class AndroidNavigationUI extends NavigationUI{
   static {
       MY_LISTS_LINK = "xpath://*[@class = 'android.widget.FrameLayout'][@content-desc ='My lists']";
   }

    public AndroidNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}
