package com.example.tomorrowpoemapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class SupportUsActivity extends AppCompatActivity {
    private ImageView button_back;
    private Activity supportUsActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_us);
        supportUsActivity = this;

        button_back = findViewById(R.id.back_button_support_us);
        Log.d("cardLayout", button_back.toString());
        //到这了

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                supportUsActivity.finish();
            }
        });
    }
}