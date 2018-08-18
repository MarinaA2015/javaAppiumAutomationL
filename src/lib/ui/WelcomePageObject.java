package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

/**
 * Created by Inka on 16-Aug-18.
 */
public class WelcomePageObject extends MainPageObject {

    private static final String
            STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
            NEXT_LINK = "id:Next",
            NEW_WAY_TO_EXPLORE_LINK = "id:New ways to explore",
            ADD_OR_EDIT_PREFERED_LANG = "id:Add or edit preferred languages",
            LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected",
            GET_STARTED_BUTTON = "id:Get started",
            SKIP = "id:Skip";

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK,"Cannot find 'Learn more about Wikipedia' link", 10);
    }

    public void waitForNewWayToExploreText()
    {
        this.waitForElementPresent(NEW_WAY_TO_EXPLORE_LINK, "Cannot find 'New way to explore' link", 10);
    }

    public void waitForAddOrEditPreferedLangText()
    {
        this.waitForElementPresent(ADD_OR_EDIT_PREFERED_LANG, "Cannot find 'Add or edit preferred languages' link", 10);
    }

    public void waitForLearnMoreAboutDataCollectedText()
    {
        this.waitForElementPresent(LEARN_MORE_ABOUT_DATA_COLLECTED_LINK, "Cannot find 'Learn more about data collected' link", 10);
    }

    public void clickNextButton()
    {
        this.waitForElementAndClick(NEXT_LINK,"Cannot find and click Next button", 10);
    }


    public void clickGetStartedButton()
    {
        this.waitForElementAndClick(GET_STARTED_BUTTON,"Cannot find and click Get started button", 10);
    }

    public void clickSkip(){
        this.waitForElementAndClick(SKIP,"Cannot find and click skip button", 10);
    }
}
