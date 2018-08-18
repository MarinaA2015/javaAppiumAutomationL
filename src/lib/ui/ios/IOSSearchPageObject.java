package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

/**
 * Created by Inka on 17-Aug-18.
 */
public class IOSSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INIT = "id:Search Wikipedia";
        SEARCH_INIT_BY_ID = "id:Search Wikipedia";
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_CLEAR_BUTTON = "id:clear mini";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeLink";
        SEARCH_EMPTY_RESULT_LABEL = "xpath://XCUIElementTypeStaticText[@name ='No results found']";
        SEARCH_RESULT_BY_TITLE_DESCR_TPL = "xpath://*[@resource-id = 'org.wikipedia:id/page_list_item_title'][@text = '{TITLE}']/..//*[@text = '{DESCRIPTION}']";
    }
    public IOSSearchPageObject(AppiumDriver driver){
        super(driver) ;
    }
}
