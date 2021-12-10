package com.example.tomorrowpoemapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TabbarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabbarFragment extends Fragment {

    private TextView tabBook,tabHome,tabUser,tabGift;

    public TabbarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TabbarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TabbarFragment newInstance() {
        TabbarFragment fragment = new TabbarFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tabbar,container,false);
        tabBook=view.findViewById(R.id.tab_book);
        tabBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), BookActivity.class);
                startActivity(intent);
            }
        });

        tabHome=view.findViewById(R.id.tab_home);
        tabHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), HomeActivity.class);
                startActivity(intent);
            }
        });

        tabGift=view.findViewById(R.id.tab_gift);
        tabGift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), GiftActivity.class);
                startActivity(intent);
            }
        });

        tabUser=view.findViewById(R.id.tab_user);
        tabUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), UserActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}