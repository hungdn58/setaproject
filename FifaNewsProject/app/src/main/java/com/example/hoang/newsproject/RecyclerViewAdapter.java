package com.example.hoang.newsproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoang.newsproject.image.ImageLoader;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hoang on 3/10/2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ArticalHolder> {

    private Context mContext;
    private List<RSSItem> arrArticals = new ArrayList<RSSItem>();
    private RSSItem model = new RSSItem();
    public ImageLoader imageLoader;
    private ArrayList<Integer> arr = new ArrayList<Integer>();

    public RecyclerViewAdapter(Context context, List<RSSItem> arr){
        this.mContext = context;
        this.arrArticals = arr;
        imageLoader = new ImageLoader(mContext);
    }

    @Override
    public RecyclerViewAdapter.ArticalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ArticalHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ArticalHolder holder, int position) {
        model = arrArticals.get(position);
        String time = "";
        holder.title.setText(model.getTitle());
        holder.link.setText(model.get_source() + "  |");

        holder.time.setText(model.getDate());
        Picasso.with(mContext)
                .load(model.getImage())
                .noFade()
                .placeholder(R.drawable.noimage)
                .into(holder.image);

        Random rd = new Random();
        int random = rd.nextInt(17);
        arr.add(random);

        holder.background.setBackgroundColor(model.getBackground());
    }

    public int pxToDp(int px) {
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public int randomColor(int rd){
        int rgbColor[] = {Color.rgb(181, 104, 59),
                Color.rgb(212, 213, 183),
                Color.rgb(155, 131, 60),
                Color.rgb(184, 212, 212),
                Color.rgb(240, 211, 211),
                Color.rgb(212, 213, 183),
                Color.rgb(155, 129, 129),
                Color.rgb(240, 211, 211),
                Color.rgb(183, 185, 156),
                Color.rgb(184, 212, 212),
                Color.rgb(105, 103, 129),
                Color.rgb(42, 156, 201),
                Color.rgb(91, 72, 126),
                Color.rgb(128, 57, 67),
                Color.rgb(65, 175, 165),
                Color.rgb(248, 110, 68),
                Color.rgb(217 , 158 , 0),
                Color.rgb(211 , 183 , 183)};

        if (rd <= 16) return rgbColor[rd];
        return rgbColor[17];
    }

    @Override
    public int getItemCount() {
        return arrArticals.size();
    }

    public class ArticalHolder extends RecyclerView.ViewHolder{

        private TextView title, link, time;
        private ImageView image, background;
        private RelativeLayout container;

        public ArticalHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.articalTitle);
            link = (TextView) itemView.findViewById(R.id.articalSource);
            time = (TextView) itemView.findViewById(R.id.articalTime);
            image = (ImageView) itemView.findViewById(R.id.image);
            background = (ImageView) itemView.findViewById(R.id.background);
            container = (RelativeLayout) itemView.findViewById(R.id.container);
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    arrArticals.get(getAdapterPosition()).setCount(arrArticals.get(getAdapterPosition()).getCount() + 1);
                    Intent intent = new Intent(mContext, MyWebViewActivity.class);
                    intent.putExtra("item", arrArticals.get(getAdapterPosition()));
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
