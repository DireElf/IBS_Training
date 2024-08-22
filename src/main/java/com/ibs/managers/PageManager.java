package com.ibs.managers;

import com.ibs.ui.FoodPage;
import com.ibs.ui.ModalWindow;
import com.ibs.ui.HomePage;

public class PageManager {
    private static PageManager pageManager;
    private HomePage homePage;
    private FoodPage foodPage;
    private ModalWindow modalWindow;

    // Private constructor to prevent instantiation from other classes
    private PageManager() {}

    /**
     * Returns the singleton instance of PageManager. If it doesn't exist, a new instance is created.
     * @return the singleton instance of PageManager
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    /**
     * Returns the HomePage instance. If it doesn't exist, a new HomePage is initialized.
     * @return the HomePage instance
     */
    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    /**
     * Returns the FoodPage instance. If it doesn't exist, a new FoodPage is initialized.
     * @return the FoodPage instance
     */
    public FoodPage getFoodPage() {
        if (foodPage == null) {
            foodPage = new FoodPage();
        }
        return foodPage;
    }

    /**
     * Returns the ModalWindow instance. If it doesn't exist, a new ModalWindow is initialized.
     * @return the ModalWindow instance
     */
    public ModalWindow getModalWindow() {
        if (modalWindow == null) {
            modalWindow = new ModalWindow();
        }
        return modalWindow;
    }
}
