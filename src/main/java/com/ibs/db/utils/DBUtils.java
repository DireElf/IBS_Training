package com.ibs.db.utils;

import java.sql.*;

public class DBUtils {
    /**
     * Establishes and returns a connection to the specified database URL using the provided user credentials.
     *
     * @param url  the database URL to which the connection is established
     * @param user the username to access the database
     * @param pass the password corresponding to the provided username
     * @return a {@link Connection} object representing the established connection to the database,
     *         or {@code null} if a connection could not be established due to a {@link SQLException}
     */
    public static Connection createConnection(String url, String user, String pass) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
