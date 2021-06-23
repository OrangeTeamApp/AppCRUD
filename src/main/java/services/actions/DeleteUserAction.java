package services.actions;

import model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUserAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        try {
            User user = userDao.findById(id);
            userDao.remove(user);
        } catch (RuntimeException ex) {
            resp.setStatus(404);
        }
        resp.setStatus(200);
    }
}
