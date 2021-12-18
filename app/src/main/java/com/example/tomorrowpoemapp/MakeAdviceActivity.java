package com.example.tomorrowpoemapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MakeAdviceActivity extends AppCompatActivity {
    private ImageView button_back2;

    private Activity makeAdviceActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_advice);
        makeAdviceActivity = this;

        button_back2 = findViewById(R.id.back_button_make_advice);
        Log.d("cardLayout", button_back2.toString());
        //到这了

        button_back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeAdviceActivity.finish();
            }
        });
    }
}