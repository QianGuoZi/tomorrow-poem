package com.example.tomorrowpoemapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import org.json.JSONException;

public class UserActivity extends AppCompatActivity {
    private TabbarFragment tabbarFragment;
    private LinearLayout ChangeName;
    private LinearLayout ShareAPP;
    private LinearLayout MakeAdvice;
    private LinearLayout SupportUs;
    private LinearLayout DeleteAccount;
    private Button SignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        tabbarFragment = TabbarFragment.newInstance(1);
        getSupportFragmentManager().beginTransaction().add(R.id.tabbar_fragment, tabbarFragment).commit();

        //修改昵称按钮
        ChangeName= findViewById(R.id.button1);
        Log.d("cardLayout", ChangeName.toString());
        //到这了

        ChangeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(UserActivity.this, ChangeNameActivity.class);
                startActivity(intent);
            }
        });

        //分享APP
        ShareAPP = findViewById(R.id.button2);
        Log.d("cardLayout", ShareAPP.toString());
        //到这了

        //分享APP的界面不知道怎么写qaq
        ShareAPP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(UserActivity.this, ChangeNameActivity.class);
                startActivity(intent);
            }
        });

        //意见与反馈
        MakeAdvice = findViewById(R.id.button3);
        Log.d("cardLayout", MakeAdvice.toString());
        //到这了

        //分享APP的界面不知道怎么写qaq
        MakeAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(UserActivity.this, MakeAdviceActivity.class);
                startActivity(intent);
            }
        });

        //支持我们
        SupportUs = findViewById(R.id.button4);
        Log.d("cardLayout", SupportUs.toString());
        //到这了

        SupportUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(UserActivity.this, SupportUsActivity.class);
                startActivity(intent);
            }
        });

        //注销账户
        DeleteAccount = findViewById(R.id.button5);
        Log.d("cardLayout", DeleteAccount.toString());
        //到这了

        DeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(UserActivity.this, SupportUsActivity.class);
                startActivity(intent);
            }
        });


        //退出登录
        SignOut= findViewById(R.id.sign_out);
        Log.d("cardLayout", SignOut.toString());
        //到这了

        SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(UserActivity.this, UserLogInActivity.class);
                startActivity(intent);
            }
        });
    }
}
