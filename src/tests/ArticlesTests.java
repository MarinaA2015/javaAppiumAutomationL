package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

/**
 * Created by Inka on 14-Aug-18.
 */
public class ArticlesTests extends CoreTestCase
{

    @Test
    public void testCompareArticleTitle()
    {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        String title = articlePageObject.getArticleTitle();

        assertEquals("We see unexpected title",
                "Java (programming language)",
                title);
    }


    @Test
    public void testSwipeArticle()
    {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.waitForSearchResult("Appium");
        searchPageObject.clickByArticleWithSubstring("Appium");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();

    }

    @Test
    public void testEx6_OpenArticleAndAssertTitle()
    {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("JavaScript");
        searchPageObject.clickByArticleWithSubstring("JavaScript");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForPencilElementToEditHeader();
        articlePageObject.assertExistsArticleTitle();

    }
}
