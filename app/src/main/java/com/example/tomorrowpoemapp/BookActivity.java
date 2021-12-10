package com.example.tomorrowpoemapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;


public class BookActivity extends AppCompatActivity {

    private TabbarFragment tabbarFragment;
    private SearchResultFragment searchResultFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        tabbarFragment=TabbarFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.tabbar_fragment, tabbarFragment).commit();

        searchResultFragment = SearchResultFragment.newInstance("静夜思","李白",3);
        getSupportFragmentManager().beginTransaction().add(R.id.search_result_fragment, searchResultFragment).commit();
    }
}