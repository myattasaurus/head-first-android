package com.mattsploration.activitylifecycle;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.util.Locale;

public class TimerDisplayHandler extends Handler {

    private final TextView timeView;

    public TimerDisplayHandler(TextView timeView) {
        this.timeView = timeView;
    }

    @Override
    public void handleMessage(Message msg) {
        TimerData timerData = (TimerData) msg.obj;
        String timeDisplay = String.format(Locale.getDefault(),
                "%d:%02d:%02d",
                timerData.getHours(),
                timerData.getMinutes(),
                timerData.getSeconds());

        timeView.setText(timeDisplay);
    }

    public void sendMessage(TimerData timerData) {
        Message msg = Message.obtain();
        msg.obj = timerData;
        sendMessageAtFrontOfQueue(msg);
    }
}
