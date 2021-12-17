package com.example.tomorrowpoemapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchActivity extends AppCompatActivity {

    private ImageView backButton;
    private Button searchButton;
    private EditText editTextSearch;
    private Activity searchActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchActivity = this;
        setContentView(R.layout.activity_search);
        setBack();
        setSearch();
    }


    private void setBack(){
        backButton = (ImageView) findViewById(R.id.back_button_search);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //结束当前Activity
                searchActivity.finish();
            }
        });
    }


    private void setSearch(){
        editTextSearch = findViewById(R.id.editTextSearch);
        searchButton = findViewById(R.id.search_sure_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editTextSearch.getText().toString();
                Intent intent = new Intent();
                intent.setClass(SearchActivity.this, DetailActivity.class);
                intent.putExtra("text",text);
                Log.d("text",text.toString());
                startActivity(intent);
            }
        });
    }
}

