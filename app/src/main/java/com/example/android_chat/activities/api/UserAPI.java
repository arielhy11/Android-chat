package com.example.android_chat.activities.api;

import com.example.android_chat.R;
import com.example.android_chat.activities.android_chat;
import com.example.android_chat.activities.entities.User;
import com.example.android_chat.activities.viewmodels.SampleViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserAPI {

    boolean retValue = false;
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public UserAPI() {

        retrofit = new Retrofit.Builder()
                .baseUrl(android_chat.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    // get request that return all users in the server
    public boolean get(SampleViewModel usersList, String name, String password) {
        Call<List<User>> call = webServiceAPI.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                usersList.getUsers().setValue(response.body());
                for(User i:usersList.getUsers().getValue()){
                    if(name == i.getId() && password == i.getPassword()){
                        retValue = true;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {}
        });
        return retValue;
    }

    public void addUser(User user) {
        Call<List<User>> call = webServiceAPI.addUser("register", user);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> users = response.body();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {}
        });
    }
}

