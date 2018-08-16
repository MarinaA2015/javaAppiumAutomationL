package tests.IOS;

import lib.iOSTestCase;
import lib.ui.WelcomePageObject;

/**
 * Created by Inka on 16-Aug-18.
 */
public class GetStartedTest extends iOSTestCase
{
    public void  testPathThroughWelcome()
    {
        WelcomePageObject welcomePage = new WelcomePageObject(driver);
        welcomePage.waitForLernMoreLink();
        welcomePage.clickNextButton();
        //......
    }
}
