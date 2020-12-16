package com.assignment.log_parser.parsing;

public class LogTimeModel {
    private Float minTime;
    private Float maxTime;
    private Float count;
    private Float totalTime;

    public LogTimeModel(Float time) {
        this.minTime = time;
        this.maxTime = time;
        this.totalTime = time;
        this.count = 1.0f;
    }

    @Override
    public String toString() {
        return "LogTimeModel{" +
                "minTime=" + minTime +
                ", maxTime=" + maxTime +
                ", avgTime=" + count +
                ", totalTime=" + totalTime +
                '}';
    }

    public Float getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Float totalTime) {
        this.totalTime = totalTime;
    }

    public Float getMinTime() {
        return minTime;
    }

    public void setMinTime(Float minTime) {
        this.minTime = minTime;
    }

    public Float getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Float maxTime) {
        this.maxTime = maxTime;
    }

    public Float getCount() {
        return count;
    }

    public void setCount(Float count) {
        this.count = count;
    }
}
