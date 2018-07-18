import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

/**
 * Created by Inka on 18-Jul-18....
 */

public class FirstTest {
    private AppiumDriver driver ;

    @Before
    public void setUp() throws Exception
    {

        DesiredCapabilities capabilities =new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDvice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity","main.MainActivity");
        capabilities.setCapability("app","E:/Marina/Tel Ran/QA/JavaSeleniumLocal/javaAppAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1.:4723/wd/hub"), capabilities);

    }
    @After
    public  void tearDown(){
        driver.quit();
    }

    @Test
    public void firstTest(){
        System.out.println("First test run");
    }
}
