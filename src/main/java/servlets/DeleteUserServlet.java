package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.JdbcUserDao;
import dao.UserDao;
import model.User;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = { "/deleteUser" })
public class DeleteUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao = new JdbcUserDao();

    public DeleteUserServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/jsp/userList.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
        Long id = data.get("id").getAsLong();
        String errorMessage = null;
        try {
            User user = userDao.findById(id);
            userDao.remove(user);
        } catch (RuntimeException ex) {
            request.setAttribute("message", "Sorry, user to delete is not exist any more!");
            request.getRequestDispatcher("/jsp/errorPage.jsp").forward(request, response);
        }
    }

}