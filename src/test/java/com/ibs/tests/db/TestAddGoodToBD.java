package com.ibs.tests.db;

import com.ibs.managers.DBManager;
import org.junit.Test;

import java.sql.SQLException;

public class TestAddGoodToBD {
    protected static DBManager dbManager = DBManager.getDBManager();
    @Test
    public void testAddNewGoodToFoodTable() {
        try {
            dbManager.getAddGoodOperation()
                    .getRowsNumberBeforeAddGood()
                    .addGood()
                    .checkRowsNumberAfterAddGood()
                    .checkLastEntryValues()
                    .removeLastEntry()
                    .checkRowsNumberAfterRemoveEntry()
                    .checkEntryDeletionById()
                    .closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
