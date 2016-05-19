package com.example.hoang.datingproject.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hoang.datingproject.Adapter.FeedsAdapter;
import com.example.hoang.datingproject.Adapter.MessagesAdapter;
import com.example.hoang.datingproject.Model.FeedModel;
import com.example.hoang.datingproject.Model.MessageModel;
import com.example.hoang.datingproject.R;

import java.util.ArrayList;

/**
 * Created by hoang on 4/4/2016.
 */
public class MessagesFragment extends Fragment {

    private RecyclerView recyclerView;
    private MessagesAdapter adapter;
    private ArrayList<MessageModel> arr;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.message_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView);
        arr = new ArrayList<MessageModel>();
        MessageModel model = new MessageModel(R.drawable.avatar, "ハン", "おはよう ございます");
        MessageModel model2 = new MessageModel(R.drawable.avatar, "ハン", "おはよう ございます");
        MessageModel model3 = new MessageModel(R.drawable.avatar, "ハン", "おはよう ございます");
        arr.add(model);
        arr.add(model2);
        arr.add(model3);
        adapter = new MessagesAdapter(getActivity(), arr);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),4);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }
}
