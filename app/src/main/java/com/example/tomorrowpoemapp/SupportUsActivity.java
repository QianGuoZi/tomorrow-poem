package com.example.tomorrowpoemapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class SupportUsActivity extends AppCompatActivity {
    private ImageView button_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_us);

        button_back = findViewById(R.id.back_button_support_us);
        Log.d("cardLayout", button_back.toString());
        //到这了

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(SupportUsActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });
    }
}