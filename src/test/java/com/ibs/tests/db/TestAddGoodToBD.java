package com.ibs.tests.db;

import com.ibs.tests.db.base_test.BaseDBTest;
import org.junit.Test;

import java.sql.SQLException;

public class TestAddGoodToBD extends BaseDBTest {
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
                    .checkEntryDeletionById();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
