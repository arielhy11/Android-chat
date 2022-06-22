package com.example.android_chat.activities.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_chat.R;
import com.example.android_chat.activities.android_chat;
import com.example.android_chat.activities.api.UserAPI;
import com.example.android_chat.activities.api.WebServiceAPI;
import com.example.android_chat.activities.entities.User;
import com.example.android_chat.databinding.ActivityRegisterBinding;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private WebServiceAPI webServiceAPI;
    private Retrofit retrofit;
    private UserAPI userAPI;

    private ActivityRegisterBinding binding;
    private String rawImage;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor myEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());

        retrofit = new Retrofit.Builder()
                .baseUrl(android_chat.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);

        sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        myEdit = sharedPreferences.edit();
        myEdit.putString("id", null);
        myEdit.commit();

        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners() {
        binding.RegisterToLogin.setOnClickListener(e -> onBackPressed());
        binding.buttonRegister.setOnClickListener(e -> {
            if (isLegal()) {
                register();
                //todo move to chat screen only if the register is OK
                Intent intent = new Intent(getApplicationContext(), ChooseChat.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        binding.wrapProfilePic.setOnClickListener(e -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            pickImage.launch(intent);
        });
    }

    private void maketext(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private Boolean isLegal() {
        //case no username
        if (binding.inputUsername.getText().toString().trim().isEmpty()) {
            maketext("Username field can not be empty.");
            return false;
        }  else if (binding.inputPassword.getText().toString().trim().isEmpty()) {
            maketext("Password field can not be empty.");
            return false;
        } /*else if (binding.inputRepeatPassword.getText().toString().trim().isEmpty()) {
            maketext("Password field can not be empty.");
            return false;
        } else if (!binding.inputPassword.getText().toString().equals(binding.inputRepeatPassword.getText().toString())) {
            maketext("Passwords do not match.");
            return false;
        } else if (rawImage == null) { //TODO: should be possible to register with no image. I want to ask user if he's sure.
            maketext("pick your image");
            return false;
        }*/ else {
            return true;
        }
    }

    private void whileLoading(Boolean isLoading) {
        if (isLoading) {
            binding.buttonRegister.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.INVISIBLE);
            binding.buttonRegister.setVisibility(View.VISIBLE);
        }
    }

    private String workRawImage(Bitmap bitmap) {
        int width = 140;
        int height = bitmap.getHeight() * width / bitmap.getWidth();
        Bitmap showBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        showBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] byteArr = byteArrayOutputStream.toByteArray();
        return android.util.Base64.encodeToString(byteArr, Base64.DEFAULT);
    }

    private final ActivityResultLauncher<Intent> pickImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    if (result.getData() != null) {
                        Uri uri = result.getData().getData();
                        try {
                            InputStream inputStream = getContentResolver().openInputStream(uri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            binding.ProfilePic.setImageBitmap(bitmap);
                            binding.ProfilePicText.setVisibility(View.GONE);
                            rawImage = workRawImage(bitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    public void register() {
        User user = new User(binding.inputUsername.getText().toString(),
                binding.inputPassword.getText().toString());
        myEdit.putString("id", binding.inputUsername.getText().toString());
        myEdit.apply();
        Call<List<User>> call = webServiceAPI.addUser("register", user);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                //todo inform on no connectivity
            }
        });
    }
}