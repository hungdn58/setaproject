package com.example.hoang.datingproject.Fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hoang.datingproject.Activity.PersonalInfoActivity;
import com.example.hoang.datingproject.Adapter.FeedsAdapter;
import com.example.hoang.datingproject.Adapter.MessagesAdapter;
import com.example.hoang.datingproject.Model.FeedModel;
import com.example.hoang.datingproject.Model.MessageModel;
import com.example.hoang.datingproject.Model.NotificationModel;
import com.example.hoang.datingproject.R;
import com.example.hoang.datingproject.Utilities.Const;
import com.example.hoang.datingproject.Utilities.OnLoadMoreListener;

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

/**
 * Created by hoang on 4/4/2016.
 */
public class MessagesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView recyclerView;
    private MessagesAdapter adapter;
    private ArrayList<MessageModel> arr;
    public static int LIMIT = 10;
    public static int OFFSET = 1;
    public String url;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.message_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        swipeRefreshLayout = (SwipeRefreshLayout) getActivity().findViewById(R.id.swipe2refresh);

        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView);
        arr = new ArrayList<MessageModel>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),4);
        recyclerView.setLayoutManager(linearLayoutManager);
//        arr.add(null);
        adapter = new MessagesAdapter(getActivity(), arr, recyclerView);
//        adapter.notifyItemInserted(arr.size() - 1);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                arr.add(null);
                adapter.notifyItemInserted(arr.size() - 1);
                LIMIT += 10;
                new GetData().execute(Const.CHAT_LIST_URL + Const.ID + "=" + PersonalInfoActivity.getDefaults("id", getActivity()) + "&" + Const.LIMIT + "=" + LIMIT + "&" + Const.OFFSET + "=" + OFFSET);
            }
        });

        new GetData().execute(Const.CHAT_LIST_URL + Const.ID + "=" + PersonalInfoActivity.getDefaults("id", getActivity()) + "&" + Const.LIMIT + "=" + LIMIT + "&" + Const.OFFSET + "=" + OFFSET);

    }

    @Override
    public void onRefresh() {
        new GetData().execute(Const.CHAT_LIST_URL + Const.ID + "=" + PersonalInfoActivity.getDefaults("id", getActivity()) + "&" + Const.LIMIT + "=" + LIMIT + "&" + Const.OFFSET + "=" + OFFSET);
        LIMIT = 10;
    }

    public class GetData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... params) {
            ArrayList<Double> resultList = null;
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

//            Log.d(Const.LOG_TAG, encodedImage);

            try{

                JSONObject jsonObject = new JSONObject(doubles);
                Log.d(Const.LOG_TAG, jsonObject.toString());
                String result = jsonObject.getString("result");
                if (result.equalsIgnoreCase("1")) {
                    ArrayList<MessageModel> arrayList = new ArrayList<MessageModel>();
                    JSONArray data = jsonObject.getJSONArray("data");

                    for (int i = 0; i < data.length(); i++) {
                        JSONObject item = data.getJSONObject(i);
                        String profileImage = item.getString(Const.PROFILE_IMAGE);
                        String content = item.getString(Const.NOTIFICATION_CONTENT);
                        String posttime = item.getString(Const.NOTIFICATION_POSTTIME);
                        String nickname = item.getString(Const.NICK_NAME);
                        String friend_id = item.getString(Const.FRIEND_ID);

                        Log.d(Const.LOG_TAG, content + " - " + nickname + " - " + posttime);

                        MessageModel model = new MessageModel(profileImage, posttime, content, nickname, friend_id);

                        arrayList.add(model);

                    }
                    arr.clear();
                    arr.addAll(arrayList);
                    adapter.notifyDataSetChanged();
//                    progressDialog.dismiss();
                    swipeRefreshLayout.setRefreshing(false);
                    adapter.setLoaded();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
