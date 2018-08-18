package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

/**
 * Created by Inka on 16-Aug-18.
 */
public class Platform {
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_IOS = "ios";
    private static final String  APPIUM_URL = "http://127.0.0.1.:4723/wd/hub";
    private static Platform instance;

    private Platform() {}
    public static Platform getInstance()
    {
     if(instance == null)
         instance = new Platform();
     return instance;
    }

    public AppiumDriver getDriver() throws Exception
    {
        URL URL = new URL(APPIUM_URL);
        if (this.isAndroid()){
            return new AndroidDriver(URL, this.getAdroidDesiredCapabilities());
        } else if (this.isIOS()){
            return  new AndroidDriver(URL, this.getIOSDesiredCapabilities());
        } else {
            throw new Exception("Cannot detect type of the Driver. Platform value: " + this.getPlatformVar());
        }
    }

    public boolean isAndroid()
    {
        return isPlatform(PLATFORM_ANDROID);
    }
    public boolean isIOS()
    {
        return isPlatform(PLATFORM_IOS);
    }
    private DesiredCapabilities getAdroidDesiredCapabilities()
    {
        DesiredCapabilities capabilities =new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDvice");
        capabilities.setCapability("platformVersion","6.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity","main.MainActivity");
        capabilities.setCapability("app","E:/Marina/Tel Ran/QA/JavaSeleniumLocal/javaAppAutomation/apks/org.wikipedia.apk");
        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities()
    {
        DesiredCapabilities capabilities =new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone SE");
        capabilities.setCapability("platformVersion", "11.3");
        capabilities.setCapability("app", "E:/Marina/Tel Ran/QA/JavaSeleniumLocal/javaAppAutomation/apks/Wikipedia.app");
        return capabilities;
    }

    private boolean isPlatform(String my_platform)
    {
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }

    private String getPlatformVar()
    {
        return System.getenv("PLATFORM");
    }

}
