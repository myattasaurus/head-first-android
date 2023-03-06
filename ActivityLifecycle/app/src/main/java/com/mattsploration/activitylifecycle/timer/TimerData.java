package com.mattsploration.activitylifecycle.timer;

import android.os.Bundle;

public class TimerData {

    private static final String STATE_TOTAL_SECONDS = "timer.total.seconds";

    private static final String STATE_RUNNING = "timer.running";

    private final SecondsPublisher secondsPublisher;

    private boolean started = false;

    private boolean paused = false;

    public TimerData(Bundle savedInstanceState, SecondsPublisher secondsPublisher) {
        if (savedInstanceState != null) {
            secondsPublisher.set(savedInstanceState.getInt(STATE_TOTAL_SECONDS));
            started = savedInstanceState.getBoolean(STATE_RUNNING);
        } else {
            secondsPublisher.set(0);
        }
        this.secondsPublisher = secondsPublisher;
    }

    public void stateSave(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(STATE_RUNNING, started);
        savedInstanceState.putInt(STATE_TOTAL_SECONDS, secondsPublisher.get());
    }

    public void resetTimeToZero() {
        secondsPublisher.set(0);
    }

    public void incrementOneSecond() {
        secondsPublisher.set(secondsPublisher.get() + 1);
    }

    public boolean isRunning() {
        return started && !paused;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
