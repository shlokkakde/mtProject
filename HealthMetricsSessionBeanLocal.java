package com.health.tracker;

import java.util.List;
import javax.ejb.Local;

@Local
public interface HealthMetricsSessionBeanLocal {
    void recordWeight(String userId, double weight, String date);
    void recordSteps(String userId, int steps, String date);
    void recordSleep(String userId, double hours, String date);
    List<Double> getWeightHistory(String userId);
    List<Integer> getStepsHistory(String userId);
    List<Double> getSleepHistory(String userId);
    double getLatestWeight(String userId);
    int getLatestSteps(String userId);
    double getLatestSleep(String userId);
}