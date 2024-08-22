package com.ibs.utils;

import com.ibs.models.enums.GoodType;

import java.sql.*;
import java.util.Arrays;
import java.util.NoSuchElementException;

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

    /**
     * Finds and returns the {@link GoodType} corresponding to the specified value.
     *
     * @param value the string value to match against the {@link GoodType} values.
     * @return the corresponding {@link GoodType} if found.
     * @throws NoSuchElementException if no matching {@link GoodType} is found.
     */
    public static GoodType getGoodType(String value) {
        GoodType[] types = GoodType.values();
        return Arrays.stream(types)
                .filter(o -> o.getValue().equals(value))
                .findFirst()
                .orElseThrow();
    }

    /**
     * Closes the database connection.
     *
     * @throws SQLException if a database access error occurs
     */
    public static void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
