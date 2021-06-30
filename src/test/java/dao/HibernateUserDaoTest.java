package dao;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.core.util.EntityManagerProvider;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.github.database.rider.core.util.EntityManagerProvider.em;
import static org.junit.Assert.assertEquals;


@RunWith(JUnit4.class)
public class HibernateUserDaoTest {


    private HibernateUserDao hibernateUserDao = new HibernateUserDao();

    @Rule
    public EntityManagerProvider emProvider = EntityManagerProvider.instance("riderDB");

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.instance(emProvider.connection());

    @Before
    public void setUp() {
        em().createNativeQuery("select 1 ").getResultList();
    }


    @Test
    @DataSet("data.yml")
    @ExpectedDataSet("expected_users_afterCreate.yml")
    public void givenDataSet_whenCreate_thenTableHasNewUser() {
        User user = new User(3L);
        user.setEmail( "ivanna23@gmail.com");
        user.setFirstName("Ivana");
        user.setLastName("Vera");
        user.setBirthDate(LocalDate.of(1975, 07, 06));

        hibernateUserDao.create(user);
    }

    @Test
    @DataSet("data.yml")
    @ExpectedDataSet("expected_users_afterUpdate.yml")
    public void givenDataSet_whenUpdate_thenTableHasUpdateUser() {
        User user = new User(2L);
        user.setEmail("nastia@mail.ru");
        user.setFirstName("Anastasia");
        user.setLastName("Ivanova");
        user.setBirthDate(LocalDate.of(1996, 11, 17));

        hibernateUserDao.update(user);
    }

    @Test
    @DataSet("data.yml")
    @ExpectedDataSet("expected_users_afterRemove.yml")
    public void givenDataSet_whenRemove_thenTableHasNotUser() {
        User user = new User(2L);
        user.setEmail("nastia@mail.ru");
        user.setFirstName("Anastasia");
        user.setLastName("Globova");
        user.setBirthDate(LocalDate.of(1996, 11, 17));

        hibernateUserDao.remove(user);
    }

    @Test
    @DataSet("data.yml")
    public void givenDataSet_whenFindAll_thenReturnsAllUsers() {
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

        List<User> actualData = hibernateUserDao.findAll();

        Assert.assertArrayEquals(expectedData.toArray(), actualData.toArray());

    }

}