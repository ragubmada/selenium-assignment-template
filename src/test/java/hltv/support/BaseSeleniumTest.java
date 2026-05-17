package hltv.support;

import hltv.config.TestConfig;
import hltv.driver.WebDriverFactory;
import java.net.MalformedURLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseSeleniumTest {

    protected final TestConfig config = TestConfig.load();
    protected WebDriver driver;
    protected WebDriverWait wait;

    @Rule
    public ScreenshotOnFailureRule screenshotOnFailureRule = new ScreenshotOnFailureRule(() -> driver);

    @Before
    public void setUp() throws MalformedURLException {
        this.driver = WebDriverFactory.create(config);
        this.driver.manage().window().setSize(new Dimension(config.getWindowWidth(), config.getWindowHeight()));
        this.wait = new WebDriverWait(driver, config.getTimeout());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}