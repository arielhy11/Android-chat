package com.example.android_chat.activities.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.android_chat.activities.adapters.UserAdapters;
import com.example.android_chat.activities.models.User;
import com.example.android_chat.databinding.ActivityChooseChatBinding;

import java.util.ArrayList;
import java.util.List;

public class ChooseChat extends AppCompatActivity {

    /*ContactAPI contactAPI;
    private SampleViewModel contactsList;*/
    private ActivityChooseChatBinding binding;
    private List<User> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChooseChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.usersRecycleview.setLayoutManager(new LinearLayoutManager(this));

        contacts = new ArrayList<>();

        for (int i = 0; i < 5; i++){
            User user = new User();
            user.setName("User" + i);
            User.lastId++;
            user.id = User.lastId;
            contacts.add(user);
        }
        binding.usersRecycleview.setAdapter(new UserAdapters(this, contacts));
        setListeners();
    }

    private void setListeners() {
        binding.addNewContact.setOnClickListener(e -> {
            Intent intent = new Intent(getApplicationContext(), AddNewContact.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }

}