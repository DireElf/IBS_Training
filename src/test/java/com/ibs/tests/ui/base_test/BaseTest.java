package com.ibs.tests.ui.base_test;

import com.ibs.managers.DriverManager;
import com.ibs.managers.PageManager;
import com.ibs.managers.PropManager;
import com.ibs.utils.WebDriverUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import static com.ibs.utils.constants.PropConst.*;

public class BaseTest {
    protected final PageManager pageManager = PageManager.getPageManager();
    private static final DriverManager driverManager = DriverManager.getDriverManager();
    private static final PropManager propManager = PropManager.getPropManager();

    @BeforeClass
    public static void beforeAll() {
        WebDriverUtils.setWebDriverOptions(
                driverManager.getWebDriver(),
                Long.parseLong(propManager.getProperty(IMPLICITLY_WAIT)),
                Long.parseLong(propManager.getProperty(PAGE_LOAD_TIMEOUT))
        );
    }

    @Before
    public void beforeEach() {
        driverManager.getWebDriver().get(PropManager.getPropManager().getProperty(TEST_APP_URL));
    }

    @AfterClass
    public static void afterAll() {
        driverManager.quitWebDriver();
    }
}
