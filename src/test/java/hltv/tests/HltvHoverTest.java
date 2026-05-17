package hltv.tests;

import static org.junit.Assert.assertTrue;

import hltv.pages.HomePage;
import hltv.support.BaseSeleniumTest;
import org.junit.Test;

public class HltvHoverTest extends BaseSeleniumTest {

    @Test
    public void hoveringMediaMenuShowsSubmenuLinks() {
        HomePage homePage = new HomePage(driver, wait, config).open();

        homePage.hoverMediaMenu();

        assertTrue(homePage.showsMediaSubmenu());
        assertTrue(driver.getCurrentUrl().contains("hltv.org"));
    }
}