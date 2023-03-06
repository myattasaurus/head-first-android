package com.mattsploration.activitylifecycle;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class TimerLogicHandler extends Handler {

    public static final int PAUSE = 4;
    public static final int UNPAUSE = 5;
    public static final int CONTINUE = 6;

    private final TimerDisplayHandler timerDisplayHandler;

    private TimerData timerData;

    private boolean wasRunning;

    public TimerLogicHandler(TimerData timerData, TimerDisplayHandler timerDisplayHandler, Looper looper) {
        super(looper);
        this.timerData = timerData;
        this.timerDisplayHandler = timerDisplayHandler;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case CONTINUE:
                continueTimer();
                break;
            case PAUSE:
                pauseTimer();
                break;
            case UNPAUSE:
                unpauseTimer();
                break;
            default:
                break;
        }

    }

    public void run() {
        Message msg = Message.obtain();
        msg.what = CONTINUE;
        sendMessage(msg);
    }

    private void continueTimer() {
        if (timerData.isRunning()) {
            timerData.incrementOneSecond();
            timerDisplayHandler.sendMessage(timerData);

            Message msg = Message.obtain();
            msg.what = CONTINUE;
            sendMessageDelayed(msg, 1000);
        }
    }

    public void pause() {
        Message msg = Message.obtain();
        msg.what = PAUSE;
        sendMessage(msg);
    }

    private void pauseTimer() {
        wasRunning = timerData.isRunning();
        timerData.setRunning(false);
    }

    public void unpause() {
        Message msg = Message.obtain();
        msg.what = UNPAUSE;
        sendMessage(msg);
    }

    private void unpauseTimer() {
        timerData.setRunning(wasRunning);
        continueTimer();
    }
}
