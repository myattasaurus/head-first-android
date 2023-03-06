package com.mattsploration.activitylifecycle;

import android.widget.TextView;

import com.mattsploration.activitylifecycle.timer.SecondsListener;

import java.util.Locale;

public class TextViewDisplaySecondsListener implements SecondsListener {

    private final TextView textView;

    public TextViewDisplaySecondsListener(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void onSecondsUpdated(int seconds) {
        int hrs = getHours(seconds);
        int mins = getMinutes(seconds);
        int secs = getSeconds(seconds);

        String timeDisplay = getTimeDisplay(hrs, mins, secs);

        textView.setText(timeDisplay);
    }

    private String getTimeDisplay(int hrs, int mins, int secs) {
        if (hrs > 0) {
            return String.format(Locale.getDefault(), "%d:%02d:%02d", hrs, mins, secs);
        } else if (mins > 0) {
            return String.format(Locale.getDefault(), "%d:%02d", mins, secs);
        } else {
            return String.format(Locale.getDefault(), "%d", secs);
        }
    }

    private int getSeconds(int totalSeconds) {
        return totalSeconds % 60;
    }

    private int getMinutes(int totalSeconds) {
        return (totalSeconds / 60) % 60;
    }

    private int getHours(int totalSeconds) {
        return totalSeconds / (60 * 60);
    }
}
