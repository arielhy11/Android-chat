package com.example.android_chat.activities.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.android_chat.R;
import com.example.android_chat.activities.android_chat;
import com.example.android_chat.activities.api.WebServiceAPI;
import com.example.android_chat.activities.entities.Contact;
import com.example.android_chat.activities.entities.Invitation;
import com.example.android_chat.activities.room.AppDB;
import com.example.android_chat.activities.room.ContactsDao;
import com.example.android_chat.databinding.ActivityAddNewContactBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddNewContact extends AppCompatActivity {

    private ActivityAddNewContactBinding binding;

    AppDB db;
    ContactsDao contactsDao;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor myEdit;

    private WebServiceAPI webServiceAPI;
    private Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNewContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        myEdit = sharedPreferences.edit();

        retrofit = new Retrofit.Builder()
                .baseUrl(android_chat.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "ChatDB").allowMainThreadQueries().build();
        contactsDao = db.contactsDao();

        setListeners();
    }

    private void setListeners() {
        binding.submitNewContact.setOnClickListener(e -> {
            EditText newName = binding.inputName;
            Contact newContact = new Contact(newName.getText().toString(),
                                             newName.getText().toString(), "5287",
                                             sharedPreferences.getString("id", "a"));

            Call<Invitation> invitationCall = webServiceAPI.sendInvitation(new Invitation(
                    sharedPreferences.getString("id", null), newName.getText().toString(),
                    sharedPreferences.getString("server", "5287")
            ));
            invitationCall.enqueue(new Callback<Invitation>() {
                @Override
                public void onResponse(Call<Invitation> invitationCall, Response<Invitation> response) {}
                @Override
                public void onFailure(Call<Invitation> invitationCall, Throwable t) {}
                         });

            Call<Contact> call = webServiceAPI.postContact(sharedPreferences.getString("id", "a"), newContact);
            call.enqueue(new Callback<Contact>() {
                @Override
                public void onResponse(Call<Contact> call, Response<Contact> response) {
                    if (response.body() != null) {
                        Intent intent = new Intent(getApplicationContext(), ChooseChat.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                }
                @Override
                public void onFailure(Call<Contact> call, Throwable t) {
                }
            });
        });
    }
}