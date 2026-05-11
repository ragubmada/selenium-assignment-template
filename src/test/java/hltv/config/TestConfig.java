package hltv.config;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public final class TestConfig {

    private final String baseUrl;
    private final String seleniumHubUrl;
    private final boolean headless;
    private final int timeoutSeconds;
    private final int windowWidth;
    private final int windowHeight;

    private TestConfig(String baseUrl, String seleniumHubUrl, boolean headless, int timeoutSeconds, int windowWidth, int windowHeight) {
        this.baseUrl = baseUrl;
        this.seleniumHubUrl = seleniumHubUrl;
        this.headless = headless;
        this.timeoutSeconds = timeoutSeconds;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    public static TestConfig load() {
        Properties properties = new Properties();

        try (InputStream inputStream = TestConfig.class.getClassLoader().getResourceAsStream("hltv-test.properties")) {
            if (inputStream == null) {
                throw new IllegalStateException("Missing test configuration file hltv-test.properties");
            }

            properties.load(inputStream);
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to read hltv-test.properties", exception);
        }

        String baseUrl = properties.getProperty("baseUrl", "https://www.hltv.org");
        String seleniumHubUrl = properties.getProperty("seleniumHubUrl", "http://selenium:4444/wd/hub");
        boolean headless = Boolean.parseBoolean(properties.getProperty("headless", "true"));
        int timeoutSeconds = Integer.parseInt(properties.getProperty("timeoutSeconds", "15"));
        int windowWidth = Integer.parseInt(properties.getProperty("windowWidth", "1600"));
        int windowHeight = Integer.parseInt(properties.getProperty("windowHeight", "1200"));

        return new TestConfig(baseUrl, seleniumHubUrl, headless, timeoutSeconds, windowWidth, windowHeight);
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getSeleniumHubUrl() {
        return seleniumHubUrl;
    }

    public boolean isHeadless() {
        return headless;
    }

    public int getTimeoutSeconds() {
        return timeoutSeconds;
    }

    public Duration getTimeout() {
        return Duration.ofSeconds(timeoutSeconds);
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }
}