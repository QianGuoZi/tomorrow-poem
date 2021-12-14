package com.example.tomorrowpoemapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PoemFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class PoemFragment extends Fragment {

    private TextView titleContent;
    private TextView authorContent;
    private TextView Content;

    private static final String ARG1_CONTENT = "content";
    private static final String ARG2_CONTENT = "title";
    private static final String ARG3_CONTENT = "author";

    private String title;
    private String author;
    private String content;

    public PoemFragment() {
    }

    public static PoemFragment newInstance(String title,String author,String content) {
        PoemFragment fragment = new PoemFragment();
        Bundle args = new Bundle();
        args.putString(ARG1_CONTENT, content);
        args.putString(ARG2_CONTENT, title);
        args.putString(ARG3_CONTENT, author);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            content = getArguments().getString(ARG1_CONTENT);
            title = getArguments().getString(ARG2_CONTENT);
            author = getArguments().getString(ARG3_CONTENT);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Content=view.findViewById(R.id.content);
        titleContent=view.findViewById(R.id.title);
        authorContent=view.findViewById(R.id.author);
        if (getArguments() != null) {
            Content.setText(getArguments().getString(ARG1_CONTENT));
            titleContent.setText(getArguments().getString(ARG2_CONTENT));
            authorContent.setText(getArguments().getString(ARG3_CONTENT));

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_poem, container, false);
    }
}