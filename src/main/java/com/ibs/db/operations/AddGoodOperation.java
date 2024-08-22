package com.ibs.db.operations;

import com.ibs.db.operations.base_operation.BaseOperation;
import com.ibs.models.Good;
import com.ibs.models.enums.GoodType;
import io.qameta.allure.Step;
import org.junit.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

import static com.ibs.db.utils.Queries.ADD_NEW_GOOD;
import static com.ibs.db.utils.Queries.SELECT_ALL;

public class AddGoodOperation extends BaseOperation {

    /**
     * Retrieves and stores the number of rows in the FOOD table before adding a new good.
     *
     * @return the current instance of AddGoodOperation
     * @throws SQLException if a database access error occurs
     */
    @Step
    public AddGoodOperation getRowsNumberBeforeAddGood() throws SQLException {
        tableEntriesNumber = getCurrentEntriesCount();
        return this;
    }

    /**
     * Adds a new good to the FOOD table using the predefined test data.
     *
     * @return the current instance of AddGoodOperation
     */
    @Step
    public AddGoodOperation addGood(String name, String type, Boolean isExotic) {
        Assert.assertTrue("The good wasn't added",
                makeUpdateQuery(connection, ADD_NEW_GOOD, name, type, isExotic));
        return this;
    }

    /**
     * Verifies the number of rows in the FOOD table after adding a new good.
     *
     * @return the current instance of AddGoodOperation
     * @throws SQLException if a database access error occurs
     */
    @Step
    public AddGoodOperation checkRowsNumberAfterAddGood() throws SQLException {
        int rowsNumberAfterUpdate = getCurrentEntriesCount();
        Assert.assertEquals("Incorrect rows number", ++tableEntriesNumber, rowsNumberAfterUpdate);
        return this;
    }

    /**
     * Verifies the values of the last entry added to the FOOD table.
     *
     * @return the current instance of AddGoodOperation
     * @throws SQLException if a database access error occurs
     */
    @Step
    public AddGoodOperation checkLastEntryValues(String name, String type, Boolean isExotic) throws SQLException {
        Map<Integer, Good> lastEntry = getLastEntryFromFood();
        lastEntryId = lastEntry.keySet().stream().mapToInt(o -> o).findFirst().orElse(0);
        Assert.assertTrue("ID is equal to 0", lastEntryId != 0);
        Good good = lastEntry.get(lastEntryId);
        Assert.assertEquals("Name should be " + name,
                name, good.getName());
        Assert.assertEquals("Type should be " + type,
                type, good.getType().getValue());
        Assert.assertFalse("Boolean should be " + isExotic, good.isExotic());
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
                getGoodType(resultSet.getString("FOOD_TYPE")),
                resultSet.getBoolean("FOOD_EXOTIC")
        );
        return Map.of(id, good);
    }

    private GoodType getGoodType(String value) {
        GoodType[] types = GoodType.values();
        return Arrays.stream(types)
                .filter(o -> o.getValue().equals(value))
                .findFirst()
                .orElseThrow();
    }
}
