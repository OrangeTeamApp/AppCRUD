package dao;

import exception.HibernateDataManipulationException;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.Query;
import java.util.List;


public class HibernateUserDao extends AbstractHibernateDao implements UserDao {


    public void create(User user) {
        try(Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(user);
                transaction.commit();
            } catch (Exception ex) {
                transaction.rollback();
                throw new HibernateDataManipulationException(ex.getMessage());
            }
        }

    }

    public void update(User user) {
        try(Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.update(user);
                transaction.commit();
            } catch (Exception ex) {
                transaction.rollback();
                throw new HibernateDataManipulationException(ex.getMessage());
            }
        }
    }


    public void remove(User user) {
        try(Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.delete(user);
                transaction.commit();
            } catch (Exception ex) {
                transaction.rollback();
                throw new HibernateDataManipulationException(ex.getMessage());
            }
        }
    }


    public List<User> findAll() {
        try (Session session = createSession()) {
            List<User> users = (List<User>) session.createQuery("From User").list();
            return users;
        }
    }


    public User findById(Long id) {
        try (Session session = createSession()) {
            User user = session.get(User.class, id);
            return user;
        }
    }

}