package com.social.media.controllers;

import com.social.media.model.User;
import com.social.media.service.UserService;
import com.social.media.util.ConnectionHelper;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        UserService userService = new UserService();
        String username = req.getParameter("username");
        String password = req.getParameter("password");


        try {
            connection = ConnectionHelper.openConnection();
            User user = userService.login(connection, username, password);
            if(user != null){
                req.getSession().setAttribute("currentUser",user);
                resp.sendRedirect("wall");
            }
            else {
                req.setAttribute("error", "user name or password is incorrect");

                // go to login page and display error message "Username or password or both is incorrect"
                req.getRequestDispatcher("/views/login.jsp").forward(req, resp);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }




    }
}
