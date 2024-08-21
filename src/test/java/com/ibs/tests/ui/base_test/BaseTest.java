package com.ibs.tests.ui.base_test;

import com.ibs.managers.DriverManager;
import com.ibs.managers.PageManager;
import com.ibs.managers.PropManager;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.time.Duration;

import static com.ibs.utils.PropConst.*;

public class BaseTest {
    protected final PageManager pageManager = PageManager.getPageManager();
    private static final DriverManager driverManager = DriverManager.getDriverManager();
    private static final PropManager propManager = PropManager.getPropManager();

    @BeforeClass
    public static void BeforeAll() {
        driverManager.getWebDriver().manage().timeouts()
                .implicitlyWait(
                        (Duration.ofSeconds
                                (Long.parseLong(propManager.getProperty(IMPLICITLY_WAIT)))));
        driverManager.getWebDriver().manage().timeouts()
                .pageLoadTimeout(
                        (Duration.ofSeconds
                                (Long.parseLong(propManager.getProperty(PAGE_LOAD_TIMEOUT)))));
    }

    @Before
    public void BeforeEach() {
        driverManager.getWebDriver().get(PropManager.getPropManager().getProperty(TEST_APP_URL));
    }

    @AfterClass
    public static void afterAll() {
        driverManager.quitWebDriver();
    }
}
