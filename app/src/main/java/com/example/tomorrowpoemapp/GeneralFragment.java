package com.example.tomorrowpoemapp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GeneralFragment extends Fragment {

    private TextView generalContent;
    private TextView Type;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG2_CONTENT = "content";
    private static final String ARG1_CONTENT = "type";

    // TODO: Rename and change types of parameters
    private String content;
    private String type;

    public GeneralFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param content Parameter 1.
     * @return A new instance of fragment NoteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GeneralFragment newInstance(String type,String content) {
        GeneralFragment fragment = new GeneralFragment();
        Bundle args = new Bundle();
        args.putString(ARG1_CONTENT, type);
        args.putString(ARG2_CONTENT, content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString(ARG1_CONTENT);
            content = getArguments().getString(ARG2_CONTENT);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        generalContent=view.findViewById(R.id.general_content);
        Type=view.findViewById(R.id.type_content);
        if (getArguments() != null) {
            Log.d("CONTENT",ARG2_CONTENT);
            if(getArguments().getString(ARG2_CONTENT).equals("false")== true){
                generalContent.setGravity(Gravity.CENTER);
                Drawable img = getResources().getDrawable(R.mipmap.lock);
                img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
                generalContent.setCompoundDrawables(null,img,null,null);
            }
            else{
                generalContent.setText(getArguments().getString(ARG2_CONTENT));

            }
            Type.setText(getArguments().getString(ARG1_CONTENT));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_general, container, false);
    }
}