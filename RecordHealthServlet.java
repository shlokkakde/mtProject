package com.health.tracker;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RecordHealthServlet", urlPatterns = {"/RecordHealthServlet"})
public class RecordHealthServlet extends HttpServlet {
    @EJB
    private HealthMetricsSessionBeanLocal healthMetricsBean;
    
    @EJB
    private HealthGoalsSessionBeanLocal healthGoalsBean;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String userId = request.getParameter("userId");
        String action = request.getParameter("action");
        String date = request.getParameter("date");
        String feedback = "";
        
        try {
            if ("weight".equals(action)) {
                double weight = Double.parseDouble(request.getParameter("weight"));
                healthMetricsBean.recordWeight(userId, weight, date);
                
                boolean goalMet = healthGoalsBean.checkWeightGoal(userId, weight);
                feedback = goalMet ? "Great job! You've met your weight goal." : 
                                    "Keep working toward your weight goal!";
            } 
            else if ("steps".equals(action)) {
                int steps = Integer.parseInt(request.getParameter("steps"));
                healthMetricsBean.recordSteps(userId, steps, date);
                
                boolean goalMet = healthGoalsBean.checkStepsGoal(userId, steps);
                feedback = goalMet ? "Congratulations! You've reached your step goal." : 
                                    "Keep moving to reach your step goal!";
            } 
            else if ("sleep".equals(action)) {
                double sleep = Double.parseDouble(request.getParameter("sleep"));
                healthMetricsBean.recordSleep(userId, sleep, date);
                
                boolean goalMet = healthGoalsBean.checkSleepGoal(userId, sleep);
                feedback = goalMet ? "Great! You've met your sleep goal." : 
                                    "Try to get more rest to meet your sleep goal!";
            }
            
            request.setAttribute("feedback", feedback);
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error recording health data: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}