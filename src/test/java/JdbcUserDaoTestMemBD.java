import config.DBUnitConfig;
import dao.JdbcUserDao;
import model.User;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.dbunit.Assertion.assertEqualsIgnoreCols;


@RunWith(JUnit4.class)
public class JdbcUserDaoTestMemBD extends DBUnitConfig {
    private JdbcUserDao jdbcUserDao = new JdbcUserDao();
    private static final String SQL_STATEMENT_TO_GET_ALL_USERS = "SELECT * FROM USERS;";
    private static final String EMPTY_STRING = "";
    private static final String TABLE_USERS = "users";
    private static final String ID_COLUMN = "id";


    @Test
    public void givenDataSet_whenCreate_thenTableHasNewUser() throws Exception {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("expected_users_afterCreate.xml")) {
            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(is);
            ITable expectedData = expectedDataSet.getTable(TABLE_USERS);

            User user = new User(3L);
            user.setEmail( "email3");
            user.setFirstName("firstName3");
            user.setLastName("lastName3");
            user.setBirthDate(LocalDate.of(2019, 03, 22));

            jdbcUserDao.create(user);
            ITable actualData = getConnection()
                    .createQueryTable(EMPTY_STRING, SQL_STATEMENT_TO_GET_ALL_USERS);

            assertEqualsIgnoreCols(expectedData, actualData, new String[] { ID_COLUMN });
        }
    }

    @Test
    public void givenDataSet_whenUpdate_thenTableHasUpdateUser() throws Exception {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("expected_users_afterUpdate.xml")) {
            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(is);
            ITable expectedData = expectedDataSet.getTable(TABLE_USERS);

            User user = new User(2L);
            user.setEmail("newEmail");
            user.setFirstName("newFirstName");
            user.setLastName("lastName2");
            user.setBirthDate(LocalDate.of(2014, 03, 22));

            jdbcUserDao.update(user);
            ITable actualData = getConnection()
                    .createQueryTable(EMPTY_STRING, SQL_STATEMENT_TO_GET_ALL_USERS);

            assertEqualsIgnoreCols(expectedData, actualData, new String[] { ID_COLUMN });
        }
    }

    @Test
    public void givenDataSet_whenRemove_thenTableHasNotUser() throws Exception {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("expected_users_afterRemove.xml")) {
            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(is);
            ITable expectedData = expectedDataSet.getTable(TABLE_USERS);

            User user = new User(2L);
            user.setEmail("email2");
            user.setFirstName("firstName2");
            user.setLastName("lastName2");
            user.setBirthDate(LocalDate.of(2019, 03, 22));

            jdbcUserDao.remove(user);
            ITable actualData = getConnection()
                    .createQueryTable(EMPTY_STRING, SQL_STATEMENT_TO_GET_ALL_USERS);

            assertEqualsIgnoreCols(expectedData, actualData, new String[] { ID_COLUMN });
        }

    }

    @Test
    public void givenDataSet_whenFindAll_thenReturnsAllUsers() throws Exception {
        User user1 = new User(1L );
        user1.setEmail("email");
        user1.setFirstName("firstName");
        user1.setLastName("lastName");
        user1.setBirthDate(LocalDate.of(2019, 03, 22));

        User user2 = new User(2L);
        user2.setEmail("email2");
        user2.setFirstName("firstName2");
        user2.setLastName("lastName2");
        user2.setBirthDate(LocalDate.of(2019, 03, 22));

        List<User> expectedData = new ArrayList<>();
        expectedData.add(user1);
        expectedData.add(user2);

        List<User> actualData = jdbcUserDao.findAll();

        assertEquals(expectedData, actualData);

    }


    @Test
    public void givenDataSet_whenFindById_thenReturnsMatchingUser() throws Exception {
        Long id = 1L;
        User expectedData = new User(id);
        expectedData.setEmail("email");
        expectedData.setFirstName("firstName");
        expectedData.setLastName("lastName");
        expectedData.setBirthDate(LocalDate.of(2019, 03, 22));

        User actualData = jdbcUserDao.findById(id);

        assertEquals(expectedData, actualData);
    }
}