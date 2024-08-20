package com.ibs.tests.ui.base_test;

import com.ibs.managers.DriverManager;
import com.ibs.managers.InitManager;
import com.ibs.managers.PageManager;
import com.ibs.managers.PropManager;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.time.Duration;

import static com.ibs.utils.PropConst.*;

public class BaseTest {
    protected PageManager pageManager = PageManager.getPageManager();
    private final DriverManager driverManager = DriverManager.getDriverManager();

    @BeforeClass
    public static void BeforeAll() {
        InitManager.initFramework();
    }

    @Before
    public void BeforeEach() {
        driverManager.getWebDriver().get(PropManager.getPropManager().getProperty(TEST_APP_URL));
    }

    @AfterClass
    public static void afterAll() {
        InitManager.quitFramework();
    }
}
