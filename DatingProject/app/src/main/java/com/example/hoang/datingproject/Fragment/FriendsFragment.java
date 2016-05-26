package com.example.hoang.datingproject.Fragment;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hoang.datingproject.Activity.PersonalInfoActivity;
import com.example.hoang.datingproject.Adapter.DatingAdapter;
import com.example.hoang.datingproject.Adapter.FriendsAdapter;
import com.example.hoang.datingproject.Model.FeedModel;
import com.example.hoang.datingproject.Model.PersonModel;
import com.example.hoang.datingproject.R;
import com.example.hoang.datingproject.Utilities.Const;
import com.example.hoang.datingproject.Utilities.FontManager;
import com.example.hoang.datingproject.Utilities.OnLoadMoreListener;
import com.example.hoang.datingproject.Utilities.RegisterUserClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hoang on 4/4/2016.
 */
public class FriendsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private FriendsAdapter adapter;
    private ArrayList<PersonModel> arr;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.friend_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        swipeRefreshLayout = (SwipeRefreshLayout) getActivity().findViewById(R.id.swipe2refresh);

        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView);
        arr = new ArrayList<PersonModel>();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(gridLayoutManager);
        arr.add(null);
        adapter = new FriendsAdapter(getActivity(), arr, recyclerView);
        adapter.notifyItemInserted(arr.size() - 1);
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
                        int index = arr.size();
//                        initData();
                        adapter.notifyDataSetChanged();
                        adapter.setLoaded();
                    }
                }, 5000);
            }
        });

        new GetData().execute(Const.USER_LIST_URL);

        Typeface font = FontManager.getTypeface(getActivity(), FontManager.FONTAWESOME);
        TextView plus = (TextView) getActivity().findViewById(R.id.plus_icon);
        plus.setTypeface(font);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    @Override
    public void onRefresh() {
        new GetData().execute(Const.USER_LIST_URL);
    }

    public void showDialog(){
        SearchAccountDialogFragment myDialog = new SearchAccountDialogFragment();
        myDialog.show(getFragmentManager(), "My Dialog");
    }

    private class GetData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            StringBuilder jsonResults = new StringBuilder();
            String result = "";

            try {
                StringBuilder sb = new StringBuilder(params[0]);

                URL url = new URL(sb.toString());
                Log.d(Const.LOG_TAG, url.toString());
                connection = (HttpURLConnection) url.openConnection();
                InputStreamReader in = new InputStreamReader(connection.getInputStream());

                int read;
                char[] buff = new char[1024];
                while ((read = in.read(buff)) != -1) {
                    jsonResults.append(buff, 0, read);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
            result = jsonResults.toString();
            return result;
        }

        @Override
        protected void onPostExecute(String doubles) {

            try{

                JSONObject jsonObject = new JSONObject(doubles);
                Log.d(Const.LOG_TAG, jsonObject.toString());
                String result = jsonObject.getString("result");
                if (result.equalsIgnoreCase("1")) {
                    JSONArray data = jsonObject.getJSONArray("data");
                    ArrayList<PersonModel> arrayList = new ArrayList<PersonModel>();
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject item = data.getJSONObject(i);

                        String profileImage = item.getString(Const.PROFILE_IMAGE);
                        String uid = item.getString(Const.PROFILE_UID);
                        String userID = item.getString(Const.USERID);
                        String nickname = item.getString(Const.NICK_NAME);

                        Log.d(Const.LOG_TAG, uid + " - " + nickname);
                        Log.d(Const.LOG_TAG, PersonalInfoActivity.getDefaults("email", getActivity()) + "-" +
                                PersonalInfoActivity.getDefaults("password", getActivity()) + "-" +
                                PersonalInfoActivity.getDefaults("id", getActivity()));

                        PersonModel model = new PersonModel(profileImage, nickname, userID);

                        arrayList.add(model);
                    }
                    arr.clear();
                    arr.addAll(arrayList);
                    adapter.notifyDataSetChanged();
//                    progressDialog.dismiss();
                    swipeRefreshLayout.setRefreshing(false);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendChat(final String userID1, String userID2, String contents, String from){
        class SendChat extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String result = jsonObject.getString("result");
                    if(result.equalsIgnoreCase("1")){
                        Log.d(Const.LOG_TAG, "send chat success");
                    }else{
                        Log.d(Const.LOG_TAG, "send chat failed");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String,String> data = new HashMap<>();
                data.put(Const.USERID1,params[0]);
                data.put(Const.USERID2,params[1]);
                data.put(Const.MESSAGE,params[2]);
                data.put(Const.FROM_USER,params[3]);

                RegisterUserClass ruc = new RegisterUserClass();

                String result = ruc.sendPostRequest(Const.CHAT_SEND_URL,data);

                Log.d(Const.LOG_TAG, result + "");
                return result;
            }
        }
        SendChat send = new SendChat();
        send.execute(userID1, userID2, contents, from);
    }

}
