package com.ibs.tests.db.base_test;

import com.ibs.db.utils.DBUtils;
import com.ibs.managers.DBManager;
import com.ibs.managers.PropManager;
import org.junit.After;
import org.junit.Before;
import java.sql.Connection;
import java.sql.SQLException;

import static com.ibs.utils.PropConst.*;

public class BaseDBTest {
    protected static DBManager dbManager = DBManager.getDBManager();
    protected static PropManager propManager = PropManager.getPropManager();
    protected static Connection connection;

    @Before
    public void BeforeEach() {
        connection = DBUtils.getConnection(
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
}
