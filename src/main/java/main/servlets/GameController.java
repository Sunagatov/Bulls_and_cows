package main.servlets;

import main.models.Game;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Integer> supposedDigits = new ArrayList<>(4);
        supposedDigits.add(Integer.parseInt(request.getParameter("0")));
        supposedDigits.add(Integer.parseInt(request.getParameter("1")));
        supposedDigits.add(Integer.parseInt(request.getParameter("2")));
        supposedDigits.add(Integer.parseInt(request.getParameter("3")));
        if (supposedDigits.isEmpty()) {
            request.getSession().setAttribute("error", "A supposed number is not guessed!");
        } else if (!isDigitsInNumberUnique(supposedDigits)) {
            request.getSession().setAttribute("error", "A supposed number has not unique digits!");
        } else {
            Game game = (Game) request.getSession().getAttribute("game");
            if (game!= null) {
                game.addAttempt(supposedDigits);
            }
        }
        response.sendRedirect("gamePage.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    private boolean isDigitsInNumberUnique(List<Integer> digits) {
        for (int i = 0; i < digits.size(); i++) {
            for (int j = i + 1; j < digits.size(); j++) {
                if (Objects.equals(digits.get(i), digits.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}