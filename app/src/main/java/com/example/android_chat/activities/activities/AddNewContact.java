package com.example.android_chat.activities.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.android_chat.databinding.ActivityAddNewContactBinding;

public class AddNewContact extends AppCompatActivity {

    private ActivityAddNewContactBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNewContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}