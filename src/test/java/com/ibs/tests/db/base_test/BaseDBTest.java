package com.ibs.tests.db.base_test;

import com.ibs.managers.DBManager;
import com.ibs.managers.PropManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.sql.Connection;
import java.sql.SQLException;

import static com.ibs.utils.PropConst.*;

public class BaseDBTest {
    private static DBManager dbManager;
    private static PropManager propManager;
    protected static Connection connection;

    @BeforeClass
    public static void BeforeAll() {
        dbManager = DBManager.getDBManager();
        propManager = PropManager.getPropManager();
    }

    @Before
    public void BeforeEach() {
        connection = dbManager.getConnection(
                propManager.getProperty(JDBC_H2_URL),
                propManager.getProperty(JDBC_H2_USER),
                propManager.getProperty(JDBC_H2_PASS));

    }

    @After
    public void AfterEach() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void AfterAll() {
        dbManager = null;
    }
}
