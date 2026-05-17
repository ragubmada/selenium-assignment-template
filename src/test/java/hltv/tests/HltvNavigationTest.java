package hltv.tests;

import static org.junit.Assert.assertTrue;

import hltv.pages.MatchesPage;
import hltv.pages.ResultsPage;
import hltv.support.BaseSeleniumTest;
import org.junit.Test;

public class HltvNavigationTest extends BaseSeleniumTest {

    @Test
    public void resultsPageShowsFeaturedMatchAndLetsUsGoBackAndForward() {
        ResultsPage resultsPage = new ResultsPage(driver, wait, config).open();

        assertTrue(resultsPage.hasFeaturedResults());
        assertTrue(resultsPage.firstResultMentions("PGL Astana"));
        resultsPage.selectTimezone("Europe/Budapest");
        assertTrue(resultsPage.selectedTimezone().contains("Europe/Budapest"));

        driver.navigate().to(config.getBaseUrl() + "/matches");
        MatchesPage matchesPage = new MatchesPage(driver, wait, config);
        assertTrue(matchesPage.hasMatchFilters());
        matchesPage.toggleRankedFilter();
        assertTrue(matchesPage.isRankedFilterSelected());

        driver.navigate().back();
        assertTrue(driver.getCurrentUrl().contains("/results"));

        driver.navigate().forward();
        assertTrue(driver.getCurrentUrl().contains("/matches"));
    }
}