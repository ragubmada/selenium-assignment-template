package hltv.tests;

import static org.junit.Assert.assertTrue;

import hltv.pages.HomePage;
import hltv.pages.MatchesPage;
import hltv.pages.ResultsPage;
import hltv.support.BaseSeleniumTest;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import org.junit.Test;

public class HltvSmokeTest extends BaseSeleniumTest {

    @Test
    public void homePageShowsStableContent() {
        HomePage homePage = new HomePage(driver, wait, config).open();

        assertTrue(driver.getTitle().contains("Counter-Strike News & Coverage"));
        assertTrue(homePage.showsTodaysNews());
        assertTrue(homePage.showsResultsSection());
        assertTrue(homePage.showsMatchesSection());
    }

    @Test
    public void sitePagesExposeTheirExpectedTitles() {
        List<PageProbe> pageProbes = Arrays.asList(
            new PageProbe("/", "Counter-Strike News & Coverage", () -> new HomePage(driver, wait, config).open().showsTodaysNews()),
            new PageProbe("/results", "Counter-Strike Results", () -> new ResultsPage(driver, wait, config).open().hasFeaturedResults()),
            new PageProbe("/matches", "Counter-Strike Matches", () -> new MatchesPage(driver, wait, config).open().hasMatchFilters())
        );

        for (PageProbe pageProbe : pageProbes) {
            driver.get(config.getBaseUrl() + pageProbe.path);
            assertTrue(driver.getTitle().contains(pageProbe.titleFragment));
            assertTrue(pageProbe.visibleCheck.get());
        }
    }

    private static final class PageProbe {
        private final String path;
        private final String titleFragment;
        private final Supplier<Boolean> visibleCheck;

        private PageProbe(String path, String titleFragment, Supplier<Boolean> visibleCheck) {
            this.path = path;
            this.titleFragment = titleFragment;
            this.visibleCheck = visibleCheck;
        }
    }
}