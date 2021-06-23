package services.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;
import model.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowUserAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String json;
        Long id = Long.parseLong(req.getParameter("id"));
        User user = new User();
        try {
            user = userDao.findById(id);
        } catch (RuntimeException ex) {
            resp.setStatus(404);
        }
        UserDto userDto = new UserDto(user);
        json = new ObjectMapper().writeValueAsString(userDto);
        resp.setStatus(200);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }
}
