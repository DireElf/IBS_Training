package com.ibs.db.operations;

import com.ibs.db.operations.base_operation.BaseOperation;
import org.junit.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddGoodOperation extends BaseOperation {
    private static final String QUERY_SELECT_ROWS_COUNT_FROM_FOOD = "SELECT COUNT(*) AS rowsNumber FROM FOOD";
    private static final String QUERY_ADD_NEW_GOOD_TO_FOOD_TABLE
            = "INSERT INTO FOOD(FOOD_ID, FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC) VALUES (5, 'Морковь', 'Овощ', false)";

    private static final String QUERY_DELETE_ROW_5_FROM_FOOD = "DELETE FROM FOOD WHERE FOOD_ID = 5";
    private static final String QUERY_SELECT_LAST_ROW_FROM_FOOD = "SELECT * FROM FOOD WHERE FOOD_ID = 5";

    private int dynamicRowsNumber;
    private ResultSet dynamicResultSet;


    public AddGoodOperation getRowsNumberBeforeAddGood() throws SQLException {
        dynamicResultSet = makeQuery(connection, QUERY_SELECT_ROWS_COUNT_FROM_FOOD);
        dynamicResultSet.next();
        dynamicRowsNumber = dynamicResultSet.getInt("rowsNumber");
        return this;
    }

    public AddGoodOperation addGood() {
        Assert.assertTrue("The good wasn't added",
                makeUpdateQuery(connection, QUERY_ADD_NEW_GOOD_TO_FOOD_TABLE));
        return this;
    }

    public AddGoodOperation getRowsNumberAfterAddGood() throws SQLException {
        dynamicResultSet = makeQuery(connection, QUERY_SELECT_ROWS_COUNT_FROM_FOOD);
        dynamicResultSet.next();
        int rowsNumberAfterUpdate = dynamicResultSet.getInt("rowsNumber");
        Assert.assertEquals("Incorrect rows number", ++dynamicRowsNumber, rowsNumberAfterUpdate);
        return this;
    }

    public AddGoodOperation checkLastEntry() throws SQLException {
        dynamicResultSet = makeQuery(connection, QUERY_SELECT_LAST_ROW_FROM_FOOD);
        dynamicResultSet.next();
        Assert.assertEquals("ID must be equals to 5",
                dynamicRowsNumber, dynamicResultSet.getInt("FOOD_ID"));
        Assert.assertEquals("Name should be 'Морковь'",
                "Морковь", dynamicResultSet.getString("FOOD_NAME"));
        Assert.assertEquals("Type should be 'Овощ'",
                "Овощ", dynamicResultSet.getString("FOOD_TYPE"));
        Assert.assertEquals("Boolean should be 'false'",
                false, dynamicResultSet.getBoolean("FOOD_EXOTIC"));
        return this;
    }

    public void removeLastEntry() throws SQLException {
        makeQuery(connection, QUERY_DELETE_ROW_5_FROM_FOOD);
        dynamicResultSet = makeQuery(connection, QUERY_SELECT_ROWS_COUNT_FROM_FOOD);
        dynamicResultSet.next();
        Assert.assertEquals("Incorrect rows number",
                --dynamicRowsNumber, dynamicResultSet.getInt("rowsNumber"));
    }
}
