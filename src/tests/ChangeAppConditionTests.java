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
public class ChangeAppConditionTests extends CoreTestCase
{
    @Test
    public void testScreenOrientationOnSearchResult()
    {
        String search_by_text = "Java";
        String article_name = "JavaScript";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_by_text);
        searchPageObject.waitForSearchResult(article_name);
        searchPageObject.clickByArticleWithSubstring(article_name);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation =articlePageObject.getArticleTitle();

        this.rotateScreenLandscape();

        String title_after_rotation = articlePageObject.getArticleTitle();

        assertEquals(
                "Article name have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation);

        this.rotateScreenPortraite();

        String title_after_second_rotation = articlePageObject.getArticleTitle();

        assertEquals(
                "Article name have been changed after screen rotation",
                title_after_rotation,
                title_after_second_rotation);

    }

    @Test
    public void testSearchArticleInBackground()
    {
        String search_by_text = "Java";
        String article_name = "JavaScript";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_by_text);
        searchPageObject.waitForSearchResult(article_name);

        this.backgroundApp(2);

        searchPageObject.waitForSearchResult(article_name);

    }

}
