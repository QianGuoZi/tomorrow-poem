package com.example.tomorrowpoemapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

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
        tabbarFragment = TabbarFragment.newInstance(4);
        getSupportFragmentManager().beginTransaction().add(R.id.tabbar_fragment, tabbarFragment).commit();

        setChangeName();
        setShareAPP();
        setMakeAdvice();
        setSupportUs();
        setDeleteAccount();
        setLogout();
    }

    //登录
    private void getLogin(){


    }

    //注册
    private void getLogon(){

    }

    //退出登录
    private void setLogout(){
        SignOut= findViewById(R.id.sign_out);
        SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(UserActivity.this, UserLogInActivity.class);
                startActivity(intent);
            }
        });
    }

    //修改昵称
    private void setChangeName(){
        ChangeName= findViewById(R.id.button1);
//        Log.d("cardLayout", ChangeName.toString());

        ChangeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(UserActivity.this, ChangeNameActivity.class);
                startActivity(intent);
            }
        });
    }

    //分享APP
    private void setShareAPP(){
        ShareAPP = findViewById(R.id.button2);
        ShareAPP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserActivity.this,"分享成功！",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //反馈与意见
    private void setMakeAdvice(){
        MakeAdvice = findViewById(R.id.button3);
        MakeAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(UserActivity.this, MakeAdviceActivity.class);
                startActivity(intent);
            }
        });
    }

    //支持我们
    private void setSupportUs(){
        SupportUs = findViewById(R.id.button4);
        SupportUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(UserActivity.this, SupportUsActivity.class);
                startActivity(intent);
            }
        });
    }

    //
    private void setDeleteAccount(){
        //注销账户
        DeleteAccount = findViewById(R.id.button5);
        Log.d("cardLayout", DeleteAccount.toString());

        DeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(UserActivity.this, UserLogInActivity.class);
                startActivity(intent);
            }
        });
    }
}