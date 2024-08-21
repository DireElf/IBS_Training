package com.ibs.tests.db;

import com.ibs.managers.DBManager;
import com.ibs.models.Good;
import com.ibs.tests.db.base_test.BaseTest;
import com.ibs.utils.TestDataUtils;
import org.junit.Test;

import java.sql.SQLException;

public class TestAddGoodByJDBC extends BaseTest {
    protected static DBManager dbManager = DBManager.getDBManager();
    private static final Good TEST_GOOD = TestDataUtils.getGoodFromJsonFile("carrot.json");
    @Test
    public void testAddNewGoodToFoodTable() {
        try {
            dbManager.getAddGoodOperation()
                    .getRowsNumberBeforeAddGood()
                    .addGood(TEST_GOOD.getName(), TEST_GOOD.getType().getValue(), TEST_GOOD.isExotic())
                    .checkRowsNumberAfterAddGood()
                    .checkLastEntryValues(TEST_GOOD.getName(), TEST_GOOD.getType().getValue(), TEST_GOOD.isExotic());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
