package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

/**
 * Created by Inka on 14-Aug-18.
 */
public class MyListsTests extends CoreTestCase
{
    @Test
    public void testSaveNewArticleToMyList()
    {
        String search_line = "Java";
        String subtitle = "Object-oriented programming language";
        String name_of_folder = "Learning Programming";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.waitForSearchResult(subtitle);
        searchPageObject.clickByArticleWithSubstring(subtitle);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String article_title = articlePageObject.getArticleTitle();

        articlePageObject.addArticleToMyList(name_of_folder);
        articlePageObject.closeArticle();

        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickToMyList();

        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openFolderByName(name_of_folder);
        myListsPageObject.swipeArticleToDelete(article_title);

    }

    @Test
    public void testEx5_SaveTwoArticlesToMyList()
    {
        String search_by_text1 = "Java";
        String search_by_text2 = "Oracle";
        String article_title1 = "JavaScript";
        String article_title2 = "Oracle Database";
        String name_of_folder = "Learning Programming";

        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject
                .searchArticleByTextAndPutToMyList(search_by_text1, article_title1, name_of_folder);
        myListsPageObject
                .searchArticleByTextAndPutToExistentFolderInMyList(search_by_text2, article_title2, name_of_folder);

        // open name_of_folder in My List
        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickToMyList();
        myListsPageObject.openFolderByName(name_of_folder);

        // delete the first article using swipe
        myListsPageObject.swipeArticleToDelete(article_title1);
        myListsPageObject.openArticleByTitle(article_title2);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        assertEquals("Article title is not the '" + article_title2 + "'",
                         articlePageObject.getArticleTitle(),
                         article_title2);

    }


}
