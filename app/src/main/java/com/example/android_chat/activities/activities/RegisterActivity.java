package com.example.android_chat.activities.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Toast;

import com.example.android_chat.R;
import com.example.android_chat.databinding.ActivityRegisterBinding;
import com.example.android_chat.databinding.ActivitySignInBinding;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private String rawImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners(){
        binding.RegisterToLogin.setOnClickListener(e-> onBackPressed());
        binding.buttonRegister.setOnClickListener(e-> {
            if (isLegal()){
                register();
            }
        });
    }

    private void maketext(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private Boolean isLegal(){
        //case no username
        if (binding.inputUsername.getText().toString().trim().isEmpty()) {
            maketext("Username field can not be empty.");
            return false;
        } else if (binding.inputEmail.getText().toString().trim().isEmpty()) {
            maketext("Email field can not be empty.");
            return false;
        } else if(!Patterns.EMAIL_ADDRESS.matcher(binding.inputEmail.getText().toString()).matches()){
            maketext("Email not valid.");
            return false;
        }else if (binding.inputPassword.getText().toString().trim().isEmpty()) {
            maketext("Password field can not be empty.");
            return false;
        } else if (binding.inputRepeatPassword.getText().toString().trim().isEmpty()) {
            maketext("Password field can not be empty.");
            return false;
        } else if (!binding.inputPassword.getText().toString().equals(binding.inputRepeatPassword.getText().toString())) {
            maketext("Passwords do not match.");
            return false;
        } else if (rawImage == null) { //TODO: should be possible to register with no image. I want to ask user if he's sure.
                maketext("pick your image");
                return false;
        } else{
            return true;
        }
    }

    public void register(){

    }
}