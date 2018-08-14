package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Inka on 11-Aug-18.
 */
public class MyListsPageObject extends MainPageObject {
    public static final String
                        FOLDER_BY_NAME_TPL = "//*[@text = '{FOLDER_NAME}']",
                        ARTICLE_BY_TITLE_TPL = "//*[@text = '{ARTICLE_TITLE}']";

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getFolderByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}",name_of_folder);
    }

    private  static String getArticleTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{ARTICLE_TITLE}",article_title);
    }

    public void openFolderByName(String name_of_folder)
    {
        String name_of_folder_xpath = getFolderByName(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(name_of_folder_xpath),
                "cannot find '" + name_of_folder + "' folder",
                15);
    }

    public void swipeArticleToDelete(String article_title)
    {
        String name_of_article_by_xpath = getArticleTitle(article_title);

        this.waitForArticleToAppearByTitle(article_title);
        this.swipeElementToLeft(
                By.xpath(name_of_article_by_xpath),
                "cannot find saved article");
        this.waitForArticleToDisappearByTitle(article_title);

    }
    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String name_of_article_xpath = getArticleTitle(article_title);
        this.waitForElementNotPresent(
                By.xpath(name_of_article_xpath),
                "the saved article with the name " + article_title + " wasn't deleted from the list",
                15);
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String name_of_article_xpath = getArticleTitle(article_title);
        this.waitForElementPresent(
                By.xpath(name_of_article_xpath),
                "Cannot find the article by title " + article_title,
                15);
    }
    public void openArticleByTitle(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        this.waitForElementAndClick(
                By.xpath(getArticleTitle(article_title)),
                "cannot find the article with name '" + article_title + "'",
                15);
    }

    public String searchArticleByTextAndPutToMyList(String text, String subtitle, String name_of_folder)
    {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        // find and add one article to my folder
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(text);
        searchPageObject.waitForSearchResult(subtitle);
        searchPageObject.clickByArticleWithSubstring(subtitle);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String article_title = articlePageObject.getArticleTitle();

        articlePageObject.addArticleToMyList(name_of_folder);
        articlePageObject.closeArticle();
        return article_title;
    }
    public String searchArticleByTextAndPutToExistentFolderInMyList(String text, String subtitle, String name_of_folder)
    {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        // find and add one article to my folder
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(text);
        searchPageObject.waitForSearchResult(subtitle);
        searchPageObject.clickByArticleWithSubstring(subtitle);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String article_title = articlePageObject.getArticleTitle();

        articlePageObject.addArticleToExistentFolderInMyList(name_of_folder);
        articlePageObject.closeArticle();
        return article_title;
    }

}
