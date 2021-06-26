package dao;

import exception.DbException;
import model.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao extends AbstractJdbcDao implements UserDao {

    private static final String SQL_INSERT_USER = "INSERT INTO users (email, first_name, last_name, birth_day) "
            + "VALUES (?, ?, ?, ?)";

    private static final String SQL_UPDATE_USER = "UPDATE users "
            + "SET email = ?, first_name = ?, last_name = ?, birth_day = ? "
            + "WHERE id = ? ";

    private static final String SQL_DELETE_USER = "DELETE FROM users WHERE id = ?";
    private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM users ";
    private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE id = ? ";

    public void create(User user) {
        try (Connection connection = createConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER)) {
            try {
                connection.setAutoCommit(false);
                statement.setString(1, user.getEmail());
                statement.setString(2, user.getFirstName());
                statement.setString(3, user.getLastName());
                statement.setDate(4, Date.valueOf(user.getBirthDate()));
                statement.executeUpdate();
                connection.commit();
            } catch (SQLException throwables) {
                connection.rollback();
                throw new SQLException(throwables.getMessage());
            } finally {
                connection.setAutoCommit(true);
            }

        } catch (SQLException throwables) {
            throw new DbException(throwables.getMessage());
        }
    }

    public void update(User user) {
        try (Connection connection = createConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER)) {
            try {
                connection.setAutoCommit(false);
                statement.setString(1, user.getEmail());
                statement.setString(2, user.getFirstName());
                statement.setString(3, user.getLastName());
                statement.setDate(4, Date.valueOf(user.getBirthDate()));
                statement.setLong(5, user.getId());
                statement.executeUpdate();
                connection.commit();
            } catch (SQLException throwables) {
                connection.rollback();
                throw new SQLException(throwables.getMessage());
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException throwables) {
            throw new DbException(throwables.getMessage());
        }
    }


    public void remove(User user) {
        try (Connection connection = createConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER)) {
            try {
                connection.setAutoCommit(false);
                statement.setLong(1, user.getId());
                statement.executeUpdate();
                connection.commit();
            } catch (SQLException throwables) {
                connection.rollback();
                throw new SQLException(throwables.getMessage());
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException throwables) {
            throw new DbException(throwables.getMessage());
        }
    }

    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = createConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_USERS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userList.add(createUser(resultSet));
            }
        } catch (SQLException throwables) {
            throw new DbException(throwables.getMessage());
        }
        return userList;
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        User user = new User(resultSet.getLong("id"));
        user.setEmail(resultSet.getString("email"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setBirthDate(resultSet.getDate("birth_day").toLocalDate());
        return user;
    }


    public User findById(Long id) {
        User result = null;
        try (Connection connection = createConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            result = createUser(resultSet);
        } catch (SQLException throwables) {
            throw new DbException(throwables.getMessage());
        }
        return result;
    }
}