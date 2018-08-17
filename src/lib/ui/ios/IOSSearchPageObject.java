package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

/**
 * Created by Inka on 17-Aug-18.
 */
public class IOSSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
        SEARCH_INIT = "xpath://*[contains(@text,'Search…')]";
        SEARCH_INIT_BY_ID = "id:org.wikipedia:id/search_src_text";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = '{SUBSTRING}']";
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@resource-id = 'org.wikipedia:id/page_list_item_container']";
        SEARCH_EMPTY_RESULT_LABEL = "xpath://*[@text = 'No results found']";
        SEARCH_RESULT_BY_TITLE_DESCR_TPL = "xpath://*[@resource-id = 'org.wikipedia:id/page_list_item_title'][@text = '{TITLE}']/..//*[@text = '{DESCRIPTION}']";
    }
    public IOSSearchPageObject(AppiumDriver driver){
        super(driver);
    }
}
