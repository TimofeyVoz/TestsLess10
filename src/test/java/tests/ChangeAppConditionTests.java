package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
@Epic("Test app condition")
public class ChangeAppConditionTests extends CoreTestCase
{
    @Test
    @Features(value={@Feature(value="App"),@Feature(value="Conditions")})
    @DisplayName("Change screen orientation")
    @Description("Checking title change after rotation")
    @Step("Starting test testChangeScreenOrientationOnSearch")
    @Severity(value = SeverityLevel.MINOR)
    public void testChangeScreenOrientationOnSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String  title_befor_rotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String  title_after_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have been changed after rotation",
                title_befor_rotation,
                title_after_rotation
        );
        this.rotateScreenPortrait();
        String  title_after_second_rotation = ArticlePageObject.getArticleTitle();
        Assert.assertEquals(
                "Article title have been changed after rotation",
                title_befor_rotation,
                title_after_second_rotation
        );

    }
    @Test
    @Features(value={@Feature(value="App"),@Feature(value="Conditions")})
    @DisplayName("Send app to background")
    @Description("Checking for data changes when sending and calling from background")
    @Step("Starting test testCheckSearchArticleInBackground")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckSearchArticleInBackground() throws InterruptedException {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(10);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");


    }
}
