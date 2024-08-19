package com.ibs.managers;

import com.ibs.db.operations.AddGoodOperation;

public class DBManager {
    private static DBManager dbManager;
    private AddGoodOperation addGoodOperation;

    // Private constructor to prevent direct instantiation
    private DBManager() {}

    /**
     * Returns the singleton instance of DBManager.
     * If it doesn't exist, creates a new instance.
     *
     * @return the singleton instance of DBManager
     */
    public static DBManager getDBManager() {
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
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
