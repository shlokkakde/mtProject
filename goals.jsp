<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Smart Health Tracker - Set Goals</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                max-width: 800px;
                margin: 0 auto;
                padding: 20px;
            }
            h1, h2 {
                color: #3498db;
            }
            .message {
                background-color: #d4edda;
                color: #155724;
                padding: 10px;
                border-radius: 5px;
                margin-bottom: 20px;
            }
            .card {
                background-color: #f9f9f9;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
                margin-bottom: 20px;
            }
            form {
                margin-bottom: 10px;
            }
            label {
                display: block;
                margin-bottom: 8px;
            }
            input[type="number"] {
                width: 100%;
                padding: 8px;
                margin-bottom: 10px;
                box-sizing: border-box;
                border: 1px solid #ddd;
                border-radius: 5px;
            }
            input[type="submit"] {
                background-color: #3498db;
                color: white;
                padding: 8px 16px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }
            input[type="submit"]:hover {
                background-color: #2980b9;
            }
            .nav {
                display: flex;
                justify-content: space-between;
                margin-bottom: 20px;
            }
            .nav a {
                background-color: #3498db;
                color: white;
                padding: 10px 15px;
                text-decoration: none;
                border-radius: 5px;
            }
            .nav a:hover {
                background-color: #2980b9;
            }
        </style>
    </head>
    <body>
        <h1>Set Health Goals</h1>
        
        <% 
            String userId = (String) session.getAttribute("userId");
            if (userId == null || userId.isEmpty()) {
                response.sendRedirect("index.jsp");
                return;
            }
            
            String message = (String) request.getAttribute("message");
            if (message != null && !message.isEmpty()) {
        %>
        <div class="message">
            <%= message %>
        </div>
        <% } %>
        
        <div class="nav">
            <a href="dashboard.jsp">Dashboard</a>
            <a href="goals.jsp">Set Goals</a>
            <a href="history.jsp">View History</a>
        </div>
        
        <h2>Set Your Health Goals</h2>
        
        <div class="card">
            <h3>Weight Goal</h3>
            <form method="post" action="SetGoalServlet">
                <input type="hidden" name="userId" value="<%= userId %>">
                <input type="hidden" name="goalType" value="weight">
                
                <label for="weightGoal">Target Weight (kg):</label>
                <input type="number" id="weightGoal" name="weightGoal" step="0.1" required>
                
                <input type="submit" value="Set Weight Goal">
            </form>
        </div>
        
        <div class="card">
            <h3>Daily Steps Goal</h3>
            <form method="post" action="SetGoalServlet">
                <input type="hidden" name="userId" value="<%= userId %>">
                <input type="hidden" name="goalType" value="steps">
                
                <label for="stepsGoal">Target Steps:</label>
                <input type="number" id="stepsGoal" name="stepsGoal" required>
                
                <input type="submit" value="Set Steps Goal">
            </form>
        </div>
        
        <div class="card">
            <h3>Sleep Goal</h3>
            <form method="post" action="SetGoalServlet">
                <input type="hidden" name="userId" value="<%= userId %>">
                <input type="hidden" name="goalType" value="sleep">
                
                <label for="sleepGoal">Target Sleep Hours:</label>
                <input type="number" id="sleepGoal" name="sleepGoal" step="0.1" required>
                
                <input type="submit" value="Set Sleep Goal">
            </form>
        </div>
    </body>
</html>