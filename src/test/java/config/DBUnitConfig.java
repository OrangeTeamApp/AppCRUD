package config;
import dao.HibernateUserDao;
import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.Before;

import javax.sql.DataSource;
import java.io.InputStream;


public class DBUnitConfig extends DataSourceBasedDBTestCase {

    private static final HibernateUserDao userDao = new HibernateUserDao();

    @Override
    protected DataSource getDataSource() {
        return null;
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("data.xml")) {
            return new FlatXmlDataSetBuilder().build(resourceAsStream);
        }
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

}