package com.mattsploration.activitylifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class StopwatchActivity extends Activity {

    private TimerData timerData;

    private TimerLogicHandler timerLogicHandler;

    private TimerDisplayHandler timerDisplayHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        initializeTimerData(savedInstanceState);
        initializeTimerDisplayHandler();
        initializeTimerLogicHandler();
        initializeStartButton();
        initializeStopButton();
        initializeResetButton();
    }

    private void initializeTimerData(Bundle savedInstanceState) {
        timerData = new TimerData(savedInstanceState);
    }

    private void initializeTimerDisplayHandler() {
        timerDisplayHandler = new TimerDisplayHandler(findViewById(R.id.time_view));
        timerDisplayHandler.sendMessage(timerData);
    }

    private void initializeTimerLogicHandler() {
        timerLogicHandler = new TimerLogicHandler(timerData, timerDisplayHandler, getMainLooper());
        timerLogicHandler.run();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        timerData.stateSave(savedInstanceState);
    }

    private void initializeStartButton() {
        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(view -> {
            timerData.setRunning(true);
            timerLogicHandler.run();
        });
    }

    private void initializeStopButton() {
        Button stopButton = findViewById(R.id.stopButton);
        stopButton.setOnClickListener(view -> {
            timerData.setRunning(false);
            timerDisplayHandler.sendMessage(timerData);
        });
    }

    private void initializeResetButton() {
        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(view -> {
            timerData.setRunning(false);
            timerData.resetTimeToZero();
            timerDisplayHandler.sendMessage(timerData);
        });
    }

}