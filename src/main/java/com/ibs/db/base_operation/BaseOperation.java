package com.ibs.db.base_operation;

import com.ibs.utils.DBUtils;
import com.ibs.managers.PropManager;

import java.sql.*;

import static com.ibs.utils.constants.QueriesConst.SELECT_ALL_ENTRIES_COUNT;
import static com.ibs.utils.constants.PropConst.*;

public class BaseOperation {
    protected static PropManager propManager = PropManager.getPropManager();

    protected Connection connection = DBUtils.createConnection(
            propManager.getProperty(JDBC_H2_URL),
            propManager.getProperty(JDBC_H2_USER),
            propManager.getProperty(JDBC_H2_PASS));

    protected static int tableEntriesNumber;
    protected static int lastEntryId;

    /**
     * Executes a SQL query and returns the result set.
     *
     * @param connection  the database connection to use
     * @param queryString the SQL query string
     * @param params      the parameters to set in the query
     * @return the result set from the executed query
     */
    public ResultSet makeQuery(Connection connection, String queryString, Object... params) {
        ResultSet resultSet = null;
        try {
            PreparedStatement pStatement = getPreparedStatement(connection, queryString, params);
            resultSet = pStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    /**
     * Executes a SQL update query and returns whether it was successful.
     *
     * @param connection  the database connection to use
     * @param queryString the SQL update query string
     * @param params      the parameters to set in the query
     * @return true if the update was successful, false otherwise
     */
    public boolean makeUpdateQuery(Connection connection, String queryString, Object... params) {
        try {
            PreparedStatement pStatement = getPreparedStatement(connection, queryString, params);
            return pStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Creates a prepared statement with the specified query and parameters.
     *
     * @param connection  the database connection to use
     * @param queryString the SQL query string
     * @param params      the parameters to set in the query
     * @return the prepared statement with the parameters set
     */
    private PreparedStatement getPreparedStatement(Connection connection, String queryString, Object... params) {
        PreparedStatement pStatement = null;
        try {
            pStatement = connection.prepareStatement(queryString);
            int paramIndex = 1;
            for (int i = 0; i < params.length; i++) {
                setObjectWithType(pStatement, paramIndex++, params[i]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pStatement;
    }

    /**
     * Sets the object in the prepared statement with the appropriate type.
     *
     * @param pStatement the prepared statement
     * @param paramIndex the index of the parameter to set
     * @param obj        the object to set in the statement
     * @throws SQLException if a database access error occurs
     */
    private void setObjectWithType(PreparedStatement pStatement, int paramIndex, Object obj) throws SQLException {
        if (obj instanceof String) {
            pStatement.setString(paramIndex, (String) obj);
        } else if (obj instanceof Integer) {
            pStatement.setInt(paramIndex, (Integer) obj);
        } else if (obj instanceof Boolean) {
            pStatement.setBoolean(paramIndex, (Boolean) obj);
        }
    }

    /**
     * Retrieves the current count of entries from the database.
     *
     * @return the total number of entries as an integer.
     * @throws SQLException if a database access error occurs or the query fails.
     */
    public int getCurrentEntriesCount() throws SQLException {
        final String columnLabel = "COUNT(*)";
        ResultSet resultSet = makeQuery(connection, SELECT_ALL_ENTRIES_COUNT);
        resultSet.next();
        return resultSet.getInt(columnLabel);
    }

    /**
     * Closes the database connection.
     *
     * @throws SQLException if a database access error occurs
     */
    public void closeConnection() throws SQLException {
        DBUtils.closeConnection(connection);
    }
}
