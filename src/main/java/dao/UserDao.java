package dao;

import model.User;

import java.util.List;

public interface UserDao {

    void create(User user);

    void update(User user);

    void remove(User user);

    List<User> findAll();

    User findById(Long id);

}