package hltv.pages;

import hltv.config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class LoginDialog extends BasePage {

    private final By dialog = By.cssSelector(".login-dialog");
    private final By usernameInput = By.cssSelector(".login-dialog input[name='username']");
    private final By passwordInput = By.cssSelector(".login-dialog input[name='password']");
    private final By rememberMeCheckbox = By.cssSelector(".login-dialog input[name='autologin']");
    private final By loginButton = By.cssSelector(".login-dialog button.login-button");

    public LoginDialog(WebDriver driver, WebDriverWait wait, TestConfig config) {
        super(driver, wait, config);
    }

    public boolean isOpen() {
        return isVisible(dialog);
    }

    public LoginDialog fillUsername(String username) {
        setValue(usernameInput, username);
        return this;
    }

    public LoginDialog fillPassword(String password) {
        setValue(passwordInput, password);
        return this;
    }

    public LoginDialog toggleRememberMe(boolean selected) {
        WebElement checkbox = driver.findElement(rememberMeCheckbox);
        Boolean checked = checkbox.isSelected();
        if (checked == null || checked != selected) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
        }
        return this;
    }

    public LoginDialog submit() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(loginButton));
        return this;
    }

    public boolean isClosed() {
        return !isVisible(dialog);
    }

    private void setValue(By locator, String value) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input', { bubbles: true })); arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
            element,
            value
        );
    }
}