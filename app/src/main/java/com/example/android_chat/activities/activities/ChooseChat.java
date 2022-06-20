package com.example.android_chat.activities.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.android_chat.R;
import com.example.android_chat.activities.adapters.ContactsAdapters;
import com.example.android_chat.activities.android_chat;
import com.example.android_chat.activities.api.WebServiceAPI;
import com.example.android_chat.activities.entities.Contact;
import com.example.android_chat.databinding.ActivityChooseChatBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChooseChat extends AppCompatActivity {

    private ActivityChooseChatBinding binding;

    private WebServiceAPI webServiceAPI;
    private Retrofit retrofit;

    private ArrayList<Contact> contacts;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor myEdit;

    private Context context = this;

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

        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        myEdit = sharedPreferences.edit();

        Call<List<Contact>> call = webServiceAPI.getUserContacts(sharedPreferences.getString("id", "a"));
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                if (response.body() != null) {
                    contacts.addAll(response.body());
                    binding.usersRecycleview.setAdapter(new ContactsAdapters(context, contacts));
                }
            }
            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                //todo add alert that can't connect to the server
            }
        });
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