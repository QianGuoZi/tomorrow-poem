package com.example.tomorrowpoemapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LikeActivity extends AppCompatActivity {

    private Integer status;
    private String msg;
    private JSONArray data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);
        getContent();
    }


    private void getContent(){
        AndroidNetworking.get("https://service-eanmnyo2-1305624698.gz.apigw.tencentcs.com/release/api/poem/all")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try{
                            //处理获取的结果
                            status = response.getInt("status");
                            msg = response.getString("msg");
                            data = response.getJSONArray("data");
                            Log.d("status",status.toString());
                            Log.d("msg",msg);
                            Log.d("data",data.toString());
                            Integer size = data.length();
                            for(int i=0;i<size;i++){
                                JSONObject dataI = data.getJSONObject(i);
                                Log.d("dataI",dataI.toString());
                                FragmentManager fm = getSupportFragmentManager();
                                fm.beginTransaction().add(R.id.like_result_Linear,
                                        SearchResultFragment.newInstance(dataI.getInt("id"),dataI.getString("title"),
                                                dataI.getString("author"),dataI.getInt("star"))
                                ).commit();
                                fm.executePendingTransactions();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(LikeActivity.this, "Data error!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Toast.makeText(LikeActivity.this,"Network error!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}