package com.ibs.tests.cucumber.steps;

import com.ibs.managers.DriverManager;
import com.ibs.managers.PropManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static com.ibs.utils.PropConst.TEST_APP_URL;

public class Hooks {
    private static DriverManager driverManager;

    @Before
    public void openHomePage() {
        driverManager = DriverManager.getDriverManager();
        driverManager.getWebDriver().get(PropManager.getPropManager().getProperty(TEST_APP_URL));
        driverManager.getWebDriver().manage().window().maximize();
    }

//    @After
//    public static void closeDriver() {
//        driverManager.getWebDriver().close();
//    }
}
