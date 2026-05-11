package hltv.tests;

import static org.junit.Assert.assertTrue;

import hltv.pages.HomePage;
import hltv.pages.LoginDialog;
import hltv.support.BaseSeleniumTest;
import org.junit.Test;

public class HltvLoginTest extends BaseSeleniumTest {

    @Test
    public void loginFormCanBeOpenedFilledAndSubmitted() {
        LoginDialog loginDialog = new HomePage(driver, wait, config).open().openLoginDialog();

        assertTrue(loginDialog.isOpen());

        loginDialog
            .fillUsername("dummy-user")
            .fillPassword("dummy-password")
            .toggleRememberMe(true)
            .submit();

        assertTrue(loginDialog.isClosed());
        assertTrue(driver.getCurrentUrl().contains("hltv.org"));
    }
}