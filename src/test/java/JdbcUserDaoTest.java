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
public class JdbcUserDaoTest extends DBUnitConfig {
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
            user.setEmail( "ivanna23@gmail.com");
            user.setFirstName("Ivana");
            user.setLastName("Vera");
            user.setBirthDate(LocalDate.of(1975, 07, 06));

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
            user.setEmail("nastia@mail.ru");
            user.setFirstName("Anastasia");
            user.setLastName("Ivanova");
            user.setBirthDate(LocalDate.of(1996, 11, 17));

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
            user.setEmail("nastia@mail.ru");
            user.setFirstName("Anastasia");
            user.setLastName("Globova");
            user.setBirthDate(LocalDate.of(1996, 11, 17));

            jdbcUserDao.remove(user);
            ITable actualData = getConnection()
                    .createQueryTable(EMPTY_STRING, SQL_STATEMENT_TO_GET_ALL_USERS);

            assertEqualsIgnoreCols(expectedData, actualData, new String[] { ID_COLUMN });
        }

    }

    @Test
    public void givenDataSet_whenFindAll_thenReturnsAllUsers() throws Exception {
        User user1 = new User(1L );
        user1.setEmail("ivan.abramov@gmail.com");
        user1.setFirstName("Ivan");
        user1.setLastName("Abramov");
        user1.setBirthDate(LocalDate.of(2001, 01, 22));

        User user2 = new User(2L);
        user2.setEmail("nastia@mail.ru");
        user2.setFirstName("Anastasia");
        user2.setLastName("Globova");
        user2.setBirthDate(LocalDate.of(1996, 11, 17));

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
        expectedData.setEmail("ivan.abramov@gmail.com");
        expectedData.setFirstName("Ivan");
        expectedData.setLastName("Abramov");
        expectedData.setBirthDate(LocalDate.of(2001, 01, 22));

        User actualData = jdbcUserDao.findById(id);

        assertEquals(expectedData, actualData);
    }
}