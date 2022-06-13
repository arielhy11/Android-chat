package com.example.android_chat.activities.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.android_chat.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {

    private ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners(){
        binding.loginToRegister.setOnClickListener(e ->
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class)));
        //THE CALL TO THE FUNCTION WRITTEN BELOW
        //binding.buttonSignIn.setOnClickListener(e->uploadToFireStore());
    }

    // A function that, once called, would push data to the Firestore
//    private void uploadToFireStore(){
//        FirebaseFirestore database = FirebaseFirestore.getInstance();
//        HashMap<String,Object> data = new HashMap<>();
//        data.put("first Name:", "Yosef");
//        database.collection("users")
//                .add(data)
//                .addOnSuccessListener(documentReference ->{
//                    Toast.makeText(getApplicationContext(), "Data In", Toast.LENGTH_SHORT).show();
//                })
//                .addOnFailureListener(exception -> {
//                    Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
//                });
//    }
}