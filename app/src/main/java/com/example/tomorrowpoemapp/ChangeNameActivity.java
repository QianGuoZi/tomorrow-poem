package com.example.tomorrowpoemapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class ChangeNameActivity extends AppCompatActivity {
    private ImageView button_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);

        button_back = findViewById(R.id.back_button_change_name);
        Log.d("cardLayout", button_back.toString());
        //到这了

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ChangeNameActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });
    }
}