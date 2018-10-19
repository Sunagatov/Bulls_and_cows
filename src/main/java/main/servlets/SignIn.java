package main.servlets;

import main.dao.UserDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class SignIn extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String login = request.getParameter("login");
        final String password = request.getParameter("psw");
        UserDao dao = UserDao.getInstance();
        try {
            if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
                request.getSession().setAttribute("error", "Your authorization data are absent!");
                response.sendRedirect("index.jsp");
                return;
            }
            Long userId = dao.getIdByValidAuthorizationData(login, password);
            if (userId == null) {
                request.getSession().setAttribute("error", "Your authorization data is not valid!");
                response.sendRedirect("index.jsp");
                return;
            }
            request.getSession().setAttribute("userId", userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("mainPage.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }
}