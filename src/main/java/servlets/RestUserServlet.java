package servlets;

import services.actions.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/users"})
public class RestUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Map<String, Action> actionMap = new HashMap<String,Action>();

    public RestUserServlet() {
        super();
        actionMap.put("user", new ShowUserAction());
        actionMap.put("list", new ShowUsersListAction());
        actionMap.put("create", new CreateUserAction());
        actionMap.put("update", new UpdateUserAction());
        actionMap.put("delete", new DeleteUserAction());
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null) {
            actionMap.get("user").execute(req, resp);
        } else {
            actionMap.get("list").execute(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        actionMap.get("create").execute(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        actionMap.get("update").execute(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        actionMap.get("delete").execute(req, resp);
    }
}
