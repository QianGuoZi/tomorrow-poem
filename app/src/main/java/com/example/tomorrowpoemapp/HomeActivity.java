package com.example.tomorrowpoemapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Layout;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends FragmentActivity {

    private TabbarFragment tabbarFragment;
    private CardFragment cardFragment;
    private RelativeLayout cardLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tabbarFragment=TabbarFragment.newInstance(1);
        getSupportFragmentManager().beginTransaction().add(R.id.tabbar_fragment, tabbarFragment).commit();


        AndroidNetworking.initialize(getApplicationContext());
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
                            Integer status = response.getInt("status");
                            String msg = response.getString("msg");
                            JSONArray data = response.getJSONArray("data");
                            Log.d("status",status.toString());
                            Log.d("msg",msg);
                            Log.d("data",data.toString());
                            Log.d("sentence",data.getJSONObject(0).getString("sentence"));
                            cardFragment = CardFragment.newInstance(data.getJSONObject(0).getString("sentence"),
                                    data.getJSONObject(0).getInt("star"),true);
                            FragmentManager fm = getSupportFragmentManager();
                            fm.beginTransaction().add(R.id.card_fragment, cardFragment).commit();
                            fm.executePendingTransactions();
                            setFont();


                            cardLayout = findViewById(R.id.card_layout);
                            cardLayout.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent();
                                    intent.setClass(HomeActivity.this, DetailActivity.class);
                                    startActivity(intent);
                                }
                            });

                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(HomeActivity.this, "Data error!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Toast.makeText(HomeActivity.this,"Network error!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setFont(){
        TextView content =  findViewById(R.id.card_content);
        Log.d("content",content.toString());
        content.setTypeface(Typeface.createFromAsset(getAssets(),"simsun.ttc"));
        TextPaint tp = content.getPaint();
        tp.setFakeBoldText(true);
    }

}