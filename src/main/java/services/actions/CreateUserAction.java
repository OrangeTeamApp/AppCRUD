package services.actions;

import exception.FormatDataException;
import model.User;
import org.json.JSONObject;
import services.validation.UserValidator;
import services.validation.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

public class CreateUserAction implements Action {

    private static Validator validator = new UserValidator();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonData = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        JSONObject jsonObj = new JSONObject(jsonData);
        String email = jsonObj.getString("email");
        String firstname = jsonObj.getString("firstname");
        String lastname = jsonObj.getString("lastname");
        String birthday = jsonObj.getString("birthday");

        try {
            User user = new User();
            LocalDate date = LocalDate.parse(birthday);
            user.setEmail(email);
            user.setFirstName(firstname);
            user.setLastName(lastname);
            user.setBirthDate(date);
            validator.validate(user);
            userDao.create(user);
            resp.setStatus(201);
        } catch (RuntimeException ex) {
            resp.setStatus(500);
        } catch (FormatDataException ex) {
            resp.setStatus(400);
            resp.getWriter().write(ex.getMessage());
            resp.getWriter().flush();
        }
    }
}
