package com.example.android_chat.activities.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.android_chat.R;
import com.example.android_chat.databinding.ActivityChatBinding;

public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}