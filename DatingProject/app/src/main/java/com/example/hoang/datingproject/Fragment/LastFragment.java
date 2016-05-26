package com.example.hoang.datingproject.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoang.datingproject.Activity.PersonalInfoActivity;
import com.example.hoang.datingproject.Activity.SettingsActivity;
import com.example.hoang.datingproject.Adapter.FeedsAdapter;
import com.example.hoang.datingproject.Adapter.LastFragmentAdapter;
import com.example.hoang.datingproject.Model.FeedModel;
import com.example.hoang.datingproject.R;
import com.example.hoang.datingproject.Utilities.Const;
import com.example.hoang.datingproject.Utilities.FontManager;
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
 * Created by hoang on 4/5/2016.
 */
public class LastFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView recyclerView;
    private LastFragmentAdapter adapter;
    private ArrayList<FeedModel> arr;
    private SwipeRefreshLayout swipeRefreshLayout;

    private TextView profile_name, profile_address, profile_description;
    private ImageView profile_image;

    private static int LIMIT = 5;
    private static int OFFSET = 1;

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

    private void getControls() {

        swipeRefreshLayout = (SwipeRefreshLayout) getActivity().findViewById(R.id.swipe2refresh);

        swipeRefreshLayout.setOnRefreshListener(this);

        profile_name = (TextView) getActivity().findViewById(R.id.profile_name);
        profile_address = (TextView) getActivity().findViewById(R.id.profile_address);
        profile_description = (TextView) getActivity().findViewById(R.id.profile_description);
        profile_image = (ImageView) getActivity().findViewById(R.id.profile_icon);

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView);
        arr = new ArrayList<FeedModel>();
//        initData();
        adapter = new LastFragmentAdapter(getActivity(), arr, recyclerView);
        arr.add(null);
        adapter.notifyItemInserted(arr.size() - 1);
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

                LIMIT += 5;
                new GetData().execute(Const.PROFILE_TIMELINE_URL + PersonalInfoActivity.getDefaults("id", getActivity()) + "&" +  Const.LIMIT + "=" + LIMIT + "&" + Const.OFFSET + "=" + OFFSET);
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
        new GetProfileData().execute(Const.USER_PROFILE_URL + PersonalInfoActivity.getDefaults("id", getActivity()));
        new GetData().execute(Const.PROFILE_TIMELINE_URL + PersonalInfoActivity.getDefaults("id", getActivity()) + "&" +  Const.LIMIT + "=" + LIMIT + "&" + Const.OFFSET + "=" + OFFSET);
    }

    @Override
    public void onRefresh() {
        new GetProfileData().execute(Const.USER_PROFILE_URL + PersonalInfoActivity.getDefaults("id", getActivity()));
        new GetData().execute(Const.PROFILE_TIMELINE_URL + PersonalInfoActivity.getDefaults("id", getActivity()) + "&" +  Const.LIMIT + "=" + LIMIT + "&" + Const.OFFSET + "=" + OFFSET);
        LIMIT = 5;
    }

    private class GetData extends AsyncTask<String, Void, String> {

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
                    JSONArray data = jsonObject.getJSONArray("data");
                    ArrayList<FeedModel> arrayList = new ArrayList<FeedModel>();
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject item = data.getJSONObject(i);
                        String content = item.getString(Const.POST_CONTENT);
                        String profileImage = item.getString(Const.PROFILE_IMAGE);
                        String nickname = item.getString(Const.NICK_NAME);
                        String itemID = item.getString(Const.ITEMID);

                        Log.d(Const.LOG_TAG, content + " - " + profileImage + " - " + nickname);

                        FeedModel model = new FeedModel(profileImage, nickname, content, itemID);

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

    private class GetProfileData extends AsyncTask<String, Void, String> {

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
            try{

                JSONObject jsonObject = new JSONObject(doubles);
                Log.d(Const.LOG_TAG, jsonObject.toString());
                String result = jsonObject.getString("result");
                if (result.equalsIgnoreCase("1")) {
                    JSONObject data = jsonObject.getJSONObject("data");
                    String address = data.getString(Const.ADDRESS);
                    String profileImage = data.getString(Const.PROFILE_IMAGE);
                    String nickname = data.getString(Const.NICK_NAME);
                    String description = data.getString(Const.DESCRIPTION);

                    profile_name.setText(nickname);
                    profile_description.setText(description);
                    profile_address.setText(address);

                    byte[] decodedString = Base64.decode(profileImage, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                    profile_image.setImageBitmap(decodedByte);

                    Log.d(Const.LOG_TAG, address + " - " + description + " - " + nickname);
                    swipeRefreshLayout.setRefreshing(false);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
