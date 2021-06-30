package crud.services;

import crud.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(long id);

    void update(User user, long id);

    void save(User newUser);

    void deleteById(long id);
}
