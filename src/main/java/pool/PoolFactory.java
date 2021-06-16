package pool;
import exception.DsException;
import org.apache.commons.dbcp2.BasicDataSource;
import java.io.IOException;
import java.io.InputStream;

import java.util.*;

public class PoolFactory {
    private static final Map<String, BasicDataSource> CACHE = new HashMap<>();
    private static final BasicDataSource defaultConnectionPool = new BasicDataSource();

    private PoolFactory() {

    }

    static {
        try (InputStream is = PoolFactory.class.getClassLoader().getResourceAsStream("db.config.properties")) {
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

    public static BasicDataSource getDefaultConnectionPool() {
        return defaultConnectionPool;
    }

    private static BasicDataSource createConnectionPool(String file) {
        BasicDataSource connectionPool = new BasicDataSource();
        try (InputStream is = PoolFactory.class.getClassLoader().getResourceAsStream(file)) {
            Properties property = new Properties();
            property.load(is);
            connectionPool.setUrl(property.getProperty("db.url"));
            connectionPool.setUsername(property.getProperty("db.username"));
            connectionPool.setPassword(property.getProperty("db.password"));
            connectionPool.setDriverClassName(property.getProperty("db.driver"));
        } catch (IOException throwables) {
            throwables.printStackTrace();
            throw new DsException(throwables.getMessage());
        }
        CACHE.put(file, connectionPool);
        return connectionPool;
    }

    public static BasicDataSource getConnectionPool(String file) {
        for (Map.Entry<String, BasicDataSource> entry : CACHE.entrySet()) {
            if (file.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return createConnectionPool(file);
    }

    public static BasicDataSource getConnectionPool() {
        return defaultConnectionPool;
    }
}

