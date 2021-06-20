package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.JdbcUserDao;
import dao.UserDao;
import exception.FormatDataException;
import model.User;
import org.json.JSONObject;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = { "/users" })
public class RestUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserDao userDao = new JdbcUserDao();
    private final UserService userService = new UserService();

    public RestUserServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json;
        if (req.getParameter("id") != null) {
            Long id = Long.parseLong(req.getParameter("id"));
            User user = null;
            try {
                user = userDao.findById(id);
            } catch (RuntimeException ex) {
                resp.setStatus(404);
            }
            json = new ObjectMapper().writeValueAsString(user);

        } else {
            List<User> users = userDao.findAll();
            json = new ObjectMapper().writeValueAsString(users);
        }
        resp.setStatus(200);
        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonData = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        JSONObject jsonObj = new JSONObject(jsonData);
        String email = jsonObj.getString("email");
        String firstname = jsonObj.getString("firstname");
        String lastname = jsonObj.getString("lastname");
        String birthday = jsonObj.getString("birthday");

        User user = new User();

        try {
            fillUser(user, email, firstname, lastname,birthday);
            userService.userFieldsValidation(user);
        } catch (FormatDataException ex) {
            resp.setStatus(400);
        }
        userDao.create(user);
        resp.setStatus(201);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonData = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        JSONObject jsonObj = new JSONObject(jsonData);
        Long id = Long.parseLong(req.getParameter("id"));
        String email = jsonObj.getString("email");
        String firstname = jsonObj.getString("firstname");
        String lastname = jsonObj.getString("lastname");
        String birthday = jsonObj.getString("birthday");

        User user = new User();
        user.setId(id);

        try {
            fillUser(user, email, firstname, lastname,birthday);
            userService.userFieldsValidation(user);
        } catch (FormatDataException ex) {
            resp.setStatus(400);
        }
        userDao.update(user);
        resp.setStatus(200);
    }

    private void fillUser(User user, String email, String firstName, String lastName, String dateOfBirth) throws FormatDataException {
        LocalDate date;
        if (userService.dateFormatValidator(dateOfBirth)) {
            date = LocalDate.parse(dateOfBirth);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setBirthDate(date);
        } else {
            throw new FormatDataException("Incorrect date of birth.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
