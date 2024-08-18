package com.ibs.db.utils;

import java.sql.*;

public class DBUtils {
    public static Connection getConnection(String url, String user, String pass) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
