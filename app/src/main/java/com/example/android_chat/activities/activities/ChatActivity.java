package com.example.android_chat.activities.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.android_chat.R;
import com.example.android_chat.activities.adapters.ChatAdapter;
import com.example.android_chat.activities.android_chat;
import com.example.android_chat.activities.api.WebServiceAPI;
import com.example.android_chat.activities.entities.Message;
import com.example.android_chat.activities.entities.User;
import com.example.android_chat.databinding.ActivityChatBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding binding;
    private User receiverUser;
    private List<Message> messages;
    private ChatAdapter chatAdapter;

    private WebServiceAPI webServiceAPI;
    private Retrofit retrofit;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor myEdit;

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        retrofit = new Retrofit.Builder()
                .baseUrl(android_chat.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
        messages = new ArrayList<>();

        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        myEdit = sharedPreferences.edit();

        //setListeners();
        //loadReceiverData();
        binding.chatRecycler.setLayoutManager(new LinearLayoutManager(this));

        Call<List<Message>> call = webServiceAPI.getMessages(sharedPreferences.getString("id", "a"), "Yosef");
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.body() != null) {
                    messages.addAll(response.body());
                    chatAdapter = new ChatAdapter(messages,context);
                    binding.chatRecycler.setAdapter(chatAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                //todo add alert that can't connect to the server
            }
        });
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