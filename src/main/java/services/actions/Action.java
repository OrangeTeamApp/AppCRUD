package services.actions;

import dao.JdbcUserDao;
import dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Action {
    UserDao userDao = new JdbcUserDao();
    void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
