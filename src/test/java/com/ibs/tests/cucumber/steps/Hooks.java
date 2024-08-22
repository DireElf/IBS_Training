package com.ibs.tests.cucumber.steps;

import com.ibs.managers.DriverManager;
import com.ibs.managers.PropManager;
import com.ibs.utils.WebDriverUtils;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

import static com.ibs.utils.constants.PropConst.*;

public class Hooks {
    private static final PropManager propManager = PropManager.getPropManager();
    private static final DriverManager driverManager = DriverManager.getDriverManager();

    @BeforeAll
    public static void configureWebDriver() {
        WebDriverUtils.setWebDriverOptions(
                driverManager.getWebDriver(),
                Long.parseLong(propManager.getProperty(IMPLICITLY_WAIT)),
                Long.parseLong(propManager.getProperty(PAGE_LOAD_TIMEOUT))
        );
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
