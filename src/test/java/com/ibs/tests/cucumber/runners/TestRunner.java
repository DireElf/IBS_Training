package com.ibs.tests.cucumber.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        features = {"src/test/resources/features/add-good-by-ui.feature",
                "src/test/resources/features/add-good-to-db.feature"},
        glue = "com/ibs/tests/cucumber/steps")
public class TestRunner {
}
