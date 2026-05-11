package hltv.pages;

import hltv.config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class SearchPage extends BasePage {

    private final By searchInput = By.xpath("(//input[@name='query'])[last()]");
    private final By searchButton = By.xpath("//button[normalize-space()='Search']");
    private final By teamTable = By.xpath("//table[.//th[normalize-space()='Team']]");
    private final By articleTable = By.xpath("//table[.//th[normalize-space()='Article']]");

    public SearchPage(WebDriver driver, WebDriverWait wait, TestConfig config) {
        super(driver, wait, config);
    }

    public SearchPage openWithQuery(String query) {
        super.open("/search?query=" + query);
        return this;
    }

    public SearchPage refineSearchTo(String query) {
        type(searchInput, query);
        click(searchButton);
        return this;
    }

    public boolean hasTeamResult(String teamName) {
        By teamLink = By.xpath("//table[.//th[normalize-space()='Team']]//a[contains(., '" + teamName + "')]");
        return isVisible(teamLink) && visible(teamTable).getText().contains(teamName);
    }

    public boolean hasArticleResultContaining(String text) {
        By articleLink = By.xpath("//table[.//th[normalize-space()='Article']]//a[contains(., '" + text + "')]");
        return isVisible(articleLink) || visible(articleTable).getText().contains(text);
    }

    public ArticlePage openFirstArticleResultContaining(String text) {
        By articleLink = By.xpath("(//table[.//th[normalize-space()='Article']]//a[contains(., '" + text + "')])[1]");
        click(articleLink);
        return new ArticlePage(driver, wait, config);
    }
}