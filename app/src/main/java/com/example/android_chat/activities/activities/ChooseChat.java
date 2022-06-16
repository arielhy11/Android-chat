package com.example.android_chat.activities.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_chat.R;
import com.example.android_chat.activities.adapters.UserAdapters;
import com.example.android_chat.activities.models.User;
import com.example.android_chat.databinding.ActivityChooseChatBinding;

import java.util.ArrayList;
import java.util.List;

public class ChooseChat extends AppCompatActivity {

    private ActivityChooseChatBinding binding;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChooseChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.usersRecycleview.setLayoutManager(new LinearLayoutManager(this));

        users = new ArrayList<>();

        for (int i = 0; i < 5; i++){
            User user = new User();
            user.setName("User" + i);
            users.add(user);
        }

        binding.usersRecycleview.setAdapter(new UserAdapters(users));
    }
    class UserViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView image;
        public UserViewHolder(ViewGroup container){
            super(LayoutInflater.from(ChooseChat.this).inflate(R.layout.user_list_item, container, true));
            name = itemView.findViewById(R.id.new_contact_name);
            image = itemView.findViewById(R.id.new_contact_Pic);
        }
        public void bind(User user){
            name.setText(user.getName());
        }
    }
}