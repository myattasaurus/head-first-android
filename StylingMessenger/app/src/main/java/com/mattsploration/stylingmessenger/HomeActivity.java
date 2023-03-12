package com.mattsploration.stylingmessenger;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button messengerButton = findViewById(R.id.btn_messenger);
        messengerButton.setOnClickListener(view -> {
            Intent goToMessenger = new Intent(this, MessengerActivity.class);
            startActivity(goToMessenger);
        });

        Button duckButton = findViewById(R.id.btn_duck);
        duckButton.setOnClickListener(view -> {
            Intent goToMessenger = new Intent(this, DuckActivity.class);
            startActivity(goToMessenger);
        });
    }
}