package com.ibs.ui.pages.base_page;

import com.ibs.managers.DriverManager;
import com.ibs.managers.PageManager;
import org.openqa.selenium.support.PageFactory;


public class BasePage {
    protected DriverManager driverManager = DriverManager.getDriverManager();
    protected PageManager pageManager = PageManager.getPageManager();

    public BasePage() {
        PageFactory.initElements(driverManager.getWebDriver(), this);
    }

}
