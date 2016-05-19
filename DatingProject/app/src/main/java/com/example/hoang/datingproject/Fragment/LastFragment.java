package com.example.hoang.datingproject.Fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hoang.datingproject.Activity.SettingsActivity;
import com.example.hoang.datingproject.Adapter.FeedsAdapter;
import com.example.hoang.datingproject.Adapter.LastFragmentAdapter;
import com.example.hoang.datingproject.Model.FeedModel;
import com.example.hoang.datingproject.R;
import com.example.hoang.datingproject.Utilities.FontManager;
import com.example.hoang.datingproject.Utilities.OnLoadMoreListener;

import java.util.ArrayList;

/**
 * Created by hoang on 4/5/2016.
 */
public class LastFragment extends Fragment {

    private RecyclerView recyclerView;
    private LastFragmentAdapter adapter;
    private ArrayList<FeedModel> arr;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.last_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getControls();
    }

    private void initData() {
        FeedModel model = new FeedModel(R.drawable.avatar, "ハン", "おはよう ございます");
        FeedModel model2 = new FeedModel(R.drawable.avatar, "ハン", "おはよう ございます");
        FeedModel model3 = new FeedModel(R.drawable.avatar, "ハン", "おはよう ございます");
        FeedModel model4 = new FeedModel(R.drawable.avatar1, "ハン", "おはよう ございます");
        FeedModel model5 = new FeedModel(R.drawable.avatar2, "ハン", "おはよう ございます");
        FeedModel model6 = new FeedModel(R.drawable.avatar, "ハン", "おはよう ございます");
        arr.add(model);
        arr.add(model2);
        arr.add(model3);
        arr.add(model4);
        arr.add(model5);
        arr.add(model6);
    }

    private void getControls() {
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView);
        arr = new ArrayList<FeedModel>();
        initData();
        adapter = new LastFragmentAdapter(getActivity(), arr, recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),4);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                arr.add(null);
                adapter.notifyItemInserted(arr.size() - 1);

                //Load more data for reyclerview
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //Remove loading item
                        arr.remove(arr.size() - 1);
                        adapter.notifyItemRemoved(arr.size());

                        //Load data
                        initData();
                        adapter.notifyDataSetChanged();
                        adapter.setLoaded();
                    }
                }, 5000);
            }
        });

        Typeface font = FontManager.getTypeface(getActivity(), FontManager.FONTAWESOME);
        TextView settings = (TextView) getActivity().findViewById(R.id.setting_icon);
        settings.setTypeface(font);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }
}
