package com.mattsploration.messaging;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CreateMessageActivity extends Activity {

    public static final String EXTRA_MESSAGE = "message";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);

        Button sendButton = findViewById(R.id.send);
        sendButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, ReceiveMessageActivity.class);
            intent.putExtra(EXTRA_MESSAGE, getMessage());
            startActivity(intent);
        });

        Button sendExternalButton = findViewById(R.id.sendExternal);
        sendExternalButton.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, getMessage());

            String chooserTitle = getString(R.string.chooser);
            Intent chosenIntent = Intent.createChooser(intent, chooserTitle);

            startActivity(chosenIntent);
        });
    }

    private String getMessage() {
        EditText messageText = findViewById(R.id.message);
        String message = messageText.getText().toString();
        return message;
    }
}