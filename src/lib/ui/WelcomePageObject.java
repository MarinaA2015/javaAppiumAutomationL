package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

/**
 * Created by Inka on 16-Aug-18.
 */
public class WelcomePageObject extends MainPageObject {

    private static final String
            STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
            NEXT_LINK = "id:Next";

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForLernMoreLink()
    {
        this.waitForElementNotPresent(STEP_LEARN_MORE_LINK,"Cannot find 'Learn more about Wikipedia' link", 10);
    }

    public void clickNextButton()
    {
        this.waitForElementAndClick(NEXT_LINK,"Cannot find and click Next button", 10);
    }
}
