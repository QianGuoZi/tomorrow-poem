package com.example.tomorrowpoemapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    private NoteFragment noteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        noteFragment=NoteFragment.newInstance(
                "[1] 静夜思：安静的夜晚产生的思绪\n" +
                "[2] 床：此诗中的“床”字，是争论和异议的焦点是争论和异议的焦点是争论和异议的焦点是争论和异议的焦点是争论和异议的焦点\n" +
                "[3] 疑：好像\n" +
                "[4] 举头：抬头"
        );
        getSupportFragmentManager().beginTransaction().add(R.id.note_fragment, noteFragment).commit();
    }
}