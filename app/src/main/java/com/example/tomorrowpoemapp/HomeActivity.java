package com.example.tomorrowpoemapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends FragmentActivity {

    private TabbarFragment tabbarFragment;
    private CardFragment cardFragment;
    private Button jumpToDetail;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tabbarFragment=TabbarFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.tabbar_fragment, tabbarFragment).commit();

        cardFragment = CardFragment.newInstance("床前明月光\n疑是地上霜",3,true);
        getSupportFragmentManager().beginTransaction().add(R.id.card_fragment, cardFragment).commit();

        // 测试文本
//        jumpToDetail=findViewById(R.id.jump_to_detail);
//        jumpToDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(HomeActivity.this, DetailActivity.class);
//                startActivity(intent);
//            }
//        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView content =  findViewById(R.id.card_content);
        content.setTypeface(Typeface.createFromAsset(getAssets(),"simsun.ttc"));
        TextPaint tp = content.getPaint();
        tp.setFakeBoldText(true);
    }
}