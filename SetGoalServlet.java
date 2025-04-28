package com.health.tracker;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SetGoalServlet", urlPatterns = {"/SetGoalServlet"})
public class SetGoalServlet extends HttpServlet {
    @EJB
    private HealthGoalsSessionBeanLocal healthGoalsBean;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String userId = request.getParameter("userId");
        String goalType = request.getParameter("goalType");
        
        try {
            if ("weight".equals(goalType)) {
                double weightGoal = Double.parseDouble(request.getParameter("weightGoal"));
                healthGoalsBean.setWeightGoal(userId, weightGoal);
            } 
            else if ("steps".equals(goalType)) {
                int stepsGoal = Integer.parseInt(request.getParameter("stepsGoal"));
                healthGoalsBean.setStepsGoal(userId, stepsGoal);
            } 
            else if ("sleep".equals(goalType)) {
                double sleepGoal = Double.parseDouble(request.getParameter("sleepGoal"));
                healthGoalsBean.setSleepGoal(userId, sleepGoal);
            }
            
            request.setAttribute("message", goalType + " goal has been set successfully!");
            request.getRequestDispatcher("goals.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error setting goal: " + e.getMessage());
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