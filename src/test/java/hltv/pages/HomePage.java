package hltv.pages;

import hltv.config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class HomePage extends BasePage {

    private final By navSearchInput = By.cssSelector("[data-nav-search-input-desktop]");
    private final By mediaMenu = By.xpath("//a[contains(@class, 'nav-link') and normalize-space()='Media']");
    private final By mediaConfirmedLink = By.linkText("HLTV Confirmed");
    private final By mediaGalleriesLink = By.linkText("Galleries");
    private final By todaysNewsHeading = By.xpath("//h2[normalize-space()=\"Today's news\"]");
    private final By resultsHeading = By.xpath("//h1[contains(normalize-space(), 'RESULTS')]");
    private final By matchesHeading = By.xpath("//h1[contains(normalize-space(), \"TODAY'S MATCHES\")]");

    public HomePage(WebDriver driver, WebDriverWait wait, TestConfig config) {
        super(driver, wait, config);
    }

    public HomePage open() {
        super.open("/");
        return this;
    }

    public SearchPage searchFor(String query) {
        type(navSearchInput, query);
        pressEnter(navSearchInput);
        return new SearchPage(driver, wait, config);
    }

    public LoginDialog openLoginDialog() {
        driver.findElement(By.cssSelector(".navsignin")).click();
        return new LoginDialog(driver, wait, config);
    }

    public HomePage hoverMediaMenu() {
        new Actions(driver).moveToElement(visible(mediaMenu)).perform();
        visible(mediaConfirmedLink);
        visible(mediaGalleriesLink);
        return this;
    }

    public boolean showsTodaysNews() {
        return isVisible(todaysNewsHeading);
    }

    public boolean showsResultsSection() {
        return isVisible(resultsHeading);
    }

    public boolean showsMediaSubmenu() {
        return isVisible(mediaConfirmedLink) && isVisible(mediaGalleriesLink);
    }

    public boolean showsMatchesSection() {
        return isVisible(matchesHeading);
    }
}