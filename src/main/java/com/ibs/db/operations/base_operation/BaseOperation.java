package com.ibs.db.operations.base_operation;

import com.ibs.db.utils.DBUtils;
import com.ibs.managers.PropManager;

import java.sql.*;

import static com.ibs.utils.PropConst.*;

public class BaseOperation {
    protected static PropManager propManager = PropManager.getPropManager();

    protected static Connection connection = DBUtils.getConnection(
            propManager.getProperty(JDBC_H2_URL),
            propManager.getProperty(JDBC_H2_USER),
            propManager.getProperty(JDBC_H2_PASS));

    public static ResultSet makeQuery(Connection connection, String queryString) {
        ResultSet resultSet = null;
        boolean hasResultSet;
        try {
            Statement statement = connection.createStatement();
            hasResultSet = statement.execute(queryString);
            if (hasResultSet) {
                resultSet = statement.executeQuery(queryString);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public static boolean makeUpdateQuery(Connection connection, String queryString) {
        try {
            return connection.createStatement().executeUpdate(queryString) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
