package com.health.tracker;

import javax.ejb.Local;

@Local
public interface HealthGoalsSessionBeanLocal {
    void setWeightGoal(String userId, double target);
    void setStepsGoal(String userId, int target);
    void setSleepGoal(String userId, double target);
    boolean checkWeightGoal(String userId, double currentWeight);
    boolean checkStepsGoal(String userId, int currentSteps);
    boolean checkSleepGoal(String userId, double currentSleep);
    double getWeightGoal(String userId);
    int getStepsGoal(String userId);
    double getSleepGoal(String userId);
}