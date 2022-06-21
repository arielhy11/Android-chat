package com.example.android_chat.activities.api;

import com.example.android_chat.R;
import com.example.android_chat.activities.android_chat;
import com.example.android_chat.activities.entities.Contact;
import com.example.android_chat.activities.entities.Message;
import com.example.android_chat.activities.viewmodels.SampleViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactAPI {

    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public ContactAPI() {

        retrofit = new Retrofit.Builder()
                .baseUrl(android_chat.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    // get list of all contacts in the server
    public void get(SampleViewModel contactsList) {
        Call<List<Contact>> call = webServiceAPI.getContacts();
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                contactsList.getContacts().setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {}
        });
    }

    // get list of all contacts of specific user.
    // param = username
    public void userGet(String username) {
        Call<List<Contact>> call = webServiceAPI.getUserContacts(username);
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                List<Contact> contacts = response.body();
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {}
        });
    }

    // get details of specific contact
    public void userGetContact(String username, String contactName) {
        Call<Contact> call = webServiceAPI.getSpecificContact(username, contactName);
        call.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {
                Contact contacts = response.body();
            }

            @Override
            public void onFailure(Call<Contact> call, Throwable t) {}
        });
    }

    // create new contact. "user" is username
    public void userPost(String username, Contact contact){
        Call<Contact> call =
        webServiceAPI.postContact(username, contact);
        call.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {

            }

            @Override
            public void onFailure(Call<Contact> call, Throwable t) {}
        });
    }

    // delete contact
    public void deleteContact(String username, String contactName) {
        Call<Contact> call = webServiceAPI.deleteContact(username, contactName);
        call.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {
                Contact contacts = response.body();
            }

            @Override
            public void onFailure(Call<Contact> call, Throwable t) {}
        });
    }

    // edit contact
    public void editContact(String username, String contactName, Contact contact) {
        Call<Contact> call = webServiceAPI.editContact(username, contactName, contact);
        call.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {
                Contact contacts = response.body();
            }

            @Override
            public void onFailure(Call<Contact> call, Throwable t) {}
        });
    }

    // get list of messages between user and contact
    public void getMessages(String username, String contactName) {
        Call<List<Message>> call = webServiceAPI.getMessages(username, contactName);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                List<Message> messages = response.body();
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {}
        });
    }

    /*// send new message between user and contact
    public void sendMessage(String username, String contactName, Message message) {
        Call<List<Message>> call = webServiceAPI.createMessage(username, contactName, message);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                List<Message> messages = response.body();
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {}
        });
    }*/
}
