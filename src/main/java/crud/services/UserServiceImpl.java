package crud.services;

import crud.dao.UserDao;
import crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findById(long id) {
        return userDao.findById(id);
    }

    public void update(User user, long id) {
        userDao.update(user);
    }

    public void save(User newUser) {
        userDao.create(newUser);
    }

    public void deleteById(long id) {
        userDao.remove(findById(id));
    }
}
