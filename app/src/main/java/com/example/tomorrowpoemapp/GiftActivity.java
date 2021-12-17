package com.example.tomorrowpoemapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class GiftActivity extends AppCompatActivity {
    private TabbarFragment tabbarFragment;
    private int times;

    private Integer status;
    private String msg;
    private JSONArray data;

    private CardFragment cardFragment;
    private GridLayout gridLayout;
    private Button giftButton;
    private TextView giftTitle;
    private TextView giftTimes;

    private ImageView g1,g2,g3,g4,g5,g6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift);

        tabbarFragment=TabbarFragment.newInstance(2);
        getSupportFragmentManager().beginTransaction().add(R.id.tabbar_fragment, tabbarFragment).commit();
        giftTimes = findViewById(R.id.gift_times);
        giftButton = findViewById(R.id.gift_button);
        giftTitle = findViewById(R.id.gift_title);
        gridLayout = findViewById(R.id.gift_grid);

        g1=findViewById(R.id.gift_1);
        g2=findViewById(R.id.gift_2);
        g3=findViewById(R.id.gift_3);
        g4=findViewById(R.id.gift_4);
        g5=findViewById(R.id.gift_5);
        g6=findViewById(R.id.gift_6);

        times = 3;
        giftTimes.setText("今日还有 " + times + " 次抽卡机会");

        AndroidNetworking.initialize(getApplicationContext());

        g1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUnSelected();
                g1.setAlpha(180);
            }
        });

        g2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUnSelected();
                g2.setAlpha(180);
            }
        });

        g3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUnSelected();
                g3.setAlpha(180);
            }
        });

        g4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUnSelected();
                g4.setAlpha(180);
            }
        });

        g5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUnSelected();
                g5.setAlpha(180);
            }
        });

        g6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUnSelected();
                g6.setAlpha(180);
            }
        });

        giftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContent();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        setFont();
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

                            int randomNumber = (int)(Math.random() * 7);
                            cardFragment = CardFragment.newInstance(data.getJSONObject(randomNumber).getString("sentence"),
                                        data.getJSONObject(randomNumber).getInt("star"),false);

                            gridLayout.setVisibility(View.INVISIBLE);
                            giftTitle.setVisibility(View.INVISIBLE);
                            giftButton.setVisibility(View.INVISIBLE);
                            giftTimes.setVisibility(View.INVISIBLE);

                            getSupportFragmentManager().beginTransaction().add(R.id.card_fragment, cardFragment).commit();
                            // 初始化
                            setFont();
                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(GiftActivity.this, "Data error!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Toast.makeText(GiftActivity.this,"Network error!", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void setFont(){
        TextView content = findViewById(R.id.gift_title);
        content.setTypeface(Typeface.createFromAsset(getAssets(),"simsun.ttc"));
        TextPaint tp = content.getPaint();
        tp.setFakeBoldText(true);
    }

    private void setUnSelected() {
        g1.setAlpha(255);
        g2.setAlpha(255);
        g3.setAlpha(255);
        g4.setAlpha(255);
        g5.setAlpha(255);
        g6.setAlpha(255);
    }
}