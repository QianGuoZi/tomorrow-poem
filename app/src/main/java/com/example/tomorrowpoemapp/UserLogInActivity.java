package com.example.tomorrowpoemapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserLogInActivity extends AppCompatActivity {

    TextView forget;
    TextView logon;
    Button longinButton;
    Activity userLoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_log_in);
        userLoginActivity = this;
        setForget();
        setLogon();
        setLogin();
    }

    private void setForget(){
        forget = findViewById(R.id.forget);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserLogInActivity.this,"哈哈，活该",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setLogon(){
        logon = findViewById(R.id.logon);
        logon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(UserLogInActivity.this,LogonActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setLogin(){
        longinButton = findViewById(R.id.login_button);
        longinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPassword();
                userLoginActivity.finish();
            }
        });
    }

    private void checkPassword(){

    }
}