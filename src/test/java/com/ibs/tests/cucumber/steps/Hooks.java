package com.ibs.tests.cucumber.steps;

import com.ibs.managers.DriverManager;
import com.ibs.managers.PropManager;
import io.cucumber.java.Before;

import static com.ibs.utils.PropConst.TEST_APP_URL;

public class Hooks {
    @Before
    public void openHomePage() {
        DriverManager driverManager = DriverManager.getDriverManager();
        driverManager.getWebDriver().get(PropManager.getPropManager().getProperty(TEST_APP_URL));
        driverManager.getWebDriver().manage().window().maximize();
    }
}
