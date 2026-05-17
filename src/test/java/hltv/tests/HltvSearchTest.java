package hltv.tests;

import static org.junit.Assert.assertTrue;

import hltv.pages.ArticlePage;
import hltv.pages.HomePage;
import hltv.pages.SearchPage;
import hltv.support.BaseSeleniumTest;
import org.junit.Test;

public class HltvSearchTest extends BaseSeleniumTest {

    @Test
    public void searchPageFindsMouzAcrossTeamsAndArticles() {
        SearchPage searchPage = new HomePage(driver, wait, config)
            .open()
            .searchFor("MOUZ");

        assertTrue(driver.getCurrentUrl().contains("/search?query=MOUZ"));
        assertTrue(searchPage.hasTeamResult("MOUZ"));
        assertTrue(searchPage.hasArticleResultContaining("MOUZ"));
    }

    @Test
    public void searchCanOpenAnArticleAndReturnWithBrowserHistory() {
        SearchPage searchPage = new HomePage(driver, wait, config)
            .open()
            .searchFor("MOUZ");

        ArticlePage articlePage = searchPage.openFirstArticleResultContaining("MOUZ");
        assertTrue(articlePage.headingContains("MOUZ"));

        articlePage.scrollToArticleBody();
        driver.navigate().back();
        assertTrue(driver.getCurrentUrl().contains("/search?query=MOUZ"));

        driver.navigate().forward();
        assertTrue(new ArticlePage(driver, wait, config).headingContains("MOUZ"));
    }
}