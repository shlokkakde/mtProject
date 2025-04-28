package com.health.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;

@Stateless
public class HealthMetricsSessionBean implements HealthMetricsSessionBeanLocal {
    // In a real application, this would use database storage
    private final Map<String, List<Double>> weightData = new HashMap<>();
    private final Map<String, List<Integer>> stepsData = new HashMap<>();
    private final Map<String, List<Double>> sleepData = new HashMap<>();
    
    @Override
    public void recordWeight(String userId, double weight, String date) {
        if (!weightData.containsKey(userId)) {
            weightData.put(userId, new ArrayList<>());
        }
        weightData.get(userId).add(weight);
    }
    
    @Override
    public void recordSteps(String userId, int steps, String date) {
        if (!stepsData.containsKey(userId)) {
            stepsData.put(userId, new ArrayList<>());
        }
        stepsData.get(userId).add(steps);
    }
    
    @Override
    public void recordSleep(String userId, double hours, String date) {
        if (!sleepData.containsKey(userId)) {
            sleepData.put(userId, new ArrayList<>());
        }
        sleepData.get(userId).add(hours);
    }
    
    @Override
    public List<Double> getWeightHistory(String userId) {
        return weightData.getOrDefault(userId, new ArrayList<>());
    }
    
    @Override
    public List<Integer> getStepsHistory(String userId) {
        return stepsData.getOrDefault(userId, new ArrayList<>());
    }
    
    @Override
    public List<Double> getSleepHistory(String userId) {
        return sleepData.getOrDefault(userId, new ArrayList<>());
    }
    
    @Override
    public double getLatestWeight(String userId) {
        List<Double> weights = weightData.get(userId);
        if (weights == null || weights.isEmpty()) {
            return 0.0;
        }
        return weights.get(weights.size() - 1);
    }
    
    @Override
    public int getLatestSteps(String userId) {
        List<Integer> steps = stepsData.get(userId);
        if (steps == null || steps.isEmpty()) {
            return 0;
        }
        return steps.get(steps.size() - 1);
    }
    
    @Override
    public double getLatestSleep(String userId) {
        List<Double> sleep = sleepData.get(userId);
        if (sleep == null || sleep.isEmpty()) {
            return 0.0;
        }
        return sleep.get(sleep.size() - 1);
    }
}