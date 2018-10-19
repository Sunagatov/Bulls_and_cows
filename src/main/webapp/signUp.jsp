<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<%
    String error = (String) request.getSession().getAttribute("error");
    if (error != null) {
        request.getSession().removeAttribute("error");
    }
%>
<html>
<body>
<div class="header">
    <h1>Bulls and cows</h1>
</div>
<div class="main">
    <form action="SignUp" method="post">
        <div class="registrationForm">
            <h2>Registration form</h2>
            <hr id="line">
            <br>
            <% if (error != null) { %>
                <br>
                <div class="error">
                    <%=error%>
                </div>
            <%}%>
            <label>Nickname:</label>
            <br>
            <input maxlength="30" style = "width: 350px" type="text"  name="nickname">
            <br><br>
            <label>Login:</label>
            <br>
            <input maxlength="30" style = "width: 350px" type="text" name="login">
            <br><br>
            <label>Password:</label>
            <br>
            <input maxlength="30" style = "width: 350px" type="password" name="password">
            <br><br>
            <input style = "width: 120px" type="submit" value="Ok">
        </div>
    </form>
</div>
</body>
</html>