package com.ibs.tests.cucumber.steps;

import com.ibs.managers.DriverManager;
import com.ibs.managers.PropManager;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

import static com.ibs.utils.PropConst.TEST_APP_URL;

public class Hooks {
    private static final PropManager propManager = PropManager.getPropManager();
    private static final DriverManager driverManager = DriverManager.getDriverManager();

    @BeforeAll
    public static void initDriver() {
        driverManager.getWebDriver().manage().window().maximize();
    }

    @Before(value = "@test-by-UI")
    public static void openHomePage() {
        driverManager.getWebDriver().get(propManager.getProperty(TEST_APP_URL));
    }

    @AfterAll
    public static void quitWebDriver() {
        driverManager.quitWebDriver();
    }
}
