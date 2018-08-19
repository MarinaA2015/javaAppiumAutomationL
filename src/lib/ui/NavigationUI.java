package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

/**
 * Created by Inka on 11-Aug-18.
 */
abstract public class NavigationUI extends MainPageObject{
    protected static String
            MY_LISTS_LINK;


    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public  void clickToMyList(){
        this.waitForElementAndClick(
                MY_LISTS_LINK,
                "Cannot find navigation button to My List",
                15);
    }


}
