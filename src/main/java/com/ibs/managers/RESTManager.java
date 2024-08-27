package com.ibs.managers;

import com.ibs.rest.AddGoodOperation;

public class RESTManager {
    private static RESTManager restManager;

    private AddGoodOperation addGoodOperation;

    // Private constructor to prevent direct instantiation
    private RESTManager() {}

    /**
     * Returns the singleton instance of RESTManager.
     * If it doesn't exist, creates a new instance.
     *
     * @return the singleton instance of RESTManager
     */
    public static RESTManager getRESTManager() {
        if (restManager == null) {
            restManager = new RESTManager();
        }
        return restManager;
    }

    /**
     * Returns an instance of AddGoodOperation.
     * If it doesn't exist, creates a new instance.
     *
     * @return an instance of AddGoodOperation
     */
    public AddGoodOperation getAddGoodOperation() {
        if (addGoodOperation == null) {
            addGoodOperation = new AddGoodOperation();
        }
        return addGoodOperation;
    }
}
