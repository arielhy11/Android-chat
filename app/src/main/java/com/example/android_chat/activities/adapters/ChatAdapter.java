package com.example.android_chat.activities.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_chat.R;
import com.example.android_chat.activities.models.Message;
import com.example.android_chat.databinding.FriendMessageBinding;
import com.example.android_chat.databinding.MyMessageBinding;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.SentMessageViewHolder> {

    private final List<Message> messages;
    private LayoutInflater layoutInflater;

    public ChatAdapter(List<Message> messages, Context context) {
        this.messages = messages;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ChatAdapter.SentMessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.my_message, parent, false);
        return new SentMessageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SentMessageViewHolder holder, int position) {
        Message message = this.messages.get(position);
        Log.d("recyclerview",message.getMessage());
        if (message.isSender()) {
            holder.friendLinear.setVisibility(View.GONE);
            holder.myText.setText(message.getMessage());
            holder.myTime.setText(message.getTime());
        } else {
            holder.myLinear.setVisibility(View.GONE);
            holder.friendText.setText(message.getMessage());
            holder.friendTime.setText(message.getTime());
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }


    public class SentMessageViewHolder extends RecyclerView.ViewHolder {

        LinearLayout myLinear;
        LinearLayout friendLinear;

        TextView myText;
        TextView friendText;
        TextView myTime;
        TextView friendTime;

        SentMessageViewHolder(View view) {
            super(view);
            myLinear = view.findViewById(R.id.my_layout);
            friendLinear = view.findViewById(R.id.friend_layout);
            myText = view.findViewById(R.id.myText);
            friendText = view.findViewById(R.id.friendText);
            myTime = view.findViewById(R.id.my_time);
            friendTime = view.findViewById(R.id.friend_time);

        }


    }


}
