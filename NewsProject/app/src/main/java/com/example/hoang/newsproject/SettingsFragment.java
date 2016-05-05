package com.example.hoang.newsproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by hoang on 3/15/2016.
 */
public class SettingsFragment extends Fragment {

    private RelativeLayout container2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.settings_fragment, container, false);
        container2 = (RelativeLayout) view.findViewById(R.id.container2);
        container2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        return view;
    }


    public void showDialog(){
        MyDialog myDialog = new MyDialog();
        myDialog.show(getFragmentManager(), "Send Email");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
