<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Smart Health Tracker - Login</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <h1>Smart Health Tracker</h1>
        <div class="card">
            <h2>Login</h2>
            <form method="post" action="dashboard.jsp">
                <label for="userId">User ID:</label>
                <input type="text" id="userId" name="userId" required>
                <input type="submit" value="Login">
            </form>
            <p>No account? Just enter your desired user ID to get started!</p>
        </div>
    </body>
</html>