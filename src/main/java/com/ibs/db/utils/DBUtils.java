package com.ibs.db.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
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

    public static int makeUpdateQuery(Connection connection, String queryString) {
        try {
            return connection.createStatement().executeUpdate(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
