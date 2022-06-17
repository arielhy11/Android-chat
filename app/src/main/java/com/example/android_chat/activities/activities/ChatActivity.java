package com.example.android_chat.activities.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.android_chat.activities.adapters.ChatAdapter;
import com.example.android_chat.activities.models.Message;
import com.example.android_chat.activities.models.User;
import com.example.android_chat.databinding.ActivityChatBinding;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding binding;
    private User receiverUser;
    private List<Message> messages;
    private ChatAdapter chatAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
        loadReceiverData();
        messages = getMessages();
        binding.chatRecycler.setLayoutManager(new LinearLayoutManager(this));
        chatAdapter = new ChatAdapter(messages,this);
        binding.chatRecycler.setAdapter(chatAdapter);
    }

    private List<Message> getMessages() {
        //todo: get from server
        List<Message>ret=new ArrayList<>();
        Message m=new Message("hi","00:30",true);
        Message m1=new Message("blabla","01:30",false);
        ret.add(m);
        ret.add(m1);
        ret.add(m);
        ret.add(m1);
        return ret;
    }

    private void sendMessage(){
        //todo:send message
        binding.inputMessage.setText(null);
    }

    void setListeners(){
        binding.frameSend.setOnClickListener(e-> sendMessage());
    }

    void loadReceiverData(){
        receiverUser = (User) getIntent().getSerializableExtra("user");
//        binding.contactName.setText(receiverUser.getName());
    }
}