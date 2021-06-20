package servlets;


import dao.JdbcUserDao;
import dao.UserDao;
import exception.FormatDataException;
import model.User;
import services.UserService;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet(urlPatterns = { "/addUser" })
public class AddUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDao userDao = new JdbcUserDao();
    private UserService userService = new UserService();

    public AddUserServlet() {
        super();
    }




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/jsp/addUser.jsp");
        dispatcher.forward(request, response);
    }

    private User getUser(String email, String firstName, String lastName, String dateOfBirth) {
        LocalDate date = null;
        User user;
        if (userService.dateFormatValidator(dateOfBirth)) {
            date = LocalDate.parse(dateOfBirth);
            user = new User();
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

        String email =  request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dateOfBirth =request.getParameter("dateOfBirth");

        User user = getUser(email, firstName, lastName, dateOfBirth);


        try {
            if (user == null) {
                throw new FormatDataException("Incorrect date of birth.") ;
            }
            userService.userFieldsValidation(user);
            userDao.create(user);
            response.sendRedirect(request.getContextPath() + "/jsp/userList.jsp");
        } catch (FormatDataException ex) {
            request.setAttribute("message", ex.getMessage());
            request.setAttribute("user", user);
            request.getRequestDispatcher("/jsp/errorPage.jsp").forward(request, response);
        }

    }

}