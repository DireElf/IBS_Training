package com.ibs.ui.utils;

import com.ibs.managers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {
    /**
     * Creates and returns an instance of {@link WebDriverWait} with the specified timeout duration.
     *
     * @param duration the timeout duration in seconds for which the wait should apply.
     * @return a {@link WebDriverWait} instance configured with the specified timeout duration.
     */
    public static WebDriverWait setExplicitlyWait(Long duration) {
        WebDriver driver = DriverManager.getDriverManager().getWebDriver();
        return new WebDriverWait(driver, Duration.ofSeconds(duration));
    }
}
