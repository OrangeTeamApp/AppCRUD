package servlets;


import dao.JdbcUserDao;
import dao.UserDao;
import model.User;
import services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(urlPatterns = { "/editUser" })
public class EditUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String EMPTY_STRING = "";
    private UserDao userDao = new JdbcUserDao();
    private UserService userService = new UserService();


    public EditUserServlet() {
        super();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong (request.getParameter("id"));
        User user = null;

        try{
            user = userDao.findById(id);
        } catch (RuntimeException ex) {
            request.setAttribute("message", "User doesnt exist in system anymore!");
            request.getRequestDispatcher("/jsp/errorPage.jsp").forward(request, response);
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("userToEdit", user);
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/jsp/editUser.jsp");
        dispatcher.forward(request, response);
    }

    private User getUser(Long id, String login, String password, String email, String firstName, String lastName, String dateOfBirth) {
        LocalDate date = null;
        User user;
        if(userService.dateFormatValidator(dateOfBirth)) {
            date = LocalDate.parse(dateOfBirth);
            user = new User(id, login, password);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setBirthDate(date);
            return user;
        } else {
            return null;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User userToEdit = (User)request.getSession().getAttribute("userToEdit");
        Long id =Long.parseLong(String.valueOf(userToEdit.getId()));


        String login =  request.getParameter("login");
        String password =  request.getParameter("password");
        String email =  request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dateOfBirth =request.getParameter("dateOfBirth");

        if(password.equals(EMPTY_STRING)) {
            password = userDao.findById(id).getPassword();
        }

        User user = getUser(id, login, password, email, firstName, lastName, dateOfBirth);
        String errorMessage = userService.userFieldsValidation(user);


        if (errorMessage == null) {
            userDao.update(user);
            response.sendRedirect(request.getContextPath() + "/jsp/userList.jsp");
        }
        else {
            request.setAttribute("message", errorMessage);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/jsp/errorPage.jsp").forward(request, response);
        }

    }
}