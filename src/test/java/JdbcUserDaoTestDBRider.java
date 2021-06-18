import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.core.util.EntityManagerProvider;
import dao.JdbcUserDao;
import model.User;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.github.database.rider.core.util.EntityManagerProvider.em;
import static org.junit.Assert.assertEquals;
import org.testcontainers.containers.PostgreSQLContainer;


@RunWith(JUnit4.class)
public class JdbcUserDaoTestDBRider {


    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:9.4.20");

    @Rule
    public EntityManagerProvider emProvider = EntityManagerProvider.instance("postgre-it");

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.instance(emProvider.connection());

    @Rule
    public ExpectedException exception = ExpectedException.none();


    @BeforeClass
    public static void setupContainer() {
        postgres.start();
    }

    @AfterClass
    public static void shutdown() {
        postgres.stop();
    }


    private JdbcUserDao jdbcUserDao = new JdbcUserDao();


    @Test
    @DataSet("data.yml")
    @ExpectedDataSet("expected_users_afterCreate.yml")
    public void givenDataSet_whenCreate_thenTableHasNewUser() {
        User user = new User();
        user.setEmail("email3@example.com");
        user.setFirstName("firstName3");
        user.setLastName("lastName3");
        user.setBirthDate(LocalDate.of(2019, 03, 22));

        jdbcUserDao.create(user);

    }

    @Test
    @DataSet("data.yml")
    @ExpectedDataSet("expected_users_afterUpdate.yml")
    public void givenDataSet_whenUpdate_thenTableHasUpdateUser() {
        User user = new User(2L);
        user.setEmail("newEmail@example.com");
        user.setFirstName("newFirstName");
        user.setLastName("lastName2");
        user.setBirthDate(LocalDate.of(2014, 03, 22));

        jdbcUserDao.update(user);
    }

    @Test
    @DataSet("data.yml")
    @ExpectedDataSet("expected_users_afterRemove.yml")
    public void givenDataSet_whenRemove_thenTableHasNotUser() {
        User user = new User(2L);
        user.setEmail("email2");
        user.setFirstName("firstName2");
        user.setLastName("lastName2");
        user.setBirthDate(LocalDate.of(2019, 03, 22));

        jdbcUserDao.remove(user);
    }

    @Test
    @DataSet("data.yml")
    public void givenDataSet_whenFindAll_thenReturnsAllUsers() {
        User user1 = new User(1L);
        user1.setEmail("email@example.com");
        user1.setFirstName("firstName");
        user1.setLastName("lastName");
        user1.setBirthDate(LocalDate.of(2019, 03, 22));


        User user2 = new User(2L);
        user2.setEmail("email2@example.com");
        user2.setFirstName("firstName2");
        user2.setLastName("lastName2");
        user2.setBirthDate(LocalDate.of(2019, 03, 22));

        List<User> expectedData = new ArrayList<>();
        expectedData.add(user1);
        expectedData.add(user2);

        List<User> actualData = jdbcUserDao.findAll();

        Assert.assertArrayEquals(expectedData.toArray(), actualData.toArray());

    }

    @Test
    @DataSet("data.yml")
    public void givenDataSet_whenFindById_thenReturnsMatchingUser() {
        Long id = 1L;
        User expectedData = new User(id);
        expectedData.setEmail("email@example.com");
        expectedData.setFirstName("firstName");
        expectedData.setLastName("lastName");
        expectedData.setBirthDate(LocalDate.of(2019, 03, 22));

        User actualData = jdbcUserDao.findById(id);

        assertEquals(expectedData, actualData);
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:tc:postgresql://localhost/users-test", "test", "test");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}