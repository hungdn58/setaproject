package com.example.hoang.newsproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CategoryFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RSSFeed rssFeed;
    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<RSSItem> arrItems = new ArrayList<RSSItem>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance(RSSFeed feed, String param2) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, feed);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rssFeed = (RSSFeed) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_category, container, false);
        arrItems.clear();
        arrItems.addAll(rssFeed.get_itemlist());
        setBackground(arrItems);
        adapter = new RecyclerViewAdapter(getActivity(), arrItems);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Inflate the layout for this fragment
    }

    public void upDateUI(List<RSSItem> arr){
        arrItems.clear();
        arrItems.addAll(arr);
        setBackground(arrItems);
        adapter.notifyDataSetChanged();
    }

    public int randomColor(int rd){
        int rgbColor[] = {Color.rgb(181, 104, 59),
                Color.rgb(212, 213, 183),
                Color.rgb(155, 131, 60),
                Color.rgb(184, 212, 212),
                Color.rgb(240, 211, 211),
                Color.rgb(212, 213, 183),
                Color.rgb(155, 129, 129),
                Color.rgb(240, 211, 211),
                Color.rgb(183, 185, 156),
                Color.rgb(184, 212, 212),
                Color.rgb(105, 103, 129),
                Color.rgb(42, 156, 201),
                Color.rgb(91, 72, 126),
                Color.rgb(128, 57, 67),
                Color.rgb(65, 175, 165),
                Color.rgb(248, 110, 68),
                Color.rgb(217 , 158 , 0),
                Color.rgb(211 , 183 , 183)};

        if (rd <= 16) return rgbColor[rd];
        return rgbColor[17];
    }

    public void setBackground(ArrayList<RSSItem> arrItems) {
        for (int i = 0; i < arrItems.size(); ++i){
            Random rd = new Random();
            int random = rd.nextInt(17);
            arrItems.get(i).setBackground(randomColor(random));
        }

        for (int i = arrItems.size() - 1; i > 0 ; i--) {
            for (int j = 0; j < i; j++){
                while (arrItems.get(j).getBackground() == arrItems.get(j+1).getBackground()){
                    Random rd = new Random();
                    int random = rd.nextInt(17);
                    arrItems.get(j+1).setBackground(randomColor(random));
                }
            }
        }

    }

}
