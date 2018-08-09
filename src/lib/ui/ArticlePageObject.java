package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Inka on 09-Aug-18.
 */
public class ArticlePageObject extends MainPageObject{
    private final static String
                    TITLE = "org.wikipedia:id/view_page_title_text",
                    FOOTER = "//*[@text = 'View page in browser']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.id(TITLE), "Cannot find element by title", 15);
    }

    public String  getArticleTitle()
    {
        WebElement element = this.waitForTitleElement();
        return element.getText();
    }

    public void swipeToFooter()
    {
        this.swipeUpTllFindElements(By.xpath(FOOTER), "Cannot find the of article", 20);
    }
}
