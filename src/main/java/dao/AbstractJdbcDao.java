package dao;

import pool.PoolFactory;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

abstract class AbstractJdbcDao {
    private static BasicDataSource connectionPool = PoolFactory.getConnectionPool();

    public Connection createConnection() throws SQLException {
        return connectionPool.getConnection();
    }

    public void changeConnectionPool(String file) {
        connectionPool = PoolFactory.getConnectionPool(file);
    }
}