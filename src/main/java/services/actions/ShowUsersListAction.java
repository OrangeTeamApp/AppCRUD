package services.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowUsersListAction implements Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String json;
        List<User> users = userDao.findAll();
        json = new ObjectMapper().writeValueAsString(users);
        resp.setStatus(200);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }
}
