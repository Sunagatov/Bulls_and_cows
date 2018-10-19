package main.servlets;

import main.dao.UserDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class SignUp extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String gameNick = request.getParameter("nickname");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String error = validateData(gameNick, login, password);
        if (error != null) {
            request.getSession().setAttribute("error", error);
            response.sendRedirect("signUp.jsp");
            return;
        }
        Long userId;
        try {
            UserDao dao = UserDao.getInstance();
            userId = dao.storeUser(gameNick, login, password);
        } catch (Exception e) {
            request.getSession().setAttribute("error", "Error!");
            response.sendRedirect("signUp.jsp");
            return;
        }
        request.getSession().setAttribute("userId", userId);
        response.sendRedirect("mainPage.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    }


    private String validateData(String gameNick, String login, String password) {
        if (gameNick == null || gameNick.isEmpty() || login == null || login.isEmpty() || password == null || password.isEmpty()) {
            return "Some of fields are empty!";
        }
        UserDao dao = UserDao.getInstance();
        try {
            if (!dao.isNicknameUnique(gameNick) || !dao.isLoginUnique(login)) {
                return "Your entered nickname or login is not unique!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}