package com.mattsploration.activitylifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import com.mattsploration.activitylifecycle.timer.SecondsListener;
import com.mattsploration.activitylifecycle.timer.SecondsPublisher;
import com.mattsploration.activitylifecycle.timer.TimerData;

public class StopwatchActivity extends Activity {

    private TimerData timerData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        initializeTimerData(savedInstanceState);
        initializeStartButton();
        initializeStopButton();
        initializeResetButton();
        initializeTimerHandler();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        timerData.setPaused(false);
    }

    @Override
    public void onPause() {
        super.onPause();
        timerData.setPaused(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        timerData.setPaused(true);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        timerData.stateSave(savedInstanceState);
    }

    private void initializeTimerData(Bundle savedInstanceState) {
        TextView timeView = findViewById(R.id.time_view);
        SecondsListener timeViewSecondsListener = new TextViewDisplaySecondsListener(timeView);
        SecondsPublisher secondsPublisher = new SecondsPublisher(timeViewSecondsListener);
        timerData = new TimerData(savedInstanceState, secondsPublisher);
    }

    private void initializeStartButton() {
        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(view -> {
            timerData.setStarted(true);
        });
    }

    private void initializeStopButton() {
        Button stopButton = findViewById(R.id.stopButton);
        stopButton.setOnClickListener(view -> {
            timerData.setStarted(false);
        });
    }

    private void initializeResetButton() {
        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(view -> {
            timerData.setStarted(false);
            timerData.resetTimeToZero();
        });
    }

    private void initializeTimerHandler() {
        Handler timerHandler = new Handler(getMainLooper());
        timerHandler.post(new Runnable() {
            @Override
            public void run() {
                if (timerData.isRunning()) {
                    timerData.incrementOneSecond();
                }
                timerHandler.postDelayed(this, 1000);
            }
        });
    }

}