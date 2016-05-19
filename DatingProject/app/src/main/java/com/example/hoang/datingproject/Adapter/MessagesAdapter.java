package com.example.hoang.datingproject.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoang.datingproject.Model.FeedModel;
import com.example.hoang.datingproject.Model.MessageModel;
import com.example.hoang.datingproject.R;
import com.example.hoang.datingproject.Utilities.FontManager;

import java.util.ArrayList;

/**
 * Created by hoang on 4/5/2016.
 */
public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageHolder>{
    private Context mContext;
    private MessageModel messageModel;
    private ArrayList<MessageModel> arr = new ArrayList<MessageModel>();

    public MessagesAdapter(Context mcontext, ArrayList<MessageModel> arr) {
        this.mContext = mcontext;
        this.arr = arr;
    }

    @Override
    public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.message_item, parent, false);
        return new MessageHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageHolder holder, int position) {
        messageModel = arr.get(position);
        holder.messageIcon.setImageResource(messageModel.getMessage_icon());
        holder.messageTitle.setText(messageModel.getMessage_title());
        holder.messageDescription.setText(messageModel.getMessage_content());
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class MessageHolder extends RecyclerView.ViewHolder{

        private ImageView messageIcon;
        private TextView messageTitle;
        private TextView messageDescription;

        public MessageHolder(View itemView) {

            super(itemView);
            messageIcon = (ImageView) itemView.findViewById(R.id.message_icon);
            messageTitle = (TextView) itemView.findViewById(R.id.message_title);
            messageDescription = (TextView) itemView.findViewById(R.id.message_content);

        }
    }
}
