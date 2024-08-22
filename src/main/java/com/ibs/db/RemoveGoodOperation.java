package com.ibs.db;

import com.ibs.db.base_operation.BaseOperation;
import org.junit.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ibs.utils.constants.QueriesConst.DELETE_ENTRY_BY_ID;
import static com.ibs.utils.constants.QueriesConst.SELECT_ENTRY_BY_ID;

public class RemoveGoodOperation extends BaseOperation {

    /**
     * Removes the last entry added to the FOOD table.
     *
     * @return the current instance of AddGoodOperation
     * @throws SQLException if a database access error occurs
     */
    public RemoveGoodOperation removeLastAddedEntry() throws SQLException {
        tableEntriesNumber = getCurrentEntriesCount();
        makeUpdateQuery(connection, DELETE_ENTRY_BY_ID, lastEntryId);
        return this;
    }

    /**
     * Verifies the number of rows in the FOOD table after removing the last entry.
     *
     * @return the current instance of AddGoodOperation
     * @throws SQLException if a database access error occurs
     */
    public RemoveGoodOperation checkRowsNumberAfterRemoveEntry() throws SQLException {
        int current = getCurrentEntriesCount();
        Assert.assertEquals("Incorrect rows number",
                --tableEntriesNumber, current);
        return this;
    }

    /**
     * Checks if the last entry was successfully deleted from the FOOD table by its ID.
     *
     * @return the current instance of AddGoodOperation
     * @throws SQLException if a database access error occurs
     */
    public RemoveGoodOperation checkEntryDeletionById() throws SQLException {
        ResultSet resultSet = makeQuery(connection, SELECT_ENTRY_BY_ID, lastEntryId);
        Assert.assertTrue(!resultSet.isBeforeFirst() && resultSet.getRow() == 0);
        return this;
    }
}
