package com.ibs.tests.cucumber.runners;

import com.ibs.managers.DriverManager;
import com.ibs.managers.PropManager;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import static com.ibs.utils.PropConst.TEST_APP_URL;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        features = {"src/test/resources/features/add-good-by-ui.feature",
                "src/test/resources/features/add-good-to-db.feature"},
        glue = "com/ibs/tests/cucumber/steps")
public class TestRunner {
    private static final PropManager propManager = PropManager.getPropManager();
    private static final DriverManager driverManager = DriverManager.getDriverManager();
    @BeforeClass
    public static void getWebDriver() {
        driverManager.getWebDriver().get(propManager.getProperty(TEST_APP_URL));
    }

    @AfterClass
    public static void quitWebDriver() {
        driverManager.quitWebDriver();
    }

}
