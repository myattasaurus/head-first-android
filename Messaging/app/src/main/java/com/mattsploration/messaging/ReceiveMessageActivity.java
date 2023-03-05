package com.mattsploration.messaging;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiveMessageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_message);

        TextView receivedMessage = findViewById(R.id.receivedMessage);
        Intent intent = getIntent();
        receivedMessage.setText(intent.getStringExtra(CreateMessageActivity.EXTRA_MESSAGE));
    }
}