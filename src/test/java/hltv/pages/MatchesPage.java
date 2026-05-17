package hltv.pages;

import hltv.config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class MatchesPage extends BasePage {

    private final By matchFiltersHeading = By.xpath("//h3[normalize-space()='Match filters']");
    private final By rankedCheckbox = By.xpath("//label[contains(., 'Ranked')]//input[@type='checkbox']");

    public MatchesPage(WebDriver driver, WebDriverWait wait, TestConfig config) {
        super(driver, wait, config);
    }

    public MatchesPage open() {
        super.open("/matches");
        return this;
    }

    public boolean hasMatchFilters() {
        return isVisible(matchFiltersHeading);
    }

    public void toggleRankedFilter() {
        click(rankedCheckbox);
    }

    public boolean isRankedFilterSelected() {
        return visible(rankedCheckbox).isSelected();
    }
}