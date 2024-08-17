package com.ibs.tests.db;

import com.ibs.db.utils.DBUtils;
import com.ibs.models.Good;
import com.ibs.models.enums.GoodType;
import com.ibs.tests.db.base_test.BaseDBTest;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestAddGoodToDB extends BaseDBTest {
    private static final String QUERY_SELECT_ROWS_COUNT_FROM_FOOD = "SELECT COUNT(*) AS rowsNumber FROM FOOD";
    private static final String QUERY_ADD_NEW_GOOD_TO_FOOD_TABLE
            = "INSERT INTO FOOD(FOOD_ID, FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC) VALUES (5, 'Морковь', 'Овощ', false)";

    private static final String QUERY_DELETE_ROW_5_FROM_FOOD = "DELETE FROM FOOD WHERE FOOD_ID = 5";
    private static final String QUERY_SELECT_LAST_ROW_FROM_FOOD = "SELECT * FROM FOOD WHERE FOOD_ID = 5";

    @Test
    public void testAddNewGoodToFoodTable() throws SQLException {
        ResultSet rowsNumberValue = DBUtils.makeQuery(connection, QUERY_SELECT_ROWS_COUNT_FROM_FOOD);
        rowsNumberValue.next();
        int rowsNumber = rowsNumberValue.getInt("rowsNumber");
        Assert.assertEquals("DB must has 4 values",4,rowsNumber);
        DBUtils.makeUpdateQuery(connection, QUERY_ADD_NEW_GOOD_TO_FOOD_TABLE);
        ResultSet rowsNumberResultSet = DBUtils.makeQuery(connection, QUERY_SELECT_ROWS_COUNT_FROM_FOOD);
        rowsNumberResultSet.next();
        int rowsNumberAfterUpdate = rowsNumberResultSet.getInt("rowsNumber");
        Assert.assertEquals("DB must has 5 values",5, rowsNumberAfterUpdate);
        ResultSet lastValue = DBUtils.makeQuery(connection, QUERY_SELECT_LAST_ROW_FROM_FOOD);
        lastValue.next();
        Assert.assertEquals("ID must be equals to 5",
                5, lastValue.getInt("FOOD_ID"));
        Assert.assertEquals("Name should be 'Морковь'",
                "Морковь", lastValue.getString("FOOD_NAME"));
        Assert.assertEquals("Type should be 'Овощ'",
                "Овощ", lastValue.getString("FOOD_TYPE"));
        Assert.assertEquals("Boolean should be 'false'",
                false, lastValue.getBoolean("FOOD_EXOTIC"));
        DBUtils.makeQuery(connection, QUERY_DELETE_ROW_5_FROM_FOOD);
        ResultSet rowsNumberAfterDelete = DBUtils.makeQuery(connection, QUERY_SELECT_ROWS_COUNT_FROM_FOOD);
        rowsNumberAfterDelete.next();
        int finalRowsNumber = rowsNumberAfterDelete.getInt("rowsNumber");
        Assert.assertEquals("DB must has 4 values",4, finalRowsNumber);
    }
}
