package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

/**
 * Created by Inka on 11-Aug-18.
 */
public class NavigationUI extends MainPageObject{
    private final static String
            MY_LISTS_LINK = "//android.widget.FrameLayout[@content-desc = 'My lists']";


    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public  void clickToMyList(){
        this.waitForElementAndClick(
                By.xpath(MY_LISTS_LINK),
                "Cannot find navigation button to My List",
                15);
    }


}
