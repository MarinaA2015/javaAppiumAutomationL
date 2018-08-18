package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.iOSTestCase;
import lib.ui.WelcomePageObject;

/**
 * Created by Inka on 16-Aug-18.
 */
public class GetStartedTest extends CoreTestCase
{
    public void  testPathThroughWelcome()
    {
        if (Platform.getInstance().isAndroid()) {
            return;
        }
        WelcomePageObject welcomePage = new WelcomePageObject(driver);
        welcomePage.waitForLearnMoreLink();
        welcomePage.clickNextButton();

        welcomePage.waitForNewWayToExploreText();
        welcomePage.clickNextButton();

        welcomePage.waitForAddOrEditPreferedLangText();
        welcomePage.clickNextButton();

        welcomePage.waitForLearnMoreAboutDataCollectedText();
        welcomePage.clickGetStartedButton();

    }
}
