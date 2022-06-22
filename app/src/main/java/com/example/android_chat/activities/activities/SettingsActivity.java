package com.example.android_chat.activities.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_chat.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {
private ActivitySettingsBinding binding;
private SharedPreferences sharedPreferences;
private SharedPreferences.Editor myEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
        sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        myEdit = sharedPreferences.edit();
    }

    private void setListeners(){
        binding.ChangePortButton.setOnClickListener(e->{
            myEdit.putString("Port", binding.inputName.getText().toString());
            myEdit.apply();
            Intent intent = new Intent(getApplicationContext(), ChooseChat.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}