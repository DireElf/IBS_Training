package com.ibs.tests.cucumber.runners;

import com.ibs.managers.DriverManager;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {
        "src/test/resources/features/add-good-by-ui.feature",
        "src/test/resources/features/add-good-to-db.feature"},
        glue = "com/ibs/tests/cucumber/steps",
        tags = "@test-by-UI")
public class RunnerTest {
    @AfterClass
    public static void tearDown() {
        DriverManager.getDriverManager().quitWebDriver();
    }

}
