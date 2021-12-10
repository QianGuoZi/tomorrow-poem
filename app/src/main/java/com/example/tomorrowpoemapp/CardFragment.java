package com.example.tomorrowpoemapp;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardFragment extends Fragment {

    private String content;
    private int star = 1;
    private Boolean canBeDeleted = true;
    private HorizontalListView starList;
    SimpleAdapter simpleAdapter;

    private TextView cardContent;
    ArrayList<Star> listData = new ArrayList<>();
    View contentView;
    ImageView cardDeleted;
    View cardLine;
//    private


    private static final String CONTENT = "content";
    private static final String STAR = "star";
    private static final String DELETED = "deleted";

    public CardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param content Parameter 1.
     * @param star Parameter 2.
     * @param canBeDeleted Parameter 3.
     * @return A new instance of fragment CardFragment.
     */
    public static CardFragment newInstance(String content, int star, Boolean canBeDeleted) {
        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        args.putString(CONTENT, content);
        args.putInt(STAR, star);
        args.putBoolean(DELETED, canBeDeleted);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        contentView = getActivity().findViewById(R.id.card_content);
//        contentView.setType(Typeface.createFromAsset(getAssets(),"simsun.ttc"));
        if (getArguments() != null) {
            content = getArguments().getString(CONTENT);
            star = getArguments().getInt(STAR);
            canBeDeleted = getArguments().getBoolean(DELETED);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card,container,false);
        starList = view.findViewById(R.id.star_list);
        simpleAdapter = new SimpleAdapter(getActivity(),getData(),
                                            R.layout.card_star,new String[]{"image"},new int[]{R.id.star});
        starList.setAdapter(simpleAdapter);
        return inflater.inflate(R.layout.fragment_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            content = getArguments().getString(CONTENT);
            star = getArguments().getInt(STAR);
            canBeDeleted = getArguments().getBoolean(DELETED);
        }
        if(canBeDeleted == true){
            cardDeleted = (ImageView) view.findViewById(R.id.card_delete);
            cardDeleted.setVisibility(View.INVISIBLE);
            cardLine = (View) view.findViewById(R.id.card_line);
            cardLine.setVisibility(View.INVISIBLE);
        }
        starList = view.findViewById(R.id.star_list);
        simpleAdapter = new SimpleAdapter(getActivity(),getData(),
                R.layout.card_star,new String[]{"image"},new int[]{R.id.star});
        starList.setAdapter(simpleAdapter);

    }

////    @Nullable
//    @Override
//    public View getView() {
//        return super.getView();
//    }

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