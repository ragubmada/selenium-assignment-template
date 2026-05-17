package hltv.driver;

import hltv.config.TestConfig;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class WebDriverFactory {

    private WebDriverFactory() {
    }

    public static WebDriver create(TestConfig config) throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--window-size=" + config.getWindowWidth() + "," + config.getWindowHeight());
        options.addArguments("--lang=en-US");

        if (config.isHeadless()) {
            options.addArguments("--headless");
        }

        return new RemoteWebDriver(new URL(config.getSeleniumHubUrl()), options);
    }
}