package com.ibs.db.operations.base_operation;

import com.ibs.db.utils.DBUtils;
import com.ibs.managers.PropManager;

import java.sql.*;

import static com.ibs.utils.PropConst.*;

public class BaseOperation {
    //TODO insert javadoc
    protected static PropManager propManager = PropManager.getPropManager();

    protected Connection connection = DBUtils.getConnection(
            propManager.getProperty(JDBC_H2_URL),
            propManager.getProperty(JDBC_H2_USER),
            propManager.getProperty(JDBC_H2_PASS));

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

    public boolean makeUpdateQuery(Connection connection, String queryString, Object... params) {
        try {
            PreparedStatement pStatement = getPreparedStatement(connection, queryString, params);
            return pStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

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

    private void setObjectWithType(PreparedStatement pStatement, int paramIndex, Object obj) throws SQLException {
        if (obj instanceof String) {
            pStatement.setString(paramIndex, (String) obj);
        } else if (obj instanceof Integer) {
            pStatement.setInt(paramIndex, ((Integer) obj).intValue());
        } else if (obj instanceof Boolean) {
            pStatement.setBoolean(paramIndex, ((Boolean) obj).booleanValue());
        }
    }


    public void closeConnection() throws SQLException {
        connection.close();
    }
}
