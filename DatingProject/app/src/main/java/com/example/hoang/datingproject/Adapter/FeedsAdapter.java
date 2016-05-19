package com.example.hoang.datingproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.hoang.datingproject.Fragment.FeedsFragment;
import com.example.hoang.datingproject.Model.FeedModel;
import com.example.hoang.datingproject.R;
import com.example.hoang.datingproject.Utilities.Const;
import com.example.hoang.datingproject.Utilities.FontManager;
import com.example.hoang.datingproject.Utilities.OnLoadMoreListener;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by hoang on 4/4/2016.
 */
public class FeedsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private FeedModel feedModel;
    private ArrayList<FeedModel> arr = new ArrayList<FeedModel>();
    private OnLoadMoreListener mOnLoadMoreListener;

    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    public FeedsAdapter(Context mcontext, ArrayList<FeedModel> arr, RecyclerView recyclerView) {
        this.mContext = mcontext;
        this.arr = arr;
        notifyDataSetChanged();
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (linearLayoutManager != null) {
                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                }

                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (mOnLoadMoreListener != null) {
                        mOnLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
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
            feedHolder.feedIcon.setImageResource(feedModel.getFeedIcon());
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
                    Toast.makeText(mContext, "Button2 is clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, MessagesActivity.class);
                    mContext.startActivity(intent);
                }
            });
            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Button3 is clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}