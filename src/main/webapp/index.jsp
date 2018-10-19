<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String error = (String) request.getSession().getAttribute("error");
    if (error != null) {
        request.getSession().removeAttribute("error");
    }
    session.setAttribute("userId", null);
%>
<!DOCTYPE html>
<html>
<body>
<div class="header">
    <h1>Bulls and cows</h1>
</div>
<div class="main">
    <div class="container">
        <form action="SignIn" method="post">
            <h2>Please, introduce yourself to the system</h2>
            <br>
            <% if (error != null) { %>
            <div class="error">
                <%=error%>
            </div>
            <%}%>
            <br>
            <label><b>Login:</b></label>
            <br>
            <input style = "width: 350px; "  maxlength="30" type="text" name="login">
            <br><br>
            <label><b>Password:</b></label>
            <br>
            <input style = "width: 350px" type="password" maxlength="30"  name="psw">
            <br><br>
            <button style = "width: 120px; padding-right: 30px;" type="submit">Sign in</button>
            <button style = "width: 120px" type="button" onclick="redirect('signUp.jsp')">Sign up</button>
        </form>
    </div>
</div>
</body>
</html>
<script>
    function redirect(page) {
        window.location = page;
    }
</script>