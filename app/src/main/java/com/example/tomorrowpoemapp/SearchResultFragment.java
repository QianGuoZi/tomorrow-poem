package com.example.tomorrowpoemapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchResultFragment extends Fragment {

    private String titleS;
    private String authorS;
    private int star = 1;

    private TextView title;
    private TextView author;
    private HorizontalListView starList;
    SimpleAdapter simpleAdapter;

    private TextView cardContent;
    ArrayList<Star> listData = new ArrayList<>();
    View contentView;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TITLE = "title";
    private static final String AUTHOR = "author";
    private static final String STAR = "star";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title Parameter 1.
     * @param author Parameter 2.
     * @param star Parameter 3.
     * @return A new instance of fragment SearchResultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchResultFragment newInstance(String title, String author, int star) {
        SearchResultFragment fragment = new SearchResultFragment();
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        args.putString(AUTHOR, author);
        args.putInt(STAR,star);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            titleS = getArguments().getString(TITLE);
            authorS = getArguments().getString(AUTHOR);
            star = getArguments().getInt(STAR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card,container,false);
        starList = view.findViewById(R.id.star_list);
        simpleAdapter = new SimpleAdapter(getActivity(),getData(),
                R.layout.detail_star,new String[]{"image"},new int[]{R.id.star});
        starList.setAdapter(simpleAdapter);
        return inflater.inflate(R.layout.fragment_search_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            titleS = getArguments().getString(TITLE);
            authorS = getArguments().getString(AUTHOR);
            star = getArguments().getInt(STAR);
        }
        title = view.findViewById(R.id.result_title);
        author = view.findViewById(R.id.result_author);
        title.setText(titleS);
        author.setText(authorS);
        starList = view.findViewById(R.id.star_list);
        simpleAdapter = new SimpleAdapter(getActivity(),getData(),
                R.layout.detail_star,new String[]{"image"},new int[]{R.id.star});
        starList.setAdapter(simpleAdapter);

    }

    private List<Map<String,Object>> getData(){
        int [] icon={R.mipmap.star_false,R.mipmap.star_true};
        List<Map<String,Object>> list = new ArrayList<>();
        for(int i=0;i<5-star;i++){
            Map map = new HashMap();
            map.put("image",icon[0]);
            list.add(map);
        }
        for(int i=5-star;i<5;i++){
            Map map = new HashMap();
            map.put("image",icon[1]);
            list.add(map);
        }
        return list;
    }
}