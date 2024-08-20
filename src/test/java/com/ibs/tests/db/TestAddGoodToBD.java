package com.ibs.tests.db;

import com.ibs.managers.DBManager;
import io.cucumber.junit.Cucumber;
import org.junit.Test;
import org.junit.runner.RunWith;

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
