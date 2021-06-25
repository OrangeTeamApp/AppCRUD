package services.actions;

import exception.FormatDataException;
import model.User;
import org.json.JSONObject;
import services.handlers.UserValidator;
import services.handlers.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class UpdateUserAction implements Action {

    private static final Validator validator = new UserValidator();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonData = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        JSONObject jsonObj = new JSONObject(jsonData);
        Long id = Long.parseLong(req.getParameter("id"));
        String email = jsonObj.getString("email");
        String firstname = jsonObj.getString("firstname");
        String lastname = jsonObj.getString("lastname");
        String birthday = jsonObj.getString("birthday");

        try {
            User user = new User();
            LocalDate date = LocalDate.parse(birthday);
            user.setId(id);
            user.setEmail(email);
            user.setFirstName(firstname);
            user.setLastName(lastname);
            user.setBirthDate(date);
            validator.validate(user);
            userDao.update(user);
            resp.setStatus(200);
        } catch (RuntimeException ex) {
            resp.setStatus(500);
        } catch (FormatDataException ex) {
            resp.setStatus(400);
            resp.getWriter().write(ex.getMessage());
            resp.getWriter().flush();
        }
    }
}
