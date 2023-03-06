package com.mattsploration.activitylifecycle;

import android.os.Bundle;

public class TimerData {

    private static final String STATE_TOTAL_SECONDS = "timer.total.seconds";

    private static final String STATE_RUNNING = "timer.running";

    private int totalSeconds = 0;

    private boolean running = false;

    public TimerData(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            totalSeconds = savedInstanceState.getInt(STATE_TOTAL_SECONDS);
            running = savedInstanceState.getBoolean(STATE_RUNNING);
        }
    }

    public void stateSave(Bundle savedInstanceState) {
        savedInstanceState.putInt(STATE_TOTAL_SECONDS, totalSeconds);
        savedInstanceState.putBoolean(STATE_RUNNING, running);
    }

    public void resetTimeToZero() {
        totalSeconds = 0;
    }

    public void incrementOneSecond() {
        totalSeconds++;
    }

    public int getSeconds() {
        return totalSeconds % 60;
    }

    public int getMinutes() {
        return (totalSeconds / 60) % 60;
    }

    public int getHours() {
        return totalSeconds / (60 * 60);
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
