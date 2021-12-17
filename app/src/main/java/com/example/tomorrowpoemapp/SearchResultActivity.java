package com.example.tomorrowpoemapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchResultActivity extends AppCompatActivity {

    private Integer status;
    private String msg;
    private JSONArray data;

    private String text;

    private Activity searchResultActivity;

    private Button chooseTitle;
    private Button chooseAuthor;
    private ImageView backButton;
    private LinearLayout searchResultLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        text = intent.getStringExtra("text");
        super.onCreate(savedInstanceState);
        searchResultActivity = this;
        setContentView(R.layout.activity_search_result);
        bindButton();
        setBack();
        getContent(text, 1);

    }

    private void bindButton(){
        chooseTitle = findViewById(R.id.search_choose_title);
        chooseAuthor = findViewById(R.id.search_choose_author);
        chooseTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseTitle.setTextColor(getResources().getColor(R.color.search_result_button_font_selected));
                chooseAuthor.setTextColor(getResources().getColor(R.color.search_result_button_font_not));
                getContent(text,1);
            }
        });

        chooseAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseTitle.setTextColor(getResources().getColor(R.color.search_result_button_font_not));
                chooseAuthor.setTextColor(getResources().getColor(R.color.search_result_button_font_selected));
                getContent(text,2);
            }
        });
    }


    private void setBack(){
        backButton = (ImageView) findViewById(R.id.back_button_search_result);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //结束当前Activity
                searchResultActivity.finish();
            }
        });
    }


    private void getContent(String string,Integer type){
        AndroidNetworking.get("https://service-eanmnyo2-1305624698.gz.apigw.tencentcs.com/release/api/search/keyword?keyword="+string+"&type="+type.toString())
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try{
                            searchResultLinear = findViewById(R.id.search_result_Linear);
                            searchResultLinear.removeAllViews();
                            //处理获取的结果
                            status = response.getInt("status");
                            msg = response.getString("msg");
                            data = response.getJSONArray("data");
                            Log.d("status",status.toString());
                            Log.d("msg",msg);
                            Log.d("data",data.toString());
                            Integer size = data.length();
                            if(size == 0 ){
                                Toast.makeText(SearchResultActivity.this,"无相关结果",Toast.LENGTH_SHORT).show();
                            }
                            for(int i=0;i<size;i++){
                                JSONObject dataI = data.getJSONObject(i);
                                Log.d("dataI",dataI.toString());
                                FragmentManager fm = getSupportFragmentManager();
                                fm.beginTransaction().add(R.id.search_result_Linear,
                                        SearchResultFragment.newInstance(dataI.getInt("id"),dataI.getString("title"),
                                                dataI.getString("author"),dataI.getInt("star"))
                                ).commit();
                                fm.executePendingTransactions();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(SearchResultActivity.this, "Data error!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Toast.makeText(SearchResultActivity.this,"Network error!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}