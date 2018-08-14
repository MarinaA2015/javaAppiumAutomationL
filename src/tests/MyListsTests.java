package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
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

        SearchPageObject searchPageObject = new SearchPageObject(driver);
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

}
