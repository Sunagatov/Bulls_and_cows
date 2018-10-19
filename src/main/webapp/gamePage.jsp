<%@ page import="main.models.Game" %>
<%@ page import="main.models.Attempt" %>
<%@ page import="java.util.List" %>
<%@ page import="main.dao.UserDao" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
    Long userId = (Long) session.getAttribute("userId");
    if (userId == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    String error = (String) request.getSession().getAttribute("error");
    if (error != null) {
        request.getSession().removeAttribute("error");
    }
    Game game = (Game) session.getAttribute("game");
    if (game == null) {
        game = new Game();
        session.setAttribute("game", game);
    }
%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/mainStyle.css">
</head>
<head><title>Bull and cows</title></head>
<body>
<fieldset>
    <h3>Become the best player!</h3>
    <% if (error != null) { %>
    <div class="error">
        <%=error%>
    </div>
    <% } %>
    <%
        if (!game.isWon()) {
            out.println("    <form action=\"GameController\" method=\"post\">\n" +
                    "        <b>Enter the new number:</b>\n" +
                    "        <select name=\"0\">\n" +
                    "            <option value=\"0\">0</option>\n" +
                    "            <option value=\"1\">1</option>\n" +
                    "            <option value=\"2\">2</option>\n" +
                    "            <option value=\"3\">3</option>\n" +
                    "            <option value=\"4\">4</option>\n" +
                    "            <option value=\"5\">5</option>\n" +
                    "            <option value=\"6\">6</option>\n" +
                    "            <option value=\"7\">7</option>\n" +
                    "            <option value=\"8\">8</option>\n" +
                    "            <option value=\"9\">9</option>\n" +
                    "        </select>\n" +
                    "        <select name=\"1\">\n" +
                    "            <option value=\"0\">0</option>\n" +
                    "            <option value=\"1\">1</option>\n" +
                    "            <option value=\"2\">2</option>\n" +
                    "            <option value=\"3\">3</option>\n" +
                    "            <option value=\"4\">4</option>\n" +
                    "            <option value=\"5\">5</option>\n" +
                    "            <option value=\"6\">6</option>\n" +
                    "            <option value=\"7\">7</option>\n" +
                    "            <option value=\"8\">8</option>\n" +
                    "            <option value=\"9\">9</option>\n" +
                    "        </select>\n" +
                    "        <select name=\"2\">\n" +
                    "            <option value=\"0\">0</option>\n" +
                    "            <option value=\"1\">1</option>\n" +
                    "            <option value=\"2\">2</option>\n" +
                    "            <option value=\"3\">3</option>\n" +
                    "            <option value=\"4\">4</option>\n" +
                    "            <option value=\"5\">5</option>\n" +
                    "            <option value=\"6\">6</option>\n" +
                    "            <option value=\"7\">7</option>\n" +
                    "            <option value=\"8\">8</option>\n" +
                    "            <option value=\"9\">9</option>\n" +
                    "        </select>\n" +
                    "        <select name=\"3\">\n" +
                    "            <option value=\"0\">0</option>\n" +
                    "            <option value=\"1\">1</option>\n" +
                    "            <option value=\"2\">2</option>\n" +
                    "            <option value=\"3\">3</option>\n" +
                    "            <option value=\"4\">4</option>\n" +
                    "            <option value=\"5\">5</option>\n" +
                    "            <option value=\"6\">6</option>\n" +
                    "            <option value=\"7\">7</option>\n" +
                    "            <option value=\"8\">8</option>\n" +
                    "            <option value=\"9\">9</option>\n" +
                    "        </select>\n" +
                    "<input style = \"width: 120px\" type=\"submit\" name=\"move\" value=\"Make a move\">" +
                    "    </form>");
        }
    %>
    <p class="submit"><input style = "width: 120px" type="button" name="move" value="Finish" onclick="redirect('mainPage.jsp')"></p>
    <%
        List<Attempt> attempts = game.getAttempts();
        if (!attempts.isEmpty()) {
            out.println("<h3>Your previous attempts:</h3>\n" +
                    "        <table>\n" +
                    "            <tr>\n" +
                    "                <th>Move</th>\n" +
                    "                <th>Number</th>\n" +
                    "                <th>Bulls</th>\n" +
                    "                <th>Cows</th>\n" +
                    "            </tr>");
            for (int i = 0; i < attempts.size(); i++) {
                Attempt attempt = attempts.get(i);
                out.println(
                        "<tr>" +
                                "<td>" + (i + 1) + "</td>" +
                                "<td>" + attempt.getSupposedDigits() + "</td>" +
                                "<td>" + attempt.getBullsCount() + "</td>" +
                                "<td>" + attempt.getCowsCount() + "</td>" +
                                "</tr>"
                );
            }
            if (game.isWon) {
                session.setAttribute("game", null);
                try {
                    UserDao dao = UserDao.getInstance();
                    dao.updateGameStatistics(userId, game.getAttemptsCount());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                out.println("Congratulations! You are a winner!");
                out.println("");
                out.println("</table>");
                out.println("<form action=\"GameController\" method=\"post\" style=\"display: block\" id=\"myDIV\">");
            }
            out.println("</table>");
            out.println("<form action=\"GameController\" method=\"post\" style=\"display: none\" id=\"myDIV\">");
        }
    %>
</fieldset>
</body>
</html>
<script>
    function redirect(page) {
        window.location = page;
    }
</script>