package com.mattsploration.activitylifecycle.timer;

public class SecondsPublisher {

    private final SecondsListener[] listeners;

    private int seconds;

    public SecondsPublisher(SecondsListener... listeners) {
        this.listeners = listeners;
    }

    public int get() {
        return seconds;
    }

    public void set(int seconds) {
        this.seconds = seconds;
        for (SecondsListener listener : listeners) {
            listener.onSecondsUpdated(seconds);
        }
    }
}
