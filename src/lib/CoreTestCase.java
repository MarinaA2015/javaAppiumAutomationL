package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

/**
 * Created by Inka on 05-Aug-18.
 */
public class CoreTestCase extends TestCase {

    protected AppiumDriver driver ;
    //protected Platform Platform;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortraite();
        this.skipWelcomePageForIOSApp();
    }

    @Override
    protected  void tearDown() throws Exception {

        driver.rotate(ScreenOrientation.PORTRAIT);
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortraite()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds)
    {
        driver.runAppInBackground(seconds);
    }

    private void skipWelcomePageForIOSApp(){
        if(Platform.getInstance().isIOS()){
            WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
            welcomePageObject.clickSkip();
        }
    }

}
