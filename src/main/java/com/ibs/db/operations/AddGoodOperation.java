package com.ibs.db.operations;

import com.ibs.db.operations.base_operation.BaseOperation;
import com.ibs.models.Good;
import com.ibs.models.enums.GoodType;
import org.junit.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AddGoodOperation extends BaseOperation {
    //TODO aggregate test data from UI and DB tests at one place
    private static final Good TEST_GOOD = new Good("Морковь", GoodType.VEGETABLE, false);

    private static final String SELECT_ALL = "SELECT * FROM FOOD";
    private static final String SELECT_ALL_ENTRIES_COUNT = "SELECT COUNT(*) AS rowsNumber FROM FOOD";
    private static final String ADD_NEW_GOOD
            = "INSERT INTO FOOD(FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC) VALUES (?, ?, ?)";
    private static final String SELECT_ENTRY_BY_ID = "SELECT * FROM FOOD WHERE FOOD_ID = ?";
    private static final String DELETE_ENTRY_BY_ID = "DELETE FROM FOOD WHERE FOOD_ID = ?";
    private int dynamicRowsNumber;
    private int lastEntryId;

    /**
     * Retrieves and stores the number of rows in the FOOD table before adding a new good.
     *
     * @return the current instance of AddGoodOperation
     * @throws SQLException if a database access error occurs
     */
    public AddGoodOperation getRowsNumberBeforeAddGood() throws SQLException {
        ResultSet resultSet = makeQuery(connection, SELECT_ALL_ENTRIES_COUNT);
        resultSet.next();
        dynamicRowsNumber = resultSet.getInt("rowsNumber");
        return this;
    }

    /**
     * Adds a new good to the FOOD table using the predefined test data.
     *
     * @return the current instance of AddGoodOperation
     */
    public AddGoodOperation addGood() {
        Assert.assertTrue("The good wasn't added",
                makeUpdateQuery(connection, ADD_NEW_GOOD,
                        TEST_GOOD.getName(),
                        TEST_GOOD.getType().getValue(),
                        TEST_GOOD.isExotic()));
        return this;
    }

    /**
     * Verifies the number of rows in the FOOD table after adding a new good.
     *
     * @return the current instance of AddGoodOperation
     * @throws SQLException if a database access error occurs
     */
    public AddGoodOperation checkRowsNumberAfterAddGood() throws SQLException {
        ResultSet resultSet = makeQuery(connection, SELECT_ALL_ENTRIES_COUNT);
        resultSet.next();
        int rowsNumberAfterUpdate = resultSet.getInt("rowsNumber");
        Assert.assertEquals("Incorrect rows number", ++dynamicRowsNumber, rowsNumberAfterUpdate);
        return this;
    }

    /**
     * Verifies the values of the last entry added to the FOOD table.
     *
     * @return the current instance of AddGoodOperation
     * @throws SQLException if a database access error occurs
     */
    public AddGoodOperation checkLastEntryValues() throws SQLException {
        Map<Integer, Good> lastEntry = getLastEntryFromFood();
        lastEntryId = lastEntry.keySet().stream().mapToInt(o -> o).findFirst().orElse(0);
        Good good = lastEntry.get(lastEntryId);
        Assert.assertEquals("Name should be 'Морковь'",
                "Морковь", good.getName());
        Assert.assertEquals("Type should be 'Овощ'",
                "Овощ", good.getType().getValue());
        Assert.assertFalse("Boolean should be 'false'", good.isExotic());
        return this;
    }

    /**
     * Removes the last entry added to the FOOD table.
     *
     * @return the current instance of AddGoodOperation
     * @throws SQLException if a database access error occurs
     */
    public AddGoodOperation removeLastEntry() throws SQLException {
        makeUpdateQuery(connection, DELETE_ENTRY_BY_ID, lastEntryId);
        return this;
    }

    /**
     * Verifies the number of rows in the FOOD table after removing the last entry.
     *
     * @return the current instance of AddGoodOperation
     * @throws SQLException if a database access error occurs
     */
    public AddGoodOperation checkRowsNumberAfterRemoveEntry() throws SQLException {
        ResultSet resultSet = makeQuery(connection, SELECT_ALL_ENTRIES_COUNT);
        resultSet.next();
        Assert.assertEquals("Incorrect rows number",
                --dynamicRowsNumber, resultSet.getInt("rowsNumber"));
        return this;
    }

    /**
     * Checks if the last entry was successfully deleted from the FOOD table by its ID.
     *
     * @return the current instance of AddGoodOperation
     * @throws SQLException if a database access error occurs
     */
    public AddGoodOperation checkEntryDeletionById() throws SQLException {
        ResultSet resultSet = makeQuery(connection, SELECT_ENTRY_BY_ID, lastEntryId);
        Assert.assertTrue(!resultSet.isBeforeFirst() && resultSet.getRow() == 0);
        return this;
    }

    /**
     * Retrieves the last entry from the FOOD table and maps it to a Good object.
     *
     * @return a map containing the ID of the last entry and the corresponding Good object
     * @throws SQLException if a database access error occurs
     */
    private Map<Integer, Good> getLastEntryFromFood() throws SQLException {
        ResultSet resultSet = makeQuery(connection, SELECT_ALL);
        resultSet.last();
        int id = resultSet.getInt("FOOD_ID");
        Good good = new Good(
                resultSet.getString("FOOD_NAME"),
                resultSet.getString("FOOD_TYPE").equals("Овощ") ? GoodType.VEGETABLE : GoodType.FRUIT,
                resultSet.getBoolean("FOOD_EXOTIC")
        );
        return Map.of(id, good);
    }
}
