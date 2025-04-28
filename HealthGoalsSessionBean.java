package com.health.tracker;

import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;

@Stateless
public class HealthGoalsSessionBean implements HealthGoalsSessionBeanLocal {
    // In a real application, this would use database storage
    private final Map<String, Double> weightGoals = new HashMap<>();
    private final Map<String, Integer> stepsGoals = new HashMap<>();
    private final Map<String, Double> sleepGoals = new HashMap<>();
    
    @Override
    public void setWeightGoal(String userId, double target) {
        weightGoals.put(userId, target);
    }
    
    @Override
    public void setStepsGoal(String userId, int target) {
        stepsGoals.put(userId, target);
    }
    
    @Override
    public void setSleepGoal(String userId, double target) {
        sleepGoals.put(userId, target);
    }
    
    @Override
    public boolean checkWeightGoal(String userId, double currentWeight) {
        if (!weightGoals.containsKey(userId)) {
            return false;
        }
        return currentWeight <= weightGoals.get(userId);
    }
    
    @Override
    public boolean checkStepsGoal(String userId, int currentSteps) {
        if (!stepsGoals.containsKey(userId)) {
            return false;
        }
        return currentSteps >= stepsGoals.get(userId);
    }
    
    @Override
    public boolean checkSleepGoal(String userId, double currentSleep) {
        if (!sleepGoals.containsKey(userId)) {
            return false;
        }
        return currentSleep >= sleepGoals.get(userId);
    }
    
    @Override
    public double getWeightGoal(String userId) {
        return weightGoals.getOrDefault(userId, 0.0);
    }
    
    @Override
    public int getStepsGoal(String userId) {
        return stepsGoals.getOrDefault(userId, 0);
    }
    
    @Override
    public double getSleepGoal(String userId) {
        return sleepGoals.getOrDefault(userId, 0.0);
    }
}