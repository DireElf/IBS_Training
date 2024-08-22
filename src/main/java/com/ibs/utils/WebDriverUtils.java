package com.ibs.utils;

import com.ibs.managers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverUtils {
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

    /**
     * Configures the provided WebDriver with specified options.
     * Maximizes the browser window and sets implicit wait and page load timeout durations.
     *
     * @param driver the WebDriver instance to configure
     * @param implicitlyWait the duration (in seconds) for the implicit wait timeout
     * @param pageLoadTimeout the duration (in seconds) for the page load timeout
     */
    public static void setWebDriverOptions(WebDriver driver, Long implicitlyWait, Long pageLoadTimeout) {
        driver.manage().window().maximize();
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(implicitlyWait));
        driver.manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
    }
}
