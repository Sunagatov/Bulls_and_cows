<%@ page import="main.models.User" %>
<%@ page import="java.util.List" %>
<%@ page import="main.dao.UserDao" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="com.google.common.collect.ComparisonChain" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.math.RoundingMode" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<%
    Long userId = (Long) session.getAttribute("userId");
    if (userId == null) {
        response.sendRedirect("index.jsp");
    }
%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/mainStyle.css">
</head>
<body>
<div class="header">
    <h1>Bulls and cows</h1>
</div>
<div class="main">
    <h2>Welcome to the game!</h2>
    <h3>The description of the game rules:</h3>
    <p>Bulls and cows is an old code-breaking mind or paper and pencil game for two or more players, predating the
        commercially marketed board game Mastermind.<br>
        It is a game that may date back a century or more which uses numbers or words. It is played by two
        opponents.<br>
    <p> The numerical version of the game is usually played with 4 digits, but can also be played with 3 or any other
        number of digits.<br>
        On a sheet of paper, the players each write a 4-digit secret number. The digits must be all different. Then, in
        turn, the players try to guess their opponent's number who gives the number of matches. If the matching digits
        are in their right positions, they are "bulls", if in different positions, they are "cows". Example:<br>
    <p>
    <p>Secret number: 4271</p>
    <p>Opponent's try: 1234</p>
    <p>Answer: 1 bull and 2 cows. (The bull is "2", the cows are "4" and "1".)<br>
        The first one to reveal the other's secret number wins the game. As the first player has a logical advantage,
        the game can be balanced over multiple games by alternating the right to go first, or over a single game by
        granting the second player an equal number of guesses, possibly resulting in a tie.<br>
        The game may also be played by two teams of 2–3 players, with the team members discussing their strategy before
        selecting a move.</p>

    <h3>Try to beat other players!</h3>
    <h4>Click new game to start</h4>
    <div class="butt">
        <input type="button" value="New game" onclick="redirect('gamePage.jsp')">
    </div>
    <br>
    <h4>Click exit to exit from system</h4>
    <div class="butt2">
        <input type="button" value="Exit" onclick="redirect('index.jsp')">
    </div>
    <% session.setAttribute("game", null);
        UserDao dao = UserDao.getInstance();
        List<User> users = null;
        try {
            users = dao.getAllUsers();
            users.sort(new Comparator<User>() {
                @Override
                public int compare(User firstUser, User secondUser) {
                    return ComparisonChain.
                            start().
                            compare(secondUser.getGameStatistics().getGamesCount(), firstUser.getGameStatistics().getGamesCount()).
                            compare(firstUser.getGameStatistics().getAverageAttemptsCount(), secondUser.getGameStatistics().getAverageAttemptsCount()).
                            result();
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (users != null && !users.isEmpty()) {
            out.println("<h3>All registered users and their ratings:</h3>");
            out.println("<table>");
            out.println("            <tr>\n" +
                    "                <th>Place</th>\n" +
                    "                <th>User</th>\n" +
                    "                <th>Games count</th>\n" +
                    "                <th>Average attempts count</th>\n" +
                    "            </tr>");

            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.CEILING);
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                out.println("<tr>" +
                        "<td>" + (i + 1) + "</td>" +
                        "<td>" + user.getGameNick() + "</td>" +
                        "<td>" + user.getGameStatistics().getGamesCount() + "</td>" +
                        "<td>" + df.format(user.getGameStatistics().getAverageAttemptsCount()) + "</td>" +
                        "</tr>");
            }
            out.println("</table>");
        }%>
</div>
</body>
</html>
<script>
    function redirect(page) {
        window.location = page;
    }
</script>