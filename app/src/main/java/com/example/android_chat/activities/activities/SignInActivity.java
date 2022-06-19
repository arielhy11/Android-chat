package com.example.android_chat.activities.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_chat.activities.api.UserAPI;
import com.example.android_chat.activities.entities.User;
import com.example.android_chat.activities.viewmodels.SampleViewModel;
import com.example.android_chat.databinding.ActivitySignInBinding;

import java.util.ArrayList;
import java.util.List;

public class SignInActivity extends AppCompatActivity {

    private ActivitySignInBinding binding;
    UserAPI userAPI;
    private SampleViewModel usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usersList = new ViewModelProvider(this).get(SampleViewModel.class);
        List<User> uul = new ArrayList<>();
        uul.add(new User("eli", "1111"));
        usersList.getUsers().setValue(uul);
        userAPI = new UserAPI();
        userAPI.get(usersList);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("id", null);
        myEdit.putString("password", null);
        myEdit.commit();
    }

   private void setListeners(){
        Log.i("Value: ", usersList.getUsers().getValue().get(0).getId());
        binding.loginToRegister.setOnClickListener(e ->
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class)));
        binding.buttonSignIn.setOnClickListener(e->{
                Intent intent = new Intent(getApplicationContext(), ChooseChat.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
        });
    }
}