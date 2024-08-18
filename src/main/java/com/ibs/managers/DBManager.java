package com.ibs.managers;

import com.ibs.db.operations.AddGoodOperation;

public class DBManager {
    public static DBManager dbManager;
    private AddGoodOperation addGoodOperation;

    private DBManager() {}

    public static DBManager getDBManager() {
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }

    public AddGoodOperation getAddGoodOperation() {
        if (addGoodOperation == null) {
            addGoodOperation = new AddGoodOperation();
        }
        return addGoodOperation;
    }
}
