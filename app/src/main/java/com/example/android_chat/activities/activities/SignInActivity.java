package com.example.android_chat.activities.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_chat.R;
import com.example.android_chat.activities.android_chat;
import com.example.android_chat.activities.api.WebServiceAPI;
import com.example.android_chat.activities.entities.User;
import com.example.android_chat.activities.room.AppDB;
import com.example.android_chat.activities.room.UsersDao;
import com.example.android_chat.databinding.ActivitySignInBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity {

    private WebServiceAPI webServiceAPI;
    private Retrofit retrofit;
    //private UserAPI userAPI;

    private ActivitySignInBinding binding;

    //private SampleViewModel usersList;
    private AppDB db;
    private UsersDao userDao;
    private ArrayList<User> users;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor myEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();

        retrofit = new Retrofit.Builder()
                .baseUrl(android_chat.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
        users = new ArrayList<>();

        //db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "UserDB").build();
        //userDao = db.usersDao();


        sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        myEdit = sharedPreferences.edit();
        myEdit.putString("id", null);
        myEdit.putString("password", null);
        myEdit.commit();
    }

   private void setListeners(){
        binding.loginToRegister.setOnClickListener(e ->
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class)));
        binding.buttonSignIn.setOnClickListener(e->{
            EditText txtName = binding.inputName;
            EditText txtPswd = binding.inputPassword;
            String name = txtName.getText().toString(), password = txtPswd.getText().toString();
            Call<List<User>> call = webServiceAPI.getUsers();
            call.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    if (response.body() != null) {
                        users.addAll(response.body());
                        for (User user : users) {
                            if (name.equals(user.getId()) && password.equals(user.getPassword())) {
                                myEdit.putString("id", name);
                                myEdit.apply();
                                Intent intent = new Intent(getApplicationContext(), ChooseChat.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                            //todo add alert that the enter is forbbiden and something went wrong
                        }
                    }
                }
                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
                    //todo add alert that can't connect to the server
                }
            });
        });
    }
}