package com.example.tomorrowpoemapp;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
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

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private Activity detaiActivity;

    private PoemFragment poemFragment;
    private NoteFragment noteFragment;
    private GeneralFragment generalFragment;
    private TextView title;
    private TextView author;
    private TextView content;
    private ImageView likeButton;
    private ImageView backButton;

    private Integer id;
    private Integer status;
    private String msg;
    private Integer star;
    private Boolean like;
    private JSONObject data,poem,note,translation,appreciation,background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        id = intent.getIntExtra("id",1);
//        id = 1;
        Log.d("id",id.toString());
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_detail);
        detaiActivity = this;
        setBack();
//        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.detail_title);


        getContent(id);



//       setFont();

    }



    private void getContent(Integer id){
        AndroidNetworking.get("https://service-eanmnyo2-1305624698.gz.apigw.tencentcs.com/release/api/poem/" + id.toString())
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try{
                            status = response.getInt("status");
                            msg = response.getString("msg");
                            data = response.getJSONObject("data");
                            star = data.getInt("star");
                            like = data.getBoolean("like");
                            poem = data.getJSONObject("poem");
                            note = data.getJSONObject("note");
                            translation = data.getJSONObject("translation");
                            appreciation = data.getJSONObject("appreciation");
                            background = data.getJSONObject("background");

                            Log.d("getContent","getContent");
                            Log.d("status",status.toString());
                            Log.d("msg",msg);
                            Log.d("data",data.toString());
                            Log.d("like",like.toString());

                            //????????????
                            if(poem.getString("access") == "true"){
                                Log.d("ok","ok");
                                poemFragment=PoemFragment.newInstance(
                                        poem.getJSONObject("content").getString("title"),
                                        "["+poem.getJSONObject("content").getString("dynasty")+"] "+poem.getJSONObject("content").getString("author"),
                                        poem.getJSONObject("content").getString("content")
                                );
                                FragmentManager fmp = getSupportFragmentManager();
                                getSupportFragmentManager().beginTransaction().add(R.id.poem_fragment, poemFragment).commit();
                                fmp.executePendingTransactions();
                            }
                            else {
                                Log.d("ok", "gg");
                            }
                            //??????
                            if(note.getString("access") == "true"){

                                noteFragment=NoteFragment.newInstance(
                                        note.getString("content")
                                );
                                FragmentManager fm = getSupportFragmentManager();
                                fm.beginTransaction().add(R.id.note_fragment, noteFragment).commit();
                                fm.executePendingTransactions();
                            }
                            else{
                                noteFragment=NoteFragment.newInstance(
                                        "false"
                                );
                                FragmentManager fm = getSupportFragmentManager();
                                fm.beginTransaction().add(R.id.note_fragment, noteFragment).commit();
                                fm.executePendingTransactions();
                            }
                            //??????
                            if(translation.getString("access") == "true"){
                                generalFragment=GeneralFragment.newInstance(
                                        "??????",translation.getString("content")
                                );
                                FragmentManager fm0 = getSupportFragmentManager();
                                getSupportFragmentManager().beginTransaction().add(R.id.general_fragment1, generalFragment).commit();
                                fm0.executePendingTransactions();
                            }
                            else{
                                generalFragment=GeneralFragment.newInstance(
                                        "??????","false"
                                );
                                FragmentManager fm0 = getSupportFragmentManager();
                                getSupportFragmentManager().beginTransaction().add(R.id.general_fragment1, generalFragment).commit();
                                fm0.executePendingTransactions();
                            }
                            //??????
                            if(appreciation.getString("access") == "true"){
                                generalFragment=GeneralFragment.newInstance(
                                        "??????",appreciation.getString("content")
                                );
                                FragmentManager fm0 = getSupportFragmentManager();
                                getSupportFragmentManager().beginTransaction().add(R.id.general_fragment2, generalFragment).commit();
                                fm0.executePendingTransactions();
                            }
                            else{
                                generalFragment=GeneralFragment.newInstance(
                                        "??????","false"
                                );
                                FragmentManager fm0 = getSupportFragmentManager();
                                getSupportFragmentManager().beginTransaction().add(R.id.general_fragment2, generalFragment).commit();
                                fm0.executePendingTransactions();
                            }
                            //????????????
                            if(background.getString("access") == "true"){
                                generalFragment=GeneralFragment.newInstance(
                                        "????????????",background.getString("content")
                                );
                                FragmentManager fm0 = getSupportFragmentManager();
                                getSupportFragmentManager().beginTransaction().add(R.id.general_fragment3, generalFragment).commit();
                                fm0.executePendingTransactions();
                            }
                            else{
                                generalFragment=GeneralFragment.newInstance(
                                        "????????????","false"
                                );
                                FragmentManager fm0 = getSupportFragmentManager();
                                getSupportFragmentManager().beginTransaction().add(R.id.general_fragment3, generalFragment).commit();
                                fm0.executePendingTransactions();
                            }


                            setFont();
                            setLike();

                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(DetailActivity.this, "Data error!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Toast.makeText(DetailActivity.this,"Network error!", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void setBack(){
        backButton = (ImageView) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //????????????Activity
                detaiActivity.finish();
            }
        });
    }

    private void setLike(){
        likeButton = findViewById(R.id.like_button);
        if(like == true){
            likeButton.setImageResource(R.mipmap.like_red);
        }
        else{
            likeButton.setImageResource(R.mipmap.like_white);
        }
    }


    private void setFont(){
        title = findViewById(R.id.title);
        Log.i("title",title.toString());
        title.setTypeface(Typeface.createFromAsset(getAssets(),"simsun.ttc"));
        TextPaint tp = title.getPaint();
        tp.setFakeBoldText(true);

        author = findViewById(R.id.author);
        Log.i("author",author.toString());
        author.setTypeface(Typeface.createFromAsset(getAssets(),"kaiti.ttf"));
        content = findViewById(R.id.content);
        Log.i("content",content.toString());
        content.setTypeface(Typeface.createFromAsset(getAssets(),"simsun.ttc"));
        TextPaint tp1 = content.getPaint();
        tp1.setFakeBoldText(true);

    }
}






