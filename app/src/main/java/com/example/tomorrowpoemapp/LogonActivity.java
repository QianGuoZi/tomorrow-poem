package com.example.tomorrowpoemapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogonActivity extends AppCompatActivity {
    Button logon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon);

        logon = findViewById(R.id.logon_button);
        logon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(LogonActivity.this,UserActivity.class);
                startActivity(intent);
            }
        });

    }
}