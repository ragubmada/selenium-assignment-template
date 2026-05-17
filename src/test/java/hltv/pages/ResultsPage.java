package hltv.pages;

import hltv.config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class ResultsPage extends BasePage {

    private final By featuredResultsHeading = By.xpath("//h1[normalize-space()='Featured results']");
    private final By firstResultLink = By.xpath("(//a[contains(@href, '/matches/') and .//table])[1]");
    private final By timezoneSelect = By.id("timezoneSelector");

    public ResultsPage(WebDriver driver, WebDriverWait wait, TestConfig config) {
        super(driver, wait, config);
    }

    public ResultsPage open() {
        super.open("/results");
        return this;
    }

    public boolean hasFeaturedResults() {
        return isVisible(featuredResultsHeading);
    }

    public ResultsPage selectTimezone(String timezone) {
        Select select = new Select(visible(timezoneSelect));
        select.selectByVisibleText(timezone);
        return this;
    }

    public String selectedTimezone() {
        return new Select(visible(timezoneSelect)).getFirstSelectedOption().getText();
    }

    public String firstResultText() {
        return visible(firstResultLink).getText();
    }

    public boolean firstResultMentions(String text) {
        return firstResultText().contains(text);
    }
}