package com.ibs.tests.db.base_test;

import com.ibs.managers.DBManager;
import org.junit.After;

import java.sql.SQLException;

public class BaseTest {
    protected static DBManager dbManager = DBManager.getDBManager();
    @After
    public void afterEach() {
        try {
            dbManager.getRemoveGoodOperation()
                    .removeLastAddedEntry()
                    .checkRowsNumberAfterRemoveEntry()
                    .checkEntryDeletionById()
                    .closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

