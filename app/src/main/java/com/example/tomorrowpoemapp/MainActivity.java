package com.example.tomorrowpoemapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout mainActivity;
    private TextView tomorrowPoemTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tomorrowPoemTitle=findViewById(R.id.tomorrow_poem_title);
        tomorrowPoemTitle.setTypeface(Typeface.createFromAsset(getAssets(),"simsun.ttc"));

        delay();

//        mainActivity=findViewById(R.id.main_activity);
//        mainActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(MainActivity.this, BookActivity.class);
//                startActivity(intent);
//            }
//        });

    }

    //延时三秒
    private void delay(){
//        System.out.println("---------->"+"AAAAAAAAA");
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                /**
                 *要执行的操作
                 */
                change();
//                System.out.println("---------->"+"我是TimerTask的延迟操作");
//                postdelay();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 2000);//3秒后执行TimeTask的run方法
    }

    public void change(){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}