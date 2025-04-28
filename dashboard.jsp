<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.health.tracker.HealthMetricsSessionBeanLocal" %>
<%@page import="com.health.tracker.HealthGoalsSessionBeanLocal" %>
<%@page import="javax.naming.InitialContext" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Smart Health Tracker - Dashboard</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <h1>Health Dashboard</h1>
        
        <% 
            String userId = request.getParameter("userId");
            if (userId == null || userId.isEmpty()) {
                userId = (String) session.getAttribute("userId");
                if (userId == null || userId.isEmpty()) {
                    response.sendRedirect("index.jsp");
                    return;
                }
            } else {
                session.setAttribute("userId", userId);
            }
            
            String feedback = (String) request.getAttribute("feedback");
            if (feedback != null && !feedback.isEmpty()) {
        %>
        <div class="feedback">
            <%= feedback %>
        </div>
        <% } %>
        
        <div class="nav">
            <a href="dashboard.jsp">Dashboard</a>
            <a href="goals.jsp">Set Goals</a>
        </div>
        
        <%
            try {
                InitialContext ic = new InitialContext();
                HealthMetricsSessionBeanLocal metricsBean = (HealthMetricsSessionBeanLocal) 
                    ic.lookup("java:global/SmartHealthTracker/HealthMetricsSessionBean!com.health.tracker.HealthMetricsSessionBeanLocal");
                HealthGoalsSessionBeanLocal goalsBean = (HealthGoalsSessionBeanLocal) 
                    ic.lookup("java:global/SmartHealthTracker/HealthGoalsSessionBean!com.health.tracker.HealthGoalsSessionBeanLocal");
                
                double latestWeight = metricsBean.getLatestWeight(userId);
                int latestSteps = metricsBean.getLatestSteps(userId);
                double latestSleep = metricsBean.getLatestSleep(userId);
                
                double weightGoal = goalsBean.getWeightGoal(userId);
                int stepsGoal = goalsBean.getStepsGoal(userId);
                double sleepGoal = goalsBean.getSleepGoal(userId);
        %>
        
        <div class="metric-summary">
            <div class="metric-box">
                <h3>Weight</h3>
                <div class="metric-value"><%= latestWeight > 0 ? latestWeight + " kg" : "No data" %></div>
                <div class="metric-goal">Goal: <%= weightGoal > 0 ? weightGoal + " kg" : "Not set" %></div>
            </div>
            <div class="metric-box">
                <h3>Steps</h3>
                <div class="metric-value"><%= latestSteps > 0 ? latestSteps : "No data" %></div>
                <div class="metric-goal">Goal: <%= stepsGoal > 0 ? stepsGoal : "Not set" %></div>
            </div>
            <div class="metric-box">
                <h3>Sleep</h3>
                <div class="metric-value"><%= latestSleep > 0 ? latestSleep + " hrs" : "No data" %></div>
                <div class="metric-goal">Goal: <%= sleepGoal > 0 ? sleepGoal + " hrs" : "Not set" %></div>
            </div>
        </div>
        
        <% 
            } catch (Exception e) {
                // Handle exception
            }
        %>
        
        <h2>Record Today's Health Data</h2>
        
        <div class="card">
            <h3>Weight</h3>
            <form method="post" action="RecordHealthServlet">
                <input type="hidden" name="userId" value="<%= userId %>">
                <input type="hidden" name="action" value="weight">
                
                <label for="weight">Weight (kg):</label>
                <input type="number" id="weight" name="weight" step="0.1" required>
                
                <label for="weightDate">Date:</label>
                <input type="date" id="weightDate" name="date" required>
                
                <input type="submit" value="Record Weight">
            </form>
        </div>
        
        <div class="card">
            <h3>Steps</h3>
            <form method="post" action="RecordHealthServlet">
                <input type="hidden" name="userId" value="<%= userId %>">
                <input type="hidden" name="action" value="steps">
                
                <label for="steps">Steps Count:</label>
                <input type="number" id="steps" name="steps" required>
                
                <label for="stepsDate">Date:</label>
                <input type="date" id="stepsDate" name="date" required>
                
                <input type="submit" value="Record Steps">
            </form>
        </div>
        
        <div class="card">
            <h3>Sleep</h3>
            <form method="post" action="RecordHealthServlet">
                <input type="hidden" name="userId" value="<%= userId %>">
                <input type="hidden" name="action" value="sleep">
                
                <label for="sleep">Sleep Hours:</label>
                <input type="number" id="sleep" name="sleep" step="0.1" required>
                
                <label for="sleepDate">Date:</label>
                <input type="date" id="sleepDate" name="date" required>
                
                <input type="submit" value="Record Sleep">
            </form>
        </div>
    </body>
</html>