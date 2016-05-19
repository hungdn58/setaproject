package com.example.hoang.datingproject.Fragment;

import android.app.Activity;
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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoang.datingproject.Activity.WrittingNoteActivity;
import com.example.hoang.datingproject.Adapter.FeedsAdapter;
import com.example.hoang.datingproject.Model.FeedModel;
import com.example.hoang.datingproject.R;
import com.example.hoang.datingproject.Utilities.Const;
import com.example.hoang.datingproject.Utilities.FontManager;
import com.example.hoang.datingproject.Utilities.OnLoadMoreListener;
import com.example.hoang.datingproject.Utilities.RegisterUserClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hoang on 4/4/2016.
 */
public class FeedsFragment extends Fragment {

    private RecyclerView recyclerView;
    private FeedsAdapter adapter;
    private ArrayList<FeedModel> arr;
    private TextView new_note;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.feed_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getControls();
    }

    private void getControls() {
        new_note = (TextView) getActivity().findViewById(R.id.edit_icon);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView);
        arr = new ArrayList<FeedModel>();
        adapter = new FeedsAdapter(getActivity(), arr, recyclerView);
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
                        int index = arr.size();
                        int end = index + 6;
                        initData();
                        adapter.notifyDataSetChanged();
                        adapter.setLoaded();
                    }
                }, 5000);
            }
        });
        Typeface font = FontManager.getTypeface(getActivity(), FontManager.FONTAWESOME);
        TextView edit = (TextView) getActivity().findViewById(R.id.edit_icon);
        edit.setTypeface(font);

        new_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), WrittingNoteActivity.class);
//                getActivity().startActivity(intent);
                showDialog();
            }
        });

//        new GetData().execute("abc");
        Bitmap icon = BitmapFactory.decodeResource(getActivity().getResources(),
                R.drawable.avatar);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        icon.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
//        getTimeline("4", encodedImage);
    }

    private void initData() {
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.profile);
        FeedModel model = new FeedModel(R.drawable.avatar, "ハン", "おはよう ございます!");
        FeedModel model2 = new FeedModel(R.drawable.avatar, "ハン", "おはよう ございます", largeIcon );
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

    public void showDialog(){
        WriteNoteDialog myDialog = new WriteNoteDialog();
        myDialog.show(getFragmentManager(), "My Dialog");
    }

    private class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity(),
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }
        @Override
        protected String doInBackground(String... params) {
            ArrayList<Double> resultList = null;
            HttpURLConnection connection = null;
            StringBuilder jsonResults = new StringBuilder();
            String result = "";

            try {
                StringBuilder sb = new StringBuilder(Const.LIST_TIMELINE_URL);

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
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject item = data.getJSONObject(i);
                        String content = item.getString(Const.POST_CONTENT);
                        String profileImage = item.getString(Const.PROFILE_IMAGE);
                        String nickname = item.getString(Const.NICK_NAME);

//                        Bitmap icon = BitmapFactory.decodeResource(getActivity().getResources(),
//                                R.drawable.avatar);
//                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                        icon.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
//                        byte[] b = baos.toByteArray();
//                        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
//
                        byte[] decodedString = Base64.decode(profileImage, Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                        Log.d(Const.LOG_TAG, content + " - " + profileImage + " - " + nickname);
                        Log.d(Const.LOG_TAG, profileImage.length()+ "size");
                        FeedModel model = new FeedModel(R.drawable.avatar, nickname, content, decodedByte);
                        if (decodedByte != null) {
                            Log.d(Const.LOG_TAG, "sao the nhi");
                        }else {
                            Log.d(Const.LOG_TAG, "sao nhi");
                        }
                        arr.add(model);
                        adapter.notifyDataSetChanged();
                    }
                    progressDialog.dismiss();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    public void getTimeline(final String userID, String contents){
        class GetTimelineData extends AsyncTask<String, Void, String> {

            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = new ProgressDialog(getActivity(),
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Authenticating...");
                progressDialog.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String result = jsonObject.getString("result");
                    if(result.equalsIgnoreCase("1")){
                        Log.d("feeddddd", "success");
                    }else{
                        Log.d("feeddddd", "failed");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String,String> data = new HashMap<>();
                data.put("userID",params[0]);
                data.put("contents",params[1]);

                RegisterUserClass ruc = new RegisterUserClass();

                String result = ruc.sendPostRequest("http://192.168.1.74/timeline/post",data);

                Log.d("feeddddd", result + "");
                return result;
            }
        }
        GetTimelineData getData = new GetTimelineData();
        getData.execute(userID, contents);
    }

}
