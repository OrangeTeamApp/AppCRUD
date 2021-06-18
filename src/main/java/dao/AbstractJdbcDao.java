package dao;

import exception.DsException;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

abstract class AbstractJdbcDao {

    private static final BasicDataSource defaultConnectionPool = new BasicDataSource();

    static {
        try (InputStream is = AbstractJdbcDao.class.getClassLoader().getResourceAsStream("db.config.properties")) {
            Properties property = new Properties();
            property.load(is);
            defaultConnectionPool.setUrl(property.getProperty("db.url"));
            defaultConnectionPool.setUsername(property.getProperty("db.username"));
            defaultConnectionPool.setPassword(property.getProperty("db.password"));
            defaultConnectionPool.setDriverClassName(property.getProperty("db.driver"));
        } catch (IOException throwables) {
            throwables.printStackTrace();
            throw new DsException(throwables.getMessage());
        }
    }

    public Connection createConnection() throws SQLException {
        return defaultConnectionPool.getConnection();
    }

}