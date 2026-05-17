package hltv.pages;

import hltv.config.TestConfig;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final TestConfig config;

    protected BasePage(WebDriver driver, WebDriverWait wait, TestConfig config) {
        this.driver = driver;
        this.wait = wait;
        this.config = config;
    }

    public void open(String path) {
        driver.get(config.getBaseUrl() + path);
        acceptCookiesIfPresent();
    }

    protected WebElement visible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String value) {
        WebElement element = visible(locator);
        element.clear();
        element.sendKeys(value);
    }

    protected void pressEnter(By locator) {
        visible(locator).sendKeys(Keys.ENTER);
    }

    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    protected void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    protected String text(By locator) {
        return visible(locator).getText();
    }

    protected boolean isVisible(By locator) {
        return !driver.findElements(locator).isEmpty() && driver.findElements(locator).get(0).isDisplayed();
    }

    protected void acceptCookiesIfPresent() {
        By consentDialog = By.id("CybotCookiebotDialog");
        By acceptButton = By.xpath("//button[contains(., 'Allow all cookies') or contains(., 'Use necessary cookies only')]");

        if (driver.findElements(consentDialog).isEmpty()) {
            return;
        }

        if (!driver.findElements(acceptButton).isEmpty()) {
            driver.findElements(acceptButton).get(0).click();
            wait.withTimeout(Duration.ofSeconds(2)).until(ExpectedConditions.invisibilityOfElementLocated(consentDialog));
        }
    }
}