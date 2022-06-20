package com.example.android_chat.activities.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.android_chat.R;
import com.example.android_chat.activities.android_chat;
import com.example.android_chat.activities.api.WebServiceAPI;
import com.example.android_chat.activities.entities.Contact;
import com.example.android_chat.databinding.ActivityChooseChatBinding;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChooseChat extends AppCompatActivity {

    private ActivityChooseChatBinding binding;

    private WebServiceAPI webServiceAPI;
    private Retrofit retrofit;

    private ArrayList<Contact> contacts;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor myEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChooseChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        retrofit = new Retrofit.Builder()
                .baseUrl(android_chat.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
        contacts = new ArrayList<>();

        binding.usersRecycleview.setLayoutManager(new LinearLayoutManager(this));

        sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        myEdit = sharedPreferences.edit();

        //binding.usersRecycleview.setAdapter(new UserAdapters(this, users));
        //setListeners();
    }

    private void setListeners() {
        binding.addNewContact.setOnClickListener(e -> {
            Intent intent = new Intent(getApplicationContext(), AddNewContact.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}