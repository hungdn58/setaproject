package com.example.hoang.datingproject.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabHost;

import com.example.hoang.datingproject.Adapter.DatingAdapter;
import com.example.hoang.datingproject.Model.MessageModel;
import com.example.hoang.datingproject.R;
import com.example.hoang.datingproject.Utilities.OnLoadMoreListener;

import java.util.ArrayList;

/**
 * Created by hoang on 4/4/2016.
 */
public class DatingFragment extends Fragment implements View.OnClickListener{

    private Button btn1, btn2;
    private TabHost tabHost;
    private RecyclerView recyclerView1, recyclerView2;
    private DatingAdapter adapter;
    private ArrayList<MessageModel> arr = new ArrayList<MessageModel>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dating_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getControls();
        loadTabs();
    }

    public void getControls() {
        btn1 = (Button) getActivity().findViewById(R.id.dating_btn1);
        btn2 = (Button) getActivity().findViewById(R.id.dating_btn2);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        recyclerView1 = (RecyclerView) getActivity().findViewById(R.id.listFriend);
        initData();
        adapter = new DatingAdapter(getActivity(), arr, recyclerView1);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),4);
        recyclerView1.setLayoutManager(linearLayoutManager);
        recyclerView1.setAdapter(adapter);
        recyclerView1.setHasFixedSize(true);

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
                        int index = arr.size();
                        int end = index + 6;
                        initData();
                        adapter.notifyDataSetChanged();
                        adapter.setLoaded();
                    }
                }, 5000);
            }
        });
    }
    private void initData() {
        MessageModel model = new MessageModel(R.drawable.avatar, "ハン", "おはよう ございます");
        MessageModel model2 = new MessageModel(R.drawable.avatar, "ハン", "おはよう ございます");
        MessageModel model3 = new MessageModel(R.drawable.avatar, "ハン", "おはよう ございます");
        arr.add(model);
        arr.add(model2);
        arr.add(model3);
    }

    private void loadTabs(){
        tabHost = (TabHost) getActivity().findViewById(android.R.id.tabhost);
        tabHost.setup();
        TabHost.TabSpec spec;
        spec = tabHost.newTabSpec("listfriend1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("All Friends");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("listfriend2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Best Friends");
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
        btn1.setPressed(true);
        btn1.setSelected(true);

//        tabHost.getTabWidget().setBackgroundResource(R.drawable.bg_toolbar);
        tabHost.getTabWidget().setDividerDrawable(null);

//        for(int i=0;i<tabHost.getTabWidget().getChildCount();i++) {
//            tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.bg_tab2);//unselected
////            tabHost.getTabWidget().getChildAt(i).setPadding(20, 20, 10 ,0);
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dating_btn1:
                tabHost.setCurrentTab(0);
                btn1.setSelected(true);
                btn1.setPressed(true);
                btn2.setSelected(false);
                btn2.setPressed(false);
                break;
            case R.id.dating_btn2:
                tabHost.setCurrentTab(1);
                btn1.setSelected(false);
                btn1.setPressed(false);
                btn2.setSelected(true);
                btn2.setPressed(true);
                break;
        }
    }
}
