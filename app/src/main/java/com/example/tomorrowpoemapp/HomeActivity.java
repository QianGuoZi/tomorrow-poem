package com.example.tomorrowpoemapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Layout;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
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
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends FragmentActivity {

    private TabbarFragment tabbarFragment;
    private RelativeLayout cardLayout;

    private ViewPager viewPager;
    private ArrayList<Fragment> cardList;
    private ViewPagerAdapter viewPagerAdapter;


    private Integer status;
    private String msg;
    private JSONArray data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tabbarFragment=TabbarFragment.newInstance(1);
        getSupportFragmentManager().beginTransaction().add(R.id.tabbar_fragment, tabbarFragment).commit();

        viewPager = findViewById(R.id.cardViewPager);
        viewPager.setOnPageChangeListener(new MyPagerChangeListener());


        AndroidNetworking.initialize(getApplicationContext());
        getContent();
    }

    //向服务器请求结果
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
                            Log.d("size",size.toString());
                            //生成随机位置
                            Integer randomPos = (int)(1+Math.random()*(size-1-1+1));
                            Log.d("randomPos",randomPos.toString());

                            //准备塞进viewPager
                            cardList = new ArrayList<Fragment>();
                            for(Integer i=0;i<size;i++){

                                Log.d("id",i.toString());
                                Log.d("sentence",data.getJSONObject(i).getString("sentence"));

                                cardList.add(CardFragment.newInstance(data.getJSONObject(i).getString("sentence"),
                                        data.getJSONObject(i).getInt("star"),true));
                            }
                            Log.d("cardList",cardList.toString());
                            viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),cardList);
                            viewPager.setAdapter(viewPagerAdapter);

                            //初始化
                            setFont();
                            setListener();
                            //设置随机跳转
                            setDefaultItem(randomPos);

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

    //修改字体
    private void setFont(){
//        CardFragment cf = (CardFragment)((ViewPagerAdapter)viewPager.getAdapter()).currentFragment;
//        TextView content =  (TextView)cf.getActivity().findViewById(R.id.card_content);

        TextView content =  findViewById(R.id.card_content);

        Log.d("content",content.getText().toString());
        content.setTypeface(Typeface.createFromAsset(getAssets(),"simsun.ttc"));
        TextPaint tp = content.getPaint();
        tp.setFakeBoldText(true);
    }

    //后续切换页面修改字体
    private void setFontChange(int position){

        Fragment fragment = viewPagerAdapter.getItem(position);

        TextView content =  (TextView)fragment.getView().findViewById(R.id.card_content);

        Log.d("content",content.getText().toString());
        content.setTypeface(Typeface.createFromAsset(getAssets(),"simsun.ttc"));
        TextPaint tp = content.getPaint();
        tp.setFakeBoldText(true);
    }

    //修改监听器
    private void setListener(){
        cardLayout = findViewById(R.id.card_layout);
        Log.d("cardLayout",cardLayout.toString());
        //到这了

        cardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(HomeActivity.this, DetailActivity.class);
                Integer cPos = viewPagerAdapter.getCurrentPos();
                Integer id = null;
                try {
                    id = data.getJSONObject(cPos).getInt("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                intent.putExtra("id",id);
                Log.d("idTrans",id.toString());
                startActivity(intent);
            }
        });
    }

    //后续切换页面修改监听器
    private void setListenerChange(int position){
        cardLayout = viewPagerAdapter.getItem(position).getView().findViewById(R.id.card_layout);
        Log.d("cardLayout",cardLayout.toString());

        cardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(HomeActivity.this, DetailActivity.class);
                Integer cPos = viewPagerAdapter.getCurrentPos();
                Integer id = null;
                try {
                    id = data.getJSONObject(cPos).getInt("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                intent.putExtra("id",id);
                Log.d("idTrans",id.toString());
                startActivity(intent);
            }
        });
    }

    //为viewPager加监听器
    public class MyPagerChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrollStateChanged(int state) {
//            setFont();
//            setFontChange();
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            setFont();
            setFontChange(position);
            setListenerChange(position);
        }

        @Override
        public void onPageSelected(int position) {
            Log.d("change","换了");
            setFontChange(position);
            setListenerChange(position);
        }
    }

    //跳转到指定页面
    private void setDefaultItem(int position){
        //我这里mViewpager是viewpager子类的实例。如果你是viewpager的实例，也可以这么干。
        try {
            Class c = Class.forName("androidx.viewpager.widget.ViewPager");
            Field field =c.getDeclaredField("mCurItem");
            field.setAccessible(true);
            field.setInt(viewPager, position);
        } catch (Exception e) {
            e.printStackTrace();
        }

        viewPagerAdapter.notifyDataSetChanged();

        viewPager.setCurrentItem(position);
    }
}