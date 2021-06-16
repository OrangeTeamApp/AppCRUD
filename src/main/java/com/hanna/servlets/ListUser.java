package com.hanna.servlets;
import com.hanna.dao.JdbcUserDao;
import com.hanna.dao.UserDao;
import com.hanna.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class ListUser extends HttpServlet {
    private UserDao userDao = new JdbcUserDao();

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<User> users =  userDao.findAll();
        request.setAttribute("userList", users);
        request.getRequestDispatcher("/jsp/userList.jsp").forward(request, response);
    }
}
