package tests;

import lib.CoreTestCase;
import lib.iOSTestCase;
import lib.ui.WelcomePageObject;

/**
 * Created by Inka on 16-Aug-18.
 */
public class GetStartedTest extends CoreTestCase
{
    public void  testPathThroughWelcome()
    {
        if (this.Platform.isAndroid()) {
            return;
        }
        WelcomePageObject welcomePage = new WelcomePageObject(driver);
        welcomePage.waitForLernMoreLink();
        welcomePage.clickNextButton();
        //......
    }
}
