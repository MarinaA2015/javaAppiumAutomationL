package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Inka on 14-Aug-18.
 */
public class MyListsTests extends CoreTestCase
{
    private static final String name_of_folder = "Learning Programming";

    @Test
    public void testSaveNewArticleToMyList()
    {
        String search_line = "Java";
        //String subtitle = "JavaScript";
        String subtitle = "Java (programming language)";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.waitForSearchResult(subtitle);
        searchPageObject.clickByArticleWithSubstring(subtitle);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = articlePageObject.getArticleTitle();
        System.out.println("article title: " + article_title);

        if(Platform.getInstance().isAndroid()){
            articlePageObject.addArticleToMyList(name_of_folder);
        }else{
            articlePageObject.addArticleToMySaved();
            articlePageObject.addArticleToMySaved();
        }

        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);

        navigationUI.clickToMyList();

        MyListsPageObject myListsPageObject = MyListPageObjectFactory.get(driver);
        if(Platform.getInstance().isAndroid())
        {
        myListsPageObject.openFolderByName(name_of_folder);
        }
        //driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
       /*myListsPageObject.waitForElementPresent(
                "id:JavaScript Programming language",
                "Cannot find my element by defined locator",
                30);*/

        myListsPageObject.waitForElementPresent(
                "xpath://XCUIElementTypeLink[contains(@name,'programming language'])",
                        "Cannot find my element by defined locator",
                        30);

        //myListsPageObject.swipeArticleToDelete(article_title + " " + subtitle);

    }

    @Test
    public void testEx5_SaveTwoArticlesToMyList()
    {
        String search_by_text1 = "Java";
        String search_by_text2 = "Oracle";
        String article_title1 = "JavaScript";
        String article_title2 = "Oracle Database";

        MyListsPageObject myListsPageObject = MyListPageObjectFactory.get(driver);
        myListsPageObject
                .searchArticleByTextAndPutToMyList(search_by_text1, article_title1, name_of_folder);
        myListsPageObject
                .searchArticleByTextAndPutToExistentFolderInMyList(search_by_text2, article_title2, name_of_folder);

        // open name_of_folder in My List
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickToMyList();
        myListsPageObject.openFolderByName(name_of_folder);

        // delete the first article using swipe
        myListsPageObject.swipeArticleToDelete(article_title1);
        myListsPageObject.openArticleByTitle(article_title2);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        assertEquals("Article title is not the '" + article_title2 + "'",
                         articlePageObject.getArticleTitle(),
                         article_title2);

    }


}
