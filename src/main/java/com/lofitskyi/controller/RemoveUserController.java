package com.lofitskyi.controller;

import com.lofitskyi.entity.User;
import com.lofitskyi.repository.PersistentException;
import com.lofitskyi.repository.UserDao;
import com.lofitskyi.repository.hibernate.HibernateUserDao;
import com.lofitskyi.repository.jdbc.JdbcUserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/del")
public class RemoveUserController extends HttpServlet{

    private UserDao dao = new HibernateUserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        Long id = Long.valueOf(req.getParameter("id"));

        User user = new User();
        user.setId(id);

        try {
            dao.removeById(user);
        } catch (PersistentException e) {
            out.println("<html><body onload=\"alert('Something went wrong. Try again')\"><a href=\"home.jsp\">Home page</a></body></html>");
            return;
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin");
        dispatcher.forward(req, resp);
    }
}
