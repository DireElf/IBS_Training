package com.ibs.managers;

import java.time.Duration;

import static com.ibs.utils.PropConst.IMPLICITLY_WAIT;
import static com.ibs.utils.PropConst.PAGE_LOAD_TIMEOUT;

public class InitManager {
    private static final PropManager propManager = PropManager.getTestPropManager();
    private static final DriverManager driverManager = DriverManager.getDriverManager();

    /**
     * Initializes the framework by maximizing the browser window, setting implicit wait time,
     * and page load timeout using values from the property manager.
     */
    public static void initFramework() {
        driverManager.getWebDriver().manage().window().maximize();
        driverManager.getWebDriver().manage().timeouts()
                .implicitlyWait(
                        (Duration.ofSeconds
                                (Long.parseLong(propManager.getProperty(IMPLICITLY_WAIT)))));
        driverManager.getWebDriver().manage().timeouts()
                .pageLoadTimeout(
                        (Duration.ofSeconds
                                (Long.parseLong(propManager.getProperty(PAGE_LOAD_TIMEOUT)))));
    }

    /**
     * Quits the WebDriver and cleans up resources, effectively shutting down the framework.
     */
    public static void quitFramework() {
        driverManager.quitWebDriver();
    }
}
