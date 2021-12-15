package com.example.tomorrowpoemapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class BookActivity extends AppCompatActivity {

    private TabbarFragment tabbarFragment;
    private SearchResultFragment searchResultFragment;
    private Spinner dynastySpinner;
    private Spinner themeSpinner;
    private Spinner typeSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        tabbarFragment=TabbarFragment.newInstance(3);
        getSupportFragmentManager().beginTransaction().add(R.id.tabbar_fragment, tabbarFragment).commit();
        bindViews();

        searchResultFragment = SearchResultFragment.newInstance("静夜思","李白",3);
        getSupportFragmentManager().beginTransaction().add(R.id.search_result_fragment, searchResultFragment).commit();
    }

    private void bindViews() {
        dynastySpinner = findViewById(R.id.dynasty_spinner);
        themeSpinner = findViewById(R.id.theme_spinner);
        typeSpinner = findViewById(R.id.type_spinner);


        ArrayList<String> dynastyList = new ArrayList<String>();
        dynastyList.add("朝代");
        dynastyList.add("三国");
        dynastyList.add("魏晋南北朝");
        dynastyList.add("唐");
        dynastyList.add("宋");
        dynastyList.add("元");
        dynastyList.add("明");
        dynastyList.add("清");
        Log.d("dynastyList",dynastyList.toString());

        ArrayAdapter<String> adapterDynasty = new ArrayAdapter<String>(this, R.layout.item_dynasty_list, dynastyList);
        adapterDynasty.setDropDownViewResource(R.layout.item_dynasty_list);
        dynastySpinner.setAdapter(adapterDynasty);

        dynastySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = ((TextView) view).getText().toString();
                Toast.makeText(BookActivity.this, "选中：" + s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        ArrayList<String> themeList = new ArrayList<String>();
        themeList.add("题材");
        themeList.add("田园");
        themeList.add("边塞");
        themeList.add("闺怨");
        themeList.add("送别");
        themeList.add("咏物");
        themeList.add("咏史");
        themeList.add("讽喻");
        themeList.add("哲理");
        themeList.add("悼亡");
        themeList.add("怀古");
        themeList.add("节日");
        themeList.add("战争");
        Log.d("themeList",themeList.toString());


        ArrayAdapter<String> adapterTheme = new ArrayAdapter<String>(this, R.layout.item_dynasty_list, themeList);
        adapterTheme.setDropDownViewResource(R.layout.item_dynasty_list);
        themeSpinner.setAdapter(adapterTheme);

        themeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = ((TextView) view).getText().toString();
                Toast.makeText(BookActivity.this, "选中：" + s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        ArrayList<String> typeList = new ArrayList<String>();
        typeList.add("体裁");
        typeList.add("古体诗");
        typeList.add("乐府诗");
        typeList.add("五言律诗");
        typeList.add("七言律诗");
        typeList.add("五言绝句");
        typeList.add("七言绝句");
        typeList.add("五言排律");
        typeList.add("七言排律");
        typeList.add("词");
        Log.d("typeList",typeList.toString());


        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(this, R.layout.item_dynasty_list, typeList);
        adapterType.setDropDownViewResource(R.layout.item_dynasty_list);
        typeSpinner.setAdapter(adapterType);

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = ((TextView) view).getText().toString();
                Toast.makeText(BookActivity.this, "选中：" + s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}