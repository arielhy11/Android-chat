package com.example.android_chat.activities.activities;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_chat.R;
import com.example.android_chat.activities.api.ContactAPI;
import com.example.android_chat.activities.api.UserAPI;
import com.example.android_chat.activities.entities.Contact;
import com.example.android_chat.activities.entities.Message;
import com.example.android_chat.activities.entities.User;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("id", null);
        myEdit.putString("password", null);
        myEdit.commit();

        ContactAPI contactAPI = new ContactAPI();
        UserAPI userAPI = new UserAPI();

        // *** API TESTS ***

        userAPI.get();
        userAPI.addUser(new User("eli", "1111"));

        contactAPI.get();
        contactAPI.userGet(sharedPreferences.getString("id", "Ariel"));
        contactAPI.userGetContact("Ariel", "Yosef");
        contactAPI.userPost("eli", new Contact("avi", "ami", "2888"));
        contactAPI.deleteContact("Ariel", "dad");
        contactAPI.editContact("Ariel", "mom",
                                new Contact("mom", "aaa", "1234"));
        contactAPI.getMessages("Ariel", "Yosef");
        contactAPI.sendMessage("eli", "avi", new Message("hello"));

    }

    //todo update the username in sharedpreferences when moving to contacts screen
}