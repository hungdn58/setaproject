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
import android.widget.Toast;

import com.example.hoang.datingproject.Activity.NewNoteActivity;
import com.example.hoang.datingproject.Activity.PersonalInfoActivity;
import com.example.hoang.datingproject.Activity.WrittingNoteActivity;
import com.example.hoang.datingproject.Adapter.DatingAdapter;
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
public class FeedsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView recyclerView;
    private FeedsAdapter adapter;
    private ArrayList<FeedModel> arr;
    private TextView new_note;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int LIMIT = 5;
    private int OFFSET = 1;

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
        swipeRefreshLayout = (SwipeRefreshLayout) getActivity().findViewById(R.id.swipe2refresh);

        swipeRefreshLayout.setOnRefreshListener(this);
        new_note = (TextView) getActivity().findViewById(R.id.edit_icon);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView);
        arr = new ArrayList<FeedModel>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),4);
        recyclerView.setLayoutManager(linearLayoutManager);
        arr.add(null);
        adapter = new FeedsAdapter(getActivity(), arr, recyclerView);
        adapter.notifyItemInserted(arr.size() - 1);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                arr.add(null);
                adapter.notifyItemInserted(arr.size() - 1);
                LIMIT += 5;
                new GetData().execute(Const.LIST_TIMELINE_URL + Const.LIMIT + "=" + LIMIT + "&" + Const.OFFSET + "=" + OFFSET);
//                new AddData().execute(Const.LIST_TIMELINE_URL);
//                adapter.setLoaded();
            }
        });
        Typeface font = FontManager.getTypeface(getActivity(), FontManager.FONTAWESOME);
        TextView edit = (TextView) getActivity().findViewById(R.id.edit_icon);
        edit.setTypeface(font);

        new_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewNoteActivity.class);
                getActivity().startActivity(intent);
//                showDialog();
            }
        });

        new GetData().execute(Const.LIST_TIMELINE_URL + Const.LIMIT + "=" + LIMIT + "&" + Const.OFFSET + "=" + OFFSET);
        Bitmap icon = BitmapFactory.decodeResource(getActivity().getResources(),
                R.drawable.avatar);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        icon.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
//        getTimeline("4", encodedImage);
    }

    @Override
    public void onRefresh() {
        new GetData().execute(Const.LIST_TIMELINE_URL + Const.LIMIT + "=" + LIMIT + "&" + Const.OFFSET + "=" + OFFSET);
        LIMIT = 5;
    }

    public void showDialog(){
        WritePostDialog myDialog = new WritePostDialog();
        myDialog.show(getFragmentManager(), "My Dialog");
    }

    private Bitmap convertStringtoBitmap(String image) {
        byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        return decodedByte;
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
                    ArrayList<FeedModel> arrayList = new ArrayList<FeedModel>();
                    JSONArray data = jsonObject.getJSONArray("data");

                    for (int i = 0,n=data.length(); i < n; i++) {
                        JSONObject item = data.getJSONObject(i);
                        String content = item.getString(Const.POST_CONTENT);
                        String profileImage = item.getString(Const.PROFILE_IMAGE);
                        String nickname = item.getString(Const.NICK_NAME);
                        String itemID = item.getString(Const.ITEMID);
                        String image = item.getString(Const.IMAGE);

                        JSONObject replyTo = item.getJSONObject(Const.REPLY_TO);

                        String userID = replyTo.getString("userID");

                        Log.d(Const.LOG_TAG, content + " - " + profileImage + " - " + nickname);

                        FeedModel model = new FeedModel(profileImage, nickname, content, convertStringtoBitmap(image),itemID, userID);

                        arrayList.add(model);

                    }
                    arr.clear();
                    arr.addAll(arrayList);
                    adapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                    adapter.setLoaded();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
