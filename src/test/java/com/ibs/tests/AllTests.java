package com.ibs.tests;

import com.ibs.managers.DriverManager;
import com.ibs.tests.cucumber.runners.TestRunner;
import com.ibs.tests.db.TestAddGoodByJDBC;
import com.ibs.tests.ui.TestAddGood;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({TestAddGood.class, TestRunner.class, TestAddGoodByJDBC.class})
public class AllTests {
    @AfterClass
    public static void tearDown() {
        DriverManager.getDriverManager().quitWebDriver();
    }
}
