package hltv.pages;

import hltv.config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class ArticlePage extends BasePage {

    private final By articleHeading = By.cssSelector("article h1, main h1");
    private final By articleBody = By.cssSelector("article, main");

    public ArticlePage(WebDriver driver, WebDriverWait wait, TestConfig config) {
        super(driver, wait, config);
    }

    public String headingText() {
        return text(articleHeading);
    }

    public boolean headingContains(String text) {
        return headingText().contains(text);
    }

    public void scrollToArticleBody() {
        scrollToElement(visible(articleBody));
    }
}