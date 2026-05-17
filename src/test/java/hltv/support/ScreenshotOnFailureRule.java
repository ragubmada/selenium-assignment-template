package hltv.support;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Supplier;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public final class ScreenshotOnFailureRule extends TestWatcher {

    private final Supplier<WebDriver> driverSupplier;

    public ScreenshotOnFailureRule(Supplier<WebDriver> driverSupplier) {
        this.driverSupplier = driverSupplier;
    }

    @Override
    protected void failed(Throwable throwable, Description description) {
        WebDriver driver = driverSupplier.get();
        if (!(driver instanceof TakesScreenshot)) {
            return;
        }

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        String fileName = description.getMethodName() + "-" + timestamp + ".png";
        Path targetDirectory = Paths.get("build", "screenshots");
        Path targetFile = targetDirectory.resolve(fileName);

        try {
            Files.createDirectories(targetDirectory);
            Files.copy(screenshot.toPath(), targetFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to store screenshot at " + targetFile, exception);
        }
    }
}