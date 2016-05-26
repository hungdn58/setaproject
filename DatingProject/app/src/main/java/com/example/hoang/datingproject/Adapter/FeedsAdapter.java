package com.example.hoang.datingproject.Adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoang.datingproject.Activity.ImageViewActivity;
import com.example.hoang.datingproject.Activity.MessagesActivity;
import com.example.hoang.datingproject.Activity.PersonalInfoActivity;
import com.example.hoang.datingproject.Fragment.FeedsFragment;
import com.example.hoang.datingproject.Fragment.WriteNoteDialog;
import com.example.hoang.datingproject.Model.FeedModel;
import com.example.hoang.datingproject.R;
import com.example.hoang.datingproject.Utilities.Const;
import com.example.hoang.datingproject.Utilities.FontManager;
import com.example.hoang.datingproject.Utilities.OnLoadMoreListener;
import com.example.hoang.datingproject.Utilities.RegisterUserClass;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hoang on 4/4/2016.
 */
public class FeedsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private FeedModel feedModel;
    private ArrayList<FeedModel> arr = new ArrayList<FeedModel>();
    private OnLoadMoreListener mOnLoadMoreListener;

    private boolean isLoading;
    private int visibleThreshold = 1;
    private int lastVisibleItem, totalItemCount;

    public FeedsAdapter(Context mcontext, ArrayList<FeedModel> arr, RecyclerView recyclerView) {
        this.mContext = mcontext;
        this.arr = arr;

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
                    .getLayoutManager();

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                    if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold) && totalItemCount >=5) {
                        if (mOnLoadMoreListener != null) {
                            Log.d(Const.LOG_TAG, totalItemCount + "size" + "-" + lastVisibleItem + "position" + visibleThreshold);
                            mOnLoadMoreListener.onLoadMore();
                        }
                        isLoading = true;
                    }
                }
            });
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    @Override
    public int getItemViewType(int position) {
        return arr.get(position) == null ? Const.VIEW_TYPE_LOADING : Const.VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Const.VIEW_TYPE_ITEM) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View view = inflater.inflate(R.layout.feed_item, parent, false);
            return new FeedHolder(view);
        } else if (viewType == Const.VIEW_TYPE_LOADING) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View view = inflater.inflate(R.layout.load_more_item, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FeedHolder) {
            feedModel = arr.get(position);
            FeedHolder feedHolder = (FeedHolder) holder;
            Typeface font = FontManager.getTypeface(mContext, FontManager.FONTAWESOME);
            byte[] decodedString = Base64.decode(feedModel.getFeedIcon(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            feedHolder.feedIcon.setImageBitmap(decodedByte);
            feedHolder.feedTitle.setText(feedModel.getFeedTitle());
            if (feedModel.getFeedDescription() != null) {
                feedHolder.feedDescription.setText(feedModel.getFeedDescription());
            }

            feedHolder.feed_description_image.setImageBitmap(feedModel.getImage());

            feedHolder.btn1_icon.setText(R.string.btn1_icon);
            feedHolder.btn1_icon.setTypeface(font);
            feedHolder.btn2_icon.setText(R.string.messages_icon);
            feedHolder.btn2_icon.setTypeface(font);
            feedHolder.btn3_icon.setText(R.string.btn3_icon);
            feedHolder.btn3_icon.setTypeface(font);
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
            loadingViewHolder.textLoadMore.setText("Loading...");
        }

    }

    @Override
    public int getItemCount() {
        return arr == null ? 0 : arr.size();
    }

    public void setLoaded() {
        isLoading = false;
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;
        private TextView textLoadMore;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);
            textLoadMore = (TextView) itemView.findViewById(R.id.text_load_more);
        }
    }

    public class FeedHolder extends RecyclerView.ViewHolder{

        private ImageView feedIcon, feed_description_image;
        private TextView feedTitle;
        private TextView feedDescription;
        private RelativeLayout btn1, btn2, btn3;
        private TextView btn1_icon, btn2_icon, btn3_icon;

        public FeedHolder(View itemView) {
            super(itemView);
            feedIcon = (ImageView) itemView.findViewById(R.id.feed_image);
            feedTitle = (TextView) itemView.findViewById(R.id.feed_title);
            feedDescription = (TextView) itemView.findViewById(R.id.feed_description);
            feed_description_image = (ImageView) itemView.findViewById(R.id.feed_description_image);

            btn1 = (RelativeLayout) itemView.findViewById(R.id.feed_btn1);
            btn2 = (RelativeLayout) itemView.findViewById(R.id.feed_btn2);
            btn3 = (RelativeLayout) itemView.findViewById(R.id.feed_btn3);

            btn1_icon = (TextView) itemView.findViewById(R.id.btn1_icon);
            btn2_icon = (TextView) itemView.findViewById(R.id.btn2_icon);
            btn3_icon = (TextView) itemView.findViewById(R.id.btn3_icon);

            feed_description_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();

                    Bitmap mImage = arr.get(getAdapterPosition()).getImage();
                    if (mImage != null) {
                        mImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] byteArray = stream.toByteArray();
                        Intent intent = new Intent(mContext, ImageViewActivity.class);
                        intent.putExtra("image", byteArray);
                        mContext.startActivity(intent);
                    }
                }
            });

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Button1 is clicked", Toast.LENGTH_SHORT).show();
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle args = new Bundle();
                    args.putString("itemID", arr.get(getAdapterPosition()).getId());
                    FragmentActivity activity = (FragmentActivity)(mContext);
                    android.support.v4.app.FragmentManager fm = activity.getSupportFragmentManager();
                    args.putString("itemID", arr.get(getAdapterPosition()).getId());
                    WriteNoteDialog alertDialog = new WriteNoteDialog();
                    alertDialog.setArguments(args);
                    alertDialog.show(fm, "this is fragment");
                    PersonalInfoActivity.setDefault("itemID", arr.get(getAdapterPosition()).getId(), mContext);
                }
            });
            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendReport(arr.get(getAdapterPosition()).getId(), PersonalInfoActivity.getDefaults("id", mContext));
                }
            });
        }
    }

    public void sendReport(final String itemID, String reportBy){
        class sendReport extends AsyncTask<String, Void, String> {
            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = new ProgressDialog(mContext,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Loading...");
                progressDialog.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String result = jsonObject.getString("result");
                    if(result.equalsIgnoreCase("1")){
                        Log.d(Const.LOG_TAG, "send reply success");
                        Toast.makeText(mContext, "Report successful", Toast.LENGTH_SHORT).show();
                    }else{
                        Log.d(Const.LOG_TAG, "send reply failed");
                        Toast.makeText(mContext, "Report failed", Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String,String> data = new HashMap<>();
                data.put(Const.REPORT_ITEM, params[1]);

                RegisterUserClass ruc = new RegisterUserClass();

                String result = ruc.sendPostRequest(Const.REPORT_TIMELINE_URL + params[0],data);

                Log.d(Const.LOG_TAG, result + "");
                return result;
            }
        }
        sendReport send = new sendReport();
        send.execute(itemID, reportBy);
    }
}
