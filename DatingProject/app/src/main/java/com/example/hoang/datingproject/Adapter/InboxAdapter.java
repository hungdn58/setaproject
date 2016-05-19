package com.example.hoang.datingproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoang.datingproject.Activity.ImageViewActivity;
import com.example.hoang.datingproject.Activity.UserProfileActivity;
import com.example.hoang.datingproject.Model.InboxModel;
import com.example.hoang.datingproject.Model.PersonModel;
import com.example.hoang.datingproject.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by hoang on 4/14/2016.
 */
public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.InboxHolder> {
    private Context mContext;
    private InboxModel inboxModel;
    private ArrayList<InboxModel> arr = new ArrayList<InboxModel>();

    public InboxAdapter(Context mcontext, ArrayList<InboxModel> arr) {
        this.mContext = mcontext;
        this.arr = arr;
    }

    @Override
    public InboxHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.inbox_item, parent, false);
        return new InboxHolder(view);
    }

    @Override
    public void onBindViewHolder(InboxHolder holder, int position) {
        inboxModel = arr.get(position);
        holder.inbox_time.setText(inboxModel.getTime());
        if (inboxModel.getContent() != null) {
            holder.inbox_content.setText(inboxModel.getContent());
        }
        if (inboxModel.getImage() != null) {
            holder.inbox_image.setImageBitmap(inboxModel.getImage());
        }
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class InboxHolder extends RecyclerView.ViewHolder{
        private TextView inbox_time;
        private TextView inbox_content;
        private ImageView inbox_image;

        public InboxHolder(View itemView) {
            super(itemView);
            inbox_time = (TextView) itemView.findViewById(R.id.inbox_time);
            inbox_content = (TextView) itemView.findViewById(R.id.inbox_content);
            inbox_image = (ImageView) itemView.findViewById(R.id.inbox_image);

            inbox_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();

                    Bitmap mImage = arr.get(getAdapterPosition()).getImage();
                    mImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    Intent intent = new Intent(mContext, ImageViewActivity.class);
                    intent.putExtra("image", byteArray);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
